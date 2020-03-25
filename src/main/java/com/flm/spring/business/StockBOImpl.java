package com.flm.spring.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

import com.flm.finance.StockData;
import com.flm.model.CompanyDailyPrice;

import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

@Component
public class StockBOImpl implements StockBO {

	public List<CompanyDailyPrice> getPriceHistory(String stockSymbol, int startYear, int endYear, Interval interval) {
		List<CompanyDailyPrice> companyDailyPriceList = new ArrayList<CompanyDailyPrice>();
		
		StockData stockData = new StockData();
		
		List<HistoricalQuote> quoteList = stockData.getPriceHistory(stockSymbol, createCalendar(startYear), createCalendar(endYear), Interval.DAILY);
		
		for (HistoricalQuote quote : quoteList) {
			CompanyDailyPrice companyDailyPrice = new CompanyDailyPrice(stockSymbol, quote.getDate().getTime(), quote.getClose().doubleValue());
			companyDailyPriceList.add(companyDailyPrice);
		}
		
		return companyDailyPriceList;
	}
	
	private Calendar createCalendar(int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, 0, 1);
	
		return cal;
	}
	
}
