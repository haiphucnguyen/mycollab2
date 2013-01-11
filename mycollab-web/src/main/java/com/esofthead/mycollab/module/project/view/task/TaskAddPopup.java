/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public class TaskAddPopup extends CustomComponent {

    private TabSheet taskContainer;
    private TaskList taskList;
    private SimpleTask task;

    public TaskAddPopup(final TaskViewImpl.TaskListDepot taskListDepot, final TaskList taskList) {
        this.taskList = taskList;

        VerticalLayout taskLayout = new VerticalLayout();
        
        task = new SimpleTask();
        taskContainer = new TabSheet();
        taskContainer.addTab(new TaskInformationLayout(), "Information");
        taskContainer.addTab(new TaskNoteLayout(), "Note & Attachments");
        
        taskLayout.addComponent(taskContainer);
        
        HorizontalLayout controlsLayout = new HorizontalLayout();
        Button saveBtn = new Button("Save", new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                taskListDepot.closeTaskAdd();
                ProjectTaskService taskService = AppContext.getSpringBean(ProjectTaskService.class);
                SimpleProject project = (SimpleProject)AppContext.getVariable(ProjectContants.PROJECT_NAME);
                task.setTasklistid(taskList.getId());
                task.setProjectid(project.getId());
                taskService.saveWithSession(task, AppContext.getUsername());
                taskListDepot.saveTaskSuccess(task);
            }
        });
        controlsLayout.addComponent(saveBtn);
        
        Button cancelBtn = new Button("Cancel", new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                taskListDepot.closeTaskAdd();
            }
        });
        
        controlsLayout.addComponent(cancelBtn);
        taskLayout.addComponent(controlsLayout);

        this.setCompositionRoot(taskLayout);
    }

    private class TaskInformationLayout extends AdvancedEditBeanForm<Task> {

        public TaskInformationLayout() {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new EditFormFieldFactory());
            this.setItemDataSource(new BeanItem<Task>(task));
        }
    }

    private class TaskNoteLayout extends VerticalLayout {
    }

    private class FormLayoutFactory implements IFormLayoutFactory {

        private GridFormLayoutHelper informationLayout;

        @Override
        public Layout getLayout() {
            informationLayout = new GridFormLayoutHelper(2, 4);
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
            } else if (propertyId.equals("deadline")) {
                informationLayout.addComponent(field, "Deadline", 0, 3);
            } else if (propertyId.equals("ismilestone")) {
                informationLayout.addComponent(field, "Is Milestone", 1, 1);
            } else if (propertyId.equals("percentagecomplete")) {
                informationLayout.addComponent(field, "Percentage Complete", 1, 2);
            } else if (propertyId.equals("priority")) {
                informationLayout.addComponent(field, "Priority", 1, 3);
            }
        }
    }

    private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

        private static final long serialVersionUID = 1L;

        @Override
        protected Field onCreateField(Item item, Object propertyId,
                com.vaadin.ui.Component uiContext) {
            return null;
        }
    }
}
