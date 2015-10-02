package com.esofthead.mycollab.module.crm.ui.format;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.module.crm.i18n.AccountI18nEnum;
import com.esofthead.mycollab.module.crm.i18n.OptionI18nEnum;
import com.esofthead.mycollab.module.user.ui.components.UserHistoryFieldFormat;
import com.esofthead.mycollab.utils.FieldGroupFormatter;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
public class AccountFieldFormatter extends FieldGroupFormatter {
    private static final AccountFieldFormatter _instance = new AccountFieldFormatter();

    private AccountFieldFormatter() {
        generateFieldDisplayHandler("accountname", AccountI18nEnum.FORM_ACCOUNT_NAME);
        generateFieldDisplayHandler("assignuser", GenericI18Enum.FORM_ASSIGNEE, new UserHistoryFieldFormat());
        generateFieldDisplayHandler("phoneoffice", AccountI18nEnum.FORM_OFFICE_PHONE);
        generateFieldDisplayHandler("website", AccountI18nEnum.FORM_WEBSITE);
        generateFieldDisplayHandler("fax", AccountI18nEnum.FORM_FAX);
        generateFieldDisplayHandler("numemployees", AccountI18nEnum.FORM_EMPLOYEES);
        generateFieldDisplayHandler("alternatephone", AccountI18nEnum.FORM_OTHER_PHONE);
        generateFieldDisplayHandler("industry", AccountI18nEnum.FORM_INDUSTRY);
        generateFieldDisplayHandler("email", AccountI18nEnum.FORM_EMAIL);
        generateFieldDisplayHandler("type", AccountI18nEnum.FORM_TYPE, new I18nHistoryFieldFormat(
                OptionI18nEnum.AccountType.class));
        generateFieldDisplayHandler("ownership", AccountI18nEnum.FORM_OWNERSHIP);
        generateFieldDisplayHandler("annualrevenue", AccountI18nEnum.FORM_ANNUAL_REVENUE);
        generateFieldDisplayHandler("billingaddress", AccountI18nEnum.FORM_BILLING_ADDRESS);
        generateFieldDisplayHandler("shippingaddress", AccountI18nEnum.FORM_SHIPPING_ADDRESS);
        generateFieldDisplayHandler("city", AccountI18nEnum.FORM_BILLING_CITY);
        generateFieldDisplayHandler("shippingcity", AccountI18nEnum.FORM_SHIPPING_CITY);
        generateFieldDisplayHandler("state", AccountI18nEnum.FORM_BILLING_STATE);
        generateFieldDisplayHandler("shippingstate", AccountI18nEnum.FORM_SHIPPING_STATE);
        generateFieldDisplayHandler("postalcode", AccountI18nEnum.FORM_BILLING_POSTAL_CODE);
        generateFieldDisplayHandler("shippingpostalcode", AccountI18nEnum.FORM_SHIPPING_POSTAL_CODE);
        generateFieldDisplayHandler("description", GenericI18Enum.FORM_DESCRIPTION);
    }

    public static AccountFieldFormatter instance() {
        return _instance;
    }
}
