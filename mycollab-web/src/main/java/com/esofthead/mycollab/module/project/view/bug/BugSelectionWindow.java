package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.project.localization.BugI18nEnum;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.LocalizationHelper;
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
	}

	public void show() {
		searchCriteria = new BugSearchCriteria();

		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setMargin(true);

		createAccountList();

		BugSimpleSearchPanel contactSimpleSearchPanel = new BugSimpleSearchPanel();
		contactSimpleSearchPanel
				.addSearchHandler(new SearchHandler<BugSearchCriteria>() {

					@Override
					public void onSearch(BugSearchCriteria criteria) {
						tableItem.setSearchCriteria(criteria);
					}

				});
		layout.addComponent(contactSimpleSearchPanel);
		layout.addComponent(tableItem);
		this.setContent(layout);

		tableItem.setSearchCriteria(searchCriteria);
		center();
	}

	@SuppressWarnings("serial")
	private void createAccountList() {
		tableItem = new BugTableDisplay(
				new String[] { "summary", "assignuserFullName", "severity",
						"resolution" },
				new String[] {
						LocalizationHelper
								.getMessage(BugI18nEnum.TABLE_SUMMARY_HEADER),
						LocalizationHelper
								.getMessage(BugI18nEnum.TABLE_ASSIGN_USER_HEADER),
						LocalizationHelper
								.getMessage(BugI18nEnum.TABLE_SEVERITY_HEADER),
						LocalizationHelper
								.getMessage(BugI18nEnum.TABLE_RESOLUTION_HEADER) });

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
		tableItem.setHeight("500px");

	}
}
