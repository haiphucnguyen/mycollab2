/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

/**
 *
 * @author haiphucnguyen
 */
public class CampaignTableDisplay extends PagedBeanTable2<CampaignService, CampaignSearchCriteria, SimpleCampaign> {

    public CampaignTableDisplay(final String[] visibleColumns, String[] columnHeaders) {
        super(AppContext.getSpringBean(CampaignService.class),
                SimpleCampaign.class, visibleColumns, columnHeaders);

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

                    }
                });

                SimpleCampaign campaign = CampaignTableDisplay.this.getBeanByIndex(itemId);
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
                ButtonLink b = new ButtonLink(campaign.getCampaignname(),
                        new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                fireTableEvent(new TableClickEvent(CampaignTableDisplay.this, campaign, "campaignname"));
                            }
                        });
                b.addStyleName("medium-text");
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

        this.setColumnExpandRatio("campaignname", 1.0f);
        this.setColumnWidth("selected", UIConstants.TABLE_CONTROL_WIDTH);
        this.setColumnWidth("status", UIConstants.TABLE_M_LABEL_WIDTH);
        this.setColumnWidth("type", UIConstants.TABLE_M_LABEL_WIDTH);
        this.setColumnWidth("expectedrevenue",
                UIConstants.TABLE_X_LABEL_WIDTH);
        this.setColumnWidth("startdate", UIConstants.TABLE_DATE_WIDTH);
        this.setColumnWidth("enddate", UIConstants.TABLE_DATE_WIDTH);
        this.setColumnWidth("assignUserFullName",
                UIConstants.TABLE_X_LABEL_WIDTH);
    }
}
