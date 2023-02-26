package com.govindan.poc.retailstore.search;

public class RangeExpression implements Expression {
	private static final long serialVersionUID = 1L;
	private String from;
	private String to;
	private String field;
	private String type;
	
	
	

	@Override
	public String asSql() {

		return " " + field + ">=" + from + " AND " + field + "<=" + to + " ";
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

}
