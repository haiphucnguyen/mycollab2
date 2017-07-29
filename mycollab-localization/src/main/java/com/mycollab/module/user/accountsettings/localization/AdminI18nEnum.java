package com.mycollab.module.user.accountsettings.localization;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("account-admin")
@LocaleData(value = {@Locale("en-US")}, defaultCharset = "UTF-8")
public enum AdminI18nEnum {
    VIEW_PROFILE,
    VIEW_BILLING,
    VIEW_BILLING_HISTORY,
    VIEW_SETTING,
    VIEW_THEME,
    VIEW_USERS_AND_ROLES,
    VIEW_SETUP,

    ACTION_CANCEL_ACCOUNT,
    ACTION_INVITE_NEW_USER,
    ACTION_RESET_DEFAULT_THEME,

    FORM_SITE_NAME,
    FORM_SITE_ADDRESS,
    FORM_DEFAULT_TIMEZONE,
    FORM_DEFAULT_CURRENCY,
    FORM_DEFAULT_LANGUAGE,
    FORM_DEFAULT_YYMMDD_FORMAT,
    FORM_DEFAULT_MMDD_FORMAT,
    FORM_DEFAULT_HUMAN_DATE_FORMAT,
    FORM_SHOW_EMAIL_PUBLICLY,
    FORM_SHOW_EMAIL_PUBLICLY_HELP,

    OPT_CHANGE_ACCOUNT_INFO,
    OPT_GENERAL_SETTINGS,
    OPT_THEME,
    OPT_CONFIRM_RESET_DEFAULT_THEME,
    OPT_LOGO_FORMAT_DESCRIPTION
}
