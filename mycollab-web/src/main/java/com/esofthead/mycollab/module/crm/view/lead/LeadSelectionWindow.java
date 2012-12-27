package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.service.LeadService;
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

public class LeadSelectionWindow extends Window {

    private static final long serialVersionUID = 1L;
    private LeadSearchCriteria searchCriteria;
    private PagedBeanTable2<LeadService, LeadSearchCriteria, SimpleLead> tableItem;
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
        tableItem = new PagedBeanTable2<LeadService, LeadSearchCriteria, SimpleLead>(
                AppContext.getSpringBean(LeadService.class), SimpleLead.class,
                new String[]{"leadName", "status", "accountname",
                    "assignUserFullName"}, new String[]{"Name",
                    "Status", "Account Name", "Assign User"});
        tableItem.setWidth("100%");
        tableItem.setHeight("200px");

        tableItem.addGeneratedColumn("leadName", new ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            public com.vaadin.ui.Component generateCell(final Table source,
                    final Object itemId, Object columnId) {
                final SimpleLead lead = tableItem.getBeanByIndex(itemId);
                Button b = new Button(lead.getLeadName(),
                        new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(ClickEvent event) {
                                fieldSelection.fireValueChange(lead);
                                LeadSelectionWindow.this.getParent()
                                        .removeWindow(LeadSelectionWindow.this);
                            }
                        });
                b.setStyleName("link");
                b.addStyleName("medium-text");
                return b;
            }
        });
    }
}
