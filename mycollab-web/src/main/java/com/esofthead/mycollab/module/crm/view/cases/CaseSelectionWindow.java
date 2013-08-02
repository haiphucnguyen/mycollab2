package com.esofthead.mycollab.module.crm.view.cases;

import java.util.Arrays;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.module.crm.events.CaseEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class CaseSelectionWindow extends Window {

	private static final long serialVersionUID = 1L;
	private CaseSearchCriteria searchCriteria;
	private CaseTableDisplay tableItem;
	private FieldSelection fieldSelection;

	public CaseSelectionWindow(FieldSelection fieldSelection) {
		super("Case Name Lookup");
		this.setWidth("900px");
		this.fieldSelection = fieldSelection;
		this.setModal(true);
	}

	public void show() {
		searchCriteria = new CaseSearchCriteria();
		searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));

		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setMargin(true);

		createCaseList();
		CaseSimpleSearchPanel caseSimpleSearchPanel = new CaseSimpleSearchPanel();
		caseSimpleSearchPanel
				.addSearchHandler(new SearchHandler<CaseSearchCriteria>() {

					@Override
					public void onSearch(CaseSearchCriteria criteria) {
						tableItem.setSearchCriteria(criteria);
					}

				});
		layout.addComponent(caseSimpleSearchPanel);
		layout.addComponent(tableItem);
		this.setContent(layout);

		tableItem.setSearchCriteria(searchCriteria);
		center();
	}

	@SuppressWarnings("serial")
	private void createCaseList() {
		tableItem = new CaseTableDisplay(Arrays.asList(
				CaseTableFieldDef.subject, CaseTableFieldDef.account,
				CaseTableFieldDef.priority, CaseTableFieldDef.status,
				CaseTableFieldDef.assignUser));

		tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(TableClickEvent event) {
						SimpleCase cases = (SimpleCase) event.getData();
						if ("subject".equals(event.getFieldName())) {
							EventBus.getInstance()
									.fireEvent(
											new CaseEvent.GotoRead(this, cases
													.getId()));
						} else if ("accountName".equals(event.getFieldName())) {
							fieldSelection.fireValueChange(cases);
							CaseSelectionWindow.this.getParent().removeWindow(
									CaseSelectionWindow.this);
						}
					}
				});
	}
}
