package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.module.crm.view.IRelatedListHandlers;
import com.esofthead.mycollab.module.crm.view.RelatedListHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;
import java.util.HashSet;
import java.util.Set;

public class AccountLeadListComp extends Depot implements IRelatedListHandlers {

    private static final long serialVersionUID = 1L;
    private PagedBeanTable2<LeadService, LeadSearchCriteria, SimpleLead> tableItem;
    private Set<RelatedListHandler> handlers;

    public AccountLeadListComp() {
        super("Leads", new VerticalLayout());

        this.setWidth("900px");

        initUI();
    }

    private void initUI() {
        VerticalLayout contentContainer = (VerticalLayout) content;
        contentContainer.setSpacing(true);

        Button createBtn = new Button("New Lead", new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                fireRelatedListHandler();
            }
        });

        createBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));
        createBtn.setStyleName(BaseTheme.BUTTON_LINK);
        contentContainer.addComponent(createBtn);

        tableItem = new PagedBeanTable2<LeadService, LeadSearchCriteria, SimpleLead>(
                AppContext.getSpringBean(LeadService.class), SimpleLead.class,
                new String[]{"leadName", "status", "officephone", "email",
                    "assignuser", "id"}, new String[]{"Name", "Status",
                    "Office Phone", "Email", "Assign User", "Action"});

        tableItem.addGeneratedColumn("leadName", new ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public Object generateCell(Table source, Object itemId,
                    Object columnId) {
                final SimpleLead lead = tableItem.getBeanByIndex(itemId);
                ButtonLink b = new ButtonLink(lead.getLeadName(),
                        new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(ClickEvent event) {
                                EventBus.getInstance().fireEvent(
                                        new LeadEvent.GotoRead(this, lead
                                        .getId()));
                            }
                        });
                return b;
            }
        });

        tableItem.addGeneratedColumn("email", new ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public com.vaadin.ui.Component generateCell(Table source,
                    Object itemId, Object columnId) {
                final SimpleLead lead = tableItem.getBeanByIndex(itemId);
                Link l = new Link();
                l.setResource(new ExternalResource("mailto:" + lead.getEmail()));
                l.setCaption(lead.getEmail());
                return l;

            }
        });
        
        tableItem.addGeneratedColumn("id", new ColumnGenerator() {
            @Override
            public Object generateCell(Table source, Object itemId,
                    Object columnId) {
                Button deleteBtn = new Button(null, new Button.ClickListener() {

                            @Override
                            public void buttonClick(ClickEvent event) {
                                throw new UnsupportedOperationException("Not supported yet.");
                            }
                        });
                deleteBtn.setStyleName("link");
                deleteBtn.setIcon(new ThemeResource("icons/16/delete.png"));
                return deleteBtn;
            }
        });

        tableItem.setWidth("100%");

        tableItem.setColumnExpandRatio("leadName", 1.0f);
        tableItem.setColumnWidth("status", UIConstants.TABLE_M_LABEL_WIDTH);
        tableItem
                .setColumnWidth("officephone", UIConstants.TABLE_X_LABEL_WIDTH);
        tableItem.setColumnWidth("email", UIConstants.TABLE_EMAIL_WIDTH);
        tableItem.setColumnWidth("assignuser", UIConstants.TABLE_X_LABEL_WIDTH);
        contentContainer.addComponent(tableItem);
    }

    public void setSearchCriteria(LeadSearchCriteria searchCriteria) {
        tableItem.setSearchCriteria(searchCriteria);
    }

    private void fireRelatedListHandler() {
        if (handlers != null) {
            for (RelatedListHandler handler : handlers) {
                handler.createNewRelatedItem();
            }
        }
    }

    @Override
    public void addRelatedListHandler(RelatedListHandler handler) {
        if (handlers == null) {
            handlers = new HashSet<RelatedListHandler>();
        }

        handlers.add(handler);
    }
}
