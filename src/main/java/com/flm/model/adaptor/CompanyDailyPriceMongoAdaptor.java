package com.flm.model.adaptor;

import java.util.Date;

import com.flm.model.CompanyDailyPrice;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class CompanyDailyPriceMongoAdaptor {
	
	public static final BasicDBObject toDBObject(CompanyDailyPrice companyDailyPrice) {
		BasicDBObject obj = new BasicDBObject();
		obj.put("_id", companyDailyPrice.getId());
		obj.put("priceDate", companyDailyPrice.getPriceDate().getTime());
		obj.put("price", companyDailyPrice.getPrice());
		obj.put("symbol", companyDailyPrice.getSymbol());
		
		return obj;
	}
	
	public static final CompanyDailyPrice fromDBObjectToCompanyDailyPrice(DBObject obj) {
		CompanyDailyPrice companyDailyPrice = new CompanyDailyPrice();
		companyDailyPrice.setId(Long.parseLong(obj.get("_id").toString()));
		companyDailyPrice.setPriceDate(new Date(Long.parseLong(obj.get("priceDate").toString())));
		companyDailyPrice.setPrice(Double.parseDouble(obj.get("price").toString()));
		companyDailyPrice.setSymbol(obj.get("symbol").toString());
		
		return companyDailyPrice;
	}
	
}
