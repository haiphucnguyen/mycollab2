package com.esofthead.mycollab.module.project.view.task.calendar;

import com.esofthead.mycollab.core.arguments.*;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.ui.components.calendar.event.BasicEventProvider;

import java.util.Date;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.1.5
 */
public class GenericTaskProvider extends BasicEventProvider {
    private Double totalBillableHours = 0d;
    private Double totalNonBillableHours = 0d;
    private int assignMeNum = 0;
    private int assignOthersNum = 0;
    private int notAssignNum = 0;

    public void loadEvents(Date start, Date end) {
        TaskSearchCriteria searchCriteria = new TaskSearchCriteria();
        searchCriteria.setProjectid(new NumberSearchField(CurrentProjectVariables.getProjectId()));
        searchCriteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
        CompositionSearchField compoField = new CompositionSearchField(SearchField.AND);
        compoField.addField(new BetweenValuesSearchField("", "m_prj_task.startdate BETWEEN ", start, end));
        compoField.addField(new BetweenValuesSearchField("", "m_prj_task.enddate BETWEEN ", start, end));
        searchCriteria.addExtraField(compoField);

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
