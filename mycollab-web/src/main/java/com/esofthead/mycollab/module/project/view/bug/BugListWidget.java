package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.localization.BugI18nEnum;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;

public class BugListWidget extends Depot {
	private static final long serialVersionUID = 1L;

	private BugSearchCriteria bugSearchCriteria;
	private BugTableDisplay tableItem;

	public BugListWidget(final String title, final String backBtnLabel,
			final BugSearchCriteria bugSearchCriteria,
			final IBugReportDisplayContainer bugReportDisplayContainer) {
		super(title, new VerticalLayout());

		final VerticalLayout contentLayout = (VerticalLayout) this.bodyContent;
		contentLayout.setSpacing(true);
		contentLayout.setWidth("100%");

		final Button backToBugReportsBtn = new Button(backBtnLabel,
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						bugReportDisplayContainer.displayBugReports();
					}
				});
		// contentLayout.addComponent(backToBugReportsBtn);
		final VerticalLayout backBtnWrapper = new VerticalLayout();
		backBtnWrapper.setMargin(false, false, true, false);

		backToBugReportsBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		backBtnWrapper.addComponent(backToBugReportsBtn);

		this.addComponentAsFirst(backBtnWrapper);

		this.tableItem = new BugTableDisplay(
				new String[] { "id", "summary", "assignuserFullName",
						"severity", "resolution", "duedate" },
				new String[] {
						"",
						LocalizationHelper
								.getMessage(BugI18nEnum.TABLE_SUMMARY_HEADER),
						LocalizationHelper
								.getMessage(BugI18nEnum.TABLE_ASSIGN_USER_HEADER),
						LocalizationHelper
								.getMessage(BugI18nEnum.TABLE_SEVERITY_HEADER),
						LocalizationHelper
								.getMessage(BugI18nEnum.TABLE_RESOLUTION_HEADER),
						LocalizationHelper
								.getMessage(BugI18nEnum.TABLE_DUE_DATE_HEADER) });
		this.tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(final TableClickEvent event) {
						final SimpleBug bug = (SimpleBug) event.getData();
						if ("summary".equals(event.getFieldName())) {
							EventBus.getInstance().fireEvent(
									new BugEvent.GotoRead(BugListWidget.this,
											bug.getId()));
						}
					}
				});

		this.tableItem.setWidth("100%");
		contentLayout.addComponent(this.tableItem);

		this.setSearchCriteria(bugSearchCriteria);
	}

	private void setSearchCriteria(final BugSearchCriteria searchCriteria) {
		this.bugSearchCriteria = searchCriteria;
		this.tableItem.setSearchCriteria(this.bugSearchCriteria);
	}
}
