package com.esofthead.mycollab.module.crm.ui.components;

import org.apache.commons.beanutils.PropertyUtils;
import org.vaadin.addon.customfield.CustomField;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;

public class RelatedReadItemField extends CustomField {
	private static final long serialVersionUID = 1L;

	private Object bean;

	public RelatedReadItemField(Object bean) {
		this.bean = bean;

		try {
			final Integer typeid = (Integer) PropertyUtils.getProperty(
					RelatedReadItemField.this.bean, "typeid");
			if (typeid == null) {
				this.setCompositionRoot(new Label(""));
				return;
			}

			final String type = (String) PropertyUtils
					.getProperty(bean, "type");
			if (type == null || type.equals("")) {
				this.setCompositionRoot(new Label(""));
				return;
			}

			ButtonLink l = null;
			
			if ("Account".equals(type)) {
				AccountService accountService = AppContext
						.getSpringBean(AccountService.class);
				final SimpleAccount account = accountService
						.findAccountById(typeid);
				if (account != null) {
					l = new ButtonLink(account.getAccountname(), new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							EventBus.getInstance().fireEvent(
									new AccountEvent.GotoRead(this, account
											.getId()));
						}
					});
				}
			} else if ("Campaign".equals(type)) {
				CampaignService campaignService = AppContext.getSpringBean(CampaignService.class);
				final SimpleCampaign campaign = campaignService.findCampaignById(typeid);
				if (campaign != null) {
					l = new ButtonLink(campaign.getCampaignname(), new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							EventBus.getInstance().fireEvent(
									new CampaignEvent.GotoRead(this, campaign
											.getId()));
							
						}
					});
				}
			}
			
			if (l != null) {
				this.setCompositionRoot(l);
			} else {
				this.setCompositionRoot(new Label(""));
			}
			
		} catch (Exception e) {
			this.setCompositionRoot(new Label(""));
		}
	}

	@Override
	public Class<?> getType() {
		return Object.class;
	}

}
