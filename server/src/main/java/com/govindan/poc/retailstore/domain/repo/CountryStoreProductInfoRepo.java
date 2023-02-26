package com.govindan.poc.retailstore.domain.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.govindan.poc.retailstore.view.CountryStoreProductInfo;

@Repository
public interface CountryStoreProductInfoRepo extends JpaRepository<CountryStoreProductInfo, String>,CountryStoreProductInfoRepoCustom {

	List<CountryStoreProductInfo> findByCountryCode(String countryCode);
}
