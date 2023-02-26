package com.govindan.poc.retailstore.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WhereExpression {
	private static final long serialVersionUID = 1L;


	// List<Expression> expressions = new ArrayList<>();
	// jackson do not worke properly
	// TODO revisit 
	private RangeExpression range;

	
	public String asSql() {

		return " WHERE " + range.asSql();
				
	}

	public RangeExpression getRange() {
		return range;
	}

	public void setRange(RangeExpression range) {
		this.range = range;
	}

}
