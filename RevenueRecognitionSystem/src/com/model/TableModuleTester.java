package com.model;

import java.util.Vector;

public class TableModuleTester {
	
	Contract contract = null;
	Product product = null;
	
	private void dataPreparation(){
		RecordSet table = new RecordSet();
		table.addColumn("id");
		table.addColumn("product");
		table.addColumn("amount");
		table.addColumn("whenSigned");
		
		Vector<Object> data = new Vector<Object>();
		data.add("1");
		data.add("1");
		data.add(new Double(300.00));
		data.add(new java.util.Date());
		table.addRow(data);
		
		data = new Vector<Object>();
		data.add("2");
		data.add("2");
		data.add(new Double(300.00));
		data.add(new java.util.Date());
		
		table.addRow(data);
		
		contract = new Contract(table);
		
		table = new RecordSet();
		table.addColumn("id");
		table.addColumn("name");
		table.addColumn("type");
		
		data = new Vector<Object>();
		data.add("1");
		data.add("word processor");
		data.add("1");
		table.addRow(data);
		
		data = new Vector<Object>();
		data.add("2");
		data.add("database");
		data.add("2");
		table.addRow(data);
		
		data = new Vector<Object>();
		data.add("3");
		data.add("spreadsheet");
		data.add("3");
		table.addRow(data);
		
		product = new Product(table);		
		
	}
	
	private void testFindContract(int i){
		Vector<?> result = contract.findContract(i);
		if(result == null)
			System.out.println("not found");
		else
			System.out.println(result.toString());
	}
	
	private void testCalculateRecognitions(int i){
		contract.calculateRecognitions(i);
		contract.displayRevenueRecognitions(i);
	}

	public static void main(String[] args) {
		TableModuleTester tester = new TableModuleTester();
		tester.dataPreparation();
		tester.testFindContract(1);
		tester.testFindContract(2);
		//tester.testCalculateRecognitions(1);
		tester.testCalculateRecognitions(2);
		

	}

}
