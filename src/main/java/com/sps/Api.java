package com.sps;

import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sps.model.AnswerDefinition;
import com.sps.model.NerdvanaDao;

@Controller
@RequestMapping(value = "api/v1")
//@PreAuthorize("hasRole('ROLE_USER')")
@ResponseBody
public class Api {
	@Autowired
	private NerdvanaDao dao;
	
	@RequestMapping(value = "/questions", method = RequestMethod.GET)
	public String questions() {
		return new Gson().toJson(dao.getQuestions());
	}
	
	@RequestMapping(value = "/data-types", method = RequestMethod.GET)
	public String dataTypes() {
		return new Gson().toJson(dao.getDataTypes());
	}
	
	@RequestMapping(value = "/answer-definitions", method = RequestMethod.POST)
	public String answerDefinitions(String jsonArray) {
		Type listType = new TypeToken<List<AnswerDefinition>>(){}.getType();
		List<AnswerDefinition> models = new Gson().fromJson(jsonArray, listType);
		int[] results = dao.save(models);
		String result = results.length == models.size() ? "success" : "failure";
		return "{ msg: \"" + result + "\" }";
	}
}
