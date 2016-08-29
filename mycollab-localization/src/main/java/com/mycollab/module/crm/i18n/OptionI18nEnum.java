/**
 * This file is part of mycollab-localization.
 *
 * mycollab-localization is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-localization is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-localization.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.crm.i18n;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

/**
 * @author MyCollab Ltd.
 * @since 4.3.0
 */
public class OptionI18nEnum {

    @BaseName("crm-accounttype")
    @LocaleData(value = {@Locale("en-US")}, defaultCharset = "UTF-8")
    public enum AccountType {
        Analyst,
        Competitor,
        Customer,
        Integrator,
        Investor,
        Partner,
        Press,
        Prospect,
        Reseller,
        Other
    }

    @BaseName("crm-accountindustry")
    @LocaleData(value = {@Locale("en-US")}, defaultCharset = "UTF-8")
    public enum AccountIndustry {
        Apparel,
        Banking,
        Biotechnology,
        Chemicals,
        Communications,
        Construction,
        Consulting,
        Education,
        Electronics,
        Energy,
        Engineering,
        Entertainment,
        Environmental,
        Finance,
        Government,
        Healthcare,
        Hospitality,
        Insurance,
        Machinery,
        Manufactory,
        Media,
        Not_For_Profit,
        Retail,
        Shipping,
        Technology,
        Telecommunications,
        Other
    }

    @BaseName("crm-opportunitysalesstage")
    @LocaleData(value = {@Locale("en-US")}, defaultCharset = "UTF-8")
    public enum OpportunitySalesStage {
        Prospecting,
        Qualification,
        Need_Analysis,
        Value_Proposition,
        Perception_Analysis,
        Proposal_Price_Quote,
        Negotiation_Review,
        Closed_Won,
        Closed_Lost
    }

    @BaseName("crm-opportunityleadsource")
    @LocaleData(value = {@Locale("en-US")}, defaultCharset = "UTF-8")
    public enum OpportunityLeadSource {
        Cold_Call,
        Existing_Customer,
        Self_Generated,
        Employee,
        Partner,
        Public_Relations,
        Direct_Email,
        Conference,
        Trade_Show,
        Website,
        Word_of_mouth,
        Email,
        Campaign,
        Other
    }

    @BaseName("crm-campaigntype")
    @LocaleData(value = {@Locale("en-US")}, defaultCharset = "UTF-8")
    public enum CampaignType {
        Conference,
        Webinar,
        Trade_Show,
        Public_Relations,
        Partners,
        Referral_Program,
        Advertisement,
        Banner_Ads,
        Direct_Email,
        Mail,
        Telemarketing,
        Other
    }
}
