package com.esofthead.mycollab.module.project.ui.format;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.esofthead.mycollab.module.project.i18n.ProblemI18nEnum;
import com.esofthead.mycollab.utils.FieldGroupFormatter;

/**
 * @author MyCollab Ltd.
 * @since 4.3.3
 */
public final class ProblemFieldFormatter extends FieldGroupFormatter {
    private static final ProblemFieldFormatter _instance = new ProblemFieldFormatter();

    private ProblemFieldFormatter() {
        super();

        this.generateFieldDisplayHandler("issuename", ProblemI18nEnum.FORM_NAME);
        this.generateFieldDisplayHandler("description", GenericI18Enum.FORM_DESCRIPTION);
        this.generateFieldDisplayHandler("raisedbyuser", ProblemI18nEnum.FORM_RAISED_BY,
                new ProjectMemberHistoryFieldFormat());
        this.generateFieldDisplayHandler("assigntouser", GenericI18Enum.FORM_ASSIGNEE,
                new ProjectMemberHistoryFieldFormat());
        this.generateFieldDisplayHandler("impact", ProblemI18nEnum.FORM_IMPACT);

        this.generateFieldDisplayHandler("datedue", ProblemI18nEnum.FORM_DATE_DUE, FieldGroupFormatter.DATE_FIELD);
        this.generateFieldDisplayHandler("priority",
                ProblemI18nEnum.FORM_PRIORITY);
        this.generateFieldDisplayHandler("status", ProblemI18nEnum.FORM_STATUS,
                new I18nHistoryFieldFormat(StatusI18nEnum.class));
        this.generateFieldDisplayHandler("level", ProblemI18nEnum.FORM_RATING);
        this.generateFieldDisplayHandler("resolution", ProblemI18nEnum.FORM_RESOLUTION);
    }

    public static ProblemFieldFormatter instance() {
        return _instance;
    }
}
