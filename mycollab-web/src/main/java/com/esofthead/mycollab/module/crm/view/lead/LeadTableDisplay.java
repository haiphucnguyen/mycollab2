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
package com.esofthead.mycollab.module.crm.view.lead;

import java.util.List;

import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.module.user.UserLinkUtils;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
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
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;

/**
 * 
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class LeadTableDisplay extends
		DefaultPagedBeanTable<LeadService, LeadSearchCriteria, SimpleLead> {

	public LeadTableDisplay(List<TableViewField> displayColumns) {
		this(null, displayColumns);
	}

	public LeadTableDisplay(TableViewField requiredColumn,
			List<TableViewField> displayColumns) {
		this(null, requiredColumn, displayColumns);

	}

	public LeadTableDisplay(String viewId, TableViewField requiredColumn,
			List<TableViewField> displayColumns) {
		super(ApplicationContextUtil.getSpringBean(LeadService.class),
				SimpleLead.class, viewId, requiredColumn, displayColumns);

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
						SimpleLead lead = LeadTableDisplay.this
								.getBeanByIndex(itemId);
						LeadTableDisplay.this.fireSelectItemEvent(lead);

						fireTableEvent(new TableClickEvent(
								LeadTableDisplay.this, lead, "selected"));
					}
				});

				SimpleLead lead = LeadTableDisplay.this.getBeanByIndex(itemId);
				lead.setExtraData(cb);
				return cb;
			}
		});

		this.addGeneratedColumn("leadName", new Table.ColumnGenerator() {
			@Override
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				final SimpleLead lead = LeadTableDisplay.this
						.getBeanByIndex(itemId);
				ButtonLink b = new ButtonLink(lead.getLeadName(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(Button.ClickEvent event) {
								fireTableEvent(new TableClickEvent(
										LeadTableDisplay.this, lead, "leadName"));
							}
						});

				if ("Dead".equals(lead.getStatus())) {
					b.addStyleName(UIConstants.LINK_COMPLETED);
				}
				b.setDescription(generateTooltip(lead));
				return b;
			}
		});

		this.addGeneratedColumn("assignUserFullName",
				new Table.ColumnGenerator() {
					private static final long serialVersionUID = 1L;

					@Override
					public com.vaadin.ui.Component generateCell(Table source,
							final Object itemId, Object columnId) {
						final SimpleLead lead = LeadTableDisplay.this
								.getBeanByIndex(itemId);
						UserLink b = new UserLink(lead.getAssignuser(), lead
								.getAssignUserAvatarId(), lead
								.getAssignUserFullName());
						return b;

					}
				});

		this.addGeneratedColumn("email", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				final SimpleLead lead = LeadTableDisplay.this
						.getBeanByIndex(itemId);
				Link l = new Link();
				l.setResource(new ExternalResource("mailto:" + lead.getEmail()));
				l.setCaption(lead.getEmail());
				return l;

			}
		});

		this.addGeneratedColumn("website", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				final SimpleLead lead = LeadTableDisplay.this
						.getBeanByIndex(itemId);
				if (lead.getWebsite() != null) {
					return new UrlLink(lead.getWebsite());
				} else {
					return new Label("");
				}

			}
		});

		this.setWidth("100%");
	}

	private String generateTooltip(SimpleLead lead) {
		try {
			Div div = new Div();
			H3 leadName = new H3();
			leadName.appendText(lead.getLeadName());
			div.appendChild(leadName);

			com.hp.gagawa.java.elements.Table table = new com.hp.gagawa.java.elements.Table();
			table.setStyle("padding-left:10px; width :500px; color: #5a5a5a; font: 11px 'Lucida Sans Unicode', 'Lucida Grande', sans-serif;");
			Tr trRow1 = new Tr();
			trRow1.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("First Name:")).appendChild(
					new Td().appendText((lead.getFirstname() != null) ? lead
							.getFirstname() : ""));
			trRow1.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Email:")).appendChild(
					new Td().appendText((lead.getEmail() != null) ? lead
							.getEmail() : ""));

			Tr trRow2 = new Tr();
			trRow2.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Last Name:")).appendChild(
					new Td().appendText((lead.getLastname() != null) ? lead
							.getLastname() : ""));
			trRow2.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Office Phone:")).appendChild(
					new Td().appendText((lead.getOfficephone() != null) ? lead
							.getOfficephone() : ""));

			Tr trRow3 = new Tr();
			trRow3.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Title:")).appendChild(
					new Td().appendText((lead.getTitle() != null) ? lead
							.getTitle() : ""));
			trRow3.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Mobile:")).appendChild(
					new Td().appendText((lead.getMobile() != null) ? lead
							.getMobile() : ""));

			Tr trRow4 = new Tr();
			trRow4.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Department:")).appendChild(
					new Td().appendText((lead.getDepartment() != null) ? lead
							.getDepartment() : ""));
			trRow4.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Fax:")).appendChild(
					new Td().appendText((lead.getFax() != null) ? lead.getFax()
							: ""));
			Tr trRow5 = new Tr();
			trRow5.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Account Name:")).appendChild(
					new Td().appendText((lead.getAccountname() != null) ? lead
							.getAccountname() : ""));
			trRow5.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Website:")).appendChild(
					new Td().appendText((lead.getWebsite() != null) ? lead
							.getWebsite() : ""));

			Tr trRow6 = new Tr();
			trRow6.appendChild(
					new Td().setStyle(
							"width: 90px; vertical-align: top; text-align: right;")
							.appendText("Lead Source:"))
					.appendChild(
							new Td().appendText((lead.getLeadsourcedesc() != null) ? lead
									.getLeadsourcedesc() : ""));
			trRow6.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Assignee:"))
					.appendChild(
							new Td().setStyle(
									"width: 150px;word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
									.appendChild(
											new A().setHref(
													(lead.getAssignuser() != null) ? UserLinkUtils
															.generatePreviewFullUserLink(
																	AppContext
																			.getSiteUrl(),
																	lead.getAssignuser())
															: "")
													.appendChild(
															new Img(
																	"",
																	UserAvatarControlFactory
																			.getAvatarLink(
																					lead.getAssignUserAvatarId(),
																					16)))
													.appendText(
															(lead.getAssignUserFullName() != null) ? lead
																	.getAssignUserFullName()
																	: "")));
			Tr trRow7 = new Tr();
			trRow7.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Address:")).appendChild(
					new Td().appendText((lead.getPrimaddress() != null) ? lead
							.getPrimaddress() : ""));
			trRow7.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Other Address:")).appendChild(
					new Td().appendText((lead.getOtheraddress() != null) ? lead
							.getOtheraddress() : ""));

			Tr trRow8 = new Tr();
			trRow8.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Postal Code:"))
					.appendChild(
							new Td().appendText((lead.getPrimpostalcode() != null) ? lead
									.getPrimpostalcode() : ""));
			trRow8.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Other Postal Code:"))
					.appendChild(
							new Td().appendText((lead.getOtherpostalcode() != null) ? lead
									.getOtherpostalcode() : ""));
			Tr trRow9 = new Tr();

			Td trRow9_value = new Td()
					.setStyle(
							"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
					.appendText(
							(lead.getDescription() != null) ? (lead
									.getDescription().length() > 200) ? lead
									.getDescription().substring(0, 200) : lead
									.getDescription() : "");
			trRow9_value.setAttribute("colspan", "3");

			trRow9.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Description:")).appendChild(
					trRow9_value);
			table.appendChild(trRow1);
			table.appendChild(trRow2);
			table.appendChild(trRow3);
			table.appendChild(trRow4);
			table.appendChild(trRow5);
			table.appendChild(trRow6);
			table.appendChild(trRow7);
			table.appendChild(trRow8);
			table.appendChild(trRow9);
			div.appendChild(table);
			return div.write();
		} catch (Exception e) {
			return "";
		}
	}
}
