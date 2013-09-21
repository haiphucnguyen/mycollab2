/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.opportunity;

import java.util.GregorianCalendar;
import java.util.List;

import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
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
@SuppressWarnings("serial")
public class OpportunityTableDisplay
		extends
		PagedBeanTable2<OpportunityService, OpportunitySearchCriteria, SimpleOpportunity> {

	public OpportunityTableDisplay(List<TableViewField> displayColumns) {
		this(null, displayColumns);
	}

	public OpportunityTableDisplay(TableViewField requiredColumn,
			List<TableViewField> displayColumns) {
		this(null, requiredColumn, displayColumns);

	}

	public OpportunityTableDisplay(String viewId,
			TableViewField requiredColumn, List<TableViewField> displayColumns) {
		super(ApplicationContextUtil.getSpringBean(OpportunityService.class),
				SimpleOpportunity.class, viewId, requiredColumn, displayColumns);

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
						SimpleOpportunity opportunity = OpportunityTableDisplay.this
								.getBeanByIndex(itemId);
						OpportunityTableDisplay.this
								.fireSelectItemEvent(opportunity);
						fireTableEvent(new TableClickEvent(
								OpportunityTableDisplay.this, opportunity,
								"selected"));
					}
				});

				SimpleOpportunity opportunity = OpportunityTableDisplay.this
						.getBeanByIndex(itemId);
				opportunity.setExtraData(cb);
				return cb;
			}
		});

		this.addGeneratedColumn("opportunityname", new Table.ColumnGenerator() {
			@Override
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				final SimpleOpportunity opportunity = OpportunityTableDisplay.this
						.getBeanByIndex(itemId);
				ButtonLink b = new ButtonLink(opportunity.getOpportunityname(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(Button.ClickEvent event) {
								fireTableEvent(new TableClickEvent(
										OpportunityTableDisplay.this,
										opportunity, "opportunityname"));
							}
						});

				if ("Closed Won".equals(opportunity.getSalesstage())
						|| "Closed Lost".equals(opportunity.getSalesstage())) {
					b.addStyleName(UIConstants.LINK_COMPLETED);
				} else {
					if (opportunity.getExpectedcloseddate() != null
							&& (opportunity.getExpectedcloseddate()
									.before(new GregorianCalendar().getTime()))) {
						b.addStyleName(UIConstants.LINK_OVERDUE);
					}
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
						final SimpleOpportunity opportunity = OpportunityTableDisplay.this
								.getBeanByIndex(itemId);
						UserLink b = new UserLink(opportunity.getAssignuser(),
								opportunity.getAssignUserAvatarId(),
								opportunity.getAssignUserFullName());
						return b;

					}
				});

		this.addGeneratedColumn("accountName", new Table.ColumnGenerator() {
			@Override
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				final SimpleOpportunity opportunity = OpportunityTableDisplay.this
						.getBeanByIndex(itemId);
				ButtonLink b = new ButtonLink(opportunity.getAccountName(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(Button.ClickEvent event) {
								EventBus.getInstance().fireEvent(
										new AccountEvent.GotoRead(this,
												opportunity.getAccountid()));
							}
						});
				return b;
			}
		});

		this.addGeneratedColumn("campaignName", new Table.ColumnGenerator() {
			@Override
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				final SimpleOpportunity opportunity = OpportunityTableDisplay.this
						.getBeanByIndex(itemId);
				ButtonLink b = new ButtonLink(opportunity.getCampaignName(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(Button.ClickEvent event) {
								EventBus.getInstance().fireEvent(
										new CampaignEvent.GotoRead(this,
												opportunity.getCampaignid()));
							}
						});
				return b;
			}
		});

		this.addGeneratedColumn("expectedcloseddate",
				new Table.ColumnGenerator() {
					private static final long serialVersionUID = 1L;

					@Override
					public com.vaadin.ui.Component generateCell(Table source,
							Object itemId, Object columnId) {
						final SimpleOpportunity opportunity = OpportunityTableDisplay.this
								.getBeanByIndex(itemId);
						Label l = new Label();
						l.setValue(AppContext.formatDate(opportunity
								.getExpectedcloseddate()));
						return l;
					}
				});

		this.setWidth("100%");
	}
}
