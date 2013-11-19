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
package com.esofthead.mycollab.module.crm.view.cases;

import java.util.List;

import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.module.crm.service.CaseService;
import com.esofthead.mycollab.module.user.UserLinkUtils;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
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
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;

@SuppressWarnings("serial")
public class CaseTableDisplay extends
		DefaultPagedBeanTable<CaseService, CaseSearchCriteria, SimpleCase> {

	public CaseTableDisplay(List<TableViewField> displayColumns) {
		this(null, displayColumns);
	}

	public CaseTableDisplay(TableViewField requiredColumn,
			List<TableViewField> displayColumns) {
		this(null, requiredColumn, displayColumns);

	}

	public CaseTableDisplay(String viewId, TableViewField requiredColumn,
			List<TableViewField> displayColumns) {
		super(ApplicationContextUtil.getSpringBean(CaseService.class),
				SimpleCase.class, viewId, requiredColumn, displayColumns);

		this.addGeneratedColumn("selected", new ColumnGenerator() {
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
						SimpleCase cases = CaseTableDisplay.this
								.getBeanByIndex(itemId);
						CaseTableDisplay.this.fireSelectItemEvent(cases);

					}
				});
				SimpleCase cases = CaseTableDisplay.this.getBeanByIndex(itemId);
				cases.setExtraData(cb);
				return cb;
			}
		});

		this.addGeneratedColumn("subject", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				final SimpleCase cases = CaseTableDisplay.this
						.getBeanByIndex(itemId);
				ButtonLink b = new ButtonLink(cases.getSubject(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								fireTableEvent(new TableClickEvent(
										CaseTableDisplay.this, cases, "subject"));
							}
						});

				if ("Closed".equals(cases.getStatus())
						|| "Rejected".equals(cases.getStatus())) {
					b.addStyleName(UIConstants.LINK_COMPLETED);
				}
				b.setDescription(generateTooltip(cases));
				return b;
			}
		});

		this.addGeneratedColumn("accountName", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				final SimpleCase cases = CaseTableDisplay.this
						.getBeanByIndex(itemId);
				ButtonLink b = new ButtonLink(cases.getAccountName(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								fireTableEvent(new TableClickEvent(
										CaseTableDisplay.this, cases,
										"accountName"));
							}
						});
				return b;
			}
		});

		this.addGeneratedColumn("assignUserFullName",
				new Table.ColumnGenerator() {
					private static final long serialVersionUID = 1L;

					@Override
					public com.vaadin.ui.Component generateCell(Table source,
							final Object itemId, Object columnId) {
						final SimpleCase cases = CaseTableDisplay.this
								.getBeanByIndex(itemId);
						UserLink b = new UserLink(cases.getAssignuser(), cases
								.getAssignUserAvatarId(), cases
								.getAssignUserFullName());
						return b;

					}
				});

		this.addGeneratedColumn("createdtime", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				final SimpleCase cases = CaseTableDisplay.this
						.getBeanByIndex(itemId);
				Label l = new Label();

				l.setValue(AppContext.formatDateTime(cases.getCreatedtime()));
				return l;
			}
		});

		this.setWidth("100%");
	}

	private String generateTooltip(SimpleCase cases) {
		try {
			Div div = new Div();
			H3 caseName = new H3();
			caseName.appendText(cases.getSubject());
			div.appendChild(caseName);

			com.hp.gagawa.java.elements.Table table = new com.hp.gagawa.java.elements.Table();
			table.setStyle("padding-left:10px; width :500px; color: #5a5a5a; font: 11px 'Lucida Sans Unicode', 'Lucida Grande', sans-serif;");
			Tr trRow1 = new Tr();
			trRow1.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Priority:")).appendChild(
					new Td().appendText(((cases.getPriority() != null) ? cases
							.getPriority() : "")));
			trRow1.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Type:")).appendChild(
					new Td().appendText((cases.getType() != null) ? cases
							.getType() : ""));

			Tr trRow2 = new Tr();
			trRow2.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Status:")).appendChild(
					new Td().appendText((cases.getStatus() != null) ? cases
							.getStatus() : ""));
			trRow2.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Reason:")).appendChild(
					new Td().appendText((cases.getReason() != null) ? cases
							.getReason() : ""));

			Tr trRow3 = new Tr();
			trRow3.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Account Name:"))
					.appendChild(
							new Td().appendText((cases.getAccountName() != null) ? cases
									.getAccountName() : ""));
			trRow3.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Email:")).appendChild(
					new Td().appendText((cases.getEmail() != null) ? cases
							.getEmail() : ""));

			Tr trRow4 = new Tr();
			trRow4.appendChild(
					new Td().setStyle(
							"width: 100px; vertical-align: top; text-align: right;")
							.appendText("Phone Number:"))
					.appendChild(
							new Td().appendText((cases.getPhonenumber() != null) ? cases
									.getPhonenumber() : ""));
			trRow4.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Assignee:"))
					.appendChild(
							new Td().setStyle(
									"width: 150px;word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
									.appendChild(
											new A().setHref(
													(cases.getAssignuser() != null) ? UserLinkUtils
															.generatePreviewFullUserLink(
																	AppContext
																			.getSiteUrl(),
																	cases.getAssignuser())
															: "")
													.appendChild(
															new Img(
																	"",
																	UserAvatarControlFactory
																			.getAvatarLink(
																					cases.getAssignUserAvatarId(),
																					16)))
													.appendText(
															(cases.getAssignUserFullName() != null) ? cases
																	.getAssignUserFullName()
																	: "")));

			Tr trRow5 = new Tr();
			Td trRow5_value = new Td()
					.setStyle(
							"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
					.appendText(
							(cases.getDescription() != null) ? (cases
									.getDescription().length() > 200) ? cases
									.getDescription().substring(0, 200) : cases
									.getDescription() : "");
			trRow5_value.setAttribute("colspan", "3");

			trRow5.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Description:")).appendChild(
					trRow5_value);

			Tr trRow6 = new Tr();
			Td trRow6_value = new Td()
					.setStyle(
							"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
					.appendText(
							(cases.getResolution() != null) ? (cases
									.getResolution().length() > 200) ? cases
									.getResolution().substring(0, 200) : cases
									.getResolution() : "");
			trRow6_value.setAttribute("colspan", "3");

			trRow6.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Resolution:")).appendChild(
					trRow6_value);

			table.appendChild(trRow1);
			table.appendChild(trRow2);
			table.appendChild(trRow3);
			table.appendChild(trRow4);
			table.appendChild(trRow5);
			table.appendChild(trRow6);
			div.appendChild(table);
			return div.write();
		} catch (Exception e) {
			return "";
		}
	}
}
