package com.mycollab.module.crm.view.contact;

import com.mycollab.common.i18n.ErrorI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.module.crm.domain.Contact;
import com.mycollab.module.crm.view.account.AccountSelectionField;
import com.mycollab.module.crm.view.lead.LeadSourceComboBox;
import com.mycollab.module.user.ui.components.ActiveUserComboBox;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.CompoundCustomField;
import com.mycollab.vaadin.ui.DateSelectionField;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.mycollab.vaadin.web.ui.CountryComboBox;
import com.mycollab.vaadin.web.ui.PrefixNameComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @param <B>
 * @author MyCollab Ltd.
 * @since 1.0
 */
class ContactEditFormFieldFactory<B extends Contact> extends AbstractBeanFieldGroupEditFieldFactory<B> {
    private static final long serialVersionUID = 1L;

    private ContactFirstNamePrefixField firstNamePrefixField;

    ContactEditFormFieldFactory(GenericBeanForm<B> form) {
        this(form, true);
    }

    ContactEditFormFieldFactory(GenericBeanForm<B> form, boolean isValidateForm) {
        super(form, isValidateForm);
        firstNamePrefixField = new ContactFirstNamePrefixField();
        firstNamePrefixField.setWidth("100%");
    }

    @Override
    protected Field<?> onCreateField(Object propertyId) {
        if (propertyId.equals("firstname") || propertyId.equals("prefix")) {
            return firstNamePrefixField;
        } else if (propertyId.equals("leadsource")) {
            return new LeadSourceComboBox();
        } else if (propertyId.equals("accountid")) {
            return new AccountSelectionField();
        } else if (propertyId.equals("lastname")) {
            MTextField tf = new MTextField();
            if (isValidateForm) {
                tf.withNullRepresentation("").withRequired(true)
                        .withRequiredError(UserUIContext.getMessage(ErrorI18nEnum.FIELD_MUST_NOT_NULL,
                                UserUIContext.getMessage(GenericI18Enum.FORM_LASTNAME)));
            }

            return tf;
        } else if (propertyId.equals("description")) {
            return new RichTextArea();
        } else if (propertyId.equals("assignuser")) {
            ActiveUserComboBox userBox = new ActiveUserComboBox();
            userBox.select(attachForm.getBean().getAssignuser());
            return userBox;
        } else if (propertyId.equals("primcountry") || propertyId.equals("othercountry")) {
            return new CountryComboBox();
        } else if (propertyId.equals("birthday")) {
            return new DateSelectionField();
        }
        return null;
    }

    private class ContactFirstNamePrefixField extends CompoundCustomField<Contact> {
        private static final long serialVersionUID = 1L;

        @Override
        protected Component initContent() {
            MHorizontalLayout layout = new MHorizontalLayout().withFullWidth();

            final PrefixNameComboBox prefixSelect = new PrefixNameComboBox();
            prefixSelect.setValue(attachForm.getBean().getPrefix());
            layout.addComponent(prefixSelect);

            prefixSelect.addValueChangeListener(valueChangeEvent -> attachForm.getBean().setPrefix((String) prefixSelect.getValue()));

            TextField firstNameField = new TextField();
            firstNameField.setWidth("100%");
            firstNameField.setNullRepresentation("");
            layout.with(firstNameField).expand(firstNameField);

            // binding field group
            fieldGroup.bind(prefixSelect, "prefix");
            fieldGroup.bind(firstNameField, "firstname");

            return layout;
        }

        @Override
        public Class<? extends Contact> getType() {
            return Contact.class;
        }
    }
}