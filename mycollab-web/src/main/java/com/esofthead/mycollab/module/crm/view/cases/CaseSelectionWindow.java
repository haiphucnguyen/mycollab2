package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.module.crm.service.CaseService;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class CaseSelectionWindow extends Window {
	private static final long serialVersionUID = 1L;

	private CaseSearchCriteria searchCriteria;

	private PagedBeanTable2<CaseService, CaseSearchCriteria, SimpleCase> tableItem;

	private FieldSelection fieldSelection;

	public CaseSelectionWindow(FieldSelection fieldSelection) {
		super("Case Name Lookup");

		this.fieldSelection = fieldSelection;
	}

	public void show() {
		searchCriteria = new CaseSearchCriteria();
		searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));

		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);

		createCaseList();
		layout.addComponent(createSearchPanel());
		layout.addComponent(tableItem);
		layout.addComponent(tableItem.createControls());
		this.setContent(layout);

		tableItem.setSearchCriteria(searchCriteria);
	}

	private ComponentContainer createSearchPanel() {
		GridFormLayoutHelper layout = new GridFormLayoutHelper(3, 2);

		return layout.getLayout();
	}

	private void createCaseList() {
		tableItem = new PagedBeanTable2<CaseService, CaseSearchCriteria, SimpleCase>(
				AppContext.getSpringBean(CaseService.class), SimpleCase.class,
				new String[] { "subject", "accountName", "priority", "status",
						"assignUserFullName" }, new String[] { "Subject",
						"Account Name", "Priority", "Status", "Assigned To" });
		tableItem.setWidth("100%");
		tableItem.setHeight("200px");

		tableItem.addGeneratedColumn("subject", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			public com.vaadin.ui.Component generateCell(final Table source,
					final Object itemId, Object columnId) {
				@SuppressWarnings("unchecked")
				final SimpleCase cases = ((PagedBeanTable2<CaseService, CaseSearchCriteria, SimpleCase>) source)
						.getBeanByIndex(itemId);
				Button b = new Button(cases.getSubject(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								fieldSelection.fireValueChange(cases);
								CaseSelectionWindow.this.getParent()
										.removeWindow(CaseSelectionWindow.this);
							}
						});
				b.setStyleName("link");
				return b;
			}
		});
	}

}
