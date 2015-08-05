package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.vaadin.ui.Label;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public class StartDateOrderComponent extends TaskGroupOrderComponent {
    @Override
    void insertTasks(List<SimpleTask> tasks) {
        for (SimpleTask task : tasks) {
            this.addComponent(new Label(task.getTaskname()));
        }
    }
}
