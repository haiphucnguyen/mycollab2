package com.esofthead.mycollab.module.project.view.task.calendar;

import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.vaadin.ui.components.calendar.event.BasicEvent;

/**
 * @author MyCollab Ltd
 * @since 5.1.5
 */
public class GenericTaskEvent extends BasicEvent {
    private ProjectGenericTask assignment;

    public GenericTaskEvent(ProjectGenericTask assignment) {
        this.assignment = assignment;
        this.setCaption(assignment.getName());
        this.setDescription(assignment.getDescription());
    }
}
