/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.view.component.RoleComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Window;
import java.util.Collection;
import org.vaadin.addon.customfield.CustomField;

/**
 *
 * @author haiphucnguyen
 */
@ViewComponent
public class UserAddViewImpl extends AbstractView implements UserAddView {
    
    private static final long serialVersionUID = 1L;
    private UserAddViewImpl.EditForm editForm;
    private User user;
    
    public UserAddViewImpl() {
        super();
        editForm = new UserAddViewImpl.EditForm();
        this.addComponent(editForm);
    }
    
    @Override
    public void editItem(User item) {
        this.user = item;
        editForm.setItemDataSource(new BeanItem<User>(user));
    }
    
    private class EditForm extends AdvancedEditBeanForm<User> {
        
        private static final long serialVersionUID = 1L;
        
        @Override
        public void setItemDataSource(Item newDataSource,
                Collection<?> propertyIds) {
            this.setFormLayoutFactory(new UserAddViewImpl.EditForm.FormLayoutFactory());
            this.setFormFieldFactory(new UserAddViewImpl.EditForm.EditFormFieldFactory());
            super.setItemDataSource(newDataSource, propertyIds);
        }
        
        private class FormLayoutFactory extends UserFormLayoutFactory {
            
            private static final long serialVersionUID = 1L;
            
            public FormLayoutFactory() {
                super("Create User");
            }
            
            private Layout createButtonControls() {
                return (new EditFormControlsGenerator<User>(UserAddViewImpl.EditForm.this))
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
                
                if (propertyId.equals("isadmin")) {
                    return new UserAddViewImpl.EditForm.AdminRoleSelectionField();
                }
                return null;
            }
        }
        
        private class AdminRoleSelectionField extends CustomField {
            
            private CheckBox isAdminCheck;
            private HorizontalLayout layout;
            private HorizontalLayout roleLayout;
            private RoleComboBox roleComboBox;
            
            public AdminRoleSelectionField() {
                layout = new HorizontalLayout();
                layout.setSpacing(true);
                
                roleLayout = new HorizontalLayout();
                roleLayout.setSpacing(true);
                roleLayout.addComponent(new Label("Role"));
                roleComboBox = new RoleComboBox();
                roleComboBox.addListener(new Property.ValueChangeListener() {
                    @Override
                    public void valueChange(Property.ValueChangeEvent event) {
                        user.setRoleid((Integer) roleComboBox.getValue());
                    }
                });
                
                
                roleLayout.addComponent(roleComboBox);
                
                isAdminCheck = new CheckBox("");
                isAdminCheck.setImmediate(true);
                isAdminCheck.setWriteThrough(true);
                isAdminCheck.addListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        user.setIsadmin((Boolean) isAdminCheck.getValue());
                        
                        System.out.println("Admin Check: " + isAdminCheck.getValue());
                        
                        if (user.getIsadmin()) {
                            user.setRoleid(null);
                            layout.removeComponent(roleLayout);
                        } else {
                            if (roleComboBox.getContainerPropertyIds().size() > 0) {
                                layout.addComponent(roleLayout);
                            } else {
                                AppContext.getApplication().getMainWindow().showNotification("Information", "You must have at least one role to deselect admin checkbox", Window.Notification.TYPE_HUMANIZED_MESSAGE);
                                isAdminCheck.setValue(Boolean.TRUE);
                            }
                        }
                    }
                });
                
                
                isAdminCheck.setValue(user.getIsadmin());
                layout.addComponent(isAdminCheck);
                if (user.getIsadmin() == null || !user.getIsadmin()) {
                    layout.addComponent(roleLayout);
                }
                
                this.setCompositionRoot(layout);
            }
            
            @Override
            public Class<?> getType() {
                return Object.class;
            }
        }
    }
    
    @Override
    public HasEditFormHandlers<User> getEditFormHandlers() {
        return editForm;
    }
}
