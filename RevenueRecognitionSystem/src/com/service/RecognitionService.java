package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

import com.common.ApplicationException;
import com.common.DateCalculator;
import com.common.Money;

public class RecognitionService {
	
	private Gateway gateway = new Gateway();
	
	
public Money recognizedRevenue(long contractNumber,  GregorianCalendar asof) throws ApplicationException{
		
		Money result = Money.dollars(0);
		try{
			ResultSet rs = gateway.findRecognitionsFor(contractNumber, asof);
			while(rs.next()){
				result = result.add(Money.dollars(rs.getDouble("amount")));
			}
			return result;
		}catch(SQLException e){
			throw new ApplicationException(e.toString());
		}
		
	}
	
	public void calculateRevenueRecognitions(long contractNumber) throws ApplicationException{
		try{
			
			ResultSet contracts = gateway.findContract(contractNumber);
			contracts.next();
			Money totalRevenue = Money.dollars(contracts.getDouble("revenue"));
			Date recognitionDate = contracts.getDate("dateSigned");
			String type = contracts.getString("type");
			if(type.equals("1")){
				gateway.insertRecognition(contractNumber, totalRevenue, recognitionDate);
				
			}else if(type.equals("2")){
				Money[] allocation = totalRevenue.allocation(3);
				gateway.insertRecognition(contractNumber, allocation[0], recognitionDate);
				gateway.insertRecognition(contractNumber, allocation[1], DateCalculator.addDay(recognitionDate, 30));
				gateway.insertRecognition(contractNumber, allocation[2], DateCalculator.addDay(recognitionDate, 60));
				
			}else if(type.equals("3")){
				Money[] allocation = totalRevenue.allocation(3);
				gateway.insertRecognition(contractNumber, allocation[0], recognitionDate);
				gateway.insertRecognition(contractNumber, allocation[1], DateCalculator.addDay(recognitionDate, 60));
				gateway.insertRecognition(contractNumber, allocation[2], DateCalculator.addDay(recognitionDate, 90));
				
			}
		}catch(SQLException e){
			throw new ApplicationException(e.toString());
		}
	}
	
	public static void main(String[] args) {
		
		RecognitionService testapp = new RecognitionService();
		try {
			//testapp.calculateRevenueRecognitions(2);
			
			java.util.GregorianCalendar date2 = new java.util.GregorianCalendar(2012, java.util.GregorianCalendar.JANUARY, 2);
			System.out.println(testapp.recognizedRevenue(2, date2).getAmount());
			
			System.out.println("done!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	   

  }
	

}
