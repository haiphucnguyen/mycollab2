package com.esofthead.mycollab.module.project.view.task.gantt;

import com.vaadin.ui.Window;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public class QuickEditTaskWindow extends Window {
    public QuickEditTaskWindow(GanttExt gantt, GanttItemWrapper task) {
        this.setWidth("600px");
        this.setModal(true);
        this.setResizable(false);
    }
}
