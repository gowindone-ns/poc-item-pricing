package com.govindan.poc.retailstore.search;

public class QueryPayload {
	private Query query;

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	// {select:["",""],from:"",where:[{range:{from:"",to:""}}],sort:[{sortBy:"",sortOrder:""}]}

}
