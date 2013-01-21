/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.ui.components.ProjectTaskListComboBox;
import com.esofthead.mycollab.module.project.ui.components.ProjectUserComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.RichTextArea;
import java.util.Collection;

/**
 *
 * @author haiphucnguyen
 */
@ViewComponent
public class TaskAddViewImpl extends AbstractView implements TaskAddView {

    private static final long serialVersionUID = 1L;
    private EditForm editForm;
    private Task task;

    public TaskAddViewImpl() {
        super();
        editForm = new EditForm();
        this.addComponent(editForm);
    }

    @Override
    public void editItem(Task item) {
        this.task = item;
        editForm.setItemDataSource(new BeanItem<Task>(task));
    }

    private class EditForm extends AdvancedEditBeanForm<Task> {

        private static final long serialVersionUID = 1L;

        @Override
        public void setItemDataSource(Item newDataSource,
                Collection<?> propertyIds) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new EditFormFieldFactory());
            super.setItemDataSource(newDataSource, propertyIds);
        }

        private class FormLayoutFactory extends TaskFormLayoutFactory {

            private static final long serialVersionUID = 1L;
            
            public FormLayoutFactory() {
                super((task.getId() == null) ? "Create Task" : task.getTaskname());
            }

            private Layout createButtonControls() {
                return (new EditFormControlsGenerator<Task>(EditForm.this))
                        .createButtonControls();
            }

            @Override
            protected Layout createTopPanel() {
                return createButtonControls();
            }

            @Override
            protected Layout createBottomPanel() {
                return createButtonControls();
            }
        }

        private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

            private static final long serialVersionUID = 1L;

            @Override
            protected Field onCreateField(Item item, Object propertyId,
                    com.vaadin.ui.Component uiContext) {
                if (propertyId.equals("assignuser")) {
                    return new ProjectUserComboBox();
                } else if (propertyId.equals("tasklistid")) {
                    return new ProjectTaskListComboBox();
                } else if (propertyId.equals("notes")) {
                    RichTextArea richTextArea = new RichTextArea();
                    richTextArea.setNullRepresentation("");
                    return richTextArea;
                }
                return null;
            }
        }
    }

    @Override
    public HasEditFormHandlers<Task> getEditFormHandlers() {
        return editForm;
    }
    
}
