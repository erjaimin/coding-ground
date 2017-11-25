package com.common;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

public class Money {
	
	private long amount;
	private Currency currency;
	
	private static final int[] cents = new int[]{1, 10, 100, 1000};
	
	public Money(double amount, Currency currency){
		this.currency = currency;
		this.amount = Math.round(amount*centFactor());
	}
	
	public Money(long amount, Currency currency){
		this.currency = currency;
		this.amount = amount*centFactor();
	}
	
	

	public Money() {
		
	}

	public Money(BigDecimal amount, Currency currency, int roundingMode) {
		amount = amount.multiply(new BigDecimal(cents[currency.getDefaultFractionDigits()]));
		this.amount = amount.longValue();
		this.currency = currency;
	}

	private int centFactor() {
		return cents[currency.getDefaultFractionDigits()];
	}
	
	public Currency currency(){
		return currency;
	}

	public static Money dollars(double amount) {
		return new Money(amount, Currency.getInstance(Locale.CANADA_FRENCH));
	}
	
	
	public BigDecimal amount(){
		return BigDecimal.valueOf(amount, currency.getDefaultFractionDigits());
	}
	
	/**
	 * make it as a bean
	 */
	public BigDecimal getAmount(){
		return BigDecimal.valueOf(amount, currency.getDefaultFractionDigits());
	}
	
	public Money add(Money other){
		if(currency.equals(other.currency))
			return newMoney(amount + other.amount);
		else
			return null;
	}
	
	
	
	public boolean equals(Object other){
		return (other instanceof Money) && (equals((Money)other));
	}
	
	public boolean equals(Money other){
		return currency.equals(other.currency) && (amount == other.amount);
	}

	public Money[] allocation(int n) {
		Money lowResult = newMoney(amount/n);
		Money highResult = newMoney(lowResult.amount +1);
		Money[] results = new Money[n];
		int remainder = (int) amount %n;
		for(int i=0; i <remainder; i++) results[i]= highResult;
		for(int i = remainder; i <n; i++) results[i] = lowResult;
		return results;
	}
	
	private Money newMoney(long amount){
		Money money = new Money();
		money.currency = this.currency;
		money.amount = amount;
		return money;
	}
	
	public static void main(String[] args){
		Money app = new Money(200, Currency.getInstance(Locale.US));
		System.out.println(app.add(new Money(200,Currency.getInstance(Locale.US))).getAmount());
		
		Money[] allocations = app.allocation(3);
		System.out.println(allocations[0].getAmount());
		System.out.println(allocations[1].getAmount());
		System.out.println(allocations[2].getAmount());
		
		app = new Money(200, Currency.getInstance(Locale.US));
		app = app.multiply(3);
		System.out.println(app.getAmount());
		
	}

	public Money multiply(double amount) {
		return multiply(new BigDecimal(amount));
	}

	private Money multiply(BigDecimal amount) {
		return multiply(amount, BigDecimal.ROUND_HALF_EVEN);
	}

	private Money multiply(BigDecimal amount, int roundingMode) {
		return new Money(amount().multiply(amount), currency, roundingMode);
	}
	
	
	

}
