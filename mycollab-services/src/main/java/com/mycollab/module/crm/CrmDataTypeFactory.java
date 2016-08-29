/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.crm;

import com.mycollab.module.crm.i18n.OptionI18nEnum.AccountIndustry;
import com.mycollab.module.crm.i18n.OptionI18nEnum.AccountType;
import com.mycollab.module.crm.i18n.OptionI18nEnum.CampaignType;
import com.mycollab.module.crm.i18n.OptionI18nEnum.OpportunitySalesStage;

import java.util.Arrays;
import java.util.List;

import static com.mycollab.module.crm.i18n.OptionI18nEnum.OpportunityLeadSource;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class CrmDataTypeFactory {
    private static AccountIndustry[] ACCOUNT_INDUSTRY_LIST = new AccountIndustry[]{AccountIndustry.Apparel,
            AccountIndustry.Banking, AccountIndustry.Biotechnology, AccountIndustry.Chemicals, AccountIndustry.Communications,
            AccountIndustry.Construction, AccountIndustry.Consulting, AccountIndustry.Education, AccountIndustry.Electronics,
            AccountIndustry.Energy, AccountIndustry.Engineering, AccountIndustry.Entertainment, AccountIndustry.Environmental,
            AccountIndustry.Finance, AccountIndustry.Government, AccountIndustry.Healthcare, AccountIndustry.Hospitality,
            AccountIndustry.Insurance, AccountIndustry.Machinery, AccountIndustry.Manufactory, AccountIndustry.Media,
            AccountIndustry.Not_For_Profit, AccountIndustry.Retail, AccountIndustry.Shipping, AccountIndustry.Technology,
            AccountIndustry.Telecommunications, AccountIndustry.Other};

    private static final List<AccountType> ACCOUNT_TYPE_LIST = Arrays.asList(
            AccountType.Analyst, AccountType.Competitor, AccountType.Customer,
            AccountType.Integrator, AccountType.Investor, AccountType.Partner,
            AccountType.Press, AccountType.Prospect, AccountType.Reseller,
            AccountType.Other);

    private static OpportunityLeadSource[] LEAD_SOURCE_LIST = new OpportunityLeadSource[]{OpportunityLeadSource.Cold_Call,
            OpportunityLeadSource.Existing_Customer, OpportunityLeadSource.Self_Generated, OpportunityLeadSource.Employee,
            OpportunityLeadSource.Partner, OpportunityLeadSource.Public_Relations, OpportunityLeadSource.Direct_Email,
            OpportunityLeadSource.Conference, OpportunityLeadSource.Trade_Show, OpportunityLeadSource.Website,
            OpportunityLeadSource.Word_of_mouth, OpportunityLeadSource.Email, OpportunityLeadSource.Campaign,
            OpportunityLeadSource.Other};

    private static String[] LEAD_STATUS_LIST = new String[]{"New",
            "Assigned", "In Process", "Converted", "Recycled", "Dead"};

    private static String[] CAMPAIGN_STATUS_LIST = new String[]{"Planning",
            "Active", "Inactive", "Completed", "In Queue", "Sending"};

    private static CampaignType[] CAMPAIGN_TYPE_LIST = new CampaignType[]{CampaignType.Conference,
            CampaignType.Webinar, CampaignType.Trade_Show, CampaignType.Public_Relations, CampaignType.Partners,
            CampaignType.Referral_Program, CampaignType.Advertisement, CampaignType.Banner_Ads, CampaignType.Direct_Email,
            CampaignType.Mail, CampaignType.Telemarketing, CampaignType.Other};

    private static OpportunitySalesStage[] OPPORTUNITY_SALES_STAGE_LIST = new OpportunitySalesStage[]{
            OpportunitySalesStage.Prospecting, OpportunitySalesStage.Qualification, OpportunitySalesStage.Need_Analysis,
            OpportunitySalesStage.Value_Proposition, OpportunitySalesStage.Perception_Analysis,
            OpportunitySalesStage.Proposal_Price_Quote, OpportunitySalesStage.Negotiation_Review,
            OpportunitySalesStage.Closed_Won, OpportunitySalesStage.Closed_Lost};

    private static String[] OPPORTUNITY_CONTACT_ROLE_LIST = new String[]{
            "Primary Decision Marker", "Evaluator", "Influencer", "Other"};

    private static String[] OPPORTUNITY_TYPE_LIST = new String[]{
            "Existing Business", "New Business"};

    private static String[] CASES_STATUS_LIST = new String[]{"New",
            "Assigned", "Closed", "Pending Input", "Rejected", "Duplicate"};

    private static String[] CASES_PRIORITY_LIST = new String[]{"High",
            "Medium", "Low"};

    private static String[] CASES_REASON_LIST = new String[]{
            "User did not attend any training", "Complex functionality",
            "New problem", "Ambigous instruction"};

    private static String[] CASES_ORIGIN_LIST = new String[]{"Email",
            "Phone", "Web", "Error Log"};

    private static String[] CASES_TYPE_LIST = new String[]{"Problem",
            "Feature Request", "Question"};

    private static String[] TASK_PRIORITY_LIST = new String[]{"High",
            "Medium", "Low"};

    private static String[] TASK_STATUS_LIST = new String[]{"Not Started",
            "In Progress", "Completed", "Pending Input", "Deferred"};

    public static AccountIndustry[] getAccountIndustryList() {
        return ACCOUNT_INDUSTRY_LIST;
    }

    public static List<? extends Enum<?>> getAccountTypeList() {
        return ACCOUNT_TYPE_LIST;
    }

    public static OpportunityLeadSource[] getLeadSourceList() {
        return LEAD_SOURCE_LIST;
    }

    public static String[] getLeadStatusList() {
        return LEAD_STATUS_LIST;
    }

    public static String[] getCampaignStatusList() {
        return CAMPAIGN_STATUS_LIST;
    }

    public static CampaignType[] getCampaignTypeList() {
        return CAMPAIGN_TYPE_LIST;
    }

    public static OpportunitySalesStage[] getOpportunitySalesStageList() {
        return OPPORTUNITY_SALES_STAGE_LIST;
    }

    public static String[] getOpportunityContactRoleList() {
        return OPPORTUNITY_CONTACT_ROLE_LIST;
    }

    public static String[] getOpportunityTypeList() {
        return OPPORTUNITY_TYPE_LIST;
    }

    public static String[] getCasesStatusList() {
        return CASES_STATUS_LIST;
    }

    public static String[] getCasesPriorityList() {
        return CASES_PRIORITY_LIST;
    }

    public static String[] getCasesReason() {
        return CASES_REASON_LIST;
    }

    public static String[] getCasesOrigin() {
        return CASES_ORIGIN_LIST;
    }

    public static String[] getCasesType() {
        return CASES_TYPE_LIST;
    }

    public static String[] getTaskPriorities() {
        return TASK_PRIORITY_LIST;
    }

    public static String[] getTaskStatuses() {
        return TASK_STATUS_LIST;
    }
}
