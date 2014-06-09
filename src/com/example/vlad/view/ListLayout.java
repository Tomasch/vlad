package com.example.vlad.view;

import com.example.vlad.model.User;
import com.google.gwt.dev.util.collect.Sets;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Table;

public class ListLayout extends Table {
	private static final long serialVersionUID = 1L;
	public BeanContainer<Integer, User> container;

	public ListLayout() {
		/*addContainerProperty("id", Long.class, "");
		addContainerProperty("name", String.class, "");
		addContainerProperty("surname", String.class, "");
		addContainerProperty("login", String.class, "");
		addContainerProperty("pass", String.class, "");
		addContainerProperty("age", Integer.class, "");
		addContainerProperty("email", String.class, "");*/
		init();
	}
	
	void init() {
		setSelectable(true);
		setImmediate(true);
		container = new BeanContainer<Integer, User>(User.class);
		container.setBeanIdProperty("id");
		container.addBean(new User("Tom", "Ja", "tom", "asz", 22, "toom@op.pl"));
		container.addBean(new User("Tuom", "Jaj", "tjom", "jasz", 282, "j@op.pl"));
		setContainerDataSource(container);
	}
	
}
