package com.sps.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User {
	
	private @Id @GeneratedValue Long id;
	private String firstName;
	private String lastName;
	private String title;
	
	private User() {}
	
	public User(String firstName, String lastName, String title) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
	}

}
