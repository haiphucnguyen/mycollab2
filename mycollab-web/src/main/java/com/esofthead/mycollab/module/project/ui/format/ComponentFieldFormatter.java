package com.esofthead.mycollab.module.project.ui.format;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.common.i18n.OptionI18nEnum;
import com.esofthead.mycollab.module.project.i18n.ComponentI18nEnum;
import com.esofthead.mycollab.utils.FieldGroupFormatter;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
public class ComponentFieldFormatter extends FieldGroupFormatter {
    private static ComponentFieldFormatter _instance = new ComponentFieldFormatter();

    private ComponentFieldFormatter() {
        generateFieldDisplayHandler("componentname", ComponentI18nEnum.FORM_NAME);
        generateFieldDisplayHandler("description", GenericI18Enum.FORM_DESCRIPTION);
        generateFieldDisplayHandler("userlead", ComponentI18nEnum.FORM_LEAD, new ProjectMemberHistoryFieldFormat());
        generateFieldDisplayHandler("status", ComponentI18nEnum.FORM_STATUS,
                new I18nHistoryFieldFormat(OptionI18nEnum.StatusI18nEnum.class));
    }

    public static ComponentFieldFormatter instance() {
        return _instance;
    }
}
