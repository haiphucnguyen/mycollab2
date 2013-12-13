package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.module.crm.view.activity.EventRelatedItemListComp;
import com.esofthead.mycollab.module.crm.view.contact.ContactDefaultDynaFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
class AbstractCampaignPreviewComp extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	protected AdvancedPreviewBeanForm<SimpleCampaign> previewForm;
	protected SimpleCampaign campaign;
	protected CampaignAccountListComp associateAccountList;
	protected CampaignContactListComp associateContactList;
	protected CampaignLeadListComp associateLeadList;
	protected EventRelatedItemListComp associateActivityList;
	protected NoteListItems noteListItems;

	protected void initRelatedComponents() {
		associateAccountList = new CampaignAccountListComp();
		associateContactList = new CampaignContactListComp();
		associateLeadList = new CampaignLeadListComp();
		associateActivityList = new EventRelatedItemListComp(true);
		noteListItems = new NoteListItems("Notes");
	}

	public void previewItem(SimpleCampaign campaign) {
		this.campaign = campaign;
		previewForm.setFormLayoutFactory(new DynaFormLayout(
				CrmTypeConstants.CONTACT, ContactDefaultDynaFormLayoutFactory
						.getForm()));
		previewForm.setBeanFormFieldFactory(new CampaignReadFormFieldFactory(
				previewForm));
		previewForm.setBean(campaign);
	}

	protected void displayNotes() {
		noteListItems.showNotes(CrmTypeConstants.CAMPAIGN, campaign.getId());
	}

	protected void displayActivities() {
		EventSearchCriteria criteria = new EventSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
		criteria.setType(new StringSearchField(SearchField.AND,
				CrmTypeConstants.CAMPAIGN));
		criteria.setTypeid(new NumberSearchField(campaign.getId()));
		associateActivityList.setSearchCriteria(criteria);
	}

	protected void displayAccounts() {
		associateAccountList.displayAccounts(campaign);
	}

	protected void displayContacts() {
		associateContactList.displayContacts(campaign);
	}

	protected void displayLeads() {
		associateLeadList.displayLeads(campaign);
	}

	public SimpleCampaign getCampaign() {
		return campaign;
	}

	public AdvancedPreviewBeanForm<SimpleCampaign> getPreviewForm() {
		return previewForm;
	}

	public EventRelatedItemListComp getAssociateActivityList() {
		return associateActivityList;
	}

	public CampaignAccountListComp getAssociateAccountList() {
		return associateAccountList;
	}

	public CampaignContactListComp getAssociateContactList() {
		return associateContactList;
	}

	public CampaignLeadListComp getAssociateLeadList() {
		return associateLeadList;
	}
}
