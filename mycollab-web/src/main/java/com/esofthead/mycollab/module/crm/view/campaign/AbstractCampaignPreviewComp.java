package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.AbstractPreviewItemComp;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.module.crm.view.activity.EventRelatedItemListComp;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
abstract class AbstractCampaignPreviewComp extends
		AbstractPreviewItemComp<SimpleCampaign> {
	private static final long serialVersionUID = 1L;

	protected CampaignAccountListComp associateAccountList;
	protected CampaignContactListComp associateContactList;
	protected CampaignLeadListComp associateLeadList;
	protected EventRelatedItemListComp associateActivityList;
	protected NoteListItems noteListItems;

	public AbstractCampaignPreviewComp() {
		super(MyCollabResource.newResource("icons/22/crm/campaign.png"));
	}

	@Override
	protected void initRelatedComponents() {
		associateAccountList = new CampaignAccountListComp();
		associateContactList = new CampaignContactListComp();
		associateLeadList = new CampaignLeadListComp();
		associateActivityList = new EventRelatedItemListComp(true);
		noteListItems = new NoteListItems("Notes");
	}

	protected void displayNotes() {
		noteListItems.showNotes(CrmTypeConstants.CAMPAIGN, beanItem.getId());
	}

	protected void displayActivities() {
		EventSearchCriteria criteria = new EventSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
		criteria.setType(new StringSearchField(SearchField.AND,
				CrmTypeConstants.CAMPAIGN));
		criteria.setTypeid(new NumberSearchField(beanItem.getId()));
		associateActivityList.setSearchCriteria(criteria);
	}

	protected void displayAccounts() {
		associateAccountList.displayAccounts(beanItem);
	}

	protected void displayContacts() {
		associateContactList.displayContacts(beanItem);
	}

	protected void displayLeads() {
		associateLeadList.displayLeads(beanItem);
	}

	public SimpleCampaign getCampaign() {
		return beanItem;
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

	@Override
	protected void onPreviewItem() {
		displayActivities();
		displayAccounts();
		displayContacts();
		displayLeads();
	}

	@Override
	protected String initFormTitle() {
		return beanItem.getCampaignname();
	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		return new DynaFormLayout(CrmTypeConstants.CAMPAIGN,
				CampaignDefaultDynaFormLayoutFactory.getForm());
	}

	@Override
	protected AbstractBeanFieldGroupViewFieldFactory<SimpleCampaign> initBeanFormFieldFactory() {
		return new CampaignReadFormFieldFactory(previewForm);
	}
}
