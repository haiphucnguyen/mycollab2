package com.esofthead.mycollab.mobile.module.crm.view.account;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.mobile.form.view.DynaFormLayout;
import com.esofthead.mycollab.mobile.module.crm.events.AccountEvent;
import com.esofthead.mycollab.mobile.module.crm.ui.AbstractPreviewItemComp;
import com.esofthead.mycollab.mobile.module.crm.ui.AccountRelatedItemsScreenData;
import com.esofthead.mycollab.mobile.module.crm.ui.CrmPreviewFormControlsGenerator;
import com.esofthead.mycollab.mobile.module.crm.ui.NotesList;
import com.esofthead.mycollab.mobile.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.mobile.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.mobile.ui.IFormLayoutFactory;
import com.esofthead.mycollab.mobile.ui.IconConstants;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.ActivitySearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
@ViewComponent
public class AccountReadViewImpl extends AbstractPreviewItemComp<SimpleAccount> implements AccountReadView {

	private static final long serialVersionUID = -5987636662071328512L;

	protected NotesList associateNotes;
	protected AccountRelatedContactView associateContacts;
	protected AccountRelatedCaseView associateCases;
	protected AccountRelatedActivityView associateActivities;
	protected AccountRelatedLeadView associateLeads;
	protected AccountRelatedOpportunityView associateOpportunities;

	@Override
	public void previewItem(SimpleAccount item) {
		this.beanItem = item;
		this.setCaption(initFormTitle());

		previewForm.setFormLayoutFactory(initFormLayoutFactory());
		previewForm.setBeanFormFieldFactory(initBeanFormFieldFactory());
		previewForm.setBean(item);

		onPreviewItem();
	}

	@Override
	public SimpleAccount getItem() {
		return this.beanItem;
	}

	public NotesList getAssociateNotes() {
		if (associateNotes == null)
			associateNotes = new NotesList("Related Notes");
		associateNotes.showNotes(CrmTypeConstants.ACCOUNT, beanItem.getId());
		return associateNotes;
	}

	public AccountRelatedContactView getAssociateContacts() {
		if (associateContacts == null)
			associateContacts = new AccountRelatedContactView();
		associateContacts.displayContacts(beanItem);
		return associateContacts;
	}

	public AccountRelatedCaseView getAssociateCases() {
		if (associateCases == null)
			associateCases = new AccountRelatedCaseView();
		final CaseSearchCriteria criteria = new CaseSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setAccountId(new NumberSearchField(beanItem.getId()));
		associateCases.setSearchCriteria(criteria);
		return associateCases;
	}

	public AccountRelatedActivityView getAssociateActivities() {
		if (associateActivities == null)
			associateActivities = new AccountRelatedActivityView();
		final ActivitySearchCriteria criteria = new ActivitySearchCriteria();
		criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
		criteria.setType(new StringSearchField(SearchField.AND,
				CrmTypeConstants.ACCOUNT));
		criteria.setTypeid(new NumberSearchField(beanItem.getId()));
		associateActivities.setSearchCriteria(criteria);
		return associateActivities;
	}

	public AccountRelatedLeadView getAssociateLeads() {
		if (associateLeads == null)
			associateLeads = new AccountRelatedLeadView();
		associateLeads.displayLeads(beanItem);
		return associateLeads;
	}

	public AccountRelatedOpportunityView getAssociateOpportunities() {
		if (associateOpportunities == null)
			associateOpportunities = new AccountRelatedOpportunityView();
		final OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setAccountId(new NumberSearchField(SearchField.AND, beanItem
				.getId()));
		associateOpportunities.setSearchCriteria(criteria);
		return associateOpportunities;
	}

	@Override
	protected void onPreviewItem() {

	}

	@Override
	protected String initFormTitle() {
		return beanItem.getAccountname();
	}

	@Override
	protected AdvancedPreviewBeanForm<SimpleAccount> initPreviewForm() {
		return new AdvancedPreviewBeanForm<SimpleAccount>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void showHistory() {
				// TODO add historyWindow
			}
		};
	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		return new DynaFormLayout(CrmTypeConstants.ACCOUNT,
				AccountDefaultDynaFormLayoutFactory.getForm());
	}

	@Override
	protected AbstractBeanFieldGroupViewFieldFactory<SimpleAccount> initBeanFormFieldFactory() {
		return new AccountReadFormFieldFactory(previewForm);
	}

	@Override
	public HasPreviewFormHandlers<SimpleAccount> getPreviewFormHandlers() {
		return this.previewForm;
	}

	@Override
	protected ComponentContainer createButtonControls() {
		return new CrmPreviewFormControlsGenerator<SimpleAccount>(previewForm)
				.createButtonControls(RolePermissionCollections.CRM_ACCOUNT);
	}

	@Override
	protected ComponentContainer createBottomPanel() {
		HorizontalLayout toolbarLayout = new HorizontalLayout();
		toolbarLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
		toolbarLayout.setSpacing(true);

		Button relatedNotes = new Button();
		relatedNotes.setCaption("<span aria-hidden=\"true\" data-icon=\""+ IconConstants.CRM_DOCUMENT + "\"></span><div class=\"screen-reader-text\">Note</div>");
		relatedNotes.setHtmlContentAllowed(true);
		relatedNotes.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 7589415773039335559L;

			@Override
			public void buttonClick(ClickEvent arg0) {
				EventBus.getInstance().fireEvent(new AccountEvent.GoToRelatedItems(this, new AccountRelatedItemsScreenData(getAssociateNotes())));
			}
		});
		toolbarLayout.addComponent(relatedNotes);

		Button relatedActivities = new Button();
		relatedActivities.setCaption("<span aria-hidden=\"true\" data-icon=\""+ IconConstants.CRM_ACTIVITY + "\"></span><div class=\"screen-reader-text\">Activities</div>");
		relatedActivities.setHtmlContentAllowed(true);
		relatedActivities.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 7589415773039335559L;

			@Override
			public void buttonClick(ClickEvent arg0) {
				EventBus.getInstance().fireEvent(new AccountEvent.GoToRelatedItems(this, new AccountRelatedItemsScreenData(getAssociateActivities())));
			}
		});
		toolbarLayout.addComponent(relatedActivities);

		Button relatedContacts = new Button();
		relatedContacts.setCaption("<span aria-hidden=\"true\" data-icon=\""+ IconConstants.CRM_CONTACT + "\"></span><div class=\"screen-reader-text\">Contacts</div>");
		relatedContacts.setHtmlContentAllowed(true);
		relatedContacts.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 7589415773039335559L;

			@Override
			public void buttonClick(ClickEvent arg0) {
				EventBus.getInstance().fireEvent(new AccountEvent.GoToRelatedItems(this, new AccountRelatedItemsScreenData(getAssociateContacts())));
			}
		});
		toolbarLayout.addComponent(relatedContacts);

		Button relatedOpportunities = new Button();
		relatedOpportunities.setCaption("<span aria-hidden=\"true\" data-icon=\""+ IconConstants.CRM_OPPORTUNITY + "\"></span><div class=\"screen-reader-text\">Opportunities</div>");
		relatedOpportunities.setHtmlContentAllowed(true);
		relatedOpportunities.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 7589415773039335559L;

			@Override
			public void buttonClick(ClickEvent arg0) {
				EventBus.getInstance().fireEvent(new AccountEvent.GoToRelatedItems(this, new AccountRelatedItemsScreenData(getAssociateOpportunities())));
			}
		});
		toolbarLayout.addComponent(relatedOpportunities);

		Button relatedLeads = new Button();
		relatedLeads.setCaption("<span aria-hidden=\"true\" data-icon=\""+ IconConstants.CRM_LEAD + "\"></span><div class=\"screen-reader-text\">Leads</div>");
		relatedLeads.setHtmlContentAllowed(true);
		relatedLeads.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 7589415773039335559L;

			@Override
			public void buttonClick(ClickEvent arg0) {
				EventBus.getInstance().fireEvent(new AccountEvent.GoToRelatedItems(this, new AccountRelatedItemsScreenData(getAssociateLeads())));
			}
		});
		toolbarLayout.addComponent(relatedLeads);

		return toolbarLayout;
	}

}
