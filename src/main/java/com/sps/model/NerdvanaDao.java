package com.sps.model;

import java.util.Arrays;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.common.base.Preconditions;
import com.sps.ConfigDependent;
import com.sps.util.LogUtil;

public class NerdvanaDao implements ConfigDependent, InitializingBean {
	private static final String TABLE_NAME = Metadata.class.getSimpleName();
	private static final Logger LOG = LoggerFactory.getLogger(NerdvanaDao.class);
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private DataSource dataSource;
	
	@Override
	public void afterPropertiesSet() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void validate() {
		Preconditions.checkArgument(isDbAlive());
	}
	
	public boolean userHasPaid(String packageDirName) {
		int exists = 1;
		String sql = new StringBuilder()
			.append("SELECT EXISTS(SELECT ? FROM ").append(TABLE_NAME).append(" ")
			.append("WHERE package_dir_name = ? ")
			.append("AND transaction_date > timestampadd(day, -?, now())")
			.append(")")
			.toString();
		
		Object[] params = new Object[] { exists, packageDirName };
		log(sql, params);
		return exists == jdbcTemplate.queryForObject(sql, Integer.class, params);
	}
	
	public int save(Metadata model) {
		String sql = new StringBuilder("INSERT INTO ").append(TABLE_NAME).append(" ")
			.append("(s) ")
			.append("VALUES (?)")
			.toString();
		
		Object[] params = new Object[] {
			model.getS()
		};
		log(sql, params);
		return jdbcTemplate.update(sql, params);
    }
	
	private boolean isDbAlive() {
		return null != jdbcTemplate.queryForObject("SELECT version()", String.class);
	}
	
	private void log(String sql, Object[] params) {
		LogUtil.debug(LOG, sql + "; " + Arrays.toString(params));
	}
}
