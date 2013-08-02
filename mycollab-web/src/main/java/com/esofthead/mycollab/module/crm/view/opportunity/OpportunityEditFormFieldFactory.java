package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.view.account.AccountSelectionField;
import com.esofthead.mycollab.module.crm.view.campaign.CampaignSelectionField;
import com.esofthead.mycollab.module.crm.view.lead.LeadSourceComboBox;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.ui.CurrencyComboBox;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class OpportunityEditFormFieldFactory extends DefaultEditFormFieldFactory{
	private static final long serialVersionUID = 1L;
	private Opportunity opportunity;
	public OpportunityEditFormFieldFactory(Opportunity opportunity){
		this.opportunity = opportunity;
	}
	@Override
	protected Field onCreateField(Item item, Object propertyId,
			com.vaadin.ui.Component uiContext) {
		if (propertyId.equals("campaignid")) {
			CampaignSelectionField campaignField = new CampaignSelectionField();
			if (opportunity.getCampaignid() != null) {
				CampaignService campaignService = AppContext
						.getSpringBean(CampaignService.class);
				SimpleCampaign campaign = campaignService
						.findById(opportunity.getCampaignid());
				if (campaign != null) {
					campaignField.setCampaign(campaign);
				}
			}
			return campaignField;
		} else if (propertyId.equals("accountid")) {
			AccountSelectionField accountField = new AccountSelectionField();
			accountField.setRequired(true);
			if (opportunity.getAccountid() != null) {
				AccountService accountService = AppContext
						.getSpringBean(AccountService.class);
				SimpleAccount account = accountService
						.findById(opportunity.getAccountid());
				if (account != null) {
					accountField.setAccount(account);
				}
			}
			return accountField;
		} else if (propertyId.equals("opportunityname")) {
			TextField tf = new TextField();
			tf.setNullRepresentation("");
			tf.setRequired(true);
			tf.setRequiredError("Name must not be null");
			return tf;
		} else if (propertyId.equals("currencyid")) {
			CurrencyComboBox currencyBox = new CurrencyComboBox();
			
			return currencyBox;
		} else if (propertyId.equals("salesstage")) {
			return new OpportunitySalesStageComboBox();
		} else if (propertyId.equals("opportunitytype")) {
			return new OpportunityTypeComboBox();
		} else if (propertyId.equals("source")) {
			return new LeadSourceComboBox();
		} else if (propertyId.equals("description")) {
			TextArea descArea = new TextArea();
			descArea.setNullRepresentation("");
			return descArea;
		} else if (propertyId.equals("assignuser")) {
			UserComboBox userBox = new UserComboBox();
			return userBox;
		}

		return null;
	}
}
