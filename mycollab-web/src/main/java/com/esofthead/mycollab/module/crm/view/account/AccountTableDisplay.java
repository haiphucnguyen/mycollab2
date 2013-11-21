/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.account;

import java.util.List;

import org.jsoup.Jsoup;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.user.UserLinkUtils;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.EmailLink;
import com.esofthead.mycollab.vaadin.ui.UrlLink;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.vaadin.ui.UserLink;
import com.esofthead.mycollab.vaadin.ui.table.DefaultPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.esofthead.mycollab.web.AppContext;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.H3;
import com.hp.gagawa.java.elements.Img;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Tr;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

/**
 * 
 * @author haiphucnguyen
 */
public class AccountTableDisplay
		extends
		DefaultPagedBeanTable<AccountService, AccountSearchCriteria, SimpleAccount> {
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
		super(ApplicationContextUtil.getSpringBean(AccountService.class),
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
				b.setDescription(generateAccountToolTip(account));
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

	private String generateAccountToolTip(SimpleAccount account) {
		try {
			Div div = new Div();
			H3 accountName = new H3();
			accountName
					.appendText(Jsoup.parse(account.getAccountname()).html());
			div.appendChild(accountName);

			com.hp.gagawa.java.elements.Table table = new com.hp.gagawa.java.elements.Table();
			table.setStyle("padding-left:10px; width :500px; color: #5a5a5a; font: 11px 'Lucida Sans Unicode', 'Lucida Grande', sans-serif;");
			Tr trRow1 = new Tr();
			trRow1.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Website:")).appendChild(
					new Td().setStyle("vertical-align:top; text-align: left;")
							.appendText(
									(account.getWebsite() != null) ? Jsoup
											.parse(account.getWebsite()).html()
											: ""));
			trRow1.appendChild(
					new Td().setStyle(
							"width: 150px; vertical-align: top; text-align: right;")
							.appendText("Office Phone:")).appendChild(
					new Td().setStyle("width:200px; vertical-align: top;")
							.appendText(
									(account.getPhoneoffice() != null) ? Jsoup
											.parse(account.getPhoneoffice())
											.html() : ""));

			Tr trRow2 = new Tr();
			trRow2.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Employees:"))
					.appendChild(
							new Td().appendText((account.getNumemployees() != null) ? account
									.getNumemployees().toString() : ""));
			trRow2.appendChild(
					new Td().setStyle(
							"width: 100px; vertical-align: top; text-align: right;")
							.appendText("Email:")).appendChild(
					new Td().appendText((account.getEmail() != null) ? account
							.getEmail() : ""));

			Tr trRow3 = new Tr();
			trRow3.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Assignee:"))
					.appendChild(
							new Td().setStyle(
									"width: 150px;word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
									.appendChild(
											new A().setHref(
													(account.getAssignuser() != null) ? UserLinkUtils
															.generatePreviewFullUserLink(
																	AppContext
																			.getSiteUrl(),
																	account.getAssignuser())
															: "")
													.appendChild(
															new Img(
																	"",
																	UserAvatarControlFactory
																			.getAvatarLink(
																					account.getAssignUserAvatarId(),
																					16)))
													.appendText(
															(account.getAssignUserFullName() != null) ? Jsoup
																	.parse(account
																			.getAssignUserFullName())
																	.html()
																	: "")));

			trRow3.appendChild(
					new Td().setStyle(
							"width: 150px; vertical-align: top; text-align: right;")
							.appendText("Annual Revenue:"))
					.appendChild(
							new Td().setStyle(
									"width: 180px;word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
									.appendText(
											(account.getAnnualrevenue() != null) ? Jsoup
													.parse(account
															.getAnnualrevenue())
													.html()
													: ""));

			Tr trRow4 = new Tr();
			Td trRow4_value = new Td()
					.setStyle(
							"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
					.appendText(
							(account.getDescription() != null) ? (Jsoup
									.parse(account.getDescription()).html()
									.length() > 200) ? Jsoup
									.parse(account.getDescription()).html()
									.substring(0, 200) : Jsoup.parse(
									account.getDescription()).html() : "");
			trRow4_value.setAttribute("colspan", "3");
			trRow4.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Description:")).appendChild(
					trRow4_value);

			table.appendChild(trRow1);
			table.appendChild(trRow2);
			table.appendChild(trRow3);
			table.appendChild(trRow4);
			div.appendChild(table);
			return div.write();
		} catch (Exception e) {
			return "";
		}
	}
}
