/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.account;

import java.util.List;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.EmailLink;
import com.esofthead.mycollab.vaadin.ui.UrlLink;
import com.esofthead.mycollab.vaadin.ui.UserLink;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

/**
 * 
 * @author haiphucnguyen
 */
public class AccountTableDisplay extends
		PagedBeanTable2<AccountService, AccountSearchCriteria, SimpleAccount> {
	private static final long serialVersionUID = 1L;

	public AccountTableDisplay(List<TableViewField> displayColumns) {
		this(null, displayColumns);
	}

	public AccountTableDisplay(TableViewField requiredColumn,
			List<TableViewField> displayColumns) {
		this(null, requiredColumn, displayColumns);

	}

	public AccountTableDisplay(String viewId, TableViewField requiredColumn,
			List<TableViewField> displayColumns) {
		super(AppContext.getSpringBean(AccountService.class),
				SimpleAccount.class, viewId, requiredColumn, displayColumns);

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

				// Insert embedLink --------
				List<TableViewField> lstTableViewField = AccountTableDisplay.this
						.getDisplayColumns();
				for (TableViewField tableViewField : lstTableViewField) {
					if (tableViewField.getField().equals("accountname")) {
						tableViewField.setEmbedLink("www.google.com.vn");
						break;
					}
				}
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
						account.getAssignUserAvatarId(), account
								.getAssignUserFullName());
				return b;

			}
		});

		addGeneratedColumn("website", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(final Table source,
					final Object itemId, final Object columnId) {
				final SimpleAccount account = AccountTableDisplay.this
						.getBeanByIndex(itemId);
				if (account.getWebsite() != null) {
					return new UrlLink(account.getWebsite());
				} else {
					return new Label("");
				}

			}
		});

		this.setWidth("100%");
	}
}
