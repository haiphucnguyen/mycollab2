/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.EmailLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UserLink;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Table;

/**
 *
 * @author haiphucnguyen
 */
public class AccountTableDisplay extends PagedBeanTable2<AccountService, AccountSearchCriteria, SimpleAccount> {

    public AccountTableDisplay(final String[] visibleColumns, String[] columnHeaders) {
        super(AppContext.getSpringBean(AccountService.class),
                SimpleAccount.class, visibleColumns, columnHeaders);

        this.addGeneratedColumn("selected", new Table.ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public Object generateCell(final Table source, final Object itemId,
                    Object columnId) {
                final CheckBox cb = new CheckBox("", false);
                cb.setImmediate(true);
                cb.addListener(new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        SimpleAccount account = AccountTableDisplay.this
                                .getBeanByIndex(itemId);
                        AccountTableDisplay.this.fireSelectItemEvent(account);
                        fireTableEvent(new TableClickEvent(
                                AccountTableDisplay.this, account,
                                "selected"));
                    }
                });

                SimpleAccount account = AccountTableDisplay.this.getBeanByIndex(itemId);
                account.setExtraData(cb);
                return cb;
            }
        });

        this.addGeneratedColumn("email", new Table.ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public com.vaadin.ui.Component generateCell(Table source,
                    Object itemId, Object columnId) {
                SimpleAccount account = AccountTableDisplay.this.getBeanByIndex(itemId);
                return new EmailLink(account.getEmail());
            }
        });

        this.addGeneratedColumn("accountname", new Table.ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public com.vaadin.ui.Component generateCell(Table source,
                    final Object itemId, Object columnId) {
                final SimpleAccount account = AccountTableDisplay.this.getBeanByIndex(itemId);
                ButtonLink b = new ButtonLink(account.getAccountname(),
                        new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                fireTableEvent(new TableClickEvent(AccountTableDisplay.this, account, "accountname"));
                            }
                        });
                b.addStyleName("medium-text");
                return b;

            }
        });

        this.addGeneratedColumn("assignUserFullName", new Table.ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public com.vaadin.ui.Component generateCell(Table source,
                    final Object itemId, Object columnId) {
                final SimpleAccount account = AccountTableDisplay.this.getBeanByIndex(itemId);
                UserLink b = new UserLink(account.getAssignuser(), account.getAssignUserFullName());
                return b;

            }
        });



        this.setColumnExpandRatio("accountname", 1);
        this.setColumnWidth("selected", UIConstants.TABLE_CONTROL_WIDTH);
        this.setColumnWidth("city", UIConstants.TABLE_X_LABEL_WIDTH);
        this
                .setColumnWidth("phoneoffice", UIConstants.TABLE_M_LABEL_WIDTH);
        this.setColumnWidth("email", UIConstants.TABLE_EMAIL_WIDTH);
        this.setColumnWidth("assignUserFullName", UIConstants.TABLE_X_LABEL_WIDTH);

        this.setWidth("100%");
    }
}
