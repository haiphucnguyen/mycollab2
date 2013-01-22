/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.easyuploads.MultiFileUploadExt;

import com.esofthead.mycollab.module.file.AttachmentConstants;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.ui.components.ProjectUserComboBox;
import com.esofthead.mycollab.module.project.ui.components.TaskPercentageCompleteComboBox;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.AttachmentPanel;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public class TaskAddPopup extends CustomComponent {

    private static Logger log = LoggerFactory.getLogger(TaskAddPopup.class);
    private TabSheet taskContainer;
    private SimpleTask task;
    private TaskNoteLayout taskNoteComponent;

    public TaskAddPopup(final TaskDisplayComponent taskDisplayComp, final TaskList taskList) {

        VerticalLayout taskLayout = new VerticalLayout();
        taskLayout.setSpacing(true);

        Label titleLbl = new Label("Add New Task");
        taskLayout.addComponent(titleLbl);

        task = new SimpleTask();
        taskContainer = new TabSheet();
        final TaskInformationLayout taskInformationLayout = new TaskInformationLayout();
        taskContainer.addTab(taskInformationLayout, "Information");

        taskNoteComponent = new TaskNoteLayout();
        taskContainer.addTab(taskNoteComponent, "Note & Attachments");

        taskLayout.addComponent(taskContainer);

        HorizontalLayout controlsLayout = new HorizontalLayout();
        controlsLayout.setSpacing(true);
        Button saveBtn = new Button("Save", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                ProjectTaskService taskService = AppContext.getSpringBean(ProjectTaskService.class);
                SimpleProject project = (SimpleProject) AppContext.getVariable(ProjectContants.PROJECT_NAME);
                task.setTasklistid(taskList.getId());
                task.setProjectid(project.getId());
                task.setSaccountid(AppContext.getAccountId());
                task.setNotes(taskNoteComponent.getNote());
                if (taskInformationLayout.validateForm(task)) {
                    taskService.saveWithSession(task, AppContext.getUsername());
                    taskNoteComponent.saveContentsToRepo(task.getId());
                    taskDisplayComp.saveTaskSuccess(task);
                    taskDisplayComp.closeTaskAdd();
                }
            }
        });
        saveBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        controlsLayout.addComponent(saveBtn);

        Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                taskDisplayComp.closeTaskAdd();
            }
        });

        cancelBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        controlsLayout.addComponent(cancelBtn);
        taskLayout.addComponent(controlsLayout);

        this.setCompositionRoot(taskLayout);
    }

    private class TaskInformationLayout extends AdvancedEditBeanForm<Task> {

        public TaskInformationLayout() {
            this.setFormLayoutFactory(new TaskLayout());
            this.setFormFieldFactory(new EditFormFieldFactory());
            this.setItemDataSource(new BeanItem<Task>(task));
        }
    }

    private static class TaskLayout implements IFormLayoutFactory {

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
            } else if (propertyId.equals("percentagecomplete")) {
                informationLayout.addComponent(field, "Complete(%)", 1, 4);
            }
        }
    }

    private class TaskNoteLayout extends VerticalLayout {

        private TextArea noteArea;
        private AttachmentPanel attachmentPanel;

        public TaskNoteLayout() {
            this.setSpacing(true);
            this.setMargin(true);
            noteArea = new TextArea();
            noteArea.setWidth("800px");
            noteArea.setHeight("200px");
            this.addComponent(noteArea);

            attachmentPanel = new AttachmentPanel();
            this.addComponent(attachmentPanel);
            MultiFileUploadExt uploadExt = new MultiFileUploadExt(attachmentPanel);
            this.addComponent(uploadExt);
            this.setComponentAlignment(uploadExt, Alignment.MIDDLE_LEFT);
        }

        public String getNote() {
            return (String) noteArea.getValue();
        }

        void saveContentsToRepo(Integer typeid) {
            attachmentPanel.saveContentsToRepo(AttachmentConstants.PROJECT_TASK_TYPE, typeid);
        }
    }

    private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

        private static final long serialVersionUID = 1L;

        @Override
        protected Field onCreateField(Item item, Object propertyId,
                com.vaadin.ui.Component uiContext) {
            if (propertyId.equals("assignuser")) {
                return new ProjectUserComboBox();
            } else if (propertyId.equals("taskname")) {
            	  TextField tf = new TextField();
                  tf.setNullRepresentation("");
                  tf.setRequired(true);
                  tf.setRequiredError("Please enter a Task Name");
                  return tf;
            } else if (propertyId.equals("percentagecomplete")) {
                return new TaskPercentageCompleteComboBox();
            }
            return null;
        }
    }
}
