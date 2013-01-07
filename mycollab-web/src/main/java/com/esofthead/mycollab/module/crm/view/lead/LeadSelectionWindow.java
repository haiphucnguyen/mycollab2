package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
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

        createLeadList();
        layout.addComponent(createSearchPanel());
        layout.addComponent(tableItem);
        this.setContent(layout);

        tableItem.setSearchCriteria(searchCriteria);
        center();
    }

    private ComponentContainer createSearchPanel() {
    	HorizontalLayout layout = new HorizontalLayout();
        
        layout.setSpacing(true);

        TextField valueField = new TextField();
        layout.addComponent(valueField);
        
        ValueComboBox group = new ValueComboBox(false, new String[]{
                "Name", "Email", "Phone",
                "Assigned To"});
	    layout.addComponent(group);
	
	    Button searchBtn = new Button("Search");
	    layout.addComponent(searchBtn);
        
        return layout;
    }

    private void createLeadList() {
        tableItem = new LeadTableDisplay(new String[]{"leadName", "status", "accountname",
                    "assignUserFullName"}, new String[]{"Name",
                    "Status", "Account Name", "Assign User"});
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
