package com.esofthead.mycollab.premium.module.project.view.task;

import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.view.task.calendar.GenericTaskEvent;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.ui.components.calendar.event.BasicEventProvider;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.1
 */
public class AgreegateGenericCalendarProvider extends BasicEventProvider {
    private Double totalBillableHours = 0d;
    private Double totalNonBillableHours = 0d;
    private int assignMeNum = 0;
    private int assignOthersNum = 0;
    private int notAssignNum = 0;

    public void loadEvents(TaskSearchCriteria searchCriteria) {
        ProjectTaskService genericTaskService = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
        List<SimpleTask> assignments = genericTaskService.findPagableListByCriteria(new SearchRequest<>
                (searchCriteria, 0, Integer.MAX_VALUE));
        for (SimpleTask assignment : assignments) {
            totalBillableHours += assignment.getBillableHours();
            totalNonBillableHours += assignment.getNonBillableHours();
            if (AppContext.getUsername().equals(assignment.getAssignuser())) {
                assignMeNum += 1;
            } else if (assignment.getAssignuser() == null) {
                notAssignNum += 1;
            } else {
                assignOthersNum += 1;
            }
            addEvent(new GenericTaskEvent(assignment));
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
