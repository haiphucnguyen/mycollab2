package com.esofthead.mycollab.module.project.view.user;

import java.util.List;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

@ViewComponent
public class MyFeedsViewImpl extends AbstractView implements MyFeedsView {

	private static final long serialVersionUID = 1L;

	private ActivityStreamComponent activityStreamComponent;

	private MessageListComponent messageListComponent;

	private TaskStatusComponent taskStatusComponent;

	public MyFeedsViewImpl() {
		this.setSpacing(true);
		this.setMargin(true);

		HorizontalLayout header = new HorizontalLayout();
		header.setWidth("100%");
		header.setMargin(false, false, false, true);
		header.addStyleName("project-feed-header");

		Label searchtitle = new Label("Projects Information");
		searchtitle.setStyleName(Reindeer.LABEL_H1);
		header.addComponent(searchtitle);
		header.setComponentAlignment(searchtitle, Alignment.MIDDLE_LEFT);

		Button createProjectBtn = new Button("Create",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(Button.ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new ProjectEvent.GotoAdd(MyFeedsViewImpl.this,
										null));

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

		messageListComponent = new MessageListComponent();
		activityStreamComponent = new ActivityStreamComponent();

		VerticalLayout leftPanel = new VerticalLayout();
		leftPanel.addComponent(new LazyLoadWrapper(messageListComponent));
		leftPanel.addComponent(new LazyLoadWrapper(
				activityStreamComponent));

		taskStatusComponent = new TaskStatusComponent();
		layout.addComponent(leftPanel);
		layout.addComponent(new LazyLoadWrapper(taskStatusComponent));

		this.addComponent(layout);
	}

	@Override
	public void displayFeeds() {
		ProjectService prjService = AppContext
				.getSpringBean(ProjectService.class);
		List<Integer> prjKeys = prjService.getUserProjectKeys(AppContext
				.getUsername());
		if (prjKeys != null && !prjKeys.isEmpty()) {
			messageListComponent.showLatestMessages(prjKeys);
			activityStreamComponent.showFeeds(prjKeys);
		}

		taskStatusComponent.showProjectTasksByStatus();
	}
}
