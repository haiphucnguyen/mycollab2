/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.VerticalLayout;
import java.util.List;

/**
 *
 * @author haiphucnguyen
 */
public class UnresolvedBugsByAssigneeWidget extends Depot {
	private static final long serialVersionUID = 1L;

	private IBugReportDisplayContainer componentLayout;
	
	public UnresolvedBugsByAssigneeWidget(IBugReportDisplayContainer componentLayout) {
        super("Unresolved by assignee", new VerticalLayout());
        
        this.componentLayout = componentLayout;
    }
    
    public void setSearchCriteria(BugSearchCriteria searchCriteria) {
        
        BugService bugService = AppContext.getSpringBean(BugService.class);
        int totalCount = bugService.getTotalCount(searchCriteria);
        List<GroupItem> groupItems = bugService.getAssignedDefectsSummary(searchCriteria);
        if (!groupItems.isEmpty()) {
            for (GroupItem item : groupItems) {
                HorizontalLayout assigneeLayout = new HorizontalLayout();
                assigneeLayout.setSpacing(true);
                Label userLbl = new Label();
                if (item.getGroupid() == null) {
                    userLbl.setValue("Undefined");
                } else {
                    userLbl.setValue(item.getGroupname());
                }
                assigneeLayout.addComponent(userLbl);
                ProgressIndicator indicator = new ProgressIndicator(new Float((float)item.getValue() / totalCount));
                indicator.setPollingInterval(1000000000);
                assigneeLayout.addComponent(indicator);
                
                Label progressLbl = new Label("(" + item.getValue() + "/" + totalCount + ")");
                assigneeLayout.addComponent(progressLbl);
                bodyContent.addComponent(assigneeLayout);
            }
            
            
        }
    }
}
