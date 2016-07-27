package com.sps.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.sps.ConfigDependent;
import com.sps.util.LogUtil;

public class NerdvanaDao implements ConfigDependent, InitializingBean {
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
	
	public List<Question> getQuestions() {
		return get(Question.class, "id", "name");
	}
	
	public List<DataType> getDataTypes() {
		return get(DataType.class, "id", "name", "description");
	}
	
	public int[] save(List<AnswerDefinition> models) {
		String sql = new StringBuilder("INSERT INTO ").append(AnswerDefinition.class.getSimpleName()).append(" ")
			.append("(question_id, name, cost, data_type_id) ")
			.append("VALUES (?, ?, ?, ?)")
			.toString();
		
		//log(sql, params);

		return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
	        @Override
	        public void setValues(PreparedStatement ps, int i) throws SQLException {
	        	AnswerDefinition model = models.get(i);
	            ps.setInt(1, model.getQuestionId());
	            ps.setString(2, model.getName());
	            ps.setFloat(3, model.getCost());
	            ps.setInt(4, model.getDataTypeId());
	        }

	        @Override
	        public int getBatchSize() {
	            return models.size();
	        }
	    });
    }

	private <T> List<T> get(Class<T> cls, String... fields) {
		String sql = new StringBuilder()
			.append("SELECT ").append(Joiner.on(", ").join(fields))
			.append(" FROM ").append(cls.getSimpleName())
			.toString();
		return jdbcTemplate.queryForList(sql, cls);
	}
	
	private boolean isDbAlive() {
		return null != jdbcTemplate.queryForObject("SELECT version()", String.class);
	}
	
	@SuppressWarnings("unused")
	private void log(String sql, Object[] params) {
		LogUtil.debug(LOG, sql + "; " + Arrays.toString(params));
	}
}
