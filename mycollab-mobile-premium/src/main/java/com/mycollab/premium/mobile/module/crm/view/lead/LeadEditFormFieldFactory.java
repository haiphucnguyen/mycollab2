package com.mycollab.premium.mobile.module.crm.view.lead;

import com.mycollab.common.i18n.ErrorI18nEnum;
import com.mycollab.mobile.module.user.ui.components.ActiveUserComboBox;
import com.mycollab.mobile.ui.CountryListSelect;
import com.mycollab.mobile.ui.IndustryListSelect;
import com.mycollab.mobile.ui.PrefixNameListSelect;
import com.mycollab.module.crm.domain.Lead;
import com.mycollab.module.crm.i18n.AccountI18nEnum;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.CompoundCustomField;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.vaadin.data.HasValue;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @param <B>
 * @author MyCollab Ltd.
 * @since 4.1
 */
class LeadEditFormFieldFactory<B extends Lead> extends AbstractBeanFieldGroupEditFieldFactory<B> {
    private static final long serialVersionUID = 1L;

    private LeadFirstNamePrefixField firstNamePrefixField;

    LeadEditFormFieldFactory(GenericBeanForm<B> form) {
        super(form);
        firstNamePrefixField = new LeadFirstNamePrefixField();
    }

    @Override
    protected HasValue<?> onCreateField(Object propertyId) {
        if (propertyId.equals("firstname") || propertyId.equals("prefixname")) {
            return firstNamePrefixField;
        } else if (propertyId.equals("primcountry") || propertyId.equals("othercountry")) {
            return new CountryListSelect();
        } else if (propertyId.equals("status")) {
            return new LeadStatusListSelect();
        } else if (propertyId.equals("industry")) {
            return new IndustryListSelect();
        } else if (propertyId.equals("assignuser")) {
            return new ActiveUserComboBox();
        } else if (propertyId.equals("source")) {
            return new LeadSourceListSelect();
        } else if (propertyId.equals("lastname")) {
            return new TextField();
        } else if (propertyId.equals("description")) {
            return new TextArea();
        } else if (propertyId.equals("accountname")) {
            MTextField txtField = new MTextField();

            // TODO: revise validation
//            if (isValidateForm) {
//                txtField.withNullRepresentation("").withRequired(true).withRequiredError(UserUIContext.getMessage
//                        (ErrorI18nEnum.FIELD_MUST_NOT_NULL, UserUIContext.getMessage(AccountI18nEnum.FORM_ACCOUNT_NAME)));
//            }

            return txtField;
        }

        return null;
    }

    private class LeadFirstNamePrefixField extends CompoundCustomField<Lead> {
        private static final long serialVersionUID = 1L;

        @Override
        protected Component initContent() {
            MHorizontalLayout layout = new MHorizontalLayout().withFullWidth();

            final PrefixNameListSelect prefixSelect = new PrefixNameListSelect();

            //TODO: revise this code
//            prefixSelect.setValue(attachForm.getBean().getPrefixname());
//            layout.addComponent(prefixSelect);
//
//            prefixSelect.addValueChangeListener(new Property.ValueChangeListener() {
//                private static final long serialVersionUID = 1L;
//
//                @Override
//                public void valueChange(Property.ValueChangeEvent event) {
//                    attachForm.getBean().setPrefixname((String) prefixSelect.getValue());
//                }
//            });

            TextField firstnameTxtField = new TextField();
            firstnameTxtField.setWidth("100%");
//            firstnameTxtField.setNullRepresentation("");
            layout.addComponent(firstnameTxtField);
            layout.setExpandRatio(firstnameTxtField, 1.0f);

            // binding field group
            binder.bind(prefixSelect, "prefixname");
            binder.bind(firstnameTxtField, "firstname");

            return layout;
        }

        @Override
        protected void doSetValue(Lead lead) {

        }

        @Override
        public Lead getValue() {
            return null;
        }
    }
}
