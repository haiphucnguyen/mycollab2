/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;

/**
 *
 * @author haiphucnguyen
 */
public class ProjectInformationComponent extends Panel {

    private PreviewForm previewForm;

    public ProjectInformationComponent() {
        super("Project Information");
        previewForm = new PreviewForm();
        this.addComponent(previewForm);
    }
    
    public void displayProjectInformation() {
        SimpleProject project = (SimpleProject) AppContext
                .getVariable(ProjectContants.PROJECT_NAME);
        previewForm.setItemDataSource(new BeanItem(project));
    }

    private class PreviewForm extends AdvancedPreviewBeanForm<SimpleProject> {

        private static final long serialVersionUID = 1L;

        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
                private static final long serialVersionUID = 1L;

                @Override
                protected Field onCreateField(Item item, Object propertyId,
                        Component uiContext) {

                    return null;
                }
            });
            super.setItemDataSource(newDataSource);
        }

        class FormLayoutFactory implements IFormLayoutFactory {

            private GridFormLayoutHelper gridLayout;

            @Override
            public Layout getLayout() {
                gridLayout = new GridFormLayoutHelper(2, 5);
                return gridLayout.getLayout();
            }

            @Override
            public void attachField(Object propertyId, Field field) {
                if (propertyId.equals("description")) {
                    gridLayout.addComponent(field, "Description", 0, 0, 2, "100%");
                } else if (propertyId.equals("priority")) {
                    gridLayout.addComponent(field, "Priority", 0, 1);
                } else if (propertyId.equals("status")) {
                    gridLayout.addComponent(field, "Status", 0, 2);
                } else if (propertyId.equals("type")) {
                    gridLayout.addComponent(field, "Type", 1, 2);
                } else if (propertyId.equals("planstartdate")) {
                    gridLayout.addComponent(field, "Plan Start Date", 0, 3);
                } else if (propertyId.equals("planenddate")) {
                    gridLayout.addComponent(field, "Plan End Date", 0, 4);
                } else if (propertyId.equals("actualstartdate")) {
                    gridLayout.addComponent(field, "Actual Start Date", 1, 3);
                } else if (propertyId.equals("actualenddate")) {
                    gridLayout.addComponent(field, "Actual End Date", 1, 4);
                }
            }
        }
    }
}
