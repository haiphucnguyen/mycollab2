/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import com.esofthead.mycollab.common.domain.PermissionMap;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.ProjectRole;
import com.esofthead.mycollab.module.project.domain.SimpleProjectRole;
import com.esofthead.mycollab.module.user.PermissionFlag;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
@ViewComponent
public class ProjectRoleReadViewImpl extends AbstractView implements ProjectRoleReadView {

    private static final long serialVersionUID = 1L;
    private PreviewForm previewForm;
    private SimpleProjectRole role;

    public ProjectRoleReadViewImpl() {
        super();
        previewForm = new PreviewForm();
        this.addComponent(previewForm);
    }

    @Override
    public void previewItem(SimpleProjectRole role) {
        this.role = role;
        previewForm.setItemDataSource(new BeanItem<ProjectRole>(role));
    }

    @Override
    public HasPreviewFormHandlers<ProjectRole> getPreviewFormHandlers() {
        return previewForm;
    }

    private class PreviewForm extends AdvancedPreviewBeanForm<ProjectRole> {

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

        class FormLayoutFactory extends ProjectRoleFormLayoutFactory {

            private static final long serialVersionUID = 1L;

            public FormLayoutFactory() {
                super(role.getRolename());
            }

            @Override
            protected Layout createTopPanel() {
                return (new PreviewFormControlsGenerator<ProjectRole>(PreviewForm.this))
                        .createButtonControls();
            }

            @Override
            protected Layout createBottomPanel() {
                VerticalLayout permissionsPanel = new VerticalLayout();
                Label organizationHeader = new Label("Permissions");
                organizationHeader.setStyleName("h2");
                permissionsPanel.addComponent(organizationHeader);

                PermissionMap permissionMap = role.getPermissionMap();

                GridFormLayoutHelper crmFormHelper = new GridFormLayoutHelper(2, ProjectRolePermissionCollections.PROJECT_PERMISSIONS.length);
                Depot crmHeader = new Depot("Customer Relationship Management", crmFormHelper.getLayout());

                for (int i = 0; i < ProjectRolePermissionCollections.PROJECT_PERMISSIONS.length; i++) {
                    String permissionPath = ProjectRolePermissionCollections.PROJECT_PERMISSIONS[i];
                    crmFormHelper.addComponent(new Label(getValueFromPerPath(permissionMap, permissionPath)), permissionPath, 0, i);
                }

                permissionsPanel.addComponent(crmHeader);

                GridFormLayoutHelper userFormHelper = new GridFormLayoutHelper(2, ProjectRolePermissionCollections.PROJECT_PERMISSIONS.length);
                Depot userHeader = new Depot("User Management", userFormHelper.getLayout());

                for (int i = 0; i < ProjectRolePermissionCollections.PROJECT_PERMISSIONS.length; i++) {
                    String permissionPath = ProjectRolePermissionCollections.PROJECT_PERMISSIONS[i];
                    userFormHelper.addComponent(new Label(getValueFromPerPath(permissionMap, permissionPath)), permissionPath, 0, i);
                }

                permissionsPanel.addComponent(userHeader);

                return permissionsPanel;
            }

            private String getValueFromPerPath(PermissionMap permissionMap, String permissionItem) {
                Integer perVal = permissionMap.get(permissionItem);
                if (perVal == null) {
                    return "No Access";
                } else {
                    return PermissionFlag.toString(perVal);
                }
            }
        }
    }

    @Override
    public SimpleProjectRole getItem() {
        return role;
    }
}
