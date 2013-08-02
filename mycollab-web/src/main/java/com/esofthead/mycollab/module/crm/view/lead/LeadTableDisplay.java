/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.lead;

import java.util.List;

import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UrlLink;
import com.esofthead.mycollab.vaadin.ui.UserLink;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.esofthead.mycollab.web.AppContext;
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
		PagedBeanTable2<LeadService, LeadSearchCriteria, SimpleLead> {

	public LeadTableDisplay(List<TableViewField> displayColumns) {
		this(null, displayColumns);
	}

	public LeadTableDisplay(TableViewField requiredColumn,
			List<TableViewField> displayColumns) {
		this(null, requiredColumn, displayColumns);

	}

	public LeadTableDisplay(String viewId, TableViewField requiredColumn,
			List<TableViewField> displayColumns) {
		super(AppContext.getSpringBean(LeadService.class), SimpleLead.class,
				viewId, requiredColumn, displayColumns);

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
}
