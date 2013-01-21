/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public abstract class TaskFormLayoutFactory implements IFormLayoutFactory {

    private String title;
    private TaskInformationLayout informationLayout;

    public TaskFormLayoutFactory(String title) {
        this.title = title;
    }

    @Override
    public Layout getLayout() {
        AddViewLayout taskAddLayout = new AddViewLayout(title, new ThemeResource("icons/48/project/task.png"));

        Layout topPanel = createTopPanel();
        if (topPanel != null) {
            taskAddLayout.addTopControls(topPanel);
        }

        informationLayout = new TaskInformationLayout();
        taskAddLayout.addBody(informationLayout.getLayout());

        Layout bottomPanel = createBottomPanel();
        if (bottomPanel != null) {
            taskAddLayout.addBottomControls(bottomPanel);
        }

        return taskAddLayout;
    }

    @Override
    public void attachField(Object propertyId, Field field) {
        informationLayout.attachField(propertyId, field);
    }

    protected abstract Layout createTopPanel();

    protected abstract Layout createBottomPanel();

    @SuppressWarnings("serial")
    public static class TaskInformationLayout implements IFormLayoutFactory {

        private GridFormLayoutHelper informationLayout;

        @Override
        public Layout getLayout() {
            informationLayout = new GridFormLayoutHelper(2, 5);
            VerticalLayout layout = new VerticalLayout();
            layout.addComponent(informationLayout.getLayout());
            return layout;
        }

        @Override
        public void attachField(Object propertyId, Field field) {
            if (propertyId.equals("taskname")) {
                informationLayout.addComponent(field, "Task Name", 0, 0, 2, "100%");
            } else if (propertyId.equals("startdate")) {
                informationLayout.addComponent(field, "Start Date", 0, 1);
            } else if (propertyId.equals("enddate")) {
                informationLayout.addComponent(field, "End Date", 0, 2);
            } else if (propertyId.equals("actualstartdate")) {
                informationLayout.addComponent(field, "Actual Start Date", 1, 1);
            } else if (propertyId.equals("actualenddate")) {
                informationLayout.addComponent(field, "Actual End Date", 1, 2);
            } else if (propertyId.equals("deadline")) {
                informationLayout.addComponent(field, "Deadline", 0, 3);
            } else if (propertyId.equals("isestimated")) {
                informationLayout.addComponent(field, "Is Estimated", 1, 3);
            } else if (propertyId.equals("assignuser")) {
                informationLayout.addComponent(field, "Assign", 0, 4);
            } else if (propertyId.equals("tasklistid")) {
                informationLayout.addComponent(field, "Task List", 1, 4);
            }
        }
    }
}
