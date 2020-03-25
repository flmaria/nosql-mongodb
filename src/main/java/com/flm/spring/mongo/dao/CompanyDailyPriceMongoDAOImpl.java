package com.flm.spring.mongo.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flm.model.CompanyDailyPrice;
import com.flm.model.adaptor.CompanyDailyPriceMongoAdaptor;
import com.flm.spring.mongo.MongoConnection;
import com.mongodb.BasicDBObject;

@Component
public class CompanyDailyPriceMongoDAOImpl implements CompanyDailyPriceMongoDAO {
	
	private MongoConnection mongoConnection;
	
	private final String CLASS_IDENTIFIER = "companyDailyPrice";
	
	@Autowired
	public CompanyDailyPriceMongoDAOImpl(MongoConnection mongoConnection) {
		this.mongoConnection = mongoConnection;
	}
	
	public void create(CompanyDailyPrice companyDailyPrice) {
		companyDailyPrice.setId(this.mongoConnection.getNextSequence(CLASS_IDENTIFIER));
		this.mongoConnection.insert(CLASS_IDENTIFIER, CompanyDailyPriceMongoAdaptor.toDBObject(companyDailyPrice));
	}
	
	public void batchCreate(final List<CompanyDailyPrice> companyDailyPriceList) {
		List<BasicDBObject> objectList = new ArrayList<BasicDBObject>();
		
		for (CompanyDailyPrice companyDailyPrice : companyDailyPriceList) {
			companyDailyPrice.setId(this.mongoConnection.getNextSequence(CLASS_IDENTIFIER));
			objectList.add(CompanyDailyPriceMongoAdaptor.toDBObject(companyDailyPrice));
		}
		
		this.mongoConnection.batchInsert(CLASS_IDENTIFIER, objectList);
	}
	
	public void removeAll() {
		this.mongoConnection.removeAll(CLASS_IDENTIFIER);
	}
	
	
	
}
