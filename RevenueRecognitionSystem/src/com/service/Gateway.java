package com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.common.DateCalculator;
import com.common.Money;

public class Gateway {

	private Connection db;
	private static final String findRecognitionsStatement = "SELECT amount "
			+ "FROM revenueRecognitions "+
			"WHERE contract = ? AND recognizedOn <=?";
	private static final String insertRecognitionStatement = "INSERT INTO revenueRecognitions Values(?,?,?)";
	private static final String findContractStatement = "SELECT *"+
	         " FROM contracts c, products p "+
			" WHERE c.ID= ? AND c.product = p.ID";


	
	
	public ResultSet findRecognitionsFor(long contractID, java.util.GregorianCalendar asof) throws SQLException{
		if(db == null){
			try {
				setConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		PreparedStatement stmt = db.prepareStatement(findRecognitionsStatement);
		stmt.setLong(1, contractID);
		stmt.setDate(2, new java.sql.Date(asof.getTime().getTime()));
		ResultSet result = stmt.executeQuery();
		return result;
	}
	
	
	/**
	 * This is a test method for testing the SQL statement findContractStatement
	 * @param resultSet
	 * @throws SQLException
	 */
	
	private void displayContracts(ResultSet resultSet) throws SQLException {
		 // ResultSet is initially before the first data set
	    while (resultSet.next()) {
	      // It is possible to get the columns via name
	      // also possible to get the columns via the column number
	      // which starts at 1
	      // e.g. resultSet.getSTring(2);
	      System.out.println("c.ID " + resultSet.getInt("c.ID"));
	      System.out.println("product " + resultSet.getInt("product"));
	      System.out.println("amount " + resultSet.getString("revenue"));
	      System.out.println("dateSigned " + resultSet.getDate("dateSigned"));
	      System.out.println("p.ID " + resultSet.getInt("p.ID"));
	      System.out.println("name " + resultSet.getString("name"));
	      System.out.println("type " + resultSet.getInt("type"));
	    }
	}
	
	private void setConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
	    db = DriverManager
	          .getConnection("jdbc:mysql://localhost:3306/test?"
	              + "user=root&password=taoci960");

	}
	
	public static void main(String[] args) {
		
			Gateway testapp = new Gateway();
			try {
				testapp.setConnection();
				testapp.displayContracts(testapp.findContract(1));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	   

	  }

	public void insertRecognition(long contractNumber, Money money,
			Date recognitionDate) throws SQLException {
		if(db == null){
			try {
				setConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		PreparedStatement stmt = db.prepareStatement(insertRecognitionStatement);
		stmt.setLong(1, contractNumber);
		stmt.setBigDecimal(2, money.amount());
		stmt.setDate(3, DateCalculator.dateTransform(recognitionDate));
		stmt.executeUpdate();
		
		
	}

	public ResultSet findContract(long contractNumber) throws SQLException {
		if(db == null){
			try {
				setConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		PreparedStatement stmt = db.prepareStatement(findContractStatement);
		stmt.setLong(1, contractNumber);
		ResultSet result = stmt.executeQuery();
		return result;
	}
	
	
	
	
	
}
