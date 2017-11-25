package com.invoices.mvc;

import java.text.DecimalFormat;

/**
 * Utility class for invoice constants and common methods 
 */
public class InvoiceUtils {
	public static final String FILE_DELIMETER = ",";
	public static final int MAX_ITEMS_PER_INVOICE = 15;
	public static final double TAX1_RATE = 0.05;
	public static final double TAX2_RATE = 0.09975;
	
	/**
	 * returns formatted value with two decimal places
	 * @param value
	 * @return
	 */
	public static String getFormattedValue(Double value){
		return new DecimalFormat("#0.00").format(value);
	}
}
