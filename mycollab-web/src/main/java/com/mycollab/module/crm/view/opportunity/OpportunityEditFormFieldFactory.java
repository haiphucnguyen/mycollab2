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

import com.mycollab.module.crm.domain.Opportunity;
import com.mycollab.module.crm.view.account.AccountSelectionField;
import com.mycollab.module.crm.view.campaign.CampaignSelectionField;
import com.mycollab.module.crm.view.lead.LeadSourceComboBox;
import com.mycollab.module.user.ui.components.ActiveUserComboBox;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.CurrencyComboBoxField;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.mycollab.vaadin.web.ui.DoubleField;
import com.mycollab.vaadin.web.ui.IntegerField;
import com.vaadin.data.HasValue;
import com.vaadin.ui.RichTextArea;
import org.vaadin.viritin.fields.MTextField;

/**
 * @param <B>
 * @author MyCollab Ltd.
 * @since 1.0
 */
// TODO
class OpportunityEditFormFieldFactory<B extends Opportunity> extends AbstractBeanFieldGroupEditFieldFactory<B> {
    private static final long serialVersionUID = 1L;

    OpportunityEditFormFieldFactory(GenericBeanForm<B> form) {
        super(form);
    }

    OpportunityEditFormFieldFactory(GenericBeanForm<B> form, boolean isValidateForm) {
        super(form, isValidateForm);
    }

    @Override
    protected HasValue<?> onCreateField(Object propertyId) {
        if (propertyId.equals("campaignid")) {
            return new CampaignSelectionField();
        } else if (propertyId.equals("accountid")) {
            AccountSelectionField accountField = new AccountSelectionField();
//            accountField.setRequired(true);
            return accountField;
        } else if (propertyId.equals("opportunityname")) {
            MTextField tf = new MTextField();
//            if (isValidateForm) {
//                tf.withNullRepresentation("").withRequired(true)
//                        .withRequiredError(UserUIContext.getMessage(ErrorI18nEnum.FIELD_MUST_NOT_NULL,
//                                UserUIContext.getMessage(GenericI18Enum.FORM_NAME)));
//            }
            return tf;
        } else if (propertyId.equals("currencyid")) {
            return new CurrencyComboBoxField();
        } else if (propertyId.equals("salesstage")) {
            return new OpportunitySalesStageComboBox();
        } else if (propertyId.equals("opportunitytype")) {
            return new OpportunityTypeComboBox();
        } else if (propertyId.equals("source")) {
            return new LeadSourceComboBox();
        } else if (propertyId.equals("description")) {
            return new RichTextArea();
        } else if (propertyId.equals("assignuser")) {
            return new ActiveUserComboBox();
        } else if (Opportunity.Field.amount.equalTo(propertyId)) {
            return new DoubleField();
        } else if (Opportunity.Field.probability.equalTo(propertyId)) {
            return new IntegerField();
        }

        return null;
    }
}
