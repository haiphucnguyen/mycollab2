package com.mycollab.module.crm.ui.format;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.module.crm.i18n.CallI18nEnum;
import com.mycollab.module.user.ui.format.UserHistoryFieldFormat;
import com.mycollab.vaadin.ui.formatter.FieldGroupFormatter;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
public class CallFieldFormatter extends FieldGroupFormatter {
    private static final CallFieldFormatter _instance = new CallFieldFormatter();

    private CallFieldFormatter() {
        generateFieldDisplayHandler("subject", CallI18nEnum.FORM_SUBJECT);
        generateFieldDisplayHandler("startdate", CallI18nEnum.FORM_START_DATE_TIME, DATE_FIELD);
        generateFieldDisplayHandler("assignuser", GenericI18Enum.FORM_ASSIGNEE, new UserHistoryFieldFormat());
        generateFieldDisplayHandler("status", GenericI18Enum.FORM_STATUS);
        generateFieldDisplayHandler("purpose", CallI18nEnum.FORM_PURPOSE);
    }

    public static CallFieldFormatter instance() {
        return _instance;
    }
}
