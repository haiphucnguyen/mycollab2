package com.esofthead.mycollab.pro.module.project.view.assignments;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTooltipGenerator;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.service.MilestoneService;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.service.RiskService;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.components.calendar.event.BasicEvent;

import java.util.Date;

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
public class GenericAssignmentEvent extends BasicEvent {
    private ProjectGenericTask assignment;

    public GenericAssignmentEvent(ProjectGenericTask assignment, boolean showProject) {
        this.assignment = assignment;
        FontAwesome icon = ProjectAssetsManager.getAsset(assignment.getType());
        if (showProject) {
            this.setCaption(String.format("%s [%s] %s", icon.getHtml(), assignment.getProjectShortName(), assignment.getName()));
        } else {
            this.setCaption(String.format("%s %s", icon.getHtml(), assignment.getName()));
        }

        this.setDescription(ProjectTooltipGenerator.generateTooltipEntity(AppContext.getUserLocale(), AppContext.getDateFormat().toPattern(),
                assignment.getType(), assignment.getTypeId(), AppContext.getAccountId(), AppContext.getSiteUrl(),
                AppContext.getUserTimeZone(), showProject));
        this.setAllDay(true);

        if (AppContext.getUsername().equals(assignment.getAssignUser())) {
            this.setStyleName("owner");
        } else if (assignment.getAssignUser() == null) {
            this.setStyleName("nonowner");
        } else {
            this.setStyleName("otheruser");
        }

        // task has not start and end has both null
        if (assignment.getStartDate() == null) {
            assignment.setStartDate(assignment.getEndDate());
        }
        if (assignment.getEndDate() == null) {
            assignment.setEndDate(assignment.getStartDate());
        }

        this.setStart(assignment.getStartDate());
        this.setEnd(assignment.getEndDate());
    }

    public ProjectGenericTask getAssignment() {
        return assignment;
    }

    @Override
    public void setStart(Date start) {
        super.setStart(start);
        assignment.setStartDate(start);
    }

    @Override
    public void setEnd(Date end) {
        super.setEnd(end);
        assignment.setEndDate(end);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GenericAssignmentEvent)) return false;

        GenericAssignmentEvent assignmentEvent = (GenericAssignmentEvent) o;
        return (assignment.getType().equals(assignmentEvent.assignment.getType())) &&
                (assignment.getTypeId() == assignmentEvent.assignment.getTypeId());

    }

    @Override
    public int hashCode() {
        return assignment.hashCode();
    }

    public void updateAssociateEntity() {
        if (ProjectTypeConstants.TASK.equals(assignment.getType()) &&
                CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS)) {
            ProjectTaskService taskService = AppContextUtil.getSpringBean(ProjectTaskService.class);
            SimpleTask task = taskService.findById(assignment.getTypeId(), AppContext.getAccountId());
            task.setStartdate(getStart());
            task.setEnddate(getEnd());
            taskService.updateWithSession(task, AppContext.getUsername());
        } else if (ProjectTypeConstants.BUG.equals(assignment.getType()) &&
                CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS)) {
            BugService bugService = AppContextUtil.getSpringBean(BugService.class);
            SimpleBug bug = bugService.findById(assignment.getTypeId(), AppContext.getAccountId());
            bug.setStartdate(getStart());
            bug.setEnddate(getEnd());
            bugService.updateWithSession(bug, AppContext.getUsername());
        } else if(ProjectTypeConstants.MILESTONE.equals(assignment.getType()) &&
                CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.MILESTONES)) {
            MilestoneService milestoneService = AppContextUtil.getSpringBean(MilestoneService.class);
            SimpleMilestone milestone = milestoneService.findById(assignment.getTypeId(), AppContext.getAccountId());
            milestone.setStartdate(getStart());
            milestone.setEnddate(getEnd());
            milestoneService.updateWithSession(milestone, AppContext.getUsername());
        } else if (ProjectTypeConstants.RISK.equals(assignment.getType()) &&
                CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.RISKS)) {
            RiskService riskService = AppContextUtil.getSpringBean(RiskService.class);
            SimpleRisk risk = riskService.findById(assignment.getTypeId(), AppContext.getAccountId());
            risk.setStartdate(getStart());
            risk.setEnddate(getEnd());
            riskService.updateWithSession(risk, AppContext.getUsername());
        }
    }
}
