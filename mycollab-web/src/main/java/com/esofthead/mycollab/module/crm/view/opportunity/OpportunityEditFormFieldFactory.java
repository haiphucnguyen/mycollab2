/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.view.account.AccountSelectionField;
import com.esofthead.mycollab.module.crm.view.campaign.CampaignSelectionField;
import com.esofthead.mycollab.module.crm.view.lead.LeadSourceComboBox;
import com.esofthead.mycollab.module.user.ui.components.ActiveUserComboBox;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupFieldFactory;
import com.esofthead.mycollab.vaadin.ui.CurrencyComboBox;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class OpportunityEditFormFieldFactory<B extends Opportunity> extends
		AbstractBeanFieldGroupFieldFactory<B> {
	private static final long serialVersionUID = 1L;

	public OpportunityEditFormFieldFactory(GenericBeanForm<B> form) {
		super(form);
	}

	@Override
	protected Field onCreateField(Object propertyId) {
		B opportunity = attachForm.getBean();

		if (propertyId.equals("campaignid")) {
			CampaignSelectionField campaignField = new CampaignSelectionField();
			if (opportunity.getCampaignid() != null) {
				CampaignService campaignService = ApplicationContextUtil
						.getSpringBean(CampaignService.class);
				SimpleCampaign campaign = campaignService.findById(
						opportunity.getCampaignid(), AppContext.getAccountId());
				if (campaign != null) {
					campaignField.setCampaign(campaign);
				}
			}
			return campaignField;
		} else if (propertyId.equals("accountid")) {
			AccountSelectionField accountField = new AccountSelectionField();
			accountField.setRequired(true);
			if (opportunity.getAccountid() != null) {
				AccountService accountService = ApplicationContextUtil
						.getSpringBean(AccountService.class);
				SimpleAccount account = accountService.findById(
						opportunity.getAccountid(), AppContext.getAccountId());
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
			if (opportunity.getCurrencyid() != null) {
				currencyBox.setValue(opportunity.getCurrencyid());
			}
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
			ActiveUserComboBox userBox = new ActiveUserComboBox();
			return userBox;
		}

		return null;
	}
}
