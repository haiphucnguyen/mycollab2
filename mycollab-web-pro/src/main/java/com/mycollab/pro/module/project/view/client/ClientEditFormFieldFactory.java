package com.mycollab.pro.module.project.view.client;

import com.mycollab.common.domain.Client;
import com.mycollab.common.i18n.ClientI18nEnum.ClientIndustry;
import com.mycollab.common.i18n.ClientI18nEnum.ClientType;
import com.mycollab.module.user.ui.components.ActiveUserComboBox;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.mycollab.vaadin.web.ui.CountryComboBox;
import com.mycollab.vaadin.web.ui.I18nValueComboBox;
import com.vaadin.data.HasValue;
import com.vaadin.ui.RichTextArea;
import org.vaadin.viritin.fields.MTextField;

public class ClientEditFormFieldFactory<B extends Client> extends AbstractBeanFieldGroupEditFieldFactory<B> {
    private static final long serialVersionUID = 1L;

    public ClientEditFormFieldFactory(GenericBeanForm<B> form) {
        super(form);
    }

    public ClientEditFormFieldFactory(GenericBeanForm<B> form, boolean isValidateForm) {
        super(form, isValidateForm);
    }

    @Override
    protected HasValue<?> onCreateField(Object propertyId) {
        if (Client.Field.type.equalTo(propertyId)) {
            return new ClientTypeComboBox();
        } else if (Client.Field.industry.equalTo(propertyId)) {
            return new IndustryComboBox();
        } else if (Client.Field.assignuser.equalTo(propertyId)) {
            ActiveUserComboBox userBox = new ActiveUserComboBox();
            userBox.selectUser(attachForm.getBean().getAssignuser());
            return userBox;
        } else if (Client.Field.description.equalTo(propertyId)) {
            return new RichTextArea();
        } else if (Client.Field.billingcountry.equalTo(propertyId) || Client.Field.shippingcountry.equalTo(propertyId)) {
            return new CountryComboBox();
        } else if (Client.Field.name.equalTo(propertyId)) {
            MTextField tf = new MTextField();
//            if (isValidateForm) {
//                tf.withNullRepresentation("").withRequired(true)
//                        .withRequiredError(UserUIContext.getMessage(ErrorI18nEnum.ERROR_USER_IS_NOT_EXISTED,
//                                UserUIContext.getMessage(ClientI18nEnum.FORM_ACCOUNT_NAME)));
//            }

            return tf;
        }

        return null;
    }

    private static class ClientTypeComboBox extends I18nValueComboBox<ClientType> {
        private static final long serialVersionUID = 1L;


        ClientTypeComboBox() {
            super(ClientType.class, ClientType.Analyst, ClientType.Competitor, ClientType.Customer,
                    ClientType.Integrator, ClientType.Investor, ClientType.Partner,
                    ClientType.Press, ClientType.Prospect, ClientType.Reseller,
                    ClientType.Other);
        }
    }

    private static class IndustryComboBox extends I18nValueComboBox<ClientIndustry> {
        private static final long serialVersionUID = 1L;

        IndustryComboBox() {
            super(ClientIndustry.class, ClientIndustry.Apparel, ClientIndustry.Banking, ClientIndustry.Biotechnology,
                    ClientIndustry.Chemicals, ClientIndustry.Communications, ClientIndustry.Construction,
                    ClientIndustry.Consulting, ClientIndustry.Education, ClientIndustry.Electronics,
                    ClientIndustry.Energy, ClientIndustry.Engineering, ClientIndustry.Entertainment,
                    ClientIndustry.Environmental, ClientIndustry.Finance, ClientIndustry.Government,
                    ClientIndustry.Healthcare, ClientIndustry.Hospitality, ClientIndustry.Insurance,
                    ClientIndustry.Machinery, ClientIndustry.Manufactory, ClientIndustry.Media,
                    ClientIndustry.Not_For_Profit, ClientIndustry.Retail, ClientIndustry.Shipping,
                    ClientIndustry.Technology, ClientIndustry.Telecommunications, ClientIndustry.Other);
        }
    }

}
