package com.esofthead.mycollab.module.project.ui;

import org.springframework.stereotype.Component;
import org.vaadin.melodion.Melodion;
import org.vaadin.melodion.Melodion.Tab;

import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.vaadin.event.LayoutEvents;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;

@SuppressWarnings("serial")
@Component
public class ProjectDashboardViewImpl extends AbstractView implements
		ProjectDashboardView {

	@Override
	protected ComponentContainer initMainLayout() {
		HorizontalLayout hoLayout = new HorizontalLayout();
		Melodion melodion = new Melodion();
		melodion.setHeight("100%");
		melodion.addListener(new LayoutEvents.LayoutClickListener() {

			@Override
			public void layoutClick(LayoutClickEvent event) {
				System.out.println("aaa" + event.getChildComponent());
			}
		});

		Tab myHome = melodion.addTab(new Label("My Home"));
		myHome.addButton(new NativeButton("My Feeds"));
		myHome.addButton(new NativeButton("My Projects"));
		myHome.addButton(new NativeButton("My Tasks"));
		myHome.addButton(new NativeButton("My Bugs"));
		melodion.setSelected(myHome);

		hoLayout.addComponent(melodion);
		return hoLayout;
	}

}
