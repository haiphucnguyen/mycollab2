/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public class TaskDisplayComponent extends VerticalLayout {

    private BeanList<ProjectTaskService, TaskSearchCriteria, SimpleTask> taskDisplay;
    private Button createTaskBtn;

    public TaskDisplayComponent(final TaskList taskList) {
        this.setSpacing(true);
        taskDisplay = new BeanList<ProjectTaskService, TaskSearchCriteria, SimpleTask>(AppContext.getSpringBean(ProjectTaskService.class), TaskRowDisplayHandler.class);
        TaskSearchCriteria taskCriteria = new TaskSearchCriteria();
        taskCriteria.setTaskListId(new NumberSearchField(taskList.getId()));
        taskDisplay.setSearchCriteria(taskCriteria);

        this.addComponent(taskDisplay);

        createTaskBtn = new Button("Add Task", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Component comp = TaskDisplayComponent.this.getComponent(0);
                if (!(comp instanceof TaskAddPopup)) {
                    TaskAddPopup taskAddView = new TaskAddPopup(TaskDisplayComponent.this, taskList);
                    TaskDisplayComponent.this.addComponent(taskAddView, 0);
                    TaskDisplayComponent.this.removeComponent(createTaskBtn);
                }
            }
        });
        
        createTaskBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        this.addComponent(createTaskBtn);
    }

    public void saveTaskSuccess(SimpleTask task) {
        taskDisplay.insertItemOnTop(task);
    }

    public void closeTaskAdd() {
        this.addComponent(createTaskBtn);
        Component comp = this.getComponent(0);
        if (comp instanceof TaskAddPopup) {
            this.removeComponent(comp);
        }
    }

    public static class TaskRowDisplayHandler implements BeanList.RowDisplayHandler<SimpleTask> {

        @Override
        public Component generateRow(SimpleTask task, int rowIndex) {
            return new Label("Task: " + task.getTaskname());
        }
    }
}
