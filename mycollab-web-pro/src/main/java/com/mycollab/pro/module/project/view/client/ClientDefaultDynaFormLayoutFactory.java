package com.mycollab.pro.module.project.view.client;

import com.mycollab.common.domain.Client;
import com.mycollab.common.i18n.ClientI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.form.view.LayoutType;
import com.mycollab.form.view.builder.*;
import com.mycollab.form.view.builder.type.DynaForm;
import com.mycollab.form.view.builder.type.DynaSection;

public class ClientDefaultDynaFormLayoutFactory {
    private static final DynaForm defaultForm;

    static {
        defaultForm = new DynaForm();

        // Build block account information
        DynaSection accountSection = new DynaSectionBuilder().layoutType(LayoutType.TWO_COLUMN).orderIndex(0)
                .header(ClientI18nEnum.SECTION_ACCOUNT_INFORMATION)
                .build();
        accountSection.fields(new TextDynaFieldBuilder().fieldName(Client.Field.name)
                .displayName(ClientI18nEnum.FORM_ACCOUNT_NAME).fieldIndex(0).mandatory(true)
                .required(true).build());

        accountSection.fields(new TextDynaFieldBuilder().fieldName(Client.Field.phoneoffice)
                .displayName(ClientI18nEnum.FORM_OFFICE_PHONE).fieldIndex(1).build());

        accountSection.fields(new TextDynaFieldBuilder().fieldName(Client.Field.website).fieldIndex(2)
                .displayName(ClientI18nEnum.FORM_WEBSITE).build());

        accountSection.fields(new PhoneDynaFieldBuilder().fieldName(Client.Field.fax).fieldIndex(3)
                .displayName(ClientI18nEnum.FORM_FAX).build());

        accountSection.fields(new IntDynaFieldBuilder().fieldName(Client.Field.numemployees).fieldIndex(4)
                .displayName(ClientI18nEnum.FORM_EMPLOYEES).build());

        accountSection.fields(new PhoneDynaFieldBuilder().fieldName(Client.Field.alternatephone).fieldIndex(5)
                .displayName(ClientI18nEnum.FORM_OTHER_PHONE).build());

        accountSection.fields(new PickListDynaFieldBuilder<String>().fieldName(Client.Field.industry).fieldIndex(6)
                .displayName(ClientI18nEnum.FORM_INDUSTRY).build());

        accountSection.fields(new EmailDynaFieldBuilder().fieldName(Client.Field.email).fieldIndex(7)
                .displayName(GenericI18Enum.FORM_EMAIL).build());

        accountSection.fields(new PickListDynaFieldBuilder<String>().fieldName(Client.Field.type).fieldIndex(8)
                .displayName(GenericI18Enum.FORM_TYPE).build());

        accountSection.fields(new TextDynaFieldBuilder().fieldName(Client.Field.ownership).fieldIndex(9)
                .displayName(ClientI18nEnum.FORM_OWNERSHIP).build());

        accountSection.fields(new TextDynaFieldBuilder().fieldName(Client.Field.assignuser).fieldIndex(10)
                .displayName(GenericI18Enum.FORM_ASSIGNEE).build());

        accountSection.fields(new TextDynaFieldBuilder().fieldName(Client.Field.annualrevenue).fieldIndex(11)
                .displayName(ClientI18nEnum.FORM_ANNUAL_REVENUE).build());

        defaultForm.sections(accountSection);

        // build block address
        DynaSection addressSection = new DynaSectionBuilder().layoutType(LayoutType.TWO_COLUMN)
                .header(ClientI18nEnum.SECTION_ADDRESS_INFORMATION).orderIndex(1).build();
        addressSection.fields(new TextDynaFieldBuilder().fieldIndex(0).fieldName(Client.Field.billingaddress)
                .displayName(ClientI18nEnum.FORM_BILLING_ADDRESS).customField(false).build());
        addressSection.fields(new TextDynaFieldBuilder().fieldIndex(1).fieldName(Client.Field.shippingaddress)
                .displayName(ClientI18nEnum.FORM_SHIPPING_ADDRESS).customField(false).build());
        addressSection.fields(new TextDynaFieldBuilder().fieldIndex(2).fieldName(Client.Field.city)
                .displayName(ClientI18nEnum.FORM_BILLING_CITY).customField(false).build());

        addressSection.fields(new TextDynaFieldBuilder().fieldIndex(3).fieldName(Client.Field.shippingcity)
                .displayName(ClientI18nEnum.FORM_SHIPPING_CITY).customField(false).build());

        addressSection.fields(new TextDynaFieldBuilder().fieldIndex(4).fieldName(Client.Field.state)
                .displayName(ClientI18nEnum.FORM_BILLING_STATE).customField(false).build());

        addressSection.fields(new TextDynaFieldBuilder().fieldIndex(5).fieldName(Client.Field.shippingstate)
                .displayName(ClientI18nEnum.FORM_SHIPPING_STATE).customField(false).build());

        addressSection.fields(new TextDynaFieldBuilder().fieldIndex(6).fieldName(Client.Field.postalcode)
                .displayName(ClientI18nEnum.FORM_BILLING_POSTAL_CODE).customField(false).build());

        addressSection.fields(new TextDynaFieldBuilder().fieldIndex(7).fieldName(Client.Field.shippingpostalcode)
                .displayName(ClientI18nEnum.FORM_SHIPPING_POSTAL_CODE).customField(false).build());

        addressSection.fields(new TextDynaFieldBuilder().fieldIndex(8).fieldName(Client.Field.billingcountry)
                .displayName(ClientI18nEnum.FORM_BILLING_COUNTRY).customField(false).build());

        addressSection.fields(new TextDynaFieldBuilder().fieldIndex(9).fieldName(Client.Field.shippingcountry)
                .displayName(ClientI18nEnum.FORM_SHIPPING_COUNTRY).customField(false).build());

        defaultForm.sections(addressSection);

        // build block description
        DynaSection descSection = new DynaSectionBuilder().layoutType(LayoutType.ONE_COLUMN)
                .header(ClientI18nEnum.SECTION_DESCRIPTION).orderIndex(2).build();

        descSection.fields(new TextDynaFieldBuilder().fieldIndex(0).fieldName(Client.Field.description).customField(false).build());
        defaultForm.sections(descSection);
    }

    public static DynaForm getForm() {
        return defaultForm;
    }
}
