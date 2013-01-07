package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class LeadSelectionWindow extends Window {

    private static final long serialVersionUID = 1L;
    private LeadSearchCriteria searchCriteria;
    private LeadTableDisplay tableItem;
    private FieldSelection fieldSelection;

    public LeadSelectionWindow(FieldSelection fieldSelection) {
        super("Lead Name Lookup");

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
    }

    private ComponentContainer createSearchPanel() {
        GridFormLayoutHelper layout = new GridFormLayoutHelper(3, 2);

        return layout.getLayout();
    }

    private void createLeadList() {
        tableItem = new LeadTableDisplay(new String[]{"leadName", "status", "accountname",
                    "assignUserFullName"}, new String[]{"Name",
                    "Status", "Account Name", "Assign User"});
        tableItem.setWidth("100%");
        tableItem.setHeight("200px");

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
