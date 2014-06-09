package com.example.vlad.model;

import java.io.Serializable;
import java.util.Random;

import javax.persistence.*;

import com.vaadin.data.fieldgroup.FieldGroup;
@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	Long id;
	String name;
	String surname;
	String login;
	String pass;
	int age;
	String email;
	
	public User() {
	}

	public User(String name, String surname, String login, String pass,
			int age, String email) {
		this.id = (new Random()).nextLong();
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.pass = pass;
		this.age = age;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getPass() {
		return "***********";
	}
	
}
