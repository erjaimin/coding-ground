package com.common;

import java.util.GregorianCalendar;


public class DateCalculator {
	
	public static java.util.Date addDay(java.util.Date date, int n){
		java.util.GregorianCalendar calendar = new java.util.GregorianCalendar();
		calendar.setTime(date);
		calendar.add(GregorianCalendar.DATE, n);
		return calendar.getTime();
		
	}
	
	public static java.util.GregorianCalendar addDay(java.util.GregorianCalendar date, int n){
		java.util.GregorianCalendar calendar = new java.util.GregorianCalendar();
		calendar.setTime(date.getTime());
		calendar.add(GregorianCalendar.DATE, n);
		return calendar;
		
	}
	
	public static java.sql.Date dateTransform(java.util.Date utilDate ){
		return  new java.sql.Date(utilDate.getTime());
	}
	
	public void dateTransform(){
		java.util.Date utilDate = new java.util.Date();
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	    System.out.println("utilDate:" + utilDate);
	    System.out.println("sqlDate:" + sqlDate);
	}
		    
	public static void main(String[] args) {
		
		java.util.Date utilDate = new java.util.Date();
		
		System.out.println("utilDate:" + utilDate + utilDate);
		System.out.println("utilDate:" + addDay(utilDate, 30));
	    //System.out.println("utilDate:" + addDay(utilDate,30));
	    
		//DateCalculator app = new DateCalculator();
	    //app.dateTransform();
	    
	    GregorianCalendar date = new GregorianCalendar(2013, 10, 11);
	    System.out.println("2013-10-11:" + date.getTime());
	    System.out.println("2013-10-11 + 30 =" + addDay(date, 30).getTime());
	    
	    
	    
	    /*GregorianCalendar worldTour = new GregorianCalendar(1872, Calendar.OCTOBER, 2);
	      worldTour.add(GregorianCalendar.DATE, 80);
	      java.util.Date d = worldTour.getTime();
	      DateFormat df = DateFormat.getDateInstance();
	      String s = df.format(d);
	      System.out.println("80 day trip will end " + s);
	   */

	}

}
