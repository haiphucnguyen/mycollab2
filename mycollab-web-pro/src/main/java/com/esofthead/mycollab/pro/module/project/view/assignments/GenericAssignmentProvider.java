package com.esofthead.mycollab.pro.module.project.view.assignments;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectGenericTaskService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.google.common.base.MoreObjects;
import com.vaadin.ui.components.calendar.event.BasicEventProvider;

import java.util.Date;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
public class GenericAssignmentProvider extends BasicEventProvider {
    private Double totalBillableHours = 0d;
    private Double totalNonBillableHours = 0d;
    private int assignMeNum = 0;
    private int assignOthersNum = 0;
    private int notAssignNum = 0;

    public void loadEvents(ProjectGenericTaskSearchCriteria searchCriteria, boolean showProject) {
        ProjectGenericTaskService genericTaskService = ApplicationContextUtil.getSpringBean(ProjectGenericTaskService.class);
        List<ProjectGenericTask> assignments = genericTaskService.findPagableListByCriteria(new SearchRequest<>
                (searchCriteria, 0, Integer.MAX_VALUE));
        for (ProjectGenericTask assignment : assignments) {
            totalBillableHours += MoreObjects.firstNonNull(assignment.getBillableHours(), 0d);
            totalNonBillableHours += MoreObjects.firstNonNull(assignment.getNonBillableHours(), 0d);
            if (AppContext.getUsername().equals(assignment.getAssignUser())) {
                assignMeNum += 1;
            } else if (assignment.getAssignUser() == null) {
                notAssignNum += 1;
            } else {
                assignOthersNum += 1;
            }
            addEvent(new GenericAssignmentEvent(assignment, showProject));
        }
        fireEventSetChange();
    }

    public Double getTotalBillableHours() {
        return totalBillableHours;
    }

    public Double getTotalNonBillableHours() {
        return totalNonBillableHours;
    }

    public int getAssignMeNum() {
        return assignMeNum;
    }

    public int getAssignOthersNum() {
        return assignOthersNum;
    }

    public int getNotAssignNum() {
        return notAssignNum;
    }
}
