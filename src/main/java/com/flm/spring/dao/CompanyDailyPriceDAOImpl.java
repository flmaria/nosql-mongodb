package com.flm.spring.dao;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.flm.model.CompanyDailyPrice;


@Component
public class CompanyDailyPriceDAOImpl implements CompanyDailyPriceDAO {
	
	JdbcTemplate jdbcTemplate;
	
	private String SQL_INSERT = "INSERT INTO companydailyprice(price_date, price, symbol) values(?, ?, ?)";
	
	@Autowired
	public CompanyDailyPriceDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public boolean create(CompanyDailyPrice companyDailyPrice) {
		return jdbcTemplate.update(SQL_INSERT, companyDailyPrice.getPriceDate(), companyDailyPrice.getPrice(), companyDailyPrice.getSymbol()) > 0;
	}
	
	public void batchCreate(final List<CompanyDailyPrice> companyDailyPriceList) {
		this.jdbcTemplate.batchUpdate(SQL_INSERT,
				new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setDate(1, new java.sql.Date(companyDailyPriceList.get(i).getPriceDate().getTime()));
						ps.setDouble(2, companyDailyPriceList.get(i).getPrice());
						ps.setString(3, companyDailyPriceList.get(i).getSymbol());
					}
					
					public int getBatchSize() {
						return companyDailyPriceList.size();
					}
				}
		);
	}
	
	public boolean removeAll() {
		String sql = "DELETE FROM companydailyprice";
		return jdbcTemplate.update(sql) > 0;
	}
	
	
	
}
