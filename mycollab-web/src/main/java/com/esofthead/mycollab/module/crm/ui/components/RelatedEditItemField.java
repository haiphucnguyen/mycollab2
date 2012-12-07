package com.esofthead.mycollab.module.crm.ui.components;

import org.apache.commons.beanutils.PropertyUtils;
import org.vaadin.addon.customfield.CustomField;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.service.CaseService;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.crm.view.account.AccountSelectionWindow;
import com.esofthead.mycollab.module.crm.view.campaign.CampaignSelectionWindow;
import com.esofthead.mycollab.module.crm.view.cases.CaseSelectionWindow;
import com.esofthead.mycollab.module.crm.view.contact.ContactSelectionWindow;
import com.esofthead.mycollab.module.crm.view.lead.LeadSelectionWindow;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunitySelectionWindow;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.event.MouseEvents;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class RelatedEditItemField extends CustomField implements FieldSelection {
	private static final long serialVersionUID = 1L;

	private Object bean;

	private RelatedItemComboBox relatedItemComboBox;

	private TextField itemField;
	private Embedded browseBtn;
	private Embedded clearBtn;

	public RelatedEditItemField(String[] types, Object bean) {
		this.bean = bean;
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);

		relatedItemComboBox = new RelatedItemComboBox(types);
		layout.addComponent(relatedItemComboBox);

		itemField = new TextField();
		itemField.setEnabled(true);
		layout.addComponent(itemField);
		layout.setComponentAlignment(itemField, Alignment.MIDDLE_LEFT);

		browseBtn = new Embedded(null, new ThemeResource(
				"icons/16/browseItem.png"));
		browseBtn.addListener(new MouseEvents.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
				String type = (String) relatedItemComboBox.getValue();
				if ("Account".equals(type)) {
					AccountSelectionWindow accountWindow = new AccountSelectionWindow(
							RelatedEditItemField.this);
					getWindow().addWindow(accountWindow);
					accountWindow.show();
				} else if ("Campaign".equals(type)) {
					CampaignSelectionWindow campaignWindow = new CampaignSelectionWindow(
							RelatedEditItemField.this);
					getWindow().addWindow(campaignWindow);
					campaignWindow.show();
				} else if ("Contact".equals(type)) {
					ContactSelectionWindow contactWindow = new ContactSelectionWindow(
							RelatedEditItemField.this);
					getWindow().addWindow(contactWindow);
					contactWindow.show();
				} else if ("Lead".equals(type)) {
					LeadSelectionWindow leadWindow = new LeadSelectionWindow(
							RelatedEditItemField.this);
					getWindow().addWindow(leadWindow);
					leadWindow.show();
				} else if ("Opportunity".equals(type)) {
					OpportunitySelectionWindow opportunityWindow = new OpportunitySelectionWindow(
							RelatedEditItemField.this);
					getWindow().addWindow(opportunityWindow);
					opportunityWindow.show();
				} else if ("Case".equals(type)) {
					CaseSelectionWindow caseWindow = new CaseSelectionWindow(
							RelatedEditItemField.this);
					getWindow().addWindow(caseWindow);
					caseWindow.show();
				} else {
					relatedItemComboBox.focus();
				}
			}
		});

		layout.addComponent(browseBtn);
		layout.setComponentAlignment(browseBtn, Alignment.MIDDLE_LEFT);

		clearBtn = new Embedded(null, new ThemeResource(
				"icons/16/clearItem.png"));
		clearBtn.addListener(new MouseEvents.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void click(ClickEvent event) {
				try {
					PropertyUtils.setProperty(RelatedEditItemField.this.bean,
							"type", "");
					PropertyUtils.setProperty(RelatedEditItemField.this.bean,
							"typeid", null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		layout.addComponent(clearBtn);
		layout.setComponentAlignment(clearBtn, Alignment.MIDDLE_LEFT);

		this.setCompositionRoot(layout);
	}

	@Override
	public Class<?> getType() {
		return (new String[2]).getClass();
	}

	private class RelatedItemComboBox extends ValueComboBox {
		private static final long serialVersionUID = 1L;

		public RelatedItemComboBox(String[] types) {
			super();
			setCaption(null);
			this.setWidth("100px");
			this.loadData(types);
		}
	}

	public void setType(String type) {
		relatedItemComboBox.select(type);
		try {
			Integer typeid = (Integer) PropertyUtils
					.getProperty(bean, "typeid");
			if (typeid != null) {
				if ("Account".equals(type)) {
					AccountService accountService = AppContext
							.getSpringBean(AccountService.class);
					SimpleAccount account = accountService
							.findAccountById(typeid);
					if (account != null) {
						itemField.setValue(account.getAccountname());
					}
				} else if ("Campaign".equals(type)) {
					CampaignService campaignService = AppContext
							.getSpringBean(CampaignService.class);
					SimpleCampaign campaign = campaignService
							.findCampaignById(typeid);
					if (campaign != null) {
						itemField.setValue(campaign.getCampaignname());
					}
				} else if ("Contact".equals(type)) {
					ContactService contactService = AppContext
							.getSpringBean(ContactService.class);
					SimpleContact contact = contactService
							.findContactById(typeid);
					if (contact != null) {
						itemField.setValue(contact.getContactName());
					}
				} else if ("Lead".equals(type)) {
					LeadService leadService = AppContext
							.getSpringBean(LeadService.class);
					SimpleLead lead = leadService.findLeadById(typeid);
					if (lead != null) {
						itemField.setValue(lead.getLeadName());
					}
				} else if ("Opportunity".equals(type)) {
					OpportunityService opportunityService = AppContext
							.getSpringBean(OpportunityService.class);
					SimpleOpportunity opportunity = opportunityService
							.findOpportunityById(typeid);
					if (opportunity != null) {
						itemField.setValue(opportunity.getOpportunityname());
					}
				} else if ("Case".equals(type)) {
					CaseService caseService = AppContext
							.getSpringBean(CaseService.class);
					SimpleCase cases = caseService.findCaseById(typeid);
					if (cases != null) {
						itemField.setValue(cases.getSubject());
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void fireValueChange(Object data) {
		try {
			if (data instanceof SimpleAccount) {
				PropertyUtils.setProperty(bean, "type", "Account");
				PropertyUtils.setProperty(bean, "typeid",
						((SimpleAccount) data).getId());
				itemField.setValue(((SimpleAccount) data).getAccountname());
			} else if (data instanceof SimpleCampaign) {
				PropertyUtils.setProperty(bean, "type", "Campaign");
				PropertyUtils.setProperty(bean, "typeid",
						((SimpleCampaign) data).getId());
				itemField.setValue(((SimpleCampaign) data).getCampaignname());
			} else if (data instanceof SimpleContact) {
				PropertyUtils.setProperty(bean, "type", "Contact");
				PropertyUtils.setProperty(bean, "typeid",
						((SimpleContact) data).getId());
				itemField.setValue(((SimpleContact) data).getContactName());
			} else if (data instanceof SimpleLead) {
				PropertyUtils.setProperty(bean, "type", "Lead");
				PropertyUtils.setProperty(bean, "typeid",
						((SimpleLead) data).getId());
				itemField.setValue(((SimpleLead) data).getLeadName());
			} else if (data instanceof SimpleOpportunity) {
				PropertyUtils.setProperty(bean, "type", "Opportunity");
				PropertyUtils.setProperty(bean, "typeid",
						((SimpleOpportunity) data).getId());
				itemField.setValue(((SimpleOpportunity) data)
						.getOpportunityname());
			} else if (data instanceof SimpleCase) {
				PropertyUtils.setProperty(bean, "type", "Case");
				PropertyUtils.setProperty(bean, "typeid",
						((SimpleCase) data).getId());
				itemField.setValue(((SimpleCase) data).getSubject());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
