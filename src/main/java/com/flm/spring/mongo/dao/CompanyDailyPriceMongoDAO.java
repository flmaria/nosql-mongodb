package com.flm.spring.mongo.dao;

import java.util.List;

import com.flm.model.CompanyDailyPrice;


public interface CompanyDailyPriceMongoDAO {
	
	public void create(CompanyDailyPrice companyDailyPrice);
	
	public void batchCreate(List<CompanyDailyPrice> companyDailyPriceList);
	
	public void removeAll();

}
