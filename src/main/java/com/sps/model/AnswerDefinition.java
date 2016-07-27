package com.sps.model;

public class AnswerDefinition {
	private int questionId;
	private String name;
	private float cost;
	private int dataTypeId;

	public int getQuestionId() {
		return questionId;
	}
	
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public float getCost() {
		return cost;
	}
	
	public void setCost(float cost) {
		this.cost = cost;
	}
	
	public int getDataTypeId() {
		return dataTypeId;
	}
	
	public void setDataTypeId(int dataTypeId) {
		this.dataTypeId = dataTypeId;
	}
}
