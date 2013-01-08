package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.service.CaseService;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
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

		this.setWidth("1035px");

		this.fieldSelection = fieldSelection;
	}

	public void show() {
		searchCriteria = new CaseSearchCriteria();
		searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));

		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setMargin(true);

		createCaseList();
		layout.addComponent(createSearchPanel());
		layout.addComponent(tableItem);
		this.setContent(layout);

		tableItem.setSearchCriteria(searchCriteria);
		center();
	}

	private TextField textValueField;
	private UserComboBox userBox;
	private GridLayout layoutSearchPane;

	private void addTextFieldSearch() {
		textValueField = new TextField();
		layoutSearchPane.addComponent(textValueField, 0, 0);
	}

	private void addUserListSelectField() {
		userBox = new UserComboBox();
		userBox.setImmediate(true);
		layoutSearchPane.addComponent(userBox, 0, 0);
	}

	private void removeComponents() {
		layoutSearchPane.removeComponent(0, 0);
		userBox = null;
		textValueField = null;
	}

	@SuppressWarnings("serial")
	private ComponentContainer createSearchPanel() {
		layoutSearchPane = new GridLayout(3, 3);
		layoutSearchPane.setSpacing(true);

		final ValueComboBox group = new ValueComboBox(false, new String[] {
				"Subject", "Account Name", "Status", "Assigned to" });
		group.select("Name");
		group.setImmediate(true);
		group.addListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				removeComponents();
				String searchType = (String) group.getValue();
				if (searchType.equals("Subject")) {
					addTextFieldSearch();
				} else if (searchType.equals("Account Name")) {
					addTextFieldSearch();
				} else if (searchType.equals("Status")) {
					addTextFieldSearch();
				} else if (searchType.equals("Assigned to")) {
					addUserListSelectField();
				}
			}
		});

		layoutSearchPane.addComponent(group, 1, 0);

		addTextFieldSearch();

		Button searchBtn = new Button("Search");
		searchBtn.addListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				searchCriteria = new CaseSearchCriteria();
				searchCriteria.setSaccountid(new NumberSearchField(
						SearchField.AND, AppContext.getAccountId()));

				String searchType = (String) group.getValue();
				if (StringUtil.isNotNullOrEmpty(searchType)) {

					if (textValueField != null) {
						String strSearch = (String) textValueField.getValue();
						if (StringUtil.isNotNullOrEmpty(strSearch)) {

							if (searchType.equals("Subject")) {
								searchCriteria
										.setSubject(new StringSearchField(
												SearchField.AND, strSearch));
							} else if (searchType.equals("Account Name")) {
								searchCriteria
										.setAccountName(new StringSearchField(
												SearchField.AND, strSearch));
							} else if (searchType.equals("Status")) {
								searchCriteria
										.setStatuses(new SetSearchField<String>(
												SearchField.AND,
												new String[] { strSearch }));
							}
						}
					}

					if (userBox != null) {
						String user = (String) userBox.getValue();
						if (StringUtil.isNotNullOrEmpty(user)) {
							searchCriteria
									.setAssignUsers(new SetSearchField<String>(
											SearchField.AND,
											new String[] { user }));
						}
					}
				}

				tableItem.setSearchCriteria(searchCriteria);
			}
		});
		layoutSearchPane.addComponent(searchBtn, 2, 0);
		return layoutSearchPane;
	}

	private void createCaseList() {
		tableItem = new PagedBeanTable2<CaseService, CaseSearchCriteria, SimpleCase>(
				AppContext.getSpringBean(CaseService.class), SimpleCase.class,
				new String[] { "subject", "accountName", "priority", "status",
						"assignUserFullName" }, new String[] { "Subject",
						"Account Name", "Priority", "Status", "Assigned To" });
		tableItem.setWidth("100%");

		tableItem.addGeneratedColumn("subject", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			public com.vaadin.ui.Component generateCell(final Table source,
					final Object itemId, Object columnId) {
				final SimpleCase cases = tableItem.getBeanByIndex(itemId);
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
				b.addStyleName("medium-text");
				return b;
			}
		});
	}
}
