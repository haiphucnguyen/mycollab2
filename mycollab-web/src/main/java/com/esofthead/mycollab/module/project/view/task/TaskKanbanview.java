package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.view.IKanbanView;
import com.esofthead.mycollab.vaadin.mvp.PageView;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public interface TaskKanbanview extends PageView, IKanbanView {
    void display();
}
