package com.controller;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;

import com.common.ApplicationException;
import com.common.Money;
import com.service.RecognitionService;

public class QueryCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		RecognitionService service = new RecognitionService();
		String contractNumber = request.getParameter("num");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("date");
		int monthNumber;
		switch (month.toLowerCase()) {
	        case "january":
	            monthNumber = GregorianCalendar.JANUARY ;
	            break;
	        case "february":
	            monthNumber = GregorianCalendar.FEBRUARY;
	            break;
	        case "march":
	            monthNumber = GregorianCalendar.MARCH;
	            break;
	        case "april":
	            monthNumber = GregorianCalendar.APRIL;
	            break;
	        case "may":
	            monthNumber = GregorianCalendar.MAY;
	            break;
	        case "june":
	            monthNumber = GregorianCalendar.JUNE;
	            break;
	        case "july":
	            monthNumber = GregorianCalendar.JULY;
	            break;
	        case "august":
	            monthNumber = GregorianCalendar.AUGUST;
	            break;
	        case "september":
	            monthNumber = GregorianCalendar.SEPTEMBER;
	            break;
	        case "october":
	            monthNumber = GregorianCalendar.OCTOBER;
	            break;
	        case "november":
	            monthNumber = GregorianCalendar.NOVEMBER;
	            break;
	        case "december":
	            monthNumber = GregorianCalendar.DECEMBER;
	            break;
	        default: 
	            monthNumber = GregorianCalendar.JANUARY;
	            break;
		}

		
		GregorianCalendar date = new GregorianCalendar();
		date.set(Integer.parseInt(year), monthNumber, Integer.parseInt(day));
		Money amount = null;
		try {
			amount = service.recognizedRevenue(Integer.parseInt(contractNumber), date);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("amount", amount);
		forward("/recognizedRevenueView.jsp");

	}

}
