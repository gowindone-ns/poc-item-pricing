package com.govindan.poc.retailstore.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.govindan.poc.retailstore.domain.model.CountryStore;
import com.govindan.poc.retailstore.domain.model.CountryStoreProduct;
import com.govindan.poc.retailstore.domain.model.Product;
import com.govindan.poc.retailstore.domain.repo.CountryStoreProductInfoRepo;
import com.govindan.poc.retailstore.domain.repo.CountryStoreProductRepo;
import com.govindan.poc.retailstore.domain.repo.CountryStoreRepo;
import com.govindan.poc.retailstore.domain.repo.ProductRepo;
import com.govindan.poc.retailstore.view.CountryStoreProductInfo;

@Service
public class CountryStoreProductService {
	@Autowired
	@Lazy
	private CountryStoreProductRepo countryStoreProductRepo;
	
	@Autowired
	@Lazy
	private CountryStoreProductInfoRepo countryStoreProductInfoRepo;

	@Autowired
	@Lazy
	private CountryStoreRepo storeRepo;

	@Autowired
	@Lazy
	private ProductRepo productRepo;

	public List<CountryStoreProductInfo> listAll() {
		return countryStoreProductInfoRepo.findAll();
	}
	
//	public List<CountryStoreProduct> listByCountry(String countryCode) {
//		return countryStoreProductRepo.findByCountry(countryCode);
//	}
	
	public List<CountryStoreProductInfo> listByCountry(String countryCode) {
		return countryStoreProductInfoRepo.findByCountryCode(countryCode);
	}


	public CountryStoreProduct save(CountryStoreProductInfo product) {
		String storeId = product.getCountryStoreId().trim();
		String productName = product.getProductName().trim();
		final String id = productName+storeId ;
		Optional<CountryStoreProduct> fromDbOptCsp = countryStoreProductRepo.findById(id);
		if (fromDbOptCsp.isPresent()) {
			// TODO use util method to copy props from one bean to another
			// TODO defensive coding
			CountryStoreProduct fromDb = fromDbOptCsp.get();
			fromDb.setLastFeedDate(product.getLastFeedDate());
			// fromDb.setLastFeedDate(new Timestamp(new Date().getTime()));
			fromDb.setPrice(product.getPrice());
			fromDb.setSku(product.getSku());
			return countryStoreProductRepo.save(fromDb);
		} else {
			Optional<CountryStore> fromDbOptCs = storeRepo.findById(storeId);
			Optional<Product> fromDbOptP = productRepo.findById(productName);

			if (fromDbOptCs.isPresent() && fromDbOptP.isPresent()) {
				CountryStore cs = fromDbOptCs.get();
				Product p = fromDbOptP.get();

				final String idNew = cs.getId().trim() + p.getName().trim();
				product.setLastFeedDate(new Timestamp(new Date().getTime()));
				CountryStoreProduct csp = new CountryStoreProduct(idNew, cs, p, product.getSku(), product.getPrice(),
						product.getLastFeedDate());
				return countryStoreProductRepo.save(csp);
			} else {
				// TODO better domain specific exception
				throw new IllegalArgumentException();
			}

		}

	}

}
