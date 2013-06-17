package com.esofthead.mycollab.module.project.view;

import java.util.List;

import com.esofthead.mycollab.module.project.localization.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.view.user.ActivityStreamComponent;
import com.esofthead.mycollab.module.project.view.user.MyProjectListComponent;
import com.esofthead.mycollab.module.project.view.user.TaskStatusComponent;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

@ViewComponent
public class UserDashboardViewImpl extends AbstractView implements
		UserDashboardView {
	private static final long serialVersionUID = 1L;

	private final MyProjectListComponent myProjectListComponent;

	private final ActivityStreamComponent activityStreamComponent;

	private final TaskStatusComponent taskStatusComponent;

	public UserDashboardViewImpl() {
		this.setSpacing(true);
		this.setMargin(true);

		final HorizontalLayout header = new HorizontalLayout();
		header.setWidth("100%");
		header.setMargin(false, false, false, true);
		header.addStyleName("project-feed-header");

		final Label searchtitle = new Label(
				LocalizationHelper
						.getMessage(ProjectCommonI18nEnum.DASHBOARD_TITLE));
		searchtitle.setStyleName(Reindeer.LABEL_H1);
		header.addComponent(searchtitle);
		header.setComponentAlignment(searchtitle, Alignment.MIDDLE_LEFT);

		final Button createProjectBtn = new Button(
				LocalizationHelper
						.getMessage(ProjectCommonI18nEnum.NEW_PROJECT_ACTION),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final Button.ClickEvent event) {
						final ProjectAddWindow projectNewWindow = new ProjectAddWindow();
						UserDashboardViewImpl.this.getWindow().addWindow(
								projectNewWindow);
					}
				});
		createProjectBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));
		createProjectBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		header.addComponent(createProjectBtn);
		header.setComponentAlignment(createProjectBtn, Alignment.MIDDLE_RIGHT);

		this.addComponent(header);

		final HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);
		layout.setMargin(true);

		final VerticalLayout leftPanel = new VerticalLayout();
		this.myProjectListComponent = new MyProjectListComponent();
		this.taskStatusComponent = new TaskStatusComponent();
		leftPanel.addComponent(this.myProjectListComponent);
		leftPanel.addComponent(new LazyLoadWrapper(this.taskStatusComponent));

		final VerticalLayout rightPanel = new VerticalLayout();
		this.activityStreamComponent = new ActivityStreamComponent();

		rightPanel.addComponent(this.activityStreamComponent);

		layout.addComponent(leftPanel);
		layout.addComponent(rightPanel);

		this.addComponent(layout);
	}

	@Override
	public void display() {
		final ProjectService prjService = AppContext
				.getSpringBean(ProjectService.class);
		final List<Integer> prjKeys = prjService.getUserProjectKeys(AppContext
				.getUsername());
		if (prjKeys != null && !prjKeys.isEmpty()) {
			this.activityStreamComponent.showFeeds(prjKeys);
			this.myProjectListComponent.showProjects(prjKeys);
		}

		this.taskStatusComponent.showProjectTasksByStatus();

	}
}
