package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class BugListWidget extends Depot {
	private static final long serialVersionUID = 1L;

	private IBugReportDisplayContainer bugReportDisplayContainer;
	private BugSearchCriteria bugSearchCriteria;
	private BugTableDisplay tableItem;

	public BugListWidget(String title, BugSearchCriteria bugSearchCriteria,
			IBugReportDisplayContainer bugReportDisplayContainer) {
		super(title, new VerticalLayout());

		this.bugReportDisplayContainer = bugReportDisplayContainer;

		initUI();
		setSearchCriteria(bugSearchCriteria);
	}

	private void initUI() {
		VerticalLayout contentLayout = (VerticalLayout) this.bodyContent;
		contentLayout.setSpacing(true);
		contentLayout.setWidth("100%");
		
		Button backToBugReportsBtn = new Button("Back to component dashboard",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						bugReportDisplayContainer.displayBugReports();
					}
				});
		contentLayout.addComponent(backToBugReportsBtn);
		backToBugReportsBtn.setStyleName(UIConstants.THEME_BLUE_LINK);

		tableItem = new BugTableDisplay(new String[] { "selected", "summary",
				"assignuserFullName", "severity", "resolution", "duedate" },
				new String[] { "", "Summary", "Assigned User", "Severity",
						"Resolution", "Due Date" });
		tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(TableClickEvent event) {
						SimpleBug bug = (SimpleBug) event.getData();
						if ("summary".equals(event.getFieldName())) {
							EventBus.getInstance().fireEvent(
									new BugEvent.GotoRead(BugListWidget.this,
											bug.getId()));
						}
					}
				});

		tableItem.setWidth("100%");
		contentLayout.addComponent(tableItem);
	}

	private void setSearchCriteria(BugSearchCriteria searchCriteria) {
		this.bugSearchCriteria = searchCriteria;
		tableItem.setSearchCriteria(bugSearchCriteria);
	}
}
