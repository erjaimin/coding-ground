package com.invoices.mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * This class performs data related operations on {@link InvoiceItem} and
 * {@link Invoice}
 *
 */
public class InvoiceDao {

	/**
	 * data members
	 */
	private Map<String, List<Invoice>> invoiceMap = new HashMap<>();
	private long numéroPremièreFacture;
	private String dateFacturation;

	/**
	 * @return the invoiceItemsMap
	 */
	public Map<String, List<Invoice>> getInvoiceMap() {
		return invoiceMap;
	}

	/**
	 * This methods add the invoice item to a Invoice
	 * @param item
	 */
	public void addInvoiceItem(InvoiceItem item){
		String key = item.getFactAdresse().getNomClient();
		if(invoiceMap.containsKey(key)){
			List<Invoice> invoices = invoiceMap.get(key);
			boolean found = false;
			for (Invoice invoice : invoices) {
				if (invoice.getLivrAddress().equals(item.getLivrAdresse())
						&& invoice.getFactAddress().equals(item.getFactAdresse()) && !invoice.isLimitReached()) {
					invoice.addInvoiceItem(item);
					found = true;
					break;
				}
			}
			if(!found){
				invoices.add(createNewInvoice(item, getNextInvoiceNo(), getDateFacturation()));
				invoiceMap.put(key, invoices);
			}
		}else{
			List<Invoice> invoices = new ArrayList<>();
			invoices.add(createNewInvoice(item, getNextInvoiceNo(), getDateFacturation()));
			invoiceMap.put(key, invoices);
		}
	}

	/**
	 * returns the next unique invoice number
	 * @return
	 */
	private long getNextInvoiceNo() {
		return numéroPremièreFacture++;
	}

	/**
	 * This method creates a new invoice with the given values
	 * @param item
	 * @param invoiceDate 
	 * @param invoiceNo 
	 * @return {@link Invoice}
	 */
	private Invoice createNewInvoice(InvoiceItem item, long invoiceNo, String invoiceDate) {
		Invoice invoice = new Invoice(invoiceNo, invoiceDate);
		invoice.setFactAddress(item.getFactAdresse());
		invoice.setLivrAddress(item.getLivrAdresse());
		List<InvoiceItem> items = new ArrayList<>();
		items.add(item);
		invoice.setItems(items);
		invoice.setSubtotal(item.getSubtotal());
		invoice.setTax1(item.getSubtotal()*InvoiceUtils.TAX1_RATE);
		invoice.setTax2(item.getSubtotal()*InvoiceUtils.TAX2_RATE);
		invoice.setTotal(item.getSubtotal()+invoice.getTax1()+invoice.getTax2());
		return invoice;
	}

	/**
	 * @return the numéroPremièreFacture
	 */
	public long getNuméroPremièreFacture() {
		return numéroPremièreFacture;
	}

	/**
	 * @param numéroPremièreFacture the numéroPremièreFacture to set
	 */
	public void setNuméroPremièreFacture(long numéroPremièreFacture) {
		this.numéroPremièreFacture = numéroPremièreFacture;
	}

	/**
	 * @return the dateFacturation
	 */
	public String getDateFacturation() {
		return dateFacturation;
	}

	/**
	 * @param dateFacturation the dateFacturation to set
	 */
	public void setDateFacturation(String dateFacturation) {
		this.dateFacturation = dateFacturation;
	}
}
