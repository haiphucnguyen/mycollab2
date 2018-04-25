/**
 * Copyright © MyCollab
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.crm.view.opportunity;

import com.mycollab.module.crm.domain.SimpleOpportunity;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.vaadin.data.HasValue;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
// TODO: revise
public class OpportunityReadFormFieldFactory extends AbstractBeanFieldGroupViewFieldFactory<SimpleOpportunity> {
    private static final long serialVersionUID = 1L;

    public OpportunityReadFormFieldFactory(GenericBeanForm<SimpleOpportunity> form) {
        super(form);
    }

    @Override
    protected HasValue<?> onCreateField(Object propertyId) {
//        Field<?> field = null;
//        final SimpleOpportunity opportunity = attachForm.getBean();
//
//        if (propertyId.equals("accountid")) {
//            field = new LinkViewField(opportunity.getAccountName(),
//                    CrmLinkGenerator.generateAccountPreviewLink(opportunity.getAccountid()),
//                    CrmAssetsManager.getAsset(CrmTypeConstants.ACCOUNT));
//        } else if (propertyId.equals("campaignid")) {
//            field = new LinkViewField(opportunity.getCampaignName(),
//                    CrmLinkGenerator.generateCampaignPreviewLink(opportunity.getCampaignid()),
//                    CrmAssetsManager.getAsset(CrmTypeConstants.CAMPAIGN));
//        } else if (propertyId.equals("assignuser")) {
//            field = new UserLinkViewField(opportunity.getAssignuser(), opportunity.getAssignUserAvatarId(),
//                    opportunity.getAssignUserFullName());
//        } else if (propertyId.equals("expectedcloseddate")) {
//            return new DateViewField(opportunity.getExpectedcloseddate());
//        } else if (propertyId.equals("currencyid")) {
//            return new CurrencyViewField(opportunity.getCurrencyid());
//        } else if (propertyId.equals("description")) {
//            return new RichTextViewField(opportunity.getDescription());
//        } else if (Opportunity.Field.salesstage.equalTo(propertyId)) {
//            return new I18nFormViewField(opportunity.getSalesstage(), OpportunitySalesStage.class).withStyleName(UIConstants.FIELD_NOTE);
//        } else if (Opportunity.Field.opportunitytype.equalTo(propertyId)) {
//            return new I18nFormViewField(opportunity.getOpportunitytype(), OpportunityType.class);
//        } else if (Opportunity.Field.source.equalTo(propertyId)) {
//            return new I18nFormViewField(opportunity.getSource(), OpportunityLeadSource.class);
//        }
//
//        return field;
        return null;
    }

}
