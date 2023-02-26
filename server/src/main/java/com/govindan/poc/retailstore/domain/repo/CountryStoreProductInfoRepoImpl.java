package com.govindan.poc.retailstore.domain.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.govindan.poc.retailstore.search.Query;
import com.govindan.poc.retailstore.view.CountryStoreProductInfo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class CountryStoreProductInfoRepoImpl implements CountryStoreProductInfoRepoCustom {
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<CountryStoreProductInfo> search(Query query) {
		TypedQuery<CountryStoreProductInfo> q = entityManager.createQuery(query.asSql(), CountryStoreProductInfo.class);

		List<CountryStoreProductInfo> result = q.getResultList();
		return result;
	}

}
