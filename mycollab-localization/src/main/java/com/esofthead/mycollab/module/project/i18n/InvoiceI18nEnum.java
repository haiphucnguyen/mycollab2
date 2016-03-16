package com.esofthead.mycollab.module.project.i18n;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
@BaseName("localization/project-invoice")
@LocaleData(value = {@Locale("en-US")}, defaultCharset = "UTF-8")
public enum InvoiceI18nEnum {
    BUTTON_NEW_INVOICE,
    FORM_NOID_FIELD,
    FORM_CLIENT_FIELD,
    FORM_ISSUE_DATE_FIELD,
    FORM_CURRENCY_FIELD,
    FORM_STATUS,
    FORM_TYPE,
    FORM_CONTACT_PERSON,
    FIX_PRICE,
    TIME_MATERIAL
}
