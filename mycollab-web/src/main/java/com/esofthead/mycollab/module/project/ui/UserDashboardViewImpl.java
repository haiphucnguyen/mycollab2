package com.esofthead.mycollab.module.project.ui;

import org.springframework.stereotype.Component;
import org.vaadin.melodion.Melodion;
import org.vaadin.melodion.Melodion.Tab;

import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;

@SuppressWarnings("serial")
@Component
public class UserDashboardViewImpl extends AbstractView implements
		UserDashboardView {

	@Override
	protected ComponentContainer initMainLayout() {
		HorizontalLayout hLayout = new HorizontalLayout();
		Melodion melodion = new Melodion();
		melodion.setSizeFull();
		melodion.setWidth("200px");

		Tab dashboardTab = melodion.addTab(new Label("Dashboard"));
		melodion.setSelected(dashboardTab);

		Tab mySpaceTab = melodion.addTab(new Label("My Home"));
		mySpaceTab.addButton(new NativeButton("My Feeds"));
		mySpaceTab.addButton(new NativeButton("My Projects", new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		}));
		
		mySpaceTab.addButton(new NativeButton("My Tasks"));
		mySpaceTab.addButton(new NativeButton("My Defects"));

		melodion.addTab(new Label("Calendar"));

		hLayout.addComponent(melodion);
		return hLayout;
	}

}
