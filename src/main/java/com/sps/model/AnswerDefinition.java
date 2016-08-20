package com.sps.model;

public class AnswerDefinition extends NerdvanaModel {
	private int questionId;
	private float cost;
	private int dataTypeId;
	
	public AnswerDefinition() {}
	public AnswerDefinition(Integer id, String name) {
		super(id, name);
	}

	public int getQuestionId() {
		return questionId;
	}
	
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
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
