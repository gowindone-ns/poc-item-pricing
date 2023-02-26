package com.govindan.poc.retailstore.search;

import java.util.List;


public class SelectExpression {
	private static final long serialVersionUID = 1L;

	private List<String> columns;
	private String type;

	
	public String asSql() {

		return "SELECT  " + String.join(",", this.columns) + " ";
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
