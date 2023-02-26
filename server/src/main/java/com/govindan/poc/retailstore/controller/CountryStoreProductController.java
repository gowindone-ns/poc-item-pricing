package com.govindan.poc.retailstore.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.govindan.poc.retailstore.search.FromExpression;
import com.govindan.poc.retailstore.search.QueryPayload;
import com.govindan.poc.retailstore.service.CountryStoreProductService;
import com.govindan.poc.retailstore.service.SearchService;
import com.govindan.poc.retailstore.view.CountryStoreProductInfo;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
//TODO this is added only for dev purpose. Should be modified before shipping to production
public class CountryStoreProductController {
	@Autowired
	@Lazy
	private CountryStoreProductService service;

	@Autowired
	@Lazy
	private SearchService searchService;

	// TODO view model

	@GetMapping("/products/")
	public List<CountryStoreProductInfo> listAll() {

		return service.listAll();

	}

	@GetMapping("/products")
	public List<CountryStoreProductInfo> listByCountry(@RequestParam("countryCode") String countryCode) {

		return service.listByCountry(countryCode);
	}

	@PostMapping("/products/search")
	public List<CountryStoreProductInfo> search(@RequestBody QueryPayload queryPayload) {

		FromExpression fromExp = new FromExpression();
		fromExp.setEntity("CountryStoreProductInfo cspi");

		// let us not expose table details to client
		// if accidentally received, let us override
		// to avoid SERIOUS security risk;
		queryPayload.getQuery().setFromExpression(fromExp);
		return searchService.searchCountryStoreProduct(queryPayload.getQuery());

	}

	@PutMapping("/products")
	public void save(@RequestBody CountryStoreProductInfo product) {

		service.save(product);
	}

	@PostMapping("/products")
	public void create(@RequestBody CountryStoreProductInfo product) {

		service.save(product);
	}

	@PostMapping("/products/{countryCode}/import")
	public void importFeeds(@RequestParam(value = "file", required = true) MultipartFile productFeedFile,
			@PathVariable String countryCode) {
		try {
			// CSV
			// Store ID SKU Product Name Price Updated Date
			// "RawRice10KgBag1",'INTN01','SKU Value Goes Here',2400,'2023-02-25'
			CsvMapper mapper = new CsvMapper();
			CsvSchema schema = CsvSchema.builder().addColumn("Store ID").addColumn("SKU").addColumn("Product Name")
					.addColumn("Price").addColumn("Updated Date").build();
			MappingIterator<Map<String, String>> lineReader = mapper.readerForMapOf(String.class)
					// NOTE: no wrapping needed
					.with(schema).readValues(productFeedFile.getInputStream());
			lineReader.next();
			while (lineReader.hasNext()) {
				Map<String, String> record = lineReader.next();
				String countryStoreId = record.get("Store ID");
				String sku = record.get("SKU");
				String productName = record.get("Product Name");
				String price = record.get("Price");
				String lastFeeddate = record.get("Updated Date");
				CountryStoreProductInfo product = new CountryStoreProductInfo();
				product.setCountryStoreId(countryStoreId);
				product.setSku(sku);
				product.setProductName(productName);
				product.setPrice(new BigDecimal(price));
				
				ZonedDateTime zonedDateTime = ZonedDateTime.parse(lastFeeddate);
				
				
				
				Date date = Date.from(zonedDateTime.toInstant());
				
				product.setLastFeedDate(new Timestamp(date.getTime()));

				service.save(product);

			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid csv file");
		}

	}

}
