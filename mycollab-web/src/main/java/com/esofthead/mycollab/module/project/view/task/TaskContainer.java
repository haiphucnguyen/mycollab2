/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

/**
 *
 * @author haiphucnguyen
 */
@ViewComponent
public class TaskContainer extends AbstractView {
	private static final long serialVersionUID = 1L;

	public static class PreviewTaskListData extends ScreenData<Integer> {

        public PreviewTaskListData(Integer taskListId) {
            super(taskListId);
        }
    }
    
    public static class ReorderTaskListRequest extends ScreenData {

        public ReorderTaskListRequest() {
            super(null);
        }
    }
    
    public static class EditTaskListData extends ScreenData<TaskList> {

        public EditTaskListData(TaskList taskList) {
            super(taskList);
        }
    }

    public static class AddTaskListData extends ScreenData<TaskList> {

        public AddTaskListData(TaskList taskList) {
            super(taskList);
        }
    }

    public static class PreviewTaskData extends ScreenData<Integer> {

        public PreviewTaskData(Integer taskId) {
            super(taskId);
        }
    }

    public static class EditTaskData extends ScreenData<Task> {

        public EditTaskData(Task task) {
            super(task);
        }
    }

    public static class AddTaskData extends ScreenData<Task> {

        public AddTaskData(Task task) {
            super(task);
        }
    }
}
