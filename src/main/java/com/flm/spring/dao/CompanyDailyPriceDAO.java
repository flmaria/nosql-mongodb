package com.flm.spring.dao;

import java.util.List;

import com.flm.model.CompanyDailyPrice;


public interface CompanyDailyPriceDAO {
	
	public boolean create(CompanyDailyPrice companyDailyPrice);
	
	public void batchCreate(List<CompanyDailyPrice> companyDailyPriceList);
	
	public boolean removeAll();

}
