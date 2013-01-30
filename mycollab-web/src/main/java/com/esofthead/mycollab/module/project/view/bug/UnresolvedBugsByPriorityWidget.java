/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.module.project.ProjectDataTypeFactory;
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
public class UnresolvedBugsByPriorityWidget extends Depot {

    public UnresolvedBugsByPriorityWidget() {
        super("Unresolved by Priority", new VerticalLayout());
    }

    public void setSearchCriteria(BugSearchCriteria searchCriteria) {

        BugService bugService = AppContext.getSpringBean(BugService.class);
        int totalCount = bugService.getTotalCount(searchCriteria);
        List<GroupItem> groupItems = bugService.getPrioritySummary(searchCriteria);
        if (!groupItems.isEmpty()) {
            for (String status : ProjectDataTypeFactory.getBugPriorityList()) {
                boolean isFound = false;
                for (GroupItem item : groupItems) {
                    if (status.equals(item.getGroupid())) {
                        isFound = true;
                        HorizontalLayout priorityLayout = new HorizontalLayout();
                        priorityLayout.setSpacing(true);
                        Label userLbl = new Label(status);

                        priorityLayout.addComponent(userLbl);
                        ProgressIndicator indicator = new ProgressIndicator(new Float((float) item.getValue() / totalCount));
                        priorityLayout.addComponent(indicator);

                        Label progressLbl = new Label("(" + item.getValue() + "/" + totalCount + ")");
                        priorityLayout.addComponent(progressLbl);
                        bodyContent.addComponent(priorityLayout);
                        continue;
                    }
                }

                if (!isFound) {
                    HorizontalLayout priorityLayout = new HorizontalLayout();
                    priorityLayout.setSpacing(true);
                    Label userLbl = new Label(status);
                    priorityLayout.addComponent(userLbl);
                    ProgressIndicator indicator = new ProgressIndicator(0f);
                    priorityLayout.addComponent(indicator);

                    Label progressLbl = new Label("(" + 0 + "/" + totalCount + ")");
                    priorityLayout.addComponent(progressLbl);
                    bodyContent.addComponent(priorityLayout);
                }
            }



        }
    }
}
