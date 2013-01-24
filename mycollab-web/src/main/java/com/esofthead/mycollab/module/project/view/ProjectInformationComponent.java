/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.view.user.ProjectFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public class ProjectInformationComponent extends VerticalLayout {

    private SimpleProject project;
    private ProjectDisplayInformation prjDisplay;

    public ProjectInformationComponent() {
        prjDisplay = new BasicProjectInformation();
        this.addComponent(prjDisplay);
    }

    public void displayProjectInformation() {
        project = (SimpleProject) AppContext
                .getVariable(ProjectContants.PROJECT_NAME);
        prjDisplay.show();
    }

    private interface ProjectDisplayInformation extends Component {

        void show();
    }

    private class BasicProjectInformation extends VerticalLayout implements ProjectDisplayInformation {

        private BasicPreviewForm previewForm;

        public BasicProjectInformation() {
            previewForm = new BasicPreviewForm();
            this.addComponent(previewForm);

            Button moreBtn = new Button("More", new Button.ClickListener() {
                @Override
                public void buttonClick(ClickEvent event) {
                    ProjectInformationComponent.this.removeComponent(BasicProjectInformation.this);
                    prjDisplay = new DetailProjectInformation();
                    ProjectInformationComponent.this.addComponent(prjDisplay);
                    prjDisplay.show();
                }
            });
            moreBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
            this.addComponent(moreBtn);
        }

        @Override
        public void show() {
            previewForm.setItemDataSource(new BeanItem(project));
        }
    }

    private class BasicPreviewForm extends AdvancedPreviewBeanForm<SimpleProject> {

        private static final long serialVersionUID = 1L;

        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new IFormLayoutFactory() {
                private GridFormLayoutHelper informationLayout;

                @Override
                public Layout getLayout() {
                    informationLayout = new GridFormLayoutHelper(2, 2);
                    informationLayout.getLayout().setWidth("900px");
                    return informationLayout.getLayout();
                }

                @Override
                public void attachField(Object propertyId, Field field) {
                    if (propertyId.equals("homepage")) {
                        informationLayout.addComponent(field, "Home Page", 0, 0);
                    } else if (propertyId.equals("actualstartdate")) {
                        informationLayout.addComponent(field, "Start Date", 1, 0);
                    } else if (propertyId.equals("description")) {
                        informationLayout.addComponent(field, "Description", 0, 1, 2,
                                UIConstants.DEFAULT_2XCONTROL_WIDTH);
                    }
                }
            });

            this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
                private static final long serialVersionUID = 1L;

                @Override
                protected Field onCreateField(Item item, Object propertyId,
                        Component uiContext) {
                    if (propertyId.equals("actualstartdate")) {
                        return new DefaultFormViewFieldFactory.FormViewField(AppContext.formatDate(project.getActualstartdate()));
                    } else if (propertyId.equals("homepage")) {
                        return new FormUrlLinkViewField(project.getHomepage());
                    }
                    return null;
                }
            });
            super.setItemDataSource(newDataSource);
        }
    }

    private class DetailProjectInformation extends VerticalLayout implements ProjectDisplayInformation {

        private DetailPreviewForm previewForm;

        public DetailProjectInformation() {
            previewForm = new DetailPreviewForm();
            this.addComponent(previewForm);

            Button lessBtn = new Button("Less", new Button.ClickListener() {
                @Override
                public void buttonClick(ClickEvent event) {
                    ProjectInformationComponent.this.removeComponent(DetailProjectInformation.this);
                    prjDisplay = new BasicProjectInformation();
                    ProjectInformationComponent.this.addComponent(prjDisplay);
                    prjDisplay.show();
                }
            });

            lessBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
            this.addComponent(lessBtn);
        }

        @Override
        public void show() {
            previewForm.setItemDataSource(new BeanItem(project));
        }
    }

    private class DetailPreviewForm extends AdvancedPreviewBeanForm<SimpleProject> {

        private static final long serialVersionUID = 1L;

        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new ProjectFormLayoutFactory.ProjectInformationLayout());
            this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
                private static final long serialVersionUID = 1L;

                @Override
                protected Field onCreateField(Item item, Object propertyId,
                        Component uiContext) {
                    if (propertyId.equals("planstartdate")) {
                        return new FormViewField(AppContext.formatDate(project.getPlanstartdate()));
                    } else if (propertyId.equals("planenddate")) {
                        return new FormViewField(AppContext.formatDate(project.getPlanenddate()));
                    } else if (propertyId.equals("actualstartdate")) {
                        return new FormViewField(AppContext.formatDate(project.getActualstartdate()));
                    } else if (propertyId.equals("actualenddate")) {
                        return new FormViewField(AppContext.formatDate(project.getActualenddate()));
                    } else if (propertyId.equals("homepage")) {
                        return new FormUrlLinkViewField(project.getHomepage());
                    }
                    return null;
                }
            });
            super.setItemDataSource(newDataSource);
        }
    }
}
