/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.esofthead.mycollab.common.domain.PermissionMap;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.ProjectRole;
import com.esofthead.mycollab.module.project.domain.SimpleProjectRole;
import com.esofthead.mycollab.module.user.view.component.PermissionComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public class ProjectRoleAddViewImpl  extends AbstractView implements ProjectRoleAddView {
    
    private static final long serialVersionUID = 1L;
    private EditForm editForm;
    private ProjectRole projectRole;
    
    public ProjectRoleAddViewImpl() {
        super();
        editForm = new EditForm();
        this.addComponent(editForm);
    }
    
    @Override
    public void editItem(ProjectRole item) {
        this.projectRole = item;
        editForm.setItemDataSource(new BeanItem<ProjectRole>(projectRole));
    }

    @Override
    public PermissionMap getPermissionMap() {
        return editForm.getPermissionMap();
    }
    
    public class EditForm extends AdvancedEditBeanForm<ProjectRole> {
        
        private static final long serialVersionUID = 1L;
        private Map<String, PermissionComboBox> permissionControlsMap = new HashMap<String, PermissionComboBox>();
        
        @Override
        public void setItemDataSource(Item newDataSource,
                Collection<?> propertyIds) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new EditFormFieldFactory());
            super.setItemDataSource(newDataSource, propertyIds);
        }
        
        private class FormLayoutFactory extends ProjectRoleFormLayoutFactory {
            
            private static final long serialVersionUID = 1L;
            
            public FormLayoutFactory() {
                super("Create projectRole");
            }
            
            private Layout createButtonControls() {
                return (new EditFormControlsGenerator<ProjectRole>(ProjectRoleAddViewImpl.EditForm.this))
                        .createButtonControls();
            }
            
            @Override
            protected Layout createTopPanel() {
                return createButtonControls();
            }
            
            @Override
            protected Layout createBottomPanel() {
                VerticalLayout permissionsPanel = new VerticalLayout();
                Label organizationHeader = new Label("Permissions");
                organizationHeader.setStyleName("h2");
                permissionsPanel.addComponent(organizationHeader);
                
                PermissionMap perMap;
                if (projectRole instanceof SimpleProjectRole) {
                    perMap = ((SimpleProjectRole)projectRole).getPermissionMap();
                } else {
                    perMap = new PermissionMap();
                }
                
                GridFormLayoutHelper permissionFormHelper = new GridFormLayoutHelper(2, ProjectRolePermissionCollections.PROJECT_PERMISSIONS.length);
                Depot crmHeader = new Depot("Customer Relationship Management", permissionFormHelper.getLayout());
                
                for (int i = 0; i < ProjectRolePermissionCollections.PROJECT_PERMISSIONS.length; i++) {
                    String permissionPath = ProjectRolePermissionCollections.PROJECT_PERMISSIONS[i];
                    PermissionComboBox permissionBox = new PermissionComboBox();
                    
                    Integer flag = perMap.getPermissionFlag(permissionPath);
                    permissionBox.setValue(flag);
                    permissionControlsMap.put(permissionPath, permissionBox);
                    permissionFormHelper.addComponent(permissionBox, permissionPath, 0, i);
                }
                
                permissionsPanel.addComponent(crmHeader);
                
                return permissionsPanel;
            }
        }
        
        public PermissionMap getPermissionMap() {
            PermissionMap permissionMap = new PermissionMap();
            
            for (String permissionItem : permissionControlsMap.keySet()) {
                PermissionComboBox permissionBox = permissionControlsMap.get(permissionItem);
                Integer perValue = (Integer) permissionBox.getValue();
                permissionMap.addPath(permissionItem, perValue);
            }
            return permissionMap;
        }
        
        private class EditFormFieldFactory extends DefaultEditFormFieldFactory {
            
            private static final long serialVersionUID = 1L;
            
            @Override
            protected Field onCreateField(Item item, Object propertyId,
                    com.vaadin.ui.Component uiContext) {
                if (propertyId.equals("description")) {
                    TextArea textArea = new TextArea();
                    textArea.setNullRepresentation("");
                    return textArea;
                } else if (propertyId.equals("isadmin")) {
                    
                } else if (propertyId.equals("rolename")) {
                	 TextField tf = new TextField();
                     tf.setNullRepresentation("");
                     tf.setRequired(true);
                     tf.setRequiredError("Please enter a projectRole name");
                     return tf;
                }
                return null;
            }
        }
    }
    
    @Override
    public HasEditFormHandlers<ProjectRole> getEditFormHandlers() {
        return editForm;
    }
    
}
