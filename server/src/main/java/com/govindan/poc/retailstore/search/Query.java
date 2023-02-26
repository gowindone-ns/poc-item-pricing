package com.govindan.poc.retailstore.search;

public class Query  {
	private static final long serialVersionUID = 1L;
	private SelectExpression selectExpression;
	private FromExpression fromExpression;
	private WhereExpression whereExpression;
	private SortByExpression sortByExpression;
	

	
	public String asSql() {
		return selectExpression.asSql() + fromExpression.asSql() + whereExpression.asSql() ;
		// return select.asSql() + fromExpression.asSql() + whereExpression.asSql() + sortByExpression.asSql();
	}

	public FromExpression getFromExpression() {
		return fromExpression;
	}

	public void setFromExpression(FromExpression fromExpression) {
		this.fromExpression = fromExpression;
	}

	public WhereExpression getWhereExpression() {
		return whereExpression;
	}

	public void setWhereExpression(WhereExpression whereExpression) {
		this.whereExpression = whereExpression;
	}

	public SortByExpression getSortByExpression() {
		return sortByExpression;
	}

	public void setSortByExpression(SortByExpression sortByExpression) {
		this.sortByExpression = sortByExpression;
	}

	public SelectExpression getSelectExpression() {
		return selectExpression;
	}

	public void setSelectExpression(SelectExpression selectExpression) {
		this.selectExpression = selectExpression;
	}


	

	
}
