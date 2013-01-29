package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.ui.components.RelatedListComp;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunityTableDisplay;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;

public class AccountOpportunityListComp extends RelatedListComp<OpportunitySearchCriteria> {

    private static final long serialVersionUID = 1L;

    public AccountOpportunityListComp() {
        super("Opportunities");

        initUI();
    }

    private void initUI() {
        VerticalLayout contentContainer = (VerticalLayout) bodyContent;
        contentContainer.setSpacing(true);

        Button newBtn = new Button("New Opportunity", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                fireNewRelatedItem("");
            }
        });
        newBtn.setIcon(new ThemeResource("icons/16/addRecordGreen.png"));
        newBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
        newBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.CRM_OPPORTUNITY));

        contentContainer.addComponent(newBtn);

        tableItem = new OpportunityTableDisplay(new String[]{"opportunityname",
                    "salesstage", "amount", "expectedcloseddate", "id"}, new String[]{"Name",
                    "Sales Stage", "Amount", "Close", "Action"});

        tableItem.addTableListener(new ApplicationEventListener<TableClickEvent>() {
            @Override
            public Class<? extends ApplicationEvent> getEventType() {
                return TableClickEvent.class;
            }

            @Override
            public void handle(TableClickEvent event) {
                SimpleOpportunity opportunity = (SimpleOpportunity) event.getData();
                if ("opportunityname".equals(event.getFieldName())) {
                    EventBus.getInstance().fireEvent(
                            new OpportunityEvent.GotoRead(this,
                            opportunity.getId()));
                }
            }
        });

        contentContainer.addComponent(tableItem);
    }
}
