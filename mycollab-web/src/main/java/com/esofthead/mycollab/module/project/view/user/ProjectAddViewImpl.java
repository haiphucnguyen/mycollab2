/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.module.project.ProjectDataTypeFactory;
import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.RichTextArea;

/**
 *
 * @author haiphucnguyen
 */
@ViewComponent
public class ProjectAddViewImpl extends AbstractView implements ProjectAddView {
    
    private Project project;
    private EditForm editForm;
    
    public ProjectAddViewImpl() {
        editForm = new EditForm();
        this.addComponent(editForm);
    }

    @Override
    public HasEditFormHandlers<Project> getEditFormHandlers() {
        return editForm;
    }

    @Override
    public void editItem(Project item) {
        this.project = item;
        editForm.setItemDataSource(new BeanItem<Project>(project));
    }

    private class EditForm extends AdvancedEditBeanForm<Project> {

        private static final long serialVersionUID = 1L;

        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new EditFormFieldFactory());
            super.setItemDataSource(newDataSource);
        }

        class FormLayoutFactory extends ProjectFormLayoutFactory {

            private static final long serialVersionUID = 1L;

            public FormLayoutFactory() {
                super("Create Project");
            }

            private Layout createButtonControls() {
                return (new EditFormControlsGenerator<Project>(ProjectAddViewImpl.EditForm.this))
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
                if (propertyId.equals("description")) {
                   RichTextArea field = new RichTextArea(); 
                   field.setHeight("350px");
                   return field;
                } else if (propertyId.equals("projectstatus")) {
                    return new ProjectStatusComboBox();
                } 
                return null;
            }
        }
    }
    
    private static class ProjectStatusComboBox extends ValueComboBox {
        public ProjectStatusComboBox() {
            this.loadData(ProjectDataTypeFactory.getProjectStatusList());
        }
    }
}
