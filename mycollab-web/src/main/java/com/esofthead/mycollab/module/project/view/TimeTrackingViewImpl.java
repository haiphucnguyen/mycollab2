package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.view.parameters.BugScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.module.project.view.parameters.TaskScreenData;
import com.esofthead.mycollab.module.project.view.time.TimeTrackingTableDisplay;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;

@ViewComponent
public class TimeTrackingViewImpl extends AbstractView implements
		TimeTrackingView {
	private static final long serialVersionUID = 1L;

	private TimeTrackingTableDisplay tableItem;

	public TimeTrackingViewImpl() {
		this.setSpacing(true);
		this.setMargin(false, false, true, false);
		this.setWidth("1130px");
		final CssLayout headerWrapper = new CssLayout();
		headerWrapper.setWidth("100%");
		headerWrapper.setStyleName("projectfeed-hdr-wrapper");

		Embedded timeIcon = new Embedded();
		timeIcon.setSource(MyCollabResource
				.newResource("icons/24/time_tracking.png"));
		headerWrapper.addComponent(timeIcon);

		final Label layoutHeader = new Label("Your Time Reporting");
		layoutHeader.addStyleName("h2");
		headerWrapper.addComponent(layoutHeader);
		this.addComponent(headerWrapper);

		final Button backBtn = new Button("Back to Work Board");
		backBtn.addListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new ShellEvent.GotoProjectModule(
								TimeTrackingViewImpl.this, null));

			}
		});

		backBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
		this.addComponent(backBtn);

		HorizontalLayout dateSelectionLayout = new HorizontalLayout();
		dateSelectionLayout.setSpacing(true);
		this.addComponent(dateSelectionLayout);

		dateSelectionLayout.addComponent(new Label("From:  "));

		PopupDateField fromDateField = new PopupDateField();
		fromDateField.setResolution(PopupDateField.RESOLUTION_DAY);
		dateSelectionLayout.addComponent(fromDateField);

		dateSelectionLayout.addComponent(new Label("  To:  "));
		PopupDateField toDateField = new PopupDateField();
		toDateField.setResolution(PopupDateField.RESOLUTION_DAY);
		dateSelectionLayout.addComponent(toDateField);

		Button queryBtn = new Button("Submit", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}
		});
		queryBtn.setStyleName(UIConstants.THEME_BLUE_LINK);

		dateSelectionLayout.addComponent(queryBtn);

		this.tableItem = new TimeTrackingTableDisplay(new String[] {
				"logUserFullName", "summary", "projectName", "createdtime",
				"logvalue" }, new String[] { "User", "Summary", "Project",
				"Created Time", "Hours" });
		this.tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(final TableClickEvent event) {
						final SimpleItemTimeLogging itemLogging = (SimpleItemTimeLogging) event
								.getData();
						if ("summary".equals(event.getFieldName())) {
							int typeId = itemLogging.getTypeid();
							int projectId = itemLogging.getProjectid();

							if (MonitorTypeConstants.PRJ_BUG.equals(itemLogging
									.getType())) {
								PageActionChain chain = new PageActionChain(
										new ProjectScreenData.Goto(projectId),
										new BugScreenData.Read(typeId));
								EventBus.getInstance().fireEvent(
										new ProjectEvent.GotoMyProject(this,
												chain));
							} else if (MonitorTypeConstants.PRJ_TASK
									.equals(itemLogging.getType())) {
								PageActionChain chain = new PageActionChain(
										new ProjectScreenData.Goto(projectId),
										new TaskScreenData.Read(typeId));
								EventBus.getInstance().fireEvent(
										new ProjectEvent.GotoMyProject(this,
												chain));
							}
						} else if ("projectName".equals(event.getFieldName())) {
							PageActionChain chain = new PageActionChain(
									new ProjectScreenData.Goto(itemLogging
											.getProjectid()));
							EventBus.getInstance()
									.fireEvent(
											new ProjectEvent.GotoMyProject(
													this, chain));
						}
					}
				});
		this.addComponent(tableItem);
	}

	@Override
	public void display() {
		searchTimeReporting();
	}

	private void searchTimeReporting() {
		ItemTimeLoggingSearchCriteria searchCriteria = new ItemTimeLoggingSearchCriteria();
		searchCriteria.setLogUsers(new SetSearchField<String>(SearchField.AND,
				new String[] { AppContext.getUsername() }));
		this.tableItem.setSearchCriteria(searchCriteria);
	}

}
