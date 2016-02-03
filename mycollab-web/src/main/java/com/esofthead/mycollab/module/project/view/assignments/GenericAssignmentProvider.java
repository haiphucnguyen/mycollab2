/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.view.assignments;

import com.esofthead.mycollab.core.arguments.*;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectGenericTaskService;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
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

    public void loadEvents(Date start, Date end) {
        ProjectGenericTaskSearchCriteria searchCriteria = new ProjectGenericTaskSearchCriteria();
        searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
        searchCriteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
        RangeDateSearchField dateRange = new RangeDateSearchField(start, end);
        searchCriteria.addExtraField(dateRange);

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
            addEvent(new GenericAssignmentEvent(assignment));
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
