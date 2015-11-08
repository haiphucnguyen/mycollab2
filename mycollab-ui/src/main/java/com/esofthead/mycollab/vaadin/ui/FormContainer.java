package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.viritin.layouts.MCssLayout;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
public class FormContainer extends VerticalLayout {
    public FormContainer() {
        this.addStyleName("form");
        this.setDefaultComponentAlignment(Alignment.TOP_CENTER);
    }

    public void addSection(String sectionName, ComponentContainer container) {
        this.addComponent(new MCssLayout(new Label(sectionName)).withStyleName("section").withWidth("100%"));
        this.addComponent(container);
    }
}
