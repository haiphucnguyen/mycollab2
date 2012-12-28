/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.crm.ui.components.AddViewLayout;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.view.milestone.MilestoneComboBox;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
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

/**
 *
 * @author haiphucnguyen
 */
public class TaskListWindow extends Window {

    private TaskList taskList;
    private TaskListForm taskListForm;

    public TaskListWindow() {
        this.setWidth("800px");
        taskList = new TaskList();
        taskListForm = new TaskListForm();
        taskListForm.setItemDataSource(new BeanItem(taskList));
        this.addComponent(taskListForm);
        center();
    }

    private class TaskListForm extends AdvancedEditBeanForm<TaskList> {

        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new TaskListFormLayoutFactory());
            this.setFormFieldFactory(new EditFormFieldFactory());
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
                        if (TaskListForm.this.validateForm(taskList)) {
                            
                        }
                    }
                });
                saveBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
                layout.addComponent(saveBtn);

                Button saveAndNewBtn = new Button("Save & New", new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        if (TaskListForm.this.validateForm(taskList)) {
                            
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
