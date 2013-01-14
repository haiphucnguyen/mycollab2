package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.ui.components.IndustryComboBox;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
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
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

@ViewComponent
public class AccountAddViewImpl extends AbstractView implements AccountAddView {

    private static final long serialVersionUID = 1L;
    private EditForm editForm;
    private Account account;

    public AccountAddViewImpl() {
        super();
        editForm = new EditForm();
        this.addComponent(editForm);
    }

    @Override
    public void editItem(Account account) {
        this.account = account;
        editForm.setItemDataSource(new BeanItem<Account>(account));
    }

    private class EditForm extends AdvancedEditBeanForm<Account> {

        private static final long serialVersionUID = 1L;

        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new EditFormFieldFactory());
            super.setItemDataSource(newDataSource);
        }

        class FormLayoutFactory extends AccountFormLayoutFactory {

            private static final long serialVersionUID = 1L;

            public FormLayoutFactory() {
                super((account.getId() == null) ? "Create Account" : account.getAccountname());
            }

            private Layout createButtonControls() {
                return (new EditFormControlsGenerator<Account>(EditForm.this))
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

                if ("type".equals(propertyId)) {
                    AccountTypeComboBox accountTypeBox = new AccountTypeComboBox();
                    return accountTypeBox;
                } else if ("industry".equals(propertyId)) {
                    IndustryComboBox accountIndustryBox = new IndustryComboBox();
                    return accountIndustryBox;
                } else if ("assignuser".equals(propertyId)) {
                    UserComboBox userBox = new UserComboBox();
                    userBox.select(account.getAssignuser());
                    return userBox;
                } else if ("description".equals(propertyId)) {
                    TextArea textArea = new TextArea("", "");
                    textArea.setNullRepresentation("");
                    return textArea;
                }

                if (propertyId.equals("accountname")) {
                    TextField tf = new TextField();
                    tf.setNullRepresentation("");
                    tf.setRequired(true);
                    tf.setRequiredError("Please enter an Account Name");
                    return tf;
                }

                return null;
            }
        }
    }

    @Override
    public HasEditFormHandlers<Account> getEditFormHandlers() {
        return editForm;
    }
}
