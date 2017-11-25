package com.model;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import com.common.Money;

public class RevenueRecognition extends TableModule {

	/*public RevenueRecognition(int contractID, Date date) {
		super(new RecordSet());
	}*/

	public RevenueRecognition() {
		super(new RecordSet());
		addColumn("contractID");
		addColumn("amount");
		addColumn("recognizedOn");
	}

	

	public void insertRecognition(int contractID, Money amount, Date date) {
		Vector<Object> data = new Vector<Object>();
		data.add(String.valueOf(contractID));
		data.add(amount.amount());
		data.add(date);
		addRow(data);
	}
        
        public Vector findRecognitionsFor(long contractNumber,  GregorianCalendar asof){
            Vector<Object> results = new Vector<Object>();
            Vector<?> data = getDataVector();

		for (int i = 0; i < data.size(); i++) {
			Vector<?> record = (Vector<?>) data.get(i);
			String recordID = (String) record.get(0);
                        Date date = (Date) record.get(3);
			if (Integer.parseInt(recordID) == contractNumber && asof.after(date)){
                            results.add(record);				
                        }
		}

		return results;
            
        }
	
	/*public void displayRevenueRecognitions(int contractID){
		Vector<Vector<?>> data = (Vector<Vector<?>>) rr.getDataVector();
		for(int i = 0; i<data.size(); i++){
			Vector<?> record = data.get(i);
			String id = (String) record.get(0);
			if(Integer.parseInt(id) == contractID){
				System.out.println(record.toString());
			}
		}
		
	}*/


}
