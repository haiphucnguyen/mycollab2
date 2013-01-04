/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.common.ActivityStreamConstants;
import com.esofthead.mycollab.common.domain.SimpleActivityStream;
import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.common.service.ActivityStreamService;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.vaadin.ui.BeanPagedList;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UserLink;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
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

        this.setWidth("400px");

        activityStreamList = new BeanPagedList<ActivityStreamService, ActivityStreamSearchCriteria, SimpleActivityStream>(AppContext.getSpringBean(ActivityStreamService.class), ActivityStreamRowDisplayHandler.class);
        ActivityStreamSearchCriteria searchCriteria = new ActivityStreamSearchCriteria();
        searchCriteria.setModuleSet(new SetSearchField<String>(SearchField.AND, new String[]{"Crm"}));
        activityStreamList.setSearchCriteria(searchCriteria);
        this.addComponent(activityStreamList);
    }

    public static class ActivityStreamRowDisplayHandler implements BeanPagedList.RowDisplayHandler<SimpleActivityStream> {

        @Override
        public Component generateRow(SimpleActivityStream activityStream, int rowIndex) {
            VerticalLayout layout = new VerticalLayout();
            HorizontalLayout header = new HorizontalLayout();
            header.addComponent(new UserLink(activityStream.getCreateduser(), activityStream.getCreatedUserFullName()));
            StringBuilder action = new StringBuilder();

            System.out.println(BeanUtility.printBeanObj(activityStream));

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

    private static class ActivitylLink extends HorizontalLayout {

        public ActivitylLink(String type, String fieldName, int typeid) {
            this.setSpacing(true);
            Embedded icon = new Embedded(null);
            
            if (CrmTypeConstants.ACCOUNT.equals(type)) {
                
            } else if (CrmTypeConstants.CAMPAIGN.equals(type)) {
                
            }else if (CrmTypeConstants.CASE.equals(type)) {
                
            }else if (CrmTypeConstants.CONTACT.equals(type)) {
                
            }else if (CrmTypeConstants.LEAD.equals(type)) {
                
            }else if (CrmTypeConstants.OPPORTUNITY.equals(type)) {
                
            }else if (CrmTypeConstants.PRODUCT.equals(type)) {
                
            }else if (CrmTypeConstants.QUOTE.equals(type)) {
                
            }else if (CrmTypeConstants.TARGET.equals(type)) {
                
            }
            
            Button linkBtn = new Button(fieldName, new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    
                }
            }); 
            
            this.addComponent(linkBtn);
            this.addComponent(icon);
        }
    }
}
