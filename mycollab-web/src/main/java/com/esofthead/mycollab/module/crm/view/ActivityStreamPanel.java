/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.common.ActivityStreamConstants;
import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.domain.SimpleActivityStream;
import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.common.service.ActivityStreamService;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.crm.events.CaseEvent;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.BeanPagedList;
import com.esofthead.mycollab.vaadin.ui.UserLink;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public class ActivityStreamPanel extends Panel {

    private BeanPagedList<ActivityStreamService, ActivityStreamSearchCriteria, SimpleActivityStream> activityStreamList;

    public ActivityStreamPanel() {
        super("Activity Channels");

        activityStreamList = new BeanPagedList<ActivityStreamService, ActivityStreamSearchCriteria, SimpleActivityStream>(AppContext.getSpringBean(ActivityStreamService.class), ActivityStreamRowDisplayHandler.class);
        ActivityStreamSearchCriteria searchCriteria = new ActivityStreamSearchCriteria();
        searchCriteria.setModuleSet(new SetSearchField<String>(SearchField.AND, new String[]{ModuleNameConstants.CRM}));
        activityStreamList.setSearchCriteria(searchCriteria);
        this.addComponent(activityStreamList);
    }

    public static class ActivityStreamRowDisplayHandler implements BeanPagedList.RowDisplayHandler<SimpleActivityStream> {

        @Override
        public Component generateRow(SimpleActivityStream activityStream, int rowIndex) {
            VerticalLayout layout = new VerticalLayout();
            HorizontalLayout header = new HorizontalLayout();
            header.setSpacing(true);
            header.addComponent(new UserLink(activityStream.getCreateduser(), activityStream.getCreatedUserFullName()));
            StringBuilder action = new StringBuilder();

            if (ActivityStreamConstants.ACTION_CREATE.equals(activityStream.getAction())) {
                action.append("create a new ");
            } else if (ActivityStreamConstants.ACTION_UPDATE.equals(activityStream.getAction())) {
                action.append("update ");
            }

            action.append(activityStream.getType());
            header.addComponent(new Label(action.toString()));
            header.addComponent(new ActivitylLink(activityStream.getType(), activityStream.getNamefield(), activityStream.getTypeid()));
            layout.addComponent(header);
            return layout;
        }
    }

    private static class ActivitylLink extends Button {

        public ActivitylLink(final String type, final String fieldName, final int typeid) {
            super(fieldName);

            if (CrmTypeConstants.ACCOUNT.equals(type)) {
                this.setIcon(new ThemeResource("icons/16/crm/account.png"));
            } else if (CrmTypeConstants.CAMPAIGN.equals(type)) {
                this.setIcon(new ThemeResource("icons/16/crm/campaign.png"));
            } else if (CrmTypeConstants.CASE.equals(type)) {
                this.setIcon(new ThemeResource("icons/16/crm/case.png"));
            } else if (CrmTypeConstants.CONTACT.equals(type)) {
                this.setIcon(new ThemeResource("icons/16/crm/contact.png"));
            } else if (CrmTypeConstants.LEAD.equals(type)) {
                this.setIcon(new ThemeResource("icons/16/crm/lead.png"));
            } else if (CrmTypeConstants.OPPORTUNITY.equals(type)) {
                this.setIcon(new ThemeResource("icons/16/crm/opportunity.png"));
            } else if (CrmTypeConstants.TASK.equals(type)) {
                this.setIcon(new ThemeResource("icons/16/crm/task.png"));
            } else if (CrmTypeConstants.MEETING.equals(type)) {
                this.setIcon(new ThemeResource("icons/16/crm/meeting.png"));
            } else if (CrmTypeConstants.CALL.equals(type)) {
                this.setIcon(new ThemeResource("icons/16/crm/call.png"));
            }

            this.setStyleName("link");
            this.addListener(new Button.ClickListener() {
                @Override
                public void buttonClick(ClickEvent event) {
                    if (CrmTypeConstants.ACCOUNT.equals(type)) {
                        EventBus.getInstance().fireEvent(new AccountEvent.GotoRead(this, typeid));
                    } else if (CrmTypeConstants.CAMPAIGN.equals(type)) {
                        EventBus.getInstance().fireEvent(new CampaignEvent.GotoRead(this, typeid));
                    } else if (CrmTypeConstants.CASE.equals(type)) {
                        EventBus.getInstance().fireEvent(new CaseEvent.GotoRead(this, typeid));
                    } else if (CrmTypeConstants.CONTACT.equals(type)) {
                        EventBus.getInstance().fireEvent(new ContactEvent.GotoRead(this, typeid));
                    } else if (CrmTypeConstants.LEAD.equals(type)) {
                        EventBus.getInstance().fireEvent(new LeadEvent.GotoRead(this, typeid));
                    } else if (CrmTypeConstants.OPPORTUNITY.equals(type)) {
                        EventBus.getInstance().fireEvent(new OpportunityEvent.GotoRead(this, typeid));
                    } else if (CrmTypeConstants.TASK.equals(type)) {
                    } else if (CrmTypeConstants.MEETING.equals(type)) {
                    } else if (CrmTypeConstants.CALL.equals(type)) {
                    }
                }
            });
        }
    }
}
