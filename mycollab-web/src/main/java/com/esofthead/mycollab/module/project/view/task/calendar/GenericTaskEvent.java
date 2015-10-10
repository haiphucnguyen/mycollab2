package com.esofthead.mycollab.module.project.view.task.calendar;

import com.esofthead.mycollab.module.project.ProjectTooltipGenerator;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.ui.components.calendar.event.BasicEvent;

import java.util.Date;

/**
 * @author MyCollab Ltd
 * @since 5.1.5
 */
public class GenericTaskEvent extends BasicEvent {
    private SimpleTask assignment;

    public GenericTaskEvent(SimpleTask assignment) {
        this.assignment = assignment;
        this.setCaption(assignment.getTaskname());
        this.setDescription(ProjectTooltipGenerator.generateToolTipTask(AppContext.getUserLocale(), assignment,
                AppContext.getSiteUrl(), AppContext.getTimezone()));
        this.setAllDay(true);

        if (AppContext.getUsername().equals(assignment.getAssignuser())) {
            this.setStyleName("owner");
        } else if (assignment.getAssignuser() == null) {
            this.setStyleName("nonowner");
        } else {
            this.setStyleName("otheruser");
        }

        // task has not start and end has both null
        if (assignment.getStartdate() == null) {
            assignment.setStartdate(assignment.getEnddate());
        }
        if (assignment.getEnddate() == null) {
            assignment.setEnddate(assignment.getStartdate());
        }

        this.setStart(assignment.getStartdate());
        this.setEnd(assignment.getEnddate());
    }

    public SimpleTask getAssignment() {
        return assignment;
    }

    @Override
    public void setStart(Date start) {
        super.setStart(start);
        assignment.setStartdate(start);
    }

    @Override
    public void setEnd(Date end) {
        super.setEnd(end);
        assignment.setEnddate(end);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GenericTaskEvent)) return false;

        GenericTaskEvent taskEvent = (GenericTaskEvent) o;
        return (assignment.getId().intValue() == taskEvent.assignment.getId().intValue());

    }

    @Override
    public int hashCode() {
        return assignment.hashCode();
    }
}
