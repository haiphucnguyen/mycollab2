package com.esofthead.mycollab.module.project.view.bug;

import java.util.Arrays;
import java.util.GregorianCalendar;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class BugSelectionWindow extends Window {

	private static final long serialVersionUID = 1L;
	private BugSearchCriteria searchCriteria;
	private PagedBeanTable2<BugService, BugSearchCriteria, SimpleBug> tableItem;
	private BugRelatedField fieldSelection;

	public BugSelectionWindow(BugRelatedField fieldSelection) {
		super("Bug Lookup");
		this.setWidth("900px");
		this.setHeight("500px");
		this.fieldSelection = fieldSelection;
		this.setModal(true);

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
		this.setContent(layout);
	}

	public void show() {
		searchCriteria = new BugSearchCriteria();
		searchCriteria.setProjectId(new NumberSearchField(SearchField.AND,
				CurrentProjectVariables.getProject().getId()));
		tableItem.setSearchCriteria(searchCriteria);
		center();
	}

	private void createAccountList() {

		tableItem = new BugTableDisplay(Arrays.asList(BugTableFieldDef.summary,
				BugTableFieldDef.severity, BugTableFieldDef.resolution,
				BugTableFieldDef.assignUser));

		tableItem.setWidth("100%");

		tableItem.addGeneratedColumn("summary", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				final SimpleBug bug = tableItem.getBeanByIndex(itemId);

				String bugname = "[%s-%s] %s";
				bugname = String.format(bugname, CurrentProjectVariables
						.getProject().getShortname(), bug.getBugkey(), bug
						.getSummary());

				ButtonLink b = new ButtonLink(bugname,
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(Button.ClickEvent event) {
								fieldSelection.fireValueChange(bug);
								BugSelectionWindow.this.getParent()
										.removeWindow(BugSelectionWindow.this);
							}
						});

				if (BugStatusConstants.VERIFIED.equals(bug.getStatus())) {
					b.addStyleName(UIConstants.LINK_COMPLETED);
				} else if (bug.getDuedate() != null
						&& (bug.getDuedate().before(new GregorianCalendar()
								.getTime()))) {
					b.addStyleName(UIConstants.LINK_OVERDUE);
				}

				return b;

			}
		});

	}
}
