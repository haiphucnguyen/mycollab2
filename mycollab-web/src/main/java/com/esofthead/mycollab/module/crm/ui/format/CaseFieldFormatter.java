package com.esofthead.mycollab.module.crm.ui.format;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.module.crm.i18n.CaseI18nEnum;
import com.esofthead.mycollab.module.user.ui.components.UserHistoryFieldFormat;
import com.esofthead.mycollab.utils.FieldGroupFormatter;

/**
 * @author MyCollab LTd
 * @since 5.1.4
 */
public class CaseFieldFormatter extends FieldGroupFormatter {
    private static final CaseFieldFormatter _instance = new CaseFieldFormatter();

    private CaseFieldFormatter() {
        generateFieldDisplayHandler("priority", CaseI18nEnum.FORM_PRIORITY);
        generateFieldDisplayHandler("status", CaseI18nEnum.FORM_STATUS);
        generateFieldDisplayHandler("accountid", CaseI18nEnum.FORM_ACCOUNT);
        generateFieldDisplayHandler("phonenumber", CaseI18nEnum.FORM_PHONE);
        generateFieldDisplayHandler("origin", CaseI18nEnum.FORM_ORIGIN);
        generateFieldDisplayHandler("type", CaseI18nEnum.FORM_TYPE);
        generateFieldDisplayHandler("reason", CaseI18nEnum.FORM_REASON);
        generateFieldDisplayHandler("subject", CaseI18nEnum.FORM_SUBJECT);
        generateFieldDisplayHandler("email", CaseI18nEnum.FORM_EMAIL);
        generateFieldDisplayHandler("assignuser", GenericI18Enum.FORM_ASSIGNEE, new UserHistoryFieldFormat());
        generateFieldDisplayHandler("description", GenericI18Enum.FORM_DESCRIPTION);
        generateFieldDisplayHandler("resolution", CaseI18nEnum.FORM_RESOLUTION);
    }

    public static CaseFieldFormatter instance() {
        return _instance;
    }
}
