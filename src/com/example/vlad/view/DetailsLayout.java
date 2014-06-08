package com.example.vlad.view;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.converter.StringToIntegerConverter;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class DetailsLayout extends FormLayout {
	private static final long serialVersionUID = 1L;
	@PropertyId("name")
	public
	TextField name;
	@PropertyId("surname")
	public
	TextField surname;
	@PropertyId("login")
	public
	TextField login;
	@PropertyId("pass")
	public
	PasswordField pass;
	@PropertyId("age")
	public
	TextField age;
	@PropertyId("email")
	public
	TextField email;
	
	public DetailsLayout() {
		init();
		add();
		setCaption("Credentials form");
		setDescription("Here you can enter new user credentials");
		
	}
	
	void init() {
		name = new TextField();
		surname = new TextField();
		login = new TextField();
		pass = new PasswordField();
		age = new TextField();
		email = new TextField();
		name.setInputPrompt("Name");
		surname.setInputPrompt("Surname");
		login.setInputPrompt("Login");
		pass.setInputPrompt("Password");
		age.setInputPrompt("Age");
		age.setConverter(new StringToIntegerConverter());
		email.setInputPrompt("e-mail");
		
		age.setNullRepresentation("");
		
	}
	void add() {
		addComponent(name);
		addComponent(surname);
		addComponent(login);
		addComponent(pass);
		addComponent(age);
		addComponent(email);
	}
}
