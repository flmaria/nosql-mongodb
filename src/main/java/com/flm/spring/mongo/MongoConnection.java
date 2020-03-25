package com.flm.spring.mongo;

import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

public interface MongoConnection {
	
	public DBCollection getCollection(String name);
	
	public void clearCollectionSequence();
	
	public long getNextSequence(String name);
	
	public void insert(String collectionName, BasicDBObject object);
	
	public void batchInsert(String collectionName, List<BasicDBObject> objectList);
	
	public void removeAll(String collectionName);

}
