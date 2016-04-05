package com.esofthead.mycollab.module.crm.ui.format;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.module.crm.i18n.OpportunityI18nEnum;
import com.esofthead.mycollab.module.user.ui.format.UserHistoryFieldFormat;
import com.esofthead.mycollab.utils.FieldGroupFormatter;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
public class OpportunityFieldFormatter extends FieldGroupFormatter {
    private static final OpportunityFieldFormatter _instance = new OpportunityFieldFormatter();

    private OpportunityFieldFormatter() {
        generateFieldDisplayHandler("opportunityname", OpportunityI18nEnum.FORM_NAME);
        generateFieldDisplayHandler("currencyid", OpportunityI18nEnum.FORM_CURRENCY, CURRENCY_FIELD);
        generateFieldDisplayHandler("amount", OpportunityI18nEnum.FORM_AMOUNT);
        generateFieldDisplayHandler("salesstage", OpportunityI18nEnum.FORM_SALE_STAGE);
        generateFieldDisplayHandler("probability", OpportunityI18nEnum.FORM_SALE_STAGE);
        generateFieldDisplayHandler("nextstep", OpportunityI18nEnum.FORM_NEXT_STEP);
        generateFieldDisplayHandler("accountid", OpportunityI18nEnum.FORM_ACCOUNT_NAME, new AccountHistoryFieldFormat());
        generateFieldDisplayHandler("expectedcloseddate", OpportunityI18nEnum.FORM_EXPECTED_CLOSE_DATE, PRETTY_DATE_FIELD);
        generateFieldDisplayHandler("opportunitytype", OpportunityI18nEnum.FORM_TYPE);
        generateFieldDisplayHandler("source", OpportunityI18nEnum.FORM_LEAD_SOURCE);
        generateFieldDisplayHandler("campaignid", OpportunityI18nEnum.FORM_CAMPAIGN_NAME, new CampaignHistoryFieldFormat());
        generateFieldDisplayHandler("assignuser", GenericI18Enum.FORM_ASSIGNEE, new UserHistoryFieldFormat());
        generateFieldDisplayHandler("description", GenericI18Enum.FORM_DESCRIPTION, TRIM_HTMLS);
    }

    public static OpportunityFieldFormatter instance() {
        return _instance;
    }
}
