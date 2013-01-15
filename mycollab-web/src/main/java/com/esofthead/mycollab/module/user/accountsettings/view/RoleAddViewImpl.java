/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.view.component.PermissionComboBox;
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
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import java.util.Collection;

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

    private class EditForm extends AdvancedEditBeanForm<Role> {

        private static final long serialVersionUID = 1L;

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

                for (String permissionPath : RolePermissionCollections.CRM_PERMISSIONS_ARR) {
                    HorizontalLayout permissionPathLayout = new HorizontalLayout();
                    permissionPathLayout.addComponent(new Label(permissionPath));
                    permissionPathLayout.addComponent(new PermissionComboBox());
                    permissionsPanel.addComponent(permissionPathLayout);
                }

                return permissionsPanel;
            }
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
                }
                return null;
            }
        }
    }

    private static class PermissionValueCommboBox extends ValueComboBox {

        public PermissionValueCommboBox() {
            super();
        }
    }

    @Override
    public HasEditFormHandlers<Role> getEditFormHandlers() {
        return editForm;
    }
}
