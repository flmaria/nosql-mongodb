package com.flm;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.flm.dto.ExecutionResultDTO;
import com.flm.model.CompanyDailyPrice;
import com.flm.spring.business.ExecutionBO;
import com.flm.spring.business.ExecutionDefaultBOImpl;
import com.flm.spring.business.ExecutionNoSQLBOImpl;
import com.flm.spring.business.StockBO;
import com.flm.spring.config.AppConfig;

import yahoofinance.histquotes.Interval;

public class Main {

	public static void main(String[] args) {
		
		Logger logger = LoggerFactory.getLogger(Main.class);
		  
		logger.info("###START PROGRAM###");
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		StockBO stockBO = context.getBean(StockBO.class);
		
		String stockSymbolArray[] = new String[] {"AAPL", "AMZN", "FB", "GOOGL"};
		
		List<CompanyDailyPrice> companyDailyPriceList = new ArrayList<CompanyDailyPrice>();
		
		for (String stockSymbol : stockSymbolArray) {
			companyDailyPriceList.addAll(stockBO.getPriceHistory(stockSymbol, 2019, 2020, Interval.DAILY));
		}
		
		ExecutionBO executionDefaultBO = context.getBean(ExecutionDefaultBOImpl.class);
		ExecutionResultDTO executionDefaultResult = executionDefaultBO.exe(companyDailyPriceList);
		
		ExecutionBO executionNoSQLBO = context.getBean(ExecutionNoSQLBOImpl.class);
		ExecutionResultDTO executionNoSQLResult = executionNoSQLBO.exe(companyDailyPriceList);
		
		logger.info(String.format("Total time of inclusion of records in stantard database: %.2f seconds", executionDefaultResult.getInclusionInSeconds()));
		logger.info(String.format("Total time of inclusion of records in NO-SQL database: %.2f seconds", executionNoSQLResult.getInclusionInSeconds()));
		
		logger.info(String.format("Total time of inclusion of batch records in stantard database: %.2f seconds", executionDefaultResult.getBatchInsertionInSeconds()));
		logger.info(String.format("Total time of inclusion of batch records in NO-SQL database: %.2f seconds", executionNoSQLResult.getBatchInsertionInSeconds()));
		
		logger.info("###END PROGRAM###");
	}
	
	
}
