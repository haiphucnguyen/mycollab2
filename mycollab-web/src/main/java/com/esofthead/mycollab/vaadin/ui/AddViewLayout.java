package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class AddViewLayout extends CustomLayout {

    private static final long serialVersionUID = 1L;
    
    private final HorizontalLayout header;
    
    private Label titleLbl;
    Embedded icon;

    public AddViewLayout(String title, ThemeResource resource) {
        super("addView");
        
        this.header = new HorizontalLayout();
        this.header.setStyleName("addViewHeader");
        this.header.addStyleName("create item header");
        
        icon= new Embedded();
        icon.setSource(resource);
        this.header.addComponent(icon);
        titleLbl = new Label(title);
        titleLbl.setWidth("100%");
        titleLbl.setStyleName("headerName");
        this.header.addComponent(titleLbl);
        this.addComponent(header, "addViewHeader");
    }
    
    public void setTitle(String title) {
        titleLbl.setValue(title);
    }

    public void addTopControls(ComponentContainer topControls) {
        this.addComponent(topControls, "addViewTopControls");
    }

    public void addBottomControls(ComponentContainer bottomControls) {
        this.addComponent(bottomControls, "addViewBottomControls");
    }

    public void addBody(ComponentContainer body) {
        this.addComponent(body, "addViewBody");
    }
}
