package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ActivitySearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.AbstractPreviewItemComp;
import com.esofthead.mycollab.module.crm.ui.components.CrmPreviewFormControlsGenerator;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.module.crm.view.activity.ActivityRelatedItemListComp;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.resource.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.resource.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.resource.ui.IFormLayoutFactory;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;

/**
 * 
 * @author MyCollab
 * @since 3.0
 * 
 */
class ContactReadComp extends AbstractPreviewItemComp<SimpleContact> {
	private static final long serialVersionUID = 1L;

	protected ContactOpportunityListComp associateOpportunityList;
	protected ActivityRelatedItemListComp associateActivityList;
	protected NoteListItems noteListItems;

	public ContactReadComp() {
		super(MyCollabResource.newResource("icons/22/crm/contact.png"));
	}

	@Override
	protected ComponentContainer createBottomPanel() {
		final TabSheet tabContainer = new TabSheet();
		tabContainer.setWidth("100%");

		tabContainer.addTab(this.noteListItems, "Notes",
				MyCollabResource.newResource("icons/16/crm/note.png"));
		tabContainer.addTab(this.associateActivityList, "Activities",
				MyCollabResource.newResource("icons/16/crm/calendar.png"));
		tabContainer.addTab(this.associateOpportunityList, "Opportunities",
				MyCollabResource.newResource("icons/16/crm/opportunity.png"));
		return tabContainer;
	}

	@Override
	protected AdvancedPreviewBeanForm<SimpleContact> initPreviewForm() {
		return new AdvancedPreviewBeanForm<SimpleContact>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void showHistory() {
				final ContactHistoryLogWindow historyLog = new ContactHistoryLogWindow(
						ModuleNameConstants.CRM, CrmTypeConstants.CONTACT);
				historyLog.loadHistory(beanItem.getId());
				UI.getCurrent().addWindow(historyLog);
			}
		};
	}

	@Override
	protected ComponentContainer createButtonControls() {
		return CrmPreviewFormControlsGenerator.createFormButtonControls(
				previewForm, RolePermissionCollections.CRM_CONTACT);
	}

	public ContactOpportunityListComp getAssociateOpportunityList() {
		return this.associateOpportunityList;
	}

	public ActivityRelatedItemListComp getAssociateActivityList() {
		return this.associateActivityList;
	}

	public SimpleContact getContact() {
		return this.beanItem;
	}

	public AdvancedPreviewBeanForm<SimpleContact> getPreviewForm() {
		return this.previewForm;
	}

	protected void displayNotes() {
		this.noteListItems.showNotes(CrmTypeConstants.CONTACT,
				this.beanItem.getId());
	}

	protected void displayActivities() {
		final ActivitySearchCriteria criteria = new ActivitySearchCriteria();
		criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
		criteria.setType(new StringSearchField(SearchField.AND,
				CrmTypeConstants.CONTACT));
		criteria.setTypeid(new NumberSearchField(this.beanItem.getId()));
		this.associateActivityList.setSearchCriteria(criteria);
	}

	protected void displayAssociateOpportunityList() {
		final OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setContactId(new NumberSearchField(SearchField.AND,
				this.beanItem.getId()));
		this.associateOpportunityList.displayOpportunities(this.beanItem);
	}

	@Override
	protected void onPreviewItem() {
		this.displayNotes();
		this.displayActivities();
		this.displayAssociateOpportunityList();
	}

	@Override
	protected String initFormTitle() {
		return beanItem.getContactName();
	}

	@Override
	protected void initRelatedComponents() {
		this.associateOpportunityList = new ContactOpportunityListComp();
		this.associateActivityList = new ActivityRelatedItemListComp(true);
		this.noteListItems = new NoteListItems("Notes");

	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		return new DynaFormLayout(CrmTypeConstants.CONTACT,
				ContactDefaultDynaFormLayoutFactory.getForm());
	}

	@Override
	protected AbstractBeanFieldGroupViewFieldFactory<SimpleContact> initBeanFormFieldFactory() {
		return new ContactReadFormFieldFactory(previewForm);
	}
}
