package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.AccountLead;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.ui.components.RelatedListComp;
import com.esofthead.mycollab.module.crm.view.lead.LeadTableDisplay;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;
import java.util.Set;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.hene.splitbutton.SplitButton;

public class AccountLeadListComp extends RelatedListComp<SimpleLead, LeadSearchCriteria> {

    private static final long serialVersionUID = 1L;
    private Account account;

    public AccountLeadListComp() {
        super("Leads");

        initUI();
    }

    public void displayLeads(Account account) {
        this.account = account;
        loadLeads();
    }

    private void loadLeads() {
        LeadSearchCriteria criteria = new LeadSearchCriteria();
        criteria.setSaccountid(new NumberSearchField(SearchField.AND,
                AppContext.getAccountId()));
        criteria.setAccountId(new NumberSearchField(SearchField.AND, account
                .getId()));
        this.setSearchCriteria(criteria);
    }

    private void initUI() {
        VerticalLayout contentContainer = (VerticalLayout) bodyContent;
        contentContainer.setSpacing(true);

        SplitButton controlsBtn = new SplitButton();
        controlsBtn.addStyleName(UIConstants.SPLIT_BUTTON);
        controlsBtn.setCaption("New Lead");
        controlsBtn.setIcon(new ThemeResource("icons/16/addRecordGreen.png"));
        controlsBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.CRM_LEAD));
        controlsBtn.addClickListener(new SplitButton.SplitButtonClickListener() {
            @Override
            public void splitButtonClick(SplitButton.SplitButtonClickEvent event) {
                fireNewRelatedItem("");
            }
        });
        Button selectBtn = new Button("Select from existing leads", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
            }
        });
        selectBtn.setIcon(new ThemeResource("icons/16/select.png"));
        selectBtn.setStyleName("link");
        controlsBtn.addComponent(selectBtn);

        contentContainer.addComponent(controlsBtn);

        tableItem = new LeadTableDisplay(
                new String[]{"leadName", "status", "officephone", "email", "id"}, new String[]{"Name", "Status",
                    "Office Phone", "Email", "Action"});

        tableItem.addTableListener(new ApplicationEventListener<TableClickEvent>() {
            @Override
            public Class<? extends ApplicationEvent> getEventType() {
                return TableClickEvent.class;
            }

            @Override
            public void handle(TableClickEvent event) {
                SimpleLead lead = (SimpleLead) event.getData();
                if ("leadName".equals(event.getFieldName())) {
                    EventBus.getInstance().fireEvent(
                            new LeadEvent.GotoRead(this, lead
                            .getId()));
                }
            }
        });

        tableItem.addGeneratedColumn("id", new ColumnGenerator() {
            @Override
            public Object generateCell(Table source, Object itemId,
                    Object columnId) {
                final SimpleLead lead = (SimpleLead) tableItem.getBeanByIndex(itemId);
                HorizontalLayout controlLayout = new HorizontalLayout();
                Button editBtn = new Button(null, new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        EventBus.getInstance().fireEvent(
                                new LeadEvent.GotoRead(
                                AccountLeadListComp.this, lead
                                .getId()));
                    }
                });
                editBtn.setStyleName("link");
                editBtn.setIcon(new ThemeResource("icons/16/edit.png"));
                controlLayout.addComponent(editBtn);

                Button deleteBtn = new Button(null, new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        ConfirmDialog.show(AppContext.getApplication().getMainWindow(),
                                "Please Confirm:",
                                "Are you sure to delete this relationship? Only the relationship is removed. The record will not be deleted.",
                                "Yes", "No", new ConfirmDialog.Listener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void onClose(ConfirmDialog dialog) {
                                if (dialog.isConfirmed()) {
                                    AccountService accountService = AppContext
                                            .getSpringBean(AccountService.class);
                                    AccountLead associateLead = new AccountLead();
                                    associateLead.setAccountid(account.getId());
                                    associateLead.setLeadid(lead.getId());
                                    accountService.removeAccountLeadRelationship(associateLead);
                                    AccountLeadListComp.this.refresh();
                                }
                            }
                        });
                    }
                });
                deleteBtn.setStyleName("link");
                deleteBtn.setIcon(new ThemeResource("icons/16/delete.png"));
                controlLayout.addComponent(deleteBtn);
                return controlLayout;
            }
        });

        contentContainer.addComponent(tableItem);
    }

    @Override
    public void setSelectedItems(Set<SimpleLead> selectedItems) {
        fireSelectedRelatedItems(selectedItems);
    }

    @Override
    public void refresh() {
        loadLeads();
    }
}
