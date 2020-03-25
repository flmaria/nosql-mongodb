package com.flm.spring.business;

import java.util.List;

import com.flm.model.CompanyDailyPrice;

import yahoofinance.histquotes.Interval;

public interface StockBO {

	public List<CompanyDailyPrice> getPriceHistory(String stockSymbol, int startYear, int endYear, Interval interval);
	
}
