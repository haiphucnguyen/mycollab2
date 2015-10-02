package com.esofthead.mycollab.module.project.ui.format;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.common.i18n.OptionI18nEnum;
import com.esofthead.mycollab.module.project.i18n.VersionI18nEnum;
import com.esofthead.mycollab.utils.FieldGroupFormatter;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
public class VersionFieldFormatter extends FieldGroupFormatter {
    private static VersionFieldFormatter _instance = new VersionFieldFormatter();

    private VersionFieldFormatter() {
        generateFieldDisplayHandler("versionname", VersionI18nEnum.FORM_NAME);
        generateFieldDisplayHandler("status",
                VersionI18nEnum.FORM_STATUS, new I18nHistoryFieldFormat(OptionI18nEnum.StatusI18nEnum.class));
        generateFieldDisplayHandler("description", GenericI18Enum.FORM_DESCRIPTION);
        generateFieldDisplayHandler("duedate",
                VersionI18nEnum.FORM_DUE_DATE, FieldGroupFormatter.DATE_FIELD);
    }

    public static VersionFieldFormatter instance() {
        return _instance;
    }
}
