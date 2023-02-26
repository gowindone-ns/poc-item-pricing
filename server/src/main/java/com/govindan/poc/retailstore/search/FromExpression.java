package com.govindan.poc.retailstore.search;

public class FromExpression  {
	private static final long serialVersionUID = 1L;
	private String entity;

	

	
	public String asSql() {

		return " FROM " + entity + " ";
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

}
