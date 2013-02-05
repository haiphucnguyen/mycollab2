package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
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
        this.setModal(true);
    }

    public void show() {
        searchCriteria = new LeadSearchCriteria();
        searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
                AppContext.getAccountId()));

        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);

        createLeadList();
        
        LeadSimpleSearchPanel leadSimpleSearchPanel = new LeadSimpleSearchPanel();
        leadSimpleSearchPanel.addSearchHandler(new SearchHandler<LeadSearchCriteria>(){

			@Override
			public void onSearch(LeadSearchCriteria criteria) {
				tableItem.setSearchCriteria(criteria);
			}
        	
        });
        layout.addComponent(leadSimpleSearchPanel);
        layout.addComponent(tableItem);
        this.setContent(layout);

        tableItem.setSearchCriteria(searchCriteria);
        center();
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
