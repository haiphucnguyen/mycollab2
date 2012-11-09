package com.esofthead.mycollab.module.project.ui;

import org.springframework.stereotype.Component;
import org.vaadin.melodion.Melodion;
import org.vaadin.melodion.Melodion.Tab;

import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Panel;

@SuppressWarnings("serial")
@Component
public class UserDashboardViewImpl extends AbstractView implements
		UserDashboardView {

	@Override
	protected ComponentContainer initMainLayout() {
		final HorizontalSplitPanel hLayout = new HorizontalSplitPanel();
		hLayout.setSplitPosition(200, Sizeable.UNITS_PIXELS);
		hLayout.setSizeFull();

		Panel lContainer = new Panel();
		Melodion melodion = new Melodion();
		melodion.setSizeUndefined();

		Tab dashboardTab = melodion.addTab(new Label("Dashboard"));
		melodion.setSelected(dashboardTab);

		Tab mySpaceTab = melodion.addTab(new Label("My Home"));
		mySpaceTab.addButton(new NativeButton("My Feeds"));
		mySpaceTab.addButton(new NativeButton("My Projects",
				new Button.ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						MyProjectsViewImpl myProject = AppContext
								.getView(MyProjectsViewImpl.class);
						hLayout.setSecondComponent((com.vaadin.ui.Component) myProject
								.getCompContainer());
					}
				}));

		mySpaceTab.addButton(new NativeButton("My Tasks",
				new Button.ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						MyTasksViewImpl myTasks = AppContext
								.getView(MyTasksViewImpl.class);
						hLayout.setSecondComponent((com.vaadin.ui.Component) myTasks
								.getCompContainer());
					}
				}));
		mySpaceTab.addButton(new NativeButton("My Defects"));

		melodion.addTab(new Label("Calendar"));
		melodion.setSizeFull();
		lContainer.addComponent(melodion);
		lContainer.setHeight(null);

		hLayout.setFirstComponent(lContainer);
		return hLayout;
	}

}
