package com.flm.spring.business;

import java.util.List;

import com.flm.dto.ExecutionResultDTO;
import com.flm.model.CompanyDailyPrice;

public interface ExecutionBO {

	public ExecutionResultDTO exe(List<CompanyDailyPrice> companyDailyPriceList);
	
}
