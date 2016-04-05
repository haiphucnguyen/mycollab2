package com.esofthead.mycollab.module.crm.ui.format;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.i18n.ContactI18nEnum;
import com.esofthead.mycollab.module.user.ui.format.UserHistoryFieldFormat;
import com.esofthead.mycollab.utils.FieldGroupFormatter;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
public class ContactFieldFormatter extends FieldGroupFormatter {
    private static final ContactFieldFormatter _instance = new ContactFieldFormatter();

    private ContactFieldFormatter() {
        generateFieldDisplayHandler("firstname", ContactI18nEnum.FORM_FIRSTNAME);
        generateFieldDisplayHandler("lastname", ContactI18nEnum.FORM_LASTNAME);
        generateFieldDisplayHandler("title", ContactI18nEnum.FORM_TITLE);
        generateFieldDisplayHandler("department", ContactI18nEnum.FORM_DEPARTMENT);
        generateFieldDisplayHandler("email", ContactI18nEnum.FORM_EMAIL);
        generateFieldDisplayHandler("assistant", ContactI18nEnum.FORM_ASSISTANT);
        generateFieldDisplayHandler("assistantphone", ContactI18nEnum.FORM_ASSISTANT_PHONE);
        generateFieldDisplayHandler("leadsource", ContactI18nEnum.FORM_LEAD_SOURCE);
        generateFieldDisplayHandler("officephone", ContactI18nEnum.FORM_OFFICE_PHONE);
        generateFieldDisplayHandler("mobile", ContactI18nEnum.FORM_MOBILE);
        generateFieldDisplayHandler("homephone", ContactI18nEnum.FORM_HOME_PHONE);
        generateFieldDisplayHandler("otherphone", ContactI18nEnum.FORM_OTHER_PHONE);
        generateFieldDisplayHandler("birthday", ContactI18nEnum.FORM_BIRTHDAY, DATE_FIELD);
        generateFieldDisplayHandler(Contact.Field.fax.name(), ContactI18nEnum.FORM_FAX);
        generateFieldDisplayHandler("iscallable", ContactI18nEnum.FORM_IS_CALLABLE);
        generateFieldDisplayHandler("assignuser", GenericI18Enum.FORM_ASSIGNEE, new UserHistoryFieldFormat());
        generateFieldDisplayHandler("primaddress", ContactI18nEnum.FORM_PRIMARY_ADDRESS);
        generateFieldDisplayHandler("primcity", ContactI18nEnum.FORM_PRIMARY_CITY);
        generateFieldDisplayHandler("primstate", ContactI18nEnum.FORM_PRIMARY_STATE);
        generateFieldDisplayHandler("primpostalcode", ContactI18nEnum.FORM_PRIMARY_POSTAL_CODE);
        generateFieldDisplayHandler("primcountry", ContactI18nEnum.FORM_PRIMARY_COUNTRY);
        generateFieldDisplayHandler("otheraddress", ContactI18nEnum.FORM_OTHER_ADDRESS);
        generateFieldDisplayHandler("othercity", ContactI18nEnum.FORM_OTHER_CITY);
        generateFieldDisplayHandler("otherstate", ContactI18nEnum.FORM_OTHER_STATE);
        generateFieldDisplayHandler("otherpostalcode", ContactI18nEnum.FORM_OTHER_POSTAL_CODE);
        generateFieldDisplayHandler("othercountry", ContactI18nEnum.FORM_OTHER_COUNTRY);
        generateFieldDisplayHandler("description", GenericI18Enum.FORM_DESCRIPTION, TRIM_HTMLS);
        generateFieldDisplayHandler(Contact.Field.accountid.name(), ContactI18nEnum.FORM_ACCOUNTS, new AccountHistoryFieldFormat());
    }

    public static ContactFieldFormatter instance() {
        return _instance;
    }
}
