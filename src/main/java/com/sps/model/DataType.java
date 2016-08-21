package com.sps.model;

public class DataType extends NerdvanaModel {
	private String description;
	
	public DataType() {}
	public DataType(Integer id, String name) {
		super(id, name);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("id [").append(getId()).append("], ")
			.append("name [").append(getName()).append("], ")
			.append("description [").append(description).append("]")
			.toString();
	}
}
