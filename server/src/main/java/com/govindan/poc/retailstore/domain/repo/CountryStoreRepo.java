package com.govindan.poc.retailstore.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.govindan.poc.retailstore.domain.model.CountryStore;

@Repository
public interface CountryStoreRepo extends JpaRepository<CountryStore, String> {

}
