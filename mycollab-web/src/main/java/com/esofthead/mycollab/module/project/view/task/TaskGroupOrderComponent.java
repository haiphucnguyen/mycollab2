package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.vaadin.ui.CssLayout;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
abstract class TaskGroupOrderComponent extends CssLayout {
    abstract void insertTasks(List<SimpleTask> tasks);
}
