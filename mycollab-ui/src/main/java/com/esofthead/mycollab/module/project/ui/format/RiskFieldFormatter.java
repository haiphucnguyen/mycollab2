package com.esofthead.mycollab.module.project.ui.format;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.esofthead.mycollab.module.project.i18n.RiskI18nEnum;
import com.esofthead.mycollab.utils.FieldGroupFormatter;

/**
 * @author MyCollab Ltd.
 * @since 4.3.3
 */
public final class RiskFieldFormatter extends FieldGroupFormatter {
    private static final RiskFieldFormatter _instance = new RiskFieldFormatter();

    public RiskFieldFormatter() {
        super();

        this.generateFieldDisplayHandler("riskname", RiskI18nEnum.FORM_NAME);
        this.generateFieldDisplayHandler("description", RiskI18nEnum.FORM_DESCRIPTION);
        this.generateFieldDisplayHandler("raisedbyuser", RiskI18nEnum.FORM_RAISED_BY, new ProjectMemberHistoryFieldFormat());
        this.generateFieldDisplayHandler("assigntouser", GenericI18Enum.FORM_ASSIGNEE, new ProjectMemberHistoryFieldFormat());
        this.generateFieldDisplayHandler("consequence", RiskI18nEnum.FORM_CONSEQUENCE);
        this.generateFieldDisplayHandler("datedue", RiskI18nEnum.FORM_DATE_DUE, FieldGroupFormatter.DATE_FIELD);
        this.generateFieldDisplayHandler("probalitity", RiskI18nEnum.FORM_PROBABILITY);
        this.generateFieldDisplayHandler("status", RiskI18nEnum.FORM_STATUS, new I18nHistoryFieldFormat(StatusI18nEnum.class));
        this.generateFieldDisplayHandler("level", RiskI18nEnum.FORM_RATING);
        this.generateFieldDisplayHandler("response", RiskI18nEnum.FORM_RESPONSE);
    }

    public static RiskFieldFormatter instance() {
        return _instance;
    }
}
