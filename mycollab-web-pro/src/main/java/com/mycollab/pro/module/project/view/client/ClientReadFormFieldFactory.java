package com.mycollab.pro.module.project.view.client;

import com.mycollab.common.domain.Client;
import com.mycollab.common.domain.SimpleClient;
import com.mycollab.common.i18n.ClientI18nEnum.ClientIndustry;
import com.mycollab.common.i18n.ClientI18nEnum.ClientType;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.mycollab.vaadin.ui.field.*;
import com.mycollab.vaadin.web.ui.field.UserLinkViewField;
import com.vaadin.data.HasValue;

public class ClientReadFormFieldFactory extends AbstractBeanFieldGroupViewFieldFactory<SimpleClient> {
    private static final long serialVersionUID = 1L;

    public ClientReadFormFieldFactory(GenericBeanForm<SimpleClient> form) {
        super(form);
    }

    @Override
    protected HasValue<?> onCreateField(Object propertyId) {
        SimpleClient account = attachForm.getBean();

        if (propertyId.equals("email")) {
            return new EmailViewField(account.getEmail());
        } else if (propertyId.equals("assignuser")) {
            return new UserLinkViewField(account.getAssignuser(), account.getAssignUserAvatarId(), account.getAssignUserFullName());
        } else if (propertyId.equals("website")) {
            return new UrlLinkViewField(account.getWebsite());
        } else if (propertyId.equals("type")) {
            return new I18nFormViewField(ClientType.class);
        } else if (Client.Field.industry.equalTo(propertyId)) {
            return new I18nFormViewField(ClientIndustry.class);
        } else if (Client.Field.description.equalTo(propertyId)) {
            return new RichTextViewField();
        } else if (Client.Field.billingcountry.equalTo(propertyId)) {
            return new CountryViewField();
        } else if (Client.Field.shippingcountry.equalTo(propertyId)) {
            return new CountryViewField();
        }

        return null;
    }

}