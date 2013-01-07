package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class OpportunitySelectionWindow extends Window {

    private static final long serialVersionUID = 1L;
    private OpportunitySearchCriteria searchCriteria;
    private OpportunityTableDisplay tableItem;
    private FieldSelection fieldSelection;

    public OpportunitySelectionWindow(FieldSelection fieldSelection) {
        super("Opportunity Name Lookup");
        this.setWidth("800px");
        this.fieldSelection = fieldSelection;
    }

    public void show() {
        searchCriteria = new OpportunitySearchCriteria();
        searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
                AppContext.getAccountId()));

        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);

        createOpportunityList();
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
                "Name", "Next Step",
                "Assigned To"});
	    layout.addComponent(group);
	
	    Button searchBtn = new Button("Search");
	    layout.addComponent(searchBtn);
        
        return layout;
    }

    private void createOpportunityList() {
        tableItem = new OpportunityTableDisplay(new String[]{"opportunityname",
                    "accountName", "salesstage", "assignUserFullName"},
                new String[]{"Name", "Account Name", "Sales Stage", "User"});
        tableItem.setWidth("100%");

        tableItem.addTableListener(new ApplicationEventListener<PagedBeanTable2.TableClickEvent>() {
            @Override
            public Class<? extends ApplicationEvent> getEventType() {
                return TableClickEvent.class;
            }

            @Override
            public void handle(TableClickEvent event) {
                SimpleOpportunity opportunity = (SimpleOpportunity) event.getData();
                if ("opportunityname".equals(
                        event.getFieldName())) {
                    fieldSelection.fireValueChange(opportunity);
                    OpportunitySelectionWindow.this
                            .getParent()
                            .removeWindow(
                            OpportunitySelectionWindow.this);
                }
            }
        });
    }
}
