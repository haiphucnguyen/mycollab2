package com.esofthead.mycollab.module.project.ui.format;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.i18n.MilestoneI18nEnum;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.esofthead.mycollab.utils.FieldGroupFormatter;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
public final class MilestoneFieldFormatter extends FieldGroupFormatter {
    private static MilestoneFieldFormatter _instance = new MilestoneFieldFormatter();

    private MilestoneFieldFormatter() {
        generateFieldDisplayHandler("name", MilestoneI18nEnum.FORM_NAME_FIELD);
        generateFieldDisplayHandler("status", MilestoneI18nEnum.FORM_STATUS_FIELD,
                new I18nHistoryFieldFormat(OptionI18nEnum.MilestoneStatus.class));
        generateFieldDisplayHandler("owner", GenericI18Enum.FORM_ASSIGNEE,
                new ProjectMemberHistoryFieldFormat());
        generateFieldDisplayHandler("startdate", MilestoneI18nEnum.FORM_START_DATE_FIELD,
                FieldGroupFormatter.DATE_FIELD);
        generateFieldDisplayHandler("enddate", MilestoneI18nEnum.FORM_END_DATE_FIELD,
                FieldGroupFormatter.DATE_FIELD);
        generateFieldDisplayHandler(Milestone.Field.description.name(), GenericI18Enum.FORM_DESCRIPTION);
    }

    public static MilestoneFieldFormatter instance() {
        return _instance;
    }
}
