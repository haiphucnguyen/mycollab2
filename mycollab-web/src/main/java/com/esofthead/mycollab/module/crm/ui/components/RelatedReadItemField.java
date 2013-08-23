package com.esofthead.mycollab.module.crm.ui.components;

import org.apache.commons.beanutils.PropertyUtils;
import org.vaadin.addon.customfield.CustomField;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.crm.events.CaseEvent;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.service.CaseService;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
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

			ButtonLink relatedLink = null;

			if ("Account".equals(type)) {
				AccountService accountService = AppContext
						.getSpringBean(AccountService.class);
				final SimpleAccount account = accountService.findById(typeid,
						AppContext.getAccountId());
				if (account != null) {
					relatedLink = new ButtonLink(account.getAccountname(),
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(ClickEvent event) {
									EventBus.getInstance().fireEvent(
											new AccountEvent.GotoRead(this,
													account.getId()));
								}
							});
				}
			} else if ("Campaign".equals(type)) {
				CampaignService campaignService = AppContext
						.getSpringBean(CampaignService.class);
				final SimpleCampaign campaign = campaignService.findById(
						typeid, AppContext.getAccountId());
				if (campaign != null) {
					relatedLink = new ButtonLink(campaign.getCampaignname(),
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(ClickEvent event) {
									EventBus.getInstance().fireEvent(
											new CampaignEvent.GotoRead(this,
													campaign.getId()));

								}
							});
				}
			} else if ("Contact".equals(type)) {
				ContactService contactService = AppContext
						.getSpringBean(ContactService.class);
				final SimpleContact contact = contactService.findById(typeid,
						AppContext.getAccountId());
				if (contact != null) {
					relatedLink = new ButtonLink(contact.getContactName(),
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(ClickEvent event) {
									EventBus.getInstance().fireEvent(
											new ContactEvent.GotoRead(this,
													contact.getId()));
								}
							});
				}
			} else if ("Lead".equals(type)) {
				LeadService leadService = AppContext
						.getSpringBean(LeadService.class);
				final SimpleLead lead = leadService.findById(typeid,
						AppContext.getAccountId());
				if (lead != null) {
					relatedLink = new ButtonLink(lead.getLeadName(),
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(ClickEvent event) {
									EventBus.getInstance().fireEvent(
											new LeadEvent.GotoRead(this, lead
													.getId()));
								}
							});
				}
			} else if ("Opportunity".equals(type)) {
				OpportunityService opportunityService = AppContext
						.getSpringBean(OpportunityService.class);
				final SimpleOpportunity opportunity = opportunityService
						.findById(typeid, AppContext.getAccountId());
				if (opportunity != null) {
					relatedLink = new ButtonLink(
							opportunity.getOpportunityname(),
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(ClickEvent event) {
									EventBus.getInstance().fireEvent(
											new OpportunityEvent.GotoRead(this,
													opportunity.getId()));
								}
							});
				}
			} else if ("Case".equals(type)) {
				CaseService caseService = AppContext
						.getSpringBean(CaseService.class);
				final SimpleCase cases = caseService.findById(typeid,
						AppContext.getAccountId());
				if (cases != null) {
					relatedLink = new ButtonLink(cases.getSubject(),
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(ClickEvent event) {
									EventBus.getInstance().fireEvent(
											new CaseEvent.GotoRead(this, cases
													.getId()));

								}
							});
				}
			}

			if (relatedLink != null) {
				this.setCompositionRoot(relatedLink);
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
