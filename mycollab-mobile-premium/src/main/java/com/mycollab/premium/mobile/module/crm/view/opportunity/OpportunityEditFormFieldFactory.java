package com.mycollab.premium.mobile.module.crm.view.opportunity;

import com.mycollab.mobile.module.user.ui.components.ActiveUserComboBox;
import com.mycollab.mobile.ui.CurrencyComboBoxField;
import com.mycollab.module.crm.domain.Opportunity;
import com.mycollab.premium.mobile.module.crm.view.account.AccountSelectionField;
import com.mycollab.premium.mobile.module.crm.view.campaign.CampaignSelectionField;
import com.mycollab.premium.mobile.module.crm.view.lead.LeadSourceListSelect;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.vaadin.data.HasValue;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

/**
 * @param <B>
 * @author MyCollab Ltd.
 * @since 4.1
 */
// TODO: revise this class
class OpportunityEditFormFieldFactory<B extends Opportunity> extends AbstractBeanFieldGroupEditFieldFactory<B> {
    private static final long serialVersionUID = 1L;

    OpportunityEditFormFieldFactory(GenericBeanForm<B> form) {
        super(form);
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
            TextField tf = new TextField();
            if (isValidateForm) {
//                tf.setNullRepresentation("");
//                tf.setRequired(true);
//                tf.setRequiredError("Name must not be null");
            }
            return tf;
        } else if (propertyId.equals("currencyid")) {
            return new CurrencyComboBoxField();
        } else if (propertyId.equals("salesstage")) {
            return new OpportunitySalesStageListSelect();
        } else if (propertyId.equals("opportunitytype")) {
            return new OpportunityTypeListSelect();
        } else if (propertyId.equals("source")) {
            return new LeadSourceListSelect();
        } else if (propertyId.equals("description")) {
            return new TextArea();
        } else if (propertyId.equals("assignuser")) {
            return new ActiveUserComboBox();
        }

        return null;
    }
}
