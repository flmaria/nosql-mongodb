package com.flm.spring.business;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flm.dto.ExecutionResultDTO;
import com.flm.model.CompanyDailyPrice;
import com.flm.spring.config.DatabaseSchema;
import com.flm.spring.dao.CompanyDailyPriceDAO;
import com.flm.utils.DateUtils;


@Component
public class ExecutionDefaultBOImpl implements ExecutionBO {
	
	private DatabaseSchema databaseSchema;
	
	private CompanyDailyPriceDAO companyDailyPriceDAO;
	
	@Autowired
	public ExecutionDefaultBOImpl(DatabaseSchema databaseSchema, CompanyDailyPriceDAO companyDailyPriceDAO) {
		this.databaseSchema = databaseSchema;
		this.companyDailyPriceDAO = companyDailyPriceDAO;
	}
	
	private void createSchema() {
		this.databaseSchema.create();
	}
	
	public ExecutionResultDTO exe(List<CompanyDailyPrice> companyDailyPriceList) {
		ExecutionResultDTO executionResult = new ExecutionResultDTO();
		
		this.createSchema();
		executionResult.setInclusionInSeconds(this.getInclusionInseconds(companyDailyPriceList));
		executionResult.setBatchInsertionInSeconds(this.getBatchInsertionInseconds(companyDailyPriceList));
		
		return executionResult;
	}
	
	private double getInclusionInseconds(List<CompanyDailyPrice> companyDailyPriceList) {
		this.companyDailyPriceDAO.removeAll();
		
		Date startDate = new Date();
		
		int size = companyDailyPriceList.size();
		for (int i=0; i<size; i++) {
			this.companyDailyPriceDAO.create(companyDailyPriceList.get(i));
		}
		
		Date endDate = new Date();
		
		return DateUtils.differenceInSeconds(startDate, endDate);
	}
	
	private double getBatchInsertionInseconds(List<CompanyDailyPrice> companyDailyPriceList) {
		companyDailyPriceDAO.removeAll();
		
		Date startDate = new Date();
		
		this.companyDailyPriceDAO.batchCreate(companyDailyPriceList);
		
		Date endDate = new Date();
		
		return DateUtils.differenceInSeconds(startDate, endDate);
	}

}
