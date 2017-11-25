package com.model;

import java.util.Vector;

public class Product extends TableModule {
	
	public Product(RecordSet set) {
		super(set);
	}

	public String getProductType(int id){
		Vector<?> data = getDataVector();
		Vector<?> productRecord = (Vector<?>) data.get(id);
		return (String) productRecord.get(2);
	}

}
