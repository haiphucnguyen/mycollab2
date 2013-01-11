/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.module.project.view.milestone.MilestoneComboBox;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.GregorianCalendar;

/**
 *
 * @author haiphucnguyen
 */
public class TaskListWindow extends Window {

    private TaskView taskView;
    private SimpleTaskList taskList;
    private TaskListWindow.TaskListForm taskListForm;

    public TaskListWindow(TaskView taskView) {
        this.setWidth("800px");
        this.taskView = taskView;
        taskList = new SimpleTaskList();
        taskListForm = new TaskListWindow.TaskListForm();
        taskListForm.setItemDataSource(new BeanItem(taskList));
        this.addComponent(taskListForm);

        center();
    }

    private void notifyToReloadTaskList() {
        taskView.insertTaskList(taskList);
    }

    private class TaskListForm extends AdvancedEditBeanForm<TaskList> {

        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new TaskListWindow.TaskListForm.TaskListFormLayoutFactory());
            this.setFormFieldFactory(new TaskListWindow.TaskListForm.EditFormFieldFactory());
            super.setItemDataSource(newDataSource);
        }

        private class TaskListFormLayoutFactory implements IFormLayoutFactory {

            private GridFormLayoutHelper informationLayout;

            @Override
            public Layout getLayout() {
                AddViewLayout taskListAddLayout = new AddViewLayout("New Task List", new ThemeResource("icons/48/project/tasklist.png"));

                informationLayout = new GridFormLayoutHelper(2, 3);

                VerticalLayout bodyLayout = new VerticalLayout();
                Label organizationHeader = new Label("Task List Information");
                organizationHeader.setStyleName("h2");
                bodyLayout.addComponent(organizationHeader);
                bodyLayout.addComponent(informationLayout.getLayout());

                taskListAddLayout.addBody(bodyLayout);
                taskListAddLayout.addBottomControls(createBottomPanel());
                return taskListAddLayout;
            }

            private Layout createBottomPanel() {
                HorizontalLayout layout = new HorizontalLayout();
                layout.setSpacing(true);
                layout.setMargin(true);
                layout.setStyleName("addNewControl");
                Button saveBtn = new Button("Save", new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        if (TaskListWindow.TaskListForm.this.validateForm(taskList)) {
                            saveTaskList();
                            TaskListWindow.this.close();
                        }
                    }
                });
                saveBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
                layout.addComponent(saveBtn);

                Button saveAndNewBtn = new Button("Save & New", new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        if (TaskListWindow.TaskListForm.this.validateForm(taskList)) {
                            saveTaskList();
                            taskList = new SimpleTaskList();
                        }
                    }
                });
                saveAndNewBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
                layout.addComponent(saveAndNewBtn);

                Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        TaskListWindow.this.close();
                    }
                });
                cancelBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
                layout.addComponent(cancelBtn);

                return layout;
            }

            private void saveTaskList() {
                ProjectTaskListService taskListService = AppContext.getSpringBean(ProjectTaskListService.class);
                taskList.setSaccountid(AppContext.getAccountId());
                taskList.setCreatedtime(new GregorianCalendar().getTime());

                SimpleProject prj = (SimpleProject) AppContext.getVariable(ProjectContants.PROJECT_NAME);
                taskList.setProjectid(prj.getId());
                taskListService.saveWithSession(taskList, AppContext.getUsername());
                notifyToReloadTaskList();
            }

            @Override
            public void attachField(Object propertyId, Field field) {
                if (propertyId.equals("name")) {
                    informationLayout.addComponent(field, "Name", 0, 0, 2, "100%");
                } else if (propertyId.equals("description")) {
                    informationLayout.addComponent(field, "Description", 0, 1, 2, "100%");
                } else if (propertyId.equals("owner")) {
                    informationLayout.addComponent(field, "Responsible User", 0, 2);
                } else if (propertyId.equals("milestoneid")) {
                    informationLayout.addComponent(field, "Related Milestone", 1, 2);
                }
            }
        }

        private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

            private static final long serialVersionUID = 1L;

            @Override
            protected Field onCreateField(Item item, Object propertyId,
                    com.vaadin.ui.Component uiContext) {
                if (propertyId.equals("description")) {
                    TextArea area = new TextArea();
                    area.setNullRepresentation("");
                    return area;
                } else if (propertyId.equals("owner")) {
                    return new UserComboBox();
                } else if (propertyId.equals("milestoneid")) {
                    return new MilestoneComboBox();
                }
                return null;
            }
        }
    }
}
