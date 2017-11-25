package com.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;


public class TableModule {
	
	/**
	 * DefaultTableModel is the closest to DataTable in C#. It is to store data
	 * like a database table (with columns and rows, with also column names)
	 */
	private RecordSet table = new RecordSet();
	
	/**
	 * ResultSet is from database query, use the query results to initialize the table
	 * @throws SQLException 
	 */
	
	public TableModule(ResultSet set) throws SQLException{	
		
	    ResultSetMetaData metaData;
		metaData = set.getMetaData();
		int totalColumn = metaData.getColumnCount();
	    Object[] dataRow = new Object[totalColumn];
	    if(set!= null)
	    {
	        for(int i=1;i<=totalColumn;i++)
	        {
	            table.addColumn(metaData.getColumnName(i));
	        }
	        while(set.next())
	        {
	            for(int i=1;i<=totalColumn;i++)
	            {
	                dataRow[i-1] = set.getObject(i);
	            }
	            table.addRow(dataRow);
	        }
	
	    }
	}
	
	/**
	 * default constructor for 
	 */
	
	public TableModule(RecordSet set){
		table = set;
	}
	
	
	public String getColumnName(int i) {
		return table.getColumnName(i);
	}

	public int getColumnCount() {
		return table.getColumnCount();
	}

	public Vector getDataVector() {
		return table.getDataVector();
	}
	
	public void addColumn(String string) {
		table.addColumn(string);
		
	}
	
	public void addRow(Vector<?> data){
		table.addRow(data);
	}
	
	public void display(){
		System.out.println(getDataVector().toString());
	}
	

}
