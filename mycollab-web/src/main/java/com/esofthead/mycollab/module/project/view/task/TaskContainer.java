/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

/**
 *
 * @author haiphucnguyen
 */
@ViewComponent
public class TaskContainer extends AbstractView {

    public static class PreviewTaskListData extends ScreenData.Edit<Integer> {

        public PreviewTaskListData(Integer taskListId) {
            super(taskListId);
        }
    }

    public static class PreviewTaskData extends ScreenData.Edit<Integer> {

        public PreviewTaskData(Integer taskId) {
            super(taskId);
        }
    }
}
