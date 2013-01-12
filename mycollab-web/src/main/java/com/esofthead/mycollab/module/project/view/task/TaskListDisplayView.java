package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.vaadin.mvp.View;

public interface TaskListDisplayView extends View {

    void insertTaskList(SimpleTaskList taskList);

    void displayTakLists();
}
