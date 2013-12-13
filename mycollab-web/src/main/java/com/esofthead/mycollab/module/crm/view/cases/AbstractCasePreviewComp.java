package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
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
abstract class AbstractCasePreviewComp extends
		AbstractPreviewItemComp<SimpleCase> {
	private static final long serialVersionUID = 1L;

	protected CaseContactListComp associateContactList;
	protected NoteListItems noteListItems;
	protected EventRelatedItemListComp associateActivityList;

	public AbstractCasePreviewComp() {
		super(MyCollabResource.newResource("icons/22/crm/case.png"));
	}

	@Override
	protected void onPreviewItem() {
		displayNotes();
		displayActivities();
		displayContacts();
	}

	@Override
	protected String initFormTitle() {
		return beanItem.getSubject();
	}

	@Override
	protected void initRelatedComponents() {
		associateContactList = new CaseContactListComp();
		associateActivityList = new EventRelatedItemListComp(true);
		noteListItems = new NoteListItems("Notes");
	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		return new DynaFormLayout(CrmTypeConstants.CASE,
				CasesDefaultFormLayoutFactory.getForm());
	}

	@Override
	protected AbstractBeanFieldGroupViewFieldFactory<SimpleCase> initBeanFormFieldFactory() {
		return new CaseReadFormFieldFactory(previewForm);
	}

	protected void displayNotes() {
		noteListItems.showNotes(CrmTypeConstants.CASE, beanItem.getId());
	}

	protected void displayActivities() {
		EventSearchCriteria criteria = new EventSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
		criteria.setType(new StringSearchField(SearchField.AND,
				CrmTypeConstants.CASE));
		criteria.setTypeid(new NumberSearchField(beanItem.getId()));
		associateActivityList.setSearchCriteria(criteria);
	}

	protected void displayContacts() {
		associateContactList.displayContacts(beanItem);
	}

	public EventRelatedItemListComp getAssociateActivityList() {
		return associateActivityList;
	}

	public CaseContactListComp getAssociateContactList() {
		return associateContactList;
	}

	public SimpleCase getCase() {
		return beanItem;
	}

	public AdvancedPreviewBeanForm<SimpleCase> getPreviewForm() {
		return previewForm;
	}

}
