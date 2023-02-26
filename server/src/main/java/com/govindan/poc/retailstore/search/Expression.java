package com.govindan.poc.retailstore.search;

import java.io.Serializable;

public interface Expression extends Serializable {

	String asSql();

	default String getType() {
		return "";
	}

}
