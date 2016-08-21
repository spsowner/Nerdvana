package com.sps.dao;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.sps.model.NerdvanaModel;

public class NerdvanaModelRowMapper <T extends NerdvanaModel> implements RowMapper<T> {
	private static final List<String> FIELDS = new ArrayList<>(Arrays.asList("id", "name"));
	private static final Class<?>[] FIELD_TYPES = {Integer.class, String.class};
	private Class<T> cls;
	
	public NerdvanaModelRowMapper(Class<T> cls) {
		this.cls = cls;
	}
	
	@Override
	public T mapRow(ResultSet rs, int rowNum) throws SQLException {
		try {
			Constructor<T> constructor = cls.getConstructor(FIELD_TYPES);
			return constructor.newInstance(
				rs.getInt(rs.findColumn(FIELDS.get(0))),
				rs.getString(rs.findColumn(FIELDS.get(1)))
			);
		}
		
		catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
	
	public List<String> getFields() {
		return FIELDS;
	}
}
