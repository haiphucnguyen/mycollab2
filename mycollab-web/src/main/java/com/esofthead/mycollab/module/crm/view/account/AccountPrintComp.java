package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
class AccountPrintComp extends AbstractAccountPreviewComp {
	private static final long serialVersionUID = 1L;

	public AccountPrintComp() {
		previewForm = new AdvancedPreviewBeanForm<SimpleAccount>();
		initRelatedComponents();

		this.addComponent(previewForm);
	}

	@Override
	public void previewItem(SimpleAccount item) {
		super.previewItem(item);
		displayActivities();
		associateContactList.displayContacts(account);
		displayAssociateCaseList();
		displayAssociateOpportunityList();
		displayAssociateLeadList();
	}

	class FormLayoutFactory extends AccountFormLayoutFactory {

		private static final long serialVersionUID = 1L;

		public FormLayoutFactory() {
			super(account.getAccountname());
		}

		@Override
		protected Layout createBottomPanel() {
			final VerticalLayout relatedItemsPanel = new VerticalLayout();
			relatedItemsPanel.setWidth("100%");

			relatedItemsPanel.addComponent(noteListItems);
			relatedItemsPanel.addComponent(associateActivityList);
			relatedItemsPanel.addComponent(associateContactList);
			relatedItemsPanel.addComponent(associateOpportunityList);
			relatedItemsPanel.addComponent(associateCaseList);
			relatedItemsPanel.addComponent(associateLeadList);

			return relatedItemsPanel;
		}

		@Override
		protected Layout createTopPanel() {
			return null;
		}
	}

}
