package com.sps.model;

public class Metadata {
	private String s;
	
	public Metadata(String s) {
		this.s = s;
	}
	
	public String getS() {
		return s;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
			.append("s [").append(s).append("], ")
			.toString();
	}
}
