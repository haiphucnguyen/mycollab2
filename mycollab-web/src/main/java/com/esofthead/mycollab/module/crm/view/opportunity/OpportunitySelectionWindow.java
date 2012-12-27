package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
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

public class OpportunitySelectionWindow extends Window {

    private static final long serialVersionUID = 1L;
    private OpportunitySearchCriteria searchCriteria;
    private PagedBeanTable2<OpportunityService, OpportunitySearchCriteria, SimpleOpportunity> tableItem;
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
        tableItem = new PagedBeanTable2<OpportunityService, OpportunitySearchCriteria, SimpleOpportunity>(
                AppContext.getSpringBean(OpportunityService.class),
                SimpleOpportunity.class, new String[]{"opportunityname",
                    "accountName", "salesstage", "assignUserFullName"},
                new String[]{"Name", "Account Name", "Sales Stage", "User"});
        tableItem.setWidth("100%");
        tableItem.setHeight("200px");

        tableItem.addGeneratedColumn("opportunityname", new ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            public com.vaadin.ui.Component generateCell(final Table source,
                    final Object itemId, Object columnId) {
                final SimpleOpportunity opportunity = tableItem
                        .getBeanByIndex(itemId);
                Button b = new Button(opportunity.getOpportunityname(),
                        new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(ClickEvent event) {
                                fieldSelection.fireValueChange(opportunity);
                                OpportunitySelectionWindow.this
                                        .getParent()
                                        .removeWindow(
                                        OpportunitySelectionWindow.this);
                            }
                        });
                b.setStyleName("link");
                b.addStyleName("medium-text");
                return b;
            }
        });
    }
}
