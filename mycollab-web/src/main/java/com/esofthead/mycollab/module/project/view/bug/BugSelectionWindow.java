package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.project.localization.BugI18nEnum;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class BugSelectionWindow extends Window {

	private static final long serialVersionUID = 1L;
	private BugSearchCriteria searchCriteria;
	private BugTableDisplay tableItem;
	private BugRelatedField fieldSelection;

	public BugSelectionWindow(BugRelatedField fieldSelection) {
		super("Bug Lookup");
		this.setWidth("900px");
		this.setHeight("500px");
		this.fieldSelection = fieldSelection;
		this.setModal(true);

		searchCriteria = new BugSearchCriteria();

		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setMargin(true);
		BugSimpleSearchPanel contactSimpleSearchPanel = new BugSimpleSearchPanel();
		contactSimpleSearchPanel
				.addSearchHandler(new SearchHandler<BugSearchCriteria>() {

					@Override
					public void onSearch(BugSearchCriteria criteria) {
						tableItem.setSearchCriteria(criteria);
					}

				});
		layout.addComponent(contactSimpleSearchPanel);
		createAccountList();
		layout.addComponent(tableItem);
		tableItem.setSearchCriteria(searchCriteria);
		this.setContent(layout);
		center();
	}

	@SuppressWarnings("serial")
	private void createAccountList() {
		tableItem = new BugTableDisplay(
				new String[] {"summary", "assignuserFullName",
						"severity", "resolution", "duedate" },
				new String[] {
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

		tableItem.setWidth("100%");

		tableItem.setColumnExpandRatio("summary", 1.0f);

		tableItem.setColumnWidth("assignuserFullName", UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem.setColumnWidth("severity", UIConstants.TABLE_S_LABEL_WIDTH);
		tableItem.setColumnWidth("resolution", UIConstants.TABLE_M_LABEL_WIDTH);

		tableItem.addGeneratedColumn("assignuserFullName",
				new Table.ColumnGenerator() {
					private static final long serialVersionUID = 1L;

					@Override
					public com.vaadin.ui.Component generateCell(Table source,
							final Object itemId, Object columnId) {
						final SimpleBug bug = tableItem.getBeanByIndex(itemId);
						ButtonLink btnUser = new ButtonLink(bug.getAssignuserFullName());
						btnUser.setIcon(UserAvatarControlFactory.getResource(
								AppContext.getAccountId(), bug.getAssignuser(), 16));
						return btnUser;

					}
				});

		tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(TableClickEvent event) {
						SimpleBug bug = (SimpleBug) event.getData();
						if ("summary".equals(event.getFieldName())) {
							fieldSelection.fireValueChange(bug);
							BugSelectionWindow.this.getParent().removeWindow(
									BugSelectionWindow.this);
						}
					}
				});

	}
}
