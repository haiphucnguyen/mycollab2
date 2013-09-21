/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.campaign;

import java.util.GregorianCalendar;
import java.util.List;

import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
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
public class CampaignTableDisplay
		extends
		PagedBeanTable2<CampaignService, CampaignSearchCriteria, SimpleCampaign> {

	public CampaignTableDisplay(List<TableViewField> displayColumns) {
		this(null, displayColumns);
	}

	public CampaignTableDisplay(TableViewField requiredColumn,
			List<TableViewField> displayColumns) {
		this(null, requiredColumn, displayColumns);

	}

	public CampaignTableDisplay(String viewId, TableViewField requiredColumn,
			List<TableViewField> displayColumns) {
		super(ApplicationContextUtil.getSpringBean(CampaignService.class),
				SimpleCampaign.class, viewId, requiredColumn, displayColumns);
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
						SimpleCampaign campaign = CampaignTableDisplay.this
								.getBeanByIndex(itemId);
						CampaignTableDisplay.this.fireSelectItemEvent(campaign);
						fireTableEvent(new TableClickEvent(
								CampaignTableDisplay.this, campaign, "selected"));
					}
				});

				SimpleCampaign campaign = CampaignTableDisplay.this
						.getBeanByIndex(itemId);
				campaign.setExtraData(cb);
				return cb;
			}
		});

		this.addGeneratedColumn("campaignname", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				final SimpleCampaign campaign = CampaignTableDisplay.this
						.getBeanByIndex(itemId);
				Button b = new Button(campaign.getCampaignname(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(Button.ClickEvent event) {
								fireTableEvent(new TableClickEvent(
										CampaignTableDisplay.this, campaign,
										"campaignname"));
							}
						});
				b.setStyleName("link");

				if ("Complete".equals(campaign.getStatus())) {
					b.addStyleName(UIConstants.LINK_COMPLETED);
				} else {
					if (campaign.getEnddate() != null
							&& (campaign.getEnddate()
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
						final SimpleCampaign campaign = CampaignTableDisplay.this
								.getBeanByIndex(itemId);
						UserLink b = new UserLink(campaign.getAssignuser(),
								campaign.getAssignUserAvatarId(), campaign
										.getAssignUserFullName());
						return b;

					}
				});

		this.addGeneratedColumn("startdate", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				final SimpleCampaign campaign = CampaignTableDisplay.this
						.getBeanByIndex(itemId);
				Label l = new Label();

				l.setValue(AppContext.formatDate(campaign.getStartdate()));
				return l;
			}
		});

		this.addGeneratedColumn("enddate", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				final SimpleCampaign campaign = CampaignTableDisplay.this
						.getBeanByIndex(itemId);
				Label l = new Label();

				l.setValue(AppContext.formatDate(campaign.getEnddate()));
				return l;
			}
		});

		this.setWidth("100%");
	}
}
