package com.mycollab.pro.module.project.view.client;

import com.mycollab.common.domain.Client;
import com.mycollab.common.i18n.ClientI18nEnum.ClientIndustry;
import com.mycollab.common.i18n.ClientI18nEnum.ClientType;
import com.mycollab.module.user.ui.components.ActiveUserComboBox;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.mycollab.vaadin.web.ui.CountryComboBox;
import com.mycollab.vaadin.web.ui.I18nValueComboBox;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.data.HasValue;
import com.vaadin.ui.RichTextArea;
import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.fields.MTextField;

public class ClientEditFormFieldFactory<B extends Client> extends AbstractBeanFieldGroupEditFieldFactory<B> {
    private static final long serialVersionUID = 1L;

    public ClientEditFormFieldFactory(GenericBeanForm<B> form) {
        super(form);
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
            return new MTextField().withRequiredIndicatorVisible(true);
        } else if (Client.Field.numemployees.equalTo(propertyId)) {
            return new IntegerField().withWidth(WebThemes.FORM_CONTROL_WIDTH);
        } else if (Client.Field.shippingpostalcode.equalTo(propertyId) ||
                Client.Field.postalcode.equalTo(propertyId) ||
                Client.Field.city.equalTo(propertyId) || Client.Field.shippingcity.equalTo(propertyId) ||
                Client.Field.state.equalTo(propertyId) || Client.Field.shippingstate.equalTo(propertyId)) {
            return new MTextField().withWidth(WebThemes.FORM_CONTROL_WIDTH);
        } else if (Client.Field.phoneoffice.equalTo(propertyId) || Client.Field.fax.equalTo(propertyId)
                || Client.Field.alternatephone.equalTo(propertyId)) {
            return new MTextField().withWidth(WebThemes.FORM_CONTROL_WIDTH);
        } else if (Client.Field.email.equalTo(propertyId) || Client.Field.website.equalTo(propertyId)) {
            return new MTextField().withWidth(WebThemes.FORM_CONTROL_WIDTH);
        } else if (Client.Field.ownership.equalTo(propertyId)) {
            return new MTextField().withWidth(WebThemes.FORM_CONTROL_WIDTH);
        } else if (Client.Field.annualrevenue.equalTo(propertyId)) {
            return new MTextField().withWidth(WebThemes.FORM_CONTROL_WIDTH);
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
            this.setWidth(WebThemes.FORM_CONTROL_WIDTH);
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
            this.setWidth(WebThemes.FORM_CONTROL_WIDTH);
        }
    }

}
