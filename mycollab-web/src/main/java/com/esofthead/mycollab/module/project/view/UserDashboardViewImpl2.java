package com.esofthead.mycollab.module.project.view;

import java.util.List;

import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.localization.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.view.user.ActivityStreamComponent;
import com.esofthead.mycollab.module.project.view.user.MyProjectListComponent;
import com.esofthead.mycollab.module.project.view.user.TaskStatusComponent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

@ViewComponent
public class UserDashboardViewImpl2 extends AbstractView implements
		UserDashboardView {
	private static final long serialVersionUID = 1L;

	private MyProjectListComponent myProjectListComponent;

	private ActivityStreamComponent activityStreamComponent;

	private TaskStatusComponent taskStatusComponent;

	public UserDashboardViewImpl2() {
		this.setSpacing(true);
		this.setMargin(true);

		HorizontalLayout header = new HorizontalLayout();
		header.setWidth("100%");
		header.setMargin(false, false, false, true);
		header.addStyleName("project-feed-header");

		Label searchtitle = new Label(
				LocalizationHelper
						.getMessage(ProjectCommonI18nEnum.DASHBOARD_TITLE));
		searchtitle.setStyleName(Reindeer.LABEL_H1);
		header.addComponent(searchtitle);
		header.setComponentAlignment(searchtitle, Alignment.MIDDLE_LEFT);

		Button createProjectBtn = new Button(
				LocalizationHelper
						.getMessage(ProjectCommonI18nEnum.NEW_PROJECT_ACTION),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(Button.ClickEvent event) {
						//TODO: show add project window
						

					}
				});
		createProjectBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));
		createProjectBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		header.addComponent(createProjectBtn);
		header.setComponentAlignment(createProjectBtn, Alignment.MIDDLE_RIGHT);

		this.addComponent(header);

		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);
		layout.setMargin(true);

		VerticalLayout leftPanel = new VerticalLayout();
		myProjectListComponent = new MyProjectListComponent();
		taskStatusComponent = new TaskStatusComponent();
		leftPanel.addComponent(myProjectListComponent);
		leftPanel.addComponent(new LazyLoadWrapper(taskStatusComponent));

		VerticalLayout rightPanel = new VerticalLayout();
		activityStreamComponent = new ActivityStreamComponent();

		rightPanel.addComponent(activityStreamComponent);

		layout.addComponent(leftPanel);
		layout.addComponent(rightPanel);

		this.addComponent(layout);
	}

	@Override
	public void display() {
		ProjectService prjService = AppContext
				.getSpringBean(ProjectService.class);
		List<Integer> prjKeys = prjService.getUserProjectKeys(AppContext
				.getUsername());
		if (prjKeys != null && !prjKeys.isEmpty()) {
			activityStreamComponent.showFeeds(prjKeys);
			myProjectListComponent.showProjects(prjKeys);
		}

		taskStatusComponent.showProjectTasksByStatus();

	}
}
