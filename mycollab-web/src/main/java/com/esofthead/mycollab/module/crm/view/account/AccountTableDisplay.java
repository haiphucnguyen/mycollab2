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
public class AccountTableDisplay extends
		PagedBeanTable2<AccountService, AccountSearchCriteria, SimpleAccount> {
	private static final long serialVersionUID = 1L;

	public AccountTableDisplay(final String[] visibleColumns,
			final String[] columnHeaders) {
		super(AppContext.getSpringBean(AccountService.class),
				SimpleAccount.class, visibleColumns, columnHeaders);

		addGeneratedColumn("selected", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object generateCell(final Table source, final Object itemId,
					final Object columnId) {
				final CheckBox cb = new CheckBox("", false);
				cb.setImmediate(true);
				cb.addListener(new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final Button.ClickEvent event) {
						final SimpleAccount account = AccountTableDisplay.this
								.getBeanByIndex(itemId);
						AccountTableDisplay.this.fireSelectItemEvent(account);
						fireTableEvent(new TableClickEvent(
								AccountTableDisplay.this, account, "selected"));
					}
				});

				final SimpleAccount account = AccountTableDisplay.this
						.getBeanByIndex(itemId);
				account.setExtraData(cb);
				return cb;
			}
		});

		addGeneratedColumn("email", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(final Table source,
					final Object itemId, final Object columnId) {
				final SimpleAccount account = AccountTableDisplay.this
						.getBeanByIndex(itemId);
				return new EmailLink(account.getEmail());
			}
		});

		addGeneratedColumn("accountname", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(final Table source,
					final Object itemId, final Object columnId) {
				final SimpleAccount account = AccountTableDisplay.this
						.getBeanByIndex(itemId);
				final ButtonLink b = new ButtonLink(account.getAccountname(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(
									final Button.ClickEvent event) {
								fireTableEvent(new TableClickEvent(
										AccountTableDisplay.this, account,
										"accountname"));
							}
						});
				return b;

			}
		});

		addGeneratedColumn("assignUserFullName", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(final Table source,
					final Object itemId, final Object columnId) {
				final SimpleAccount account = AccountTableDisplay.this
						.getBeanByIndex(itemId);
				final UserLink b = new UserLink(account.getAssignuser(),
						account.getAssignUserFullName());
				return b;

			}
		});

		setColumnExpandRatio("accountname", 1);
		setColumnWidth("selected", UIConstants.TABLE_CONTROL_WIDTH);
		setColumnWidth("city", UIConstants.TABLE_X_LABEL_WIDTH);
		setColumnWidth("phoneoffice", UIConstants.TABLE_M_LABEL_WIDTH);
		setColumnWidth("email", UIConstants.TABLE_EMAIL_WIDTH);
		setColumnWidth("assignUserFullName", UIConstants.TABLE_X_LABEL_WIDTH);

		this.setWidth("100%");
	}
}
