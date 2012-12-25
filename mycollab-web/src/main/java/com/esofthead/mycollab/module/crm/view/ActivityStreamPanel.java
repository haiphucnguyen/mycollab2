/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.common.domain.SimpleActivityStream;
import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.common.service.ActivityStreamService;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.vaadin.ui.BeanPagedList;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

/**
 *
 * @author haiphucnguyen
 */
public class ActivityStreamPanel extends Panel {

    private BeanPagedList<ActivityStreamService, ActivityStreamSearchCriteria, SimpleActivityStream> activityStreamList;

    public ActivityStreamPanel() {
        super("Activity Channels");
        
        activityStreamList = new BeanPagedList<ActivityStreamService, ActivityStreamSearchCriteria, SimpleActivityStream>(AppContext.getSpringBean(ActivityStreamService.class), new ActivityStreamRowDisplayHandler());
        ActivityStreamSearchCriteria searchCriteria = new ActivityStreamSearchCriteria();
        searchCriteria.setModuleSet(new SetSearchField<String>(SearchField.AND, new String[]{"Crm"}));
        activityStreamList.setSearchCriteria(searchCriteria);
        this.addComponent(activityStreamList);
    }
    
    class ActivityStreamRowDisplayHandler implements BeanPagedList.RowDisplayHandler<SimpleActivityStream> {

        @Override
        public Component generateRow(SimpleActivityStream obj, int rowIndex) {
            return new Label("aaa");
        }
        
    }
}
