package com.invoices.mvc;

import java.util.List;
/**
 * 
 * This class represents the fields related to invoice 
 *
 */
public class Invoice {
	private long invoiceNo;
	private String invoiceDate;
	private List<InvoiceItem> items;
	private double subtotal;
	private double tax1;
	private double tax2;
	private double total;
	private Address factAddress;
	private Address livrAddress;
	private String formattedSubtotal;
	private String formattedTax1;
	private String formattedTax2;
	private String formattedTotal;
	
	public Invoice(long invoiceNo, String invoiceDate){
		this.invoiceNo = invoiceNo;
		this.invoiceDate = invoiceDate;
	}
	/**
	 * @return the invoiceNo
	 */
	public long getInvoiceNo() {
		return invoiceNo;
	}
	/**
	 * @param invoiceNo the invoiceNo to set
	 */
	public void setInvoiceNo(long invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	/**
	 * @return the invoiceDate
	 */
	public String getInvoiceDate() {
		return invoiceDate;
	}
	/**
	 * @param invoiceDate the invoiceDate to set
	 */
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	/**
	 * @return the factAddress
	 */
	public Address getFactAddress() {
		return factAddress;
	}
	/**
	 * @param factAddress the factAddress to set
	 */
	public void setFactAddress(Address factAddress) {
		this.factAddress = factAddress;
	}
	
	/**
	 * @return the livrAddress
	 */
	public Address getLivrAddress() {
		return livrAddress;
	}
	/**
	 * @param livrAddress the livrAddress to set
	 */
	public void setLivrAddress(Address livrAddress) {
		this.livrAddress = livrAddress;
	}
	/**
	 * @return the items
	 */
	public List<InvoiceItem> getItems() {
		return items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(List<InvoiceItem> items) {
		this.items = items;
	}
	/**
	 * @return the subtotal
	 */
	public double getSubtotal() {
		return subtotal;
	}
	/**
	 * @return the formattedSubtotal
	 */
	public String getFormattedSubtotal() {
		return InvoiceUtils.getFormattedValue(getSubtotal());
	}
	/**
	 * @return the formattedTax1
	 */
	public String getFormattedTax1() {
		return InvoiceUtils.getFormattedValue(getTax1());
	}
	/**
	 * @return the formattedTax2
	 */
	public String getFormattedTax2() {
		return InvoiceUtils.getFormattedValue(getTax2());
	}
	/**
	 * @return the formattedTotal
	 */
	public String getFormattedTotal() {
		return InvoiceUtils.getFormattedValue(getTotal());
	}
	/**
	 * @param subtotal the subtotal to set
	 */
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	/**
	 * @return the tax1
	 */
	public double getTax1() {
		return tax1;
	}
	/**
	 * @param tax1 the tax1 to set
	 */
	public void setTax1(double tax1) {
		this.tax1 = tax1;
	}
	/**
	 * @return the tax2
	 */
	public double getTax2() {
		return tax2;
	}
	/**
	 * @param tax2 the tax2 to set
	 */
	public void setTax2(double tax2) {
		this.tax2 = tax2;
	}
	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}
	
	/**
	 * @return the limitReached
	 */
	public boolean isLimitReached() {
		return (items.size() == InvoiceUtils.MAX_ITEMS_PER_INVOICE);
	}
	
	/**
	 * 
	 * @param item
	 */
	public void addInvoiceItem(InvoiceItem item){
		this.items.add(item);
		this.setSubtotal(this.getSubtotal() + item.getSubtotal());
		this.setTax1(this.getSubtotal()*InvoiceUtils.TAX1_RATE);
		this.setTax2(this.getSubtotal()*InvoiceUtils.TAX2_RATE);
		this.setTotal(this.getSubtotal()+this.getTax1()+this.getTax2());
	}
}
