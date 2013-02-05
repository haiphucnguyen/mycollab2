package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class OpportunitySelectionWindow extends Window {

    private static final long serialVersionUID = 1L;
    private OpportunitySearchCriteria searchCriteria;
    private OpportunityTableDisplay tableItem;
    private FieldSelection fieldSelection;

    public OpportunitySelectionWindow(FieldSelection fieldSelection) {
        super("Opportunity Name Lookup");
        this.setWidth("900px");
        this.fieldSelection = fieldSelection;
        this.setModal(true);
    }

    public void show() {
        searchCriteria = new OpportunitySearchCriteria();
        searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
                AppContext.getAccountId()));

        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);

        createOpportunityList();
        OpportunitySimpleSearchPanel opportunitySimpleSearchPanel = new OpportunitySimpleSearchPanel();
        opportunitySimpleSearchPanel.addSearchHandler(new SearchHandler<OpportunitySearchCriteria>(){

			@Override
			public void onSearch(OpportunitySearchCriteria criteria) {
				tableItem.setSearchCriteria(criteria);
			}
        	
        });
        layout.addComponent(opportunitySimpleSearchPanel);
        layout.addComponent(tableItem);
        this.setContent(layout);

        tableItem.setSearchCriteria(searchCriteria);
        center();
    }

    private void createOpportunityList() {
        tableItem = new OpportunityTableDisplay(new String[]{"opportunityname",
                    "salesstage", "assignUserFullName", "accountName"},
                new String[]{"Name", "Sales Stage", "User", "Account Name"});
        tableItem.setWidth("100%");
        tableItem.addTableListener(new ApplicationEventListener<TableClickEvent>() {
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
