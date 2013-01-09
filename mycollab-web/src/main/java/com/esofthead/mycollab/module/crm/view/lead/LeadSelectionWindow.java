package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class LeadSelectionWindow extends Window {

    private static final long serialVersionUID = 1L;
    private LeadSearchCriteria searchCriteria;
    private LeadTableDisplay tableItem;
    private FieldSelection fieldSelection;

    public LeadSelectionWindow(FieldSelection fieldSelection) {
        super("Lead Name Lookup");
        this.setWidth("800px");
        this.fieldSelection = fieldSelection;
    }

    public void show() {
        searchCriteria = new LeadSearchCriteria();
        searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
                AppContext.getAccountId()));

        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);

        createLeadList();
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
		layoutSearchPane.setComponentAlignment(textValueField, Alignment.MIDDLE_CENTER);
	}

	private void addUserListSelectField() {
		userBox = new UserComboBox();
		userBox.setImmediate(true);
		layoutSearchPane.addComponent(userBox, 0, 0);
		layoutSearchPane.setComponentAlignment(userBox, Alignment.MIDDLE_CENTER);
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
				"Name", "Email", "Phone", "Assigned to" });
		group.select("Name");
		group.setImmediate(true);
		group.addListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				removeComponents();
				String searchType = (String) group.getValue();
				if (searchType.equals("Name")) {
					addTextFieldSearch();
				} else if (searchType.equals("Email")) {
					addTextFieldSearch();
				}else if (searchType.equals("Phone")) {
					addTextFieldSearch();
				} else if (searchType.equals("Assigned to")) {
					addUserListSelectField();
				}
			}
		});
		
		layoutSearchPane.addComponent(group, 1, 0);
		layoutSearchPane.setComponentAlignment(group, Alignment.MIDDLE_CENTER);
		addTextFieldSearch(); 

		Button searchBtn = new Button("Search");
		searchBtn.addListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				searchCriteria = new LeadSearchCriteria();
				searchCriteria.setSaccountid(new NumberSearchField(
						SearchField.AND, AppContext.getAccountId()));
				
				String searchType = (String) group.getValue();
				if (StringUtil.isNotNullOrEmpty(searchType)) {
					
					if (textValueField != null) {
						String strSearch = (String) textValueField.getValue();
						if (StringUtil.isNotNullOrEmpty(strSearch)) {

							if (searchType.equals("Name")) {
								searchCriteria
										.setLeadName(new StringSearchField(
												SearchField.AND, strSearch));
							} else if (searchType.equals("Email")) {
								searchCriteria.setAnyEmail(new StringSearchField(
										SearchField.AND, strSearch));
							} else if (searchType.equals("Phone")) {
								searchCriteria.setAnyPhone(new StringSearchField(
										SearchField.AND, strSearch));
							}
						}
					}
					
					if (userBox != null) {
						String user = (String) userBox.getValue();
						if (StringUtil.isNotNullOrEmpty(user)) {
							searchCriteria.setAssignUsers(new SetSearchField<String>(SearchField.AND, new String[]{user}));
						}
					}
				}

				tableItem.setSearchCriteria(searchCriteria);
			}
		});
		layoutSearchPane.addComponent(searchBtn, 2, 0);
		layoutSearchPane.setComponentAlignment(searchBtn, Alignment.MIDDLE_CENTER);
		return layoutSearchPane;
	}

    @SuppressWarnings("serial")
	private void createLeadList() {
        tableItem = new LeadTableDisplay(new String[]{"leadName", "status",
                    "assignUserFullName", "accountname"}, new String[]{"Name",
                    "Status", "Assign User", "Account name"});
        tableItem.setWidth("100%");
        tableItem.addTableListener(new ApplicationEventListener<TableClickEvent>() {
            @Override
            public Class<? extends ApplicationEvent> getEventType() {
                return TableClickEvent.class;
            }

            @Override
            public void handle(TableClickEvent event) {
                SimpleLead lead = (SimpleLead) event.getData();
                if ("leadName".equals(event.getFieldName())) {
                    fieldSelection.fireValueChange(lead);
                    LeadSelectionWindow.this.getParent()
                            .removeWindow(LeadSelectionWindow.this);
                }
            }
        });
    }
}
