package com.mycollab.pro.module.project.view.assignments;

import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.module.project.domain.ProjectTicket;
import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria;
import com.mycollab.module.project.service.ProjectTicketService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;
import com.google.common.base.MoreObjects;
import com.vaadin.ui.components.calendar.event.BasicEventProvider;

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

    public void loadEvents(ProjectTicketSearchCriteria searchCriteria, boolean showProject) {
        ProjectTicketService genericTaskService = AppContextUtil.getSpringBean(ProjectTicketService.class);
        List<ProjectTicket> assignments = genericTaskService.findPageableListByCriteria(new BasicSearchRequest<>(searchCriteria));
        for (ProjectTicket assignment : assignments) {
            totalBillableHours += MoreObjects.firstNonNull(assignment.getBillableHours(), 0d);
            totalNonBillableHours += MoreObjects.firstNonNull(assignment.getNonBillableHours(), 0d);
            if (UserUIContext.getUsername().equals(assignment.getAssignUser())) {
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
