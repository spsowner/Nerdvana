package com.sps.model;

public abstract class NerdvanaModel {
	private int id;
	private String name;
	
	public NerdvanaModel() {}
	public NerdvanaModel(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
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
			.append("id [").append(id).append("], ")
			.append("name [").append(name).append("]")
			.toString();
	}
}
