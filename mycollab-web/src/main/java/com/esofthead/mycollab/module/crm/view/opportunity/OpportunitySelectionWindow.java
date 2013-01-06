package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class OpportunitySelectionWindow extends Window {

    private static final long serialVersionUID = 1L;
    private OpportunitySearchCriteria searchCriteria;
    private OpportunityTableDisplay tableItem;
    private FieldSelection fieldSelection;

    public OpportunitySelectionWindow(FieldSelection fieldSelection) {
        super("Opportunity Name Lookup");

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
    }

    private ComponentContainer createSearchPanel() {
        GridFormLayoutHelper layout = new GridFormLayoutHelper(3, 2);

        return layout.getLayout();
    }

    private void createOpportunityList() {
        tableItem = new OpportunityTableDisplay(new String[]{"opportunityname",
                    "accountName", "salesstage", "assignUserFullName"},
                new String[]{"Name", "Account Name", "Sales Stage", "User"});
        tableItem.setWidth("100%");
        tableItem.setHeight("200px");

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
