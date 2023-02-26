package com.govindan.poc.retailstore.view;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "country_store_product_info", schema = "public")
public class CountryStoreProductInfo {
	private String id;
	private String countryStoreId;
	private String productName;
	private String sku;
	private BigDecimal price;
	private Timestamp lastFeedDate;
	private String countryCode;

	@Id

	@Column(name = "id", unique = true, nullable = false, length = 263)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCountryStoreId() {
		return countryStoreId;
	}

	public void setCountryStoreId(String countryStoreId) {
		this.countryStoreId = countryStoreId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Timestamp getLastFeedDate() {
		return lastFeedDate;
	}

	public void setLastFeedDate(Timestamp lastFeedDate) {
		this.lastFeedDate = lastFeedDate;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

}
