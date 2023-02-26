package com.govindan.poc.retailstore.search;

public class SortExpression implements Expression {
	private static final long serialVersionUID = 1L;
	private String sortBy;
	private String sortOrder;

	public SortExpression(String sortBy, String sortOrder) {
		this.sortBy = sortBy;
		this.sortOrder = sortOrder;
	}

	@Override
	public String asSql() {

		return " " + this.sortBy + " " + this.sortOrder;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

}
