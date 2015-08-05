package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.SimpleTask;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public class SimpleListOrderComponent extends TaskGroupOrderComponent {
    public SimpleListOrderComponent() {
        this.setStyleName("tasklist");
    }

    @Override
    void insertTasks(List<SimpleTask> tasks) {
        for (SimpleTask task : tasks) {
            this.addComponent(new TaskRowRenderer(task));
        }
    }
}
