package com.flm.model;

import java.util.Date;

public class CompanyDailyPrice {

	private long id;
	
	private String symbol;

	private Date priceDate;
	
	private double price;
	
	public CompanyDailyPrice() {
		super();
	}

	public CompanyDailyPrice(String symbol, Date priceDate, double price) {
		super();
		this.symbol = symbol;
		this.priceDate = priceDate;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Date getPriceDate() {
		return priceDate;
	}

	public void setPriceDate(Date priceDate) {
		this.priceDate = priceDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

		
}
