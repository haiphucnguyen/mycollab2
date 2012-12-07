package com.esofthead.mycollab.module.crm.ui.components;

import org.apache.commons.beanutils.PropertyUtils;
import org.vaadin.addon.customfield.CustomField;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.view.account.AccountSelectionWindow;
import com.esofthead.mycollab.module.crm.view.campaign.CampaignSelectionWindow;
import com.esofthead.mycollab.module.crm.view.contact.ContactSelectionWindow;
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
