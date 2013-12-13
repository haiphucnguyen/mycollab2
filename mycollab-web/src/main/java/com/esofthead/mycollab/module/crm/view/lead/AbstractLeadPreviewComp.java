package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
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
abstract class AbstractLeadPreviewComp extends
		AbstractPreviewItemComp<SimpleLead> {
	private static final long serialVersionUID = 1L;

	protected LeadCampaignListComp associateCampaignList;
	protected EventRelatedItemListComp associateActivityList;
	protected NoteListItems noteListItems;

	public AbstractLeadPreviewComp() {
		super(MyCollabResource.newResource("icons/22/crm/lead.png"));
	}

	@Override
	protected void onPreviewItem() {
		displayNotes();
		displayActivities();
		displayCampaigns();
	}

	@Override
	protected String initFormTitle() {
		return beanItem.getLeadName();
	}

	@Override
	protected void initRelatedComponents() {
		associateCampaignList = new LeadCampaignListComp();
		noteListItems = new NoteListItems("Notes");
		associateActivityList = new EventRelatedItemListComp(true);
	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		return new DynaFormLayout(CrmTypeConstants.LEAD,
				LeadDefaultDynaFormLayoutFactory.getForm());
	}

	@Override
	protected AbstractBeanFieldGroupViewFieldFactory<SimpleLead> initBeanFormFieldFactory() {
		return new LeadReadFormFieldFactory(previewForm);
	}

	protected void displayCampaigns() {
		associateCampaignList.displayCampaigns(beanItem);
	}

	protected void displayNotes() {
		noteListItems.showNotes(CrmTypeConstants.LEAD, beanItem.getId());
	}

	protected void displayActivities() {
		EventSearchCriteria criteria = new EventSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
		criteria.setType(new StringSearchField(SearchField.AND,
				CrmTypeConstants.LEAD));
		criteria.setTypeid(new NumberSearchField(beanItem.getId()));
		associateActivityList.setSearchCriteria(criteria);
	}

	public EventRelatedItemListComp getAssociateActivityList() {
		return associateActivityList;
	}

	public LeadCampaignListComp getAssociateCampaignList() {
		return associateCampaignList;
	}

	public AdvancedPreviewBeanForm<SimpleLead> getPreviewForm() {
		return previewForm;
	}

}
