package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.module.crm.view.activity.EventRelatedItemListComp;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class AbstractContactPreviewComp extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	protected AdvancedPreviewBeanForm<SimpleContact> previewForm;
	protected SimpleContact contact;
	protected ContactOpportunityListComp associateOpportunityList;
	protected EventRelatedItemListComp associateActivityList;
	protected NoteListItems noteListItems;

	protected void initRelatedComponent() {
		this.associateOpportunityList = new ContactOpportunityListComp();
		this.associateActivityList = new EventRelatedItemListComp(true);
		this.noteListItems = new NoteListItems("Notes");
	}

	public void previewItem(final SimpleContact item) {
		this.contact = item;
		previewForm.setFormLayoutFactory(new DynaFormLayout(
				CrmTypeConstants.CONTACT, ContactDefaultDynaFormLayoutFactory
						.getForm()));
		previewForm.setBeanFormFieldFactory(new ContactReadFormFieldFactory(
				previewForm));
		previewForm.setBean(item);
		this.displayNotes();
		this.displayActivities();
		this.displayAssociateOpportunityList();
	}

	public ContactOpportunityListComp getAssociateOpportunityList() {
		return this.associateOpportunityList;
	}

	public EventRelatedItemListComp getAssociateActivityList() {
		return this.associateActivityList;
	}

	public SimpleContact getContact() {
		return this.contact;
	}

	public AdvancedPreviewBeanForm<SimpleContact> getPreviewForm() {
		return this.previewForm;
	}

	protected void displayNotes() {
		this.noteListItems.showNotes(CrmTypeConstants.CONTACT,
				this.contact.getId());
	}

	protected void displayActivities() {
		final EventSearchCriteria criteria = new EventSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
		criteria.setType(new StringSearchField(SearchField.AND,
				CrmTypeConstants.CONTACT));
		criteria.setTypeid(new NumberSearchField(this.contact.getId()));
		this.associateActivityList.setSearchCriteria(criteria);
	}

	protected void displayAssociateOpportunityList() {
		final OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setContactId(new NumberSearchField(SearchField.AND,
				this.contact.getId()));
		this.associateOpportunityList.displayOpportunities(this.contact);
	}

}
