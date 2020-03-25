package com.flm.finance;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

public class StockData {

	public List<HistoricalQuote> getPriceHistory(String symbol, Calendar from, Calendar to, Interval interval) {
		List<HistoricalQuote> quoteList = null;
		
		try {
			Stock stock = YahooFinance.get(symbol.trim().toUpperCase(), from, to, interval);
			
			quoteList = stock.getHistory();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return quoteList;
	}
	
}
