package com.model;

import java.util.Vector;

import com.common.DateCalculator;
import com.common.Money;

public class Contract extends TableModule {
	/*descrepted*/
	private RevenueRecognition rr = new RevenueRecognition();
        
        public Contract(RecordSet set){
            super(set);
        }

	public Contract() {
		super(new RecordSet());           
                addColumn("id");
                addColumn("product");
                addColumn("amount");
                addColumn("whenSigned");
	}
        
        public void calculateRecognitions(int contractID, RevenueRecognition recognitions) {
		Vector<?> contractRecord = findContract(contractID);
		String type = (String) contractRecord.get(1);
		Double amount = (Double) contractRecord.get(2);
		java.util.Date recognitionDate = (java.util.Date) contractRecord.get(3);
		Money totalRevenue = Money.dollars(amount.doubleValue());
		if (type.equals("1")) {
			recognitions.insertRecognition(contractID, totalRevenue, recognitionDate);

		} else if (type.equals("2")) {
			Money[] allocation = totalRevenue.allocation(3);
			recognitions.insertRecognition(contractID, allocation[0], recognitionDate);
			recognitions.insertRecognition(contractID, allocation[1],
					DateCalculator.addDay(recognitionDate, 30));
			recognitions.insertRecognition(contractID, allocation[2],
					DateCalculator.addDay(recognitionDate, 60));

		} else if (type.equals("3")) {
			Money[] allocation = totalRevenue.allocation(3);
			recognitions.insertRecognition(contractID, allocation[0], recognitionDate);
			recognitions.insertRecognition(contractID, allocation[1],
					DateCalculator.addDay(recognitionDate, 60));
			recognitions.insertRecognition(contractID, allocation[2],
					DateCalculator.addDay(recognitionDate, 90));

		}

	}
        

	public void calculateRecognitions(int contractID) {
		Vector<?> contractRecord = findContract(contractID);
		String type = (String) contractRecord.get(1);
		Double amount = (Double) contractRecord.get(2);
		java.util.Date recognitionDate = (java.util.Date) contractRecord.get(3);
		Money totalRevenue = Money.dollars(amount.doubleValue());
		if (type.equals("1")) {
			rr.insertRecognition(contractID, totalRevenue, recognitionDate);

		} else if (type.equals("2")) {
			Money[] allocation = totalRevenue.allocation(3);
			rr.insertRecognition(contractID, allocation[0], recognitionDate);
			rr.insertRecognition(contractID, allocation[1],
					DateCalculator.addDay(recognitionDate, 30));
			rr.insertRecognition(contractID, allocation[2],
					DateCalculator.addDay(recognitionDate, 60));

		} else if (type.equals("3")) {
			Money[] allocation = totalRevenue.allocation(3);
			rr.insertRecognition(contractID, allocation[0], recognitionDate);
			rr.insertRecognition(contractID, allocation[1],
					DateCalculator.addDay(recognitionDate, 60));
			rr.insertRecognition(contractID, allocation[2],
					DateCalculator.addDay(recognitionDate, 90));

		}

	}
	
	public void displayRevenueRecognitions(int contractID){
		Vector<Vector<?>> data = (Vector<Vector<?>>) rr.getDataVector();
		for(int i = 0; i<data.size(); i++){
			Vector<?> record = data.get(i);
			String id = (String) record.get(0);
			if(Integer.parseInt(id) == contractID){
				System.out.println(record.toString());
			}
		}
		
	}

	/**
	 * Return a vector that contains the contract record with id
	 * 
	 * @param id
	 * @return
	 */
	public Vector<?> findContract(int id) {
		Vector<?> data = getDataVector();
		
		/*int id_index = -1;

		for (int i = 0; i < getColumnCount(); i++) {
			String columnName = getColumnName(i);
			if (columnName.toLowerCase() == "id") {
				id_index = i;
				break;
			}

		}*/

		for (int i = 0; i < data.size(); i++) {
			Vector<?> record = (Vector<?>) data.get(i);
			String recordID = (String) record.get(0);
			if (Integer.parseInt(recordID) == id)
				return record;
		}

		return null;
	}

}
