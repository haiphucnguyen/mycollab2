package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
class ContactPrintComp extends AbstractContactPreviewComp {
	private static final long serialVersionUID = 1L;

	public ContactPrintComp() {
		previewForm = new AdvancedPreviewBeanForm<SimpleContact>();
		this.initRelatedComponent();

		this.addComponent(previewForm);
	}

	@Override
	public void previewItem(SimpleContact item) {
		super.previewItem(item);
		displayActivities();
		displayAssociateOpportunityList();
		displayNotes();
	}

	class FormLayoutFactory extends ContactFormLayoutFactory {

		private static final long serialVersionUID = 1L;

		public FormLayoutFactory() {
			super(contact.getContactName());
		}

		@Override
		protected Layout createBottomPanel() {
			final VerticalLayout relatedItemsPanel = new VerticalLayout();
			relatedItemsPanel.setWidth("100%");

			relatedItemsPanel.addComponent(noteListItems);
			relatedItemsPanel.addComponent(associateActivityList);
			relatedItemsPanel.addComponent(associateOpportunityList);

			return relatedItemsPanel;
		}

		@Override
		protected Layout createTopPanel() {
			return null;
		}
	}
}
