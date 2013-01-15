/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.common.domain.PermissionMap;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.view.component.PermissionComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author haiphucnguyen
 */
@ViewComponent
public class RoleAddViewImpl extends AbstractView implements RoleAddView {
    
    private static final long serialVersionUID = 1L;
    private RoleAddViewImpl.EditForm editForm;
    private Role role;
    
    public RoleAddViewImpl() {
        super();
        editForm = new EditForm();
        this.addComponent(editForm);
    }
    
    @Override
    public void editItem(Role item) {
        this.role = item;
        editForm.setItemDataSource(new BeanItem<Role>(role));
    }

    @Override
    public PermissionMap getPermissionMap() {
        return editForm.getPermissionMap();
    }
    
    public class EditForm extends AdvancedEditBeanForm<Role> {
        
        private static final long serialVersionUID = 1L;
        private Map<String, PermissionComboBox> permissionControlsMap = new HashMap<String, PermissionComboBox>();
        
        @Override
        public void setItemDataSource(Item newDataSource,
                Collection<?> propertyIds) {
            this.setFormLayoutFactory(new RoleAddViewImpl.EditForm.FormLayoutFactory());
            this.setFormFieldFactory(new RoleAddViewImpl.EditForm.EditFormFieldFactory());
            super.setItemDataSource(newDataSource, propertyIds);
        }
        
        private class FormLayoutFactory extends RoleFormLayoutFactory {
            
            private static final long serialVersionUID = 1L;
            
            public FormLayoutFactory() {
                super("Create Role");
            }
            
            private Layout createButtonControls() {
                return (new EditFormControlsGenerator<Role>(RoleAddViewImpl.EditForm.this))
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
                
                GridFormLayoutHelper crmFormHelper = new GridFormLayoutHelper(2, RolePermissionCollections.CRM_PERMISSIONS_ARR.length);
                Depot crmHeader = new Depot("Customer Relationship Management", crmFormHelper.getLayout());
                
                for (int i = 0; i < RolePermissionCollections.CRM_PERMISSIONS_ARR.length; i++) {
                    String permissionPath = RolePermissionCollections.CRM_PERMISSIONS_ARR[i];
                    PermissionComboBox permissionBox = new PermissionComboBox();
                    permissionControlsMap.put(permissionPath, permissionBox);
                    crmFormHelper.addComponent(permissionBox, permissionPath, 0, i);
                }
                
                permissionsPanel.addComponent(crmHeader);
                
                GridFormLayoutHelper userFormHelper = new GridFormLayoutHelper(2, RolePermissionCollections.USER_PERMISSION_ARR.length);
                Depot userHeader = new Depot("User Management", userFormHelper.getLayout());
                
                for (int i = 0; i < RolePermissionCollections.USER_PERMISSION_ARR.length; i++) {
                    String permissionPath = RolePermissionCollections.USER_PERMISSION_ARR[i];
                    PermissionComboBox permissionBox = new PermissionComboBox();
                    permissionControlsMap.put(permissionPath, permissionBox);
                    userFormHelper.addComponent(permissionBox, permissionPath, 0, i);
                }
                
                permissionsPanel.addComponent(userHeader);
                
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
                    
                }
                return null;
            }
        }
    }
    
    @Override
    public HasEditFormHandlers<Role> getEditFormHandlers() {
        return editForm;
    }
}
