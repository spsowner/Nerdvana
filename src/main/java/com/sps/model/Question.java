package com.sps.model;

public class Question {
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("name [").append(name).append("]")
			.toString();
	}
}