package com.govindan.poc.retailstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.govindan.poc.retailstore.domain.repo.CountryStoreProductInfoRepo;
import com.govindan.poc.retailstore.search.Query;
import com.govindan.poc.retailstore.view.CountryStoreProductInfo;

@Service
public class SearchService {
	@Autowired
	@Lazy
	private CountryStoreProductInfoRepo countryStoreProductInfoRepo;

	public List<CountryStoreProductInfo> searchCountryStoreProduct(Query query) {
		return countryStoreProductInfoRepo.search(query);

	}

}
