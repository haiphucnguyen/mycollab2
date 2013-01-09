/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.vaadin.events.EventBus;
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
public class OpportunityTableDisplay extends PagedBeanTable2<OpportunityService, OpportunitySearchCriteria, SimpleOpportunity> {

    public OpportunityTableDisplay(final String[] visibleColumns, String[] columnHeaders) {
        super(AppContext.getSpringBean(OpportunityService.class),
                SimpleOpportunity.class, visibleColumns, columnHeaders);

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
                        OpportunityTableDisplay.this.fireSelectItemEvent(opportunity);
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
                                fireTableEvent(new TableClickEvent(OpportunityTableDisplay.this, opportunity, "opportunityname"));
                            }
                        });
                b.addStyleName("medium-text");
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

        this.addGeneratedColumn("expectedcloseddate",
                new Table.ColumnGenerator() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public com.vaadin.ui.Component generateCell(Table source,
                            Object itemId, Object columnId) {
                        final SimpleOpportunity opportunity = OpportunityTableDisplay.this
                                .getBeanByIndex(itemId);
                        Label l = new Label();
                        l.setValue(AppContext.formatDateTime(opportunity
                                .getExpectedcloseddate()));
                        return l;
                    }
                });

        this.setWidth("100%");

        this.setColumnExpandRatio("opportunityname", 1.0f);

        this.setColumnWidth("selected", UIConstants.TABLE_CONTROL_WIDTH);
        this.setColumnWidth("salesstage", UIConstants.TABLE_M_LABEL_WIDTH);
        this.setColumnWidth("amount", UIConstants.TABLE_M_LABEL_WIDTH);
        this.setColumnWidth("accountName", UIConstants.TABLE_X_LABEL_WIDTH);
        
        this.setColumnWidth("expectedcloseddate",
                UIConstants.TABLE_DATE_TIME_WIDTH);
        this.setColumnWidth("assignUserFullName",
                UIConstants.TABLE_X_LABEL_WIDTH);
        this.setColumnWidth("createdtime", UIConstants.TABLE_DATE_TIME_WIDTH);
    }
}
