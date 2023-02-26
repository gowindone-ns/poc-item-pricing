package com.govindan.poc.retailstore.domain.repo;

import java.util.List;

import com.govindan.poc.retailstore.search.Query;
import com.govindan.poc.retailstore.view.CountryStoreProductInfo;

public interface CountryStoreProductInfoRepoCustom {
	List<CountryStoreProductInfo> search(Query query);
}
