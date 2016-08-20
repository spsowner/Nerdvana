package com.sps.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import com.google.gson.Gson;
import com.sps.ConfigDependent;
import com.sps.model.AnswerDefinition;
import com.sps.model.DataType;
import com.sps.model.NerdvanaModel;
import com.sps.model.Question;
import com.sps.util.LogUtil;

public class NerdvanaDao implements ConfigDependent, InitializingBean {
	private static final NerdvanaModelRowMapper<Question> QUESTION_MAPPER = new NerdvanaModelRowMapper<>(Question.class); 
	private static final NerdvanaModelRowMapper<DataType> DATA_TYPE_MAPPER = new NerdvanaModelRowMapper<DataType>(DataType.class) {
		@Override
		public DataType mapRow(ResultSet rs, int rowNum) throws SQLException {
			DataType dt = super.mapRow(rs, rowNum);
			dt.setDescription(rs.getString(rs.findColumn("description")));
			return dt;
		}
		
		@Override
		public List<String> getFields() {
			List<String> fields = new ArrayList<>(super.getFields());
			fields.add("description");
			return fields;
		}
	};
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
	
	public String getQuestionsAsJson() {
		return new Gson().toJson(getQuestions());
	}
	
	public String getDataTypesAsJson() {
		return new Gson().toJson(getDataTypes());
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

	private <T extends NerdvanaModel> List<T> get(Class<T> cls, NerdvanaModelRowMapper<T> rowMapper) {
		String sql = new StringBuilder()
			.append("SELECT ").append(Joiner.on(", ").join(rowMapper.getFields()))
			.append(" FROM ").append(cls.getSimpleName())
			.toString();
		return jdbcTemplate.query(sql, rowMapper);
	}
	
	private boolean isDbAlive() {
		return null != jdbcTemplate.queryForObject("SELECT version()", String.class);
	}
	
	@SuppressWarnings("unused")
	private void log(String sql, Object[] params) {
		LogUtil.debug(LOG, sql + "; " + Arrays.toString(params));
	}
	
	// package-friendly for testing
	List<Question> getQuestions() {
		return get(Question.class, QUESTION_MAPPER);
	}
	
	// package-friendly for testing
	List<DataType> getDataTypes() {
		return get(DataType.class, DATA_TYPE_MAPPER);
	}
}
