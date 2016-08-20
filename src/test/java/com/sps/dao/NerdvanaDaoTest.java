package com.sps.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sps.dao.NerdvanaDao;
import com.sps.model.DataType;
import com.sps.model.Question;
import com.sps.test.SpringTestContext;

public class NerdvanaDaoTest extends SpringTestContext {
	@Autowired
	private NerdvanaDao dao;

	@Test
	public void testGetQuestions() {
		List<Question> questions = dao.getQuestions();
		assertEquals(1, questions.size());
		assertEquals("What projects should we do this year?", questions.get(0).getName());
	}
	
	@Test
	public void testGetDataTypes() {
		List<DataType> dataTypes = dao.getDataTypes();
		assertEquals(5, dataTypes.size());
		DataType dt = dataTypes.get(2);
		assertEquals("rank", dt.getName());
		assertEquals("Select Low, Medium, or High.", dt.getDescription());
	}
	
	@Test
	public void testGetQuestionsAsJson() {
		assertEquals(EXPECTED_QUESTIONS, dao.getQuestionsAsJson());
	}
	
	@Test
	public void testGetDataTypesAsJson() {
		assertEquals(EXPECTED_DATA_TYPES, dao.getDataTypesAsJson());
	}
	
	private static final String EXPECTED_QUESTIONS = "[{\"id\":1,\"name\":\"What projects should we do this year?\"}]";
	private static final String EXPECTED_DATA_TYPES = "[" +
		"{\"description\":\"What does this date tell you?\",\"id\":1,\"name\":\"date\"}," +
		"{\"description\":\"What does this number tell you?\",\"id\":2,\"name\":\"number\"}," +
		"{\"description\":\"Select Low, Medium, or High.\",\"id\":3,\"name\":\"rank\"}," +
		"{\"description\":\"Select Small, Medium, or Large.\",\"id\":4,\"name\":\"size\"}," +
		"{\"description\":\"Describe the text an answerer would provide.\",\"id\":5,\"name\":\"text\"}" +
	"]";
}
