package com.example.vlad.view;

import com.example.vlad.model.User;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class DetailsView extends VerticalLayout {
	private static final long serialVersionUID = 1L;
	public final FieldGroup fieldGroup;
	public DetailsView() {
		fieldGroup = new BeanFieldGroup<User>(User.class);
		fieldGroup.setItemDataSource(new BeanItem<User>(new User()));
        for (Object propertyId : fieldGroup.getUnboundPropertyIds()) {
        	System.out.println(propertyId);
            addComponent(fieldGroup.buildAndBind(propertyId));
        }
        fieldGroup.setItemDataSource(new BeanItem<User>(new User()));
	}
}
