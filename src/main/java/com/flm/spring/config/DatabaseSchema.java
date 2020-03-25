package com.flm.spring.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class DatabaseSchema {

	JdbcTemplate jdbcTemplate;
	
	Logger logger;
	
	@Autowired
	public DatabaseSchema(DataSource dataSource) {
		logger = LoggerFactory.getLogger(DatabaseSchema.class);
        
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void create() {
		logger.info("Creating database schema");
		
		/** Dropping Tables **/
		logger.info("Deleting company daily price table");
		jdbcTemplate.execute("DROP TABLE IF EXISTS public.companydailyprice");
		
		/** Creating Tables **/
		logger.info("Creating company daily price table");
		jdbcTemplate.execute("CREATE TABLE public.companydailyprice (" +
					"id serial NOT NULL," +
					"price_date date NOT NULL," +
					"price float8 NOT NULL," +
					"symbol varchar(10) NOT NULL," +
					"CONSTRAINT companydailyprice_pkey PRIMARY KEY (id)" +
				")");
	}
	
}
