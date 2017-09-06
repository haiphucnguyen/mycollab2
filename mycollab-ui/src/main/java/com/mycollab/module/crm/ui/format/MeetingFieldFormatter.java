package com.mycollab.module.crm.ui.format;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.module.crm.i18n.MeetingI18nEnum;
import com.mycollab.vaadin.ui.formatter.FieldGroupFormatter;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
public class MeetingFieldFormatter extends FieldGroupFormatter {
    private static final MeetingFieldFormatter _instance = new MeetingFieldFormatter();

    private MeetingFieldFormatter() {
        generateFieldDisplayHandler("subject", MeetingI18nEnum.FORM_SUBJECT);
        generateFieldDisplayHandler("status", GenericI18Enum.FORM_STATUS);
        generateFieldDisplayHandler("startdate", MeetingI18nEnum.FORM_START_DATE_TIME, DATETIME_FIELD);
        generateFieldDisplayHandler("enddate", MeetingI18nEnum.FORM_END_DATE_TIME, DATETIME_FIELD);
        generateFieldDisplayHandler("location", MeetingI18nEnum.FORM_LOCATION);
    }

    public static MeetingFieldFormatter instance() {
        return _instance;
    }
}
