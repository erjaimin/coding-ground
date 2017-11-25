package com.model;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class RecordSet extends DefaultTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7032542541009637394L;

	public RecordSet(Vector<Vector<Object>> data, Vector<String> columnNames) {
		super(data, columnNames);
	}

	public RecordSet() {
		super();
	}
	



}
