package com.flm.spring.mongo;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.UpperCamelCaseStrategy;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@Component
public class MongoConnectionImpl implements MongoConnection {
	
	private MongoClient mongoClient;
	private DB database;
	
	private final String SEQUENCER_COLLECTION_IDENTIFIER = "tableCounters";
	
	public DBCollection getCollection(String name) {
		return this.getDatabase().getCollection(name);
	}
	
	private DB getDatabase() {
		if (this.mongoClient == null) {
			this.mongoClient = new MongoClient();
			this.database = mongoClient.getDB("test-nosql");
		}
		return this.database;
	}
	
	public void clearCollectionSequence() {
		this.removeAll(SEQUENCER_COLLECTION_IDENTIFIER);
	}

	public long getNextSequence(String name) {
		DBCollection collection = this.getCollection(SEQUENCER_COLLECTION_IDENTIFIER);
		
		DBObject object = null;
		
		if (collection.count() == 0) {
			object = new BasicDBObject();
			object.put("_id", name);
			object.put("seq", 1);
			collection.insert(object);
		}
		else {
			BasicDBObject filterObject = new BasicDBObject();
			filterObject.put("_id", name);
			
			BasicDBObject actionObject = new BasicDBObject();
			
			actionObject.put("$inc", new BasicDBObject("seq", 1));
			object = collection.findAndModify(filterObject, new BasicDBObject(), new BasicDBObject(), false, actionObject, true, true);
			
		}
		
		return Long.parseLong(object.get("seq").toString());
	}
	
	
	
	public void insert(String collectionName, BasicDBObject object) {
		DBCollection collection = this.getCollection(collectionName);
		collection.insert(object);
	}
	
	public void batchInsert(String collectionName, List<BasicDBObject> objectList) {
		BulkWriteOperation bulkWrite = this.getCollection(collectionName).initializeUnorderedBulkOperation();
		
		for (BasicDBObject object : objectList) {
			bulkWrite.insert(object);
		}
		
		bulkWrite.execute();
	}
	
	public void removeAll(String collectionName) {
		DBCollection collection = this.getCollection(collectionName);
		
		collection.remove(new BasicDBObject());
	}
}
