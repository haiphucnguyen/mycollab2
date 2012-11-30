package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.view.account.AccountSelectionField;
import com.esofthead.mycollab.module.crm.view.campaign.CampaignSelectionField;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.FormAddView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;

public class OpportunityAddViewImpl extends FormAddView<Opportunity> implements
		OpportunityAddView {
	private static final long serialVersionUID = 1L;

	private EditForm editForm;

	private Opportunity opportunity;

	public OpportunityAddViewImpl() {
		super();
		editForm = new EditForm();
		this.addComponent(editForm);
	}

	@Override
	protected void onNewItem() {
		this.opportunity = new Opportunity();
		editForm.setItemDataSource(new BeanItem<Opportunity>(opportunity));
	}

	@Override
	public void onEditItem(Opportunity item) {
		this.opportunity = item;
		editForm.setItemDataSource(new BeanItem<Opportunity>(opportunity));
	}

	private class EditForm extends AdvancedEditBeanForm<Opportunity> {
		private static final long serialVersionUID = 1L;

		public EditForm() {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
		}

		class FormLayoutFactory extends OpportunityFormLayoutFactory {

			@Override
			protected Layout createButtonControls() {
				return (new EditFormControlsGenerator<Opportunity>(
						EditForm.this)).createButtonControls();
			}
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {
			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(Item item, Object propertyId,
					com.vaadin.ui.Component uiContext) {
				if (propertyId.equals("campaignid")) {
					CampaignSelectionField campaignField = new CampaignSelectionField();
					if (opportunity.getCampaignid() != null) {
						CampaignService campaignService = AppContext
								.getSpringBean(CampaignService.class);
						SimpleCampaign campaign = campaignService
								.findCampaignById(opportunity.getCampaignid());
						if (campaign != null) {
							campaignField.setCampaign(campaign);
						}
					}
					return campaignField;
				} else if (propertyId.equals("accountid")) {
					AccountSelectionField accountField = new AccountSelectionField();
					if (opportunity.getAccountid() != null) {
						AccountService accountService = AppContext
								.getSpringBean(AccountService.class);
						SimpleAccount account = accountService
								.findAccountById(opportunity.getAccountid());
						if (account != null) {
							accountField.setAccount(account);
						}
					}
					return accountField;
				}
				return null;
			}
		}
	}

	@Override
	public HasEditFormHandlers<Opportunity> getEditFormHandlers() {
		return editForm;
	}
}
