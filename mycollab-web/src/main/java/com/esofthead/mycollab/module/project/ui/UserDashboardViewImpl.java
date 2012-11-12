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
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Component
public class UserDashboardViewImpl extends AbstractView implements
		UserDashboardView {
	private Melodion melodion;
	private NativeButton myFeedsBtn;
	private NativeButton myProjectsBtn;
	private NativeButton myTasksBtn;
	private NativeButton myDefectsBtn;

	@Override
	protected ComponentContainer initMainLayout() {
		VerticalLayout layout = new VerticalLayout();
		final HorizontalSplitPanel hLayout = new HorizontalSplitPanel();
		layout.addComponent(hLayout);
		layout.setExpandRatio(hLayout, 1);
		hLayout.setSplitPosition(200, Sizeable.UNITS_PIXELS);
		hLayout.setLocked(true);
		hLayout.setSizeFull();

		VerticalLayout lContainer = new VerticalLayout();
		melodion = new Melodion();

		Tab dashboardTab = melodion.addTab(new Label("Dashboard"));
		melodion.setSelected(dashboardTab);

		Tab mySpaceTab = melodion.addTab(new Label("My Home"));

		myFeedsBtn = new NativeButton("My Feeds");
		mySpaceTab.addButton(myFeedsBtn);

		myProjectsBtn = new NativeButton("My Projects",
				new Button.ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						MyProjectsViewImpl myProject = AppContext
								.getView(MyProjectsViewImpl.class);
						hLayout.setSecondComponent((com.vaadin.ui.Component) myProject
								.getCompContainer());
						myProject.doDefaultSearch();
					}
				});
		mySpaceTab.addButton(myProjectsBtn);

		myTasksBtn = new NativeButton("My Tasks", new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				MyTasksViewImpl myTasks = AppContext
						.getView(MyTasksViewImpl.class);
				hLayout.setSecondComponent((com.vaadin.ui.Component) myTasks
						.getCompContainer());
			}
		});
		mySpaceTab.addButton(myTasksBtn);

		myDefectsBtn = new NativeButton("My Defects",
				new Button.ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						MyDefectsViewImpl myDefects = AppContext
								.getView(MyDefectsViewImpl.class);
						hLayout.setSecondComponent((com.vaadin.ui.Component) myDefects
								.getCompContainer());

					}
				});
		mySpaceTab.addButton(myDefectsBtn);

		melodion.addTab(new Label("Calendar"));
		lContainer.addComponent(melodion);

		lContainer.setExpandRatio(melodion, 1);

		hLayout.setFirstComponent(lContainer);
		return hLayout;
	}

	@Override
	public void gotoMyProjectList() {
		melodion.setSelected(myProjectsBtn);

	}

}
