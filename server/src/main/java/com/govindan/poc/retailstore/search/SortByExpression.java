package com.govindan.poc.retailstore.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SortByExpression implements Expression {
	
	private static final long serialVersionUID = 1L;
	List<SortExpression> sortExpressions = new ArrayList<>();

	@Override
	public String asSql() {

		return " order by "
				+ String.join(",", sortExpressions.stream().map(SortExpression::asSql).collect(Collectors.toList()));
	}

	public List<SortExpression> getSortExpressions() {
		return sortExpressions;
	}

	public void setSortExpressions(List<SortExpression> sortExpressions) {
		this.sortExpressions = sortExpressions;
	}

}
