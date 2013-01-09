/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.module.user.domain.User;
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
import java.util.Collection;

/**
 *
 * @author haiphucnguyen
 */
@ViewComponent
public class UserAddViewImpl  extends AbstractView implements UserAddView {

    private static final long serialVersionUID = 1L;
    private EditForm editForm;
    private User user;

    public UserAddViewImpl() {
        super();
        editForm = new EditForm();
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
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new EditFormFieldFactory());
            super.setItemDataSource(newDataSource, propertyIds);
        }

        private class FormLayoutFactory extends UserFormLayoutFactory {

            private static final long serialVersionUID = 1L;
            
            public FormLayoutFactory() {
                super("Create User");
            }

            private Layout createButtonControls() {
                return (new EditFormControlsGenerator<User>(EditForm.this))
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
                
                return null;
            }
        }
    }

    @Override
    public HasEditFormHandlers<User> getEditFormHandlers() {
        return editForm;
    }
}
