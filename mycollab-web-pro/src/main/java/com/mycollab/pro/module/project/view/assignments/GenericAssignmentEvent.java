package com.mycollab.pro.module.project.view.assignments;

import com.mycollab.module.project.domain.ProjectTicket;

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
// TODO
public class GenericAssignmentEvent {
    private ProjectTicket ticket;

    public GenericAssignmentEvent(ProjectTicket ticket, boolean showProject) {
//        this.ticket = ticket;
//        FontAwesome icon = ProjectAssetsManager.getAsset(ticket.getType());
//        if (showProject) {
//            this.setCaption(String.format("%s [%s] %s", icon.getHtml(), ticket.getProjectShortName(), ticket.getName()));
//        } else {
//            this.setCaption(String.format("%s %s", icon.getHtml(), ticket.getName()));
//        }

//        this.setDescription(ProjectTooltipGenerator.generateTooltipEntity(UserUIContext.getUserLocale(), AppUI.getDateFormat(),
//                ticket.getType(), ticket.getTypeId(), AppUI.getAccountId(), AppUI.getSiteUrl(),
//                UserUIContext.getUserTimeZone(), showProject));
//        this.setAllDay(true);
//
//        if (UserUIContext.getUsername().equals(ticket.getAssignUser())) {
//            this.setStyleName("owner");
//        } else if (ticket.getAssignUser() == null) {
//            this.setStyleName("nonowner");
//        } else {
//            this.setStyleName("otheruser");
//        }
//
//        // task has not start and end has both null
//        if (ticket.getStartDate() == null) {
//            ticket.setStartDate(ticket.getEndDate());
//        }
//        if (ticket.getEndDate() == null) {
//            ticket.setEndDate(ticket.getStartDate());
//        }
//
//        this.setStart(ticket.getStartDate());
//        this.setEnd(ticket.getEndDate());
    }

    public ProjectTicket getTicket() {
        return ticket;
    }

//    @Override
//    public void setStart(Date start) {
//        super.setStart(start);
//        ticket.setStartDate(start);
//    }
//
//    @Override
//    public void setEnd(Date end) {
//        super.setEnd(end);
//        ticket.setEndDate(end);
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof GenericAssignmentEvent)) return false;
//
//        GenericAssignmentEvent assignmentEvent = (GenericAssignmentEvent) o;
//        return (ticket.getType().equals(assignmentEvent.ticket.getType())) &&
//                (ticket.getTypeId() == assignmentEvent.ticket.getTypeId());
//
//    }

    public void updateAssociateEntity() {
//        if (ProjectTypeConstants.TASK.equals(ticket.getType()) &&
//                CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS)) {
//            ProjectTaskService taskService = AppContextUtil.getSpringBean(ProjectTaskService.class);
//            SimpleTask task = taskService.findById(ticket.getTypeId(), AppUI.getAccountId());
//            task.setStartdate(getStart());
//            task.setEnddate(getEnd());
//            taskService.updateWithSession(task, UserUIContext.getUsername());
//        } else if (ProjectTypeConstants.BUG.equals(ticket.getType()) &&
//                CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS)) {
//            BugService bugService = AppContextUtil.getSpringBean(BugService.class);
//            SimpleBug bug = bugService.findById(ticket.getTypeId(), AppUI.getAccountId());
//            bug.setStartdate(getStart());
//            bug.setEnddate(getEnd());
//            bugService.updateWithSession(bug, UserUIContext.getUsername());
//        } else if(ProjectTypeConstants.MILESTONE.equals(ticket.getType()) &&
//                CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.MILESTONES)) {
//            MilestoneService milestoneService = AppContextUtil.getSpringBean(MilestoneService.class);
//            SimpleMilestone milestone = milestoneService.findById(ticket.getTypeId(), AppUI.getAccountId());
//            milestone.setStartdate(getStart());
//            milestone.setEnddate(getEnd());
//            milestoneService.updateWithSession(milestone, UserUIContext.getUsername());
//        } else if (ProjectTypeConstants.RISK.equals(ticket.getType()) &&
//                CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.RISKS)) {
//            RiskService riskService = AppContextUtil.getSpringBean(RiskService.class);
//            SimpleRisk risk = riskService.findById(ticket.getTypeId(), AppUI.getAccountId());
//            risk.setStartdate(getStart());
//            risk.setEnddate(getEnd());
//            riskService.updateWithSession(risk, UserUIContext.getUsername());
//        }
    }
}
