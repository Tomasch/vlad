package com.example.vlad;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.example.vlad.model.SessionHandler;
import com.example.vlad.model.User;
import com.example.vlad.test.PlainJavaObj;
import com.example.vlad.view.DetailsLayout;
import com.example.vlad.view.ListLayout;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroupFieldFactory;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.example.vlad.view.*;

@SuppressWarnings("serial")
@Theme("vlad")
public class VladUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = VladUI.class, widgetset = "com.example.vlad.widgetset.VladWidgetset")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final HorizontalLayout mainLayout = new HorizontalLayout();
		//DetailsLayout detailsLayout = new DetailsLayout();
		final ListLayout listLayout = new ListLayout();
		final DetailsView detailsView = new DetailsView();
		Button addBtn = new Button("Add");
		Button updBtn = new Button("Update");
		Button delBtn = new Button("Delete");
		setContent(mainLayout);
		//setContent(detailsView);
		
		initList(listLayout);
		
		//PropertysetItem item = getPropertyItem();
		mainLayout.addComponent(detailsView);
		mainLayout.addComponent(listLayout);
				
		mainLayout.setMargin(true);
		HorizontalLayout panelLaout = new HorizontalLayout();
		detailsView.addComponent(panelLaout);
		
		panelLaout.addComponent(addBtn);
		panelLaout.addComponent(updBtn);
		panelLaout.addComponent(delBtn);
		
		addBtn.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				User user = new User(
						(String)detailsView.fieldGroup.getField("name").getValue(),
						(String)detailsView.fieldGroup.getField("surname").getValue(),
						(String)detailsView.fieldGroup.getField("login").getValue(),
						(String)detailsView.fieldGroup.getField("pass").getValue(),
						Integer.parseInt((String)detailsView.fieldGroup.getField("age").getValue()),
						(String)detailsView.fieldGroup.getField("email").getValue()
						);
				listLayout.container.addBean(user);
				System.out.println(user);
				try {
					detailsView.fieldGroup.commit();
				} catch (CommitException e) {
					e.printStackTrace();
				}
			}
		});
		
		updBtn.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					detailsView.fieldGroup.commit();
				} catch (CommitException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		delBtn.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				Long id = (Long)listLayout.getValue();

				listLayout.container.removeItem(id);
				detailsView.fieldGroup.setItemDataSource(new BeanItem<User>(new User()));
			}
		});
				
		listLayout.addValueChangeListener(new Property.ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				BeanItem<User> item = (BeanItem)listLayout.getItem(listLayout.getValue());
				if(item != null) {
					detailsView.fieldGroup.setItemDataSource(item);
				}
				else {
				}
			}
		});
	}

	private void initList(ListLayout listLayout) {
		
	}

	private PropertysetItem getPropertyItem() {
		PropertysetItem item = new PropertysetItem();
		item.addItemProperty("name", new ObjectProperty<String>(""));
		item.addItemProperty("surname", new ObjectProperty<String>(""));
		item.addItemProperty("login", new ObjectProperty<String>(""));
		item.addItemProperty("pass", new ObjectProperty<String>(""));
		item.addItemProperty("age", new ObjectProperty<Integer>(0));
		item.addItemProperty("email", new ObjectProperty<String>(""));
		return item;
	}
}