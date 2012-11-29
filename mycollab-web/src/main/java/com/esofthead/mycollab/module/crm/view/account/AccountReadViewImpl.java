package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class AccountReadViewImpl extends AbstractView implements
		AccountReadView {
	private static final long serialVersionUID = 1L;

	private PreviewForm previewForm;
	
	private Depot contactView;

	public AccountReadViewImpl() {
		super();
		previewForm = new PreviewForm();
		this.addComponent(previewForm);
	}

	@Override
	public void displayItem(Account account) {
		previewForm.setItemDataSource(new BeanItem<Account>(account));
	}

	@Override
	public HasPreviewFormHandlers<Account> getPreviewFormHandlers() {
		return previewForm;
	}

	private class PreviewForm extends AdvancedPreviewBeanForm<Account> {
		private static final long serialVersionUID = 1L;

		public PreviewForm() {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory());
		}

		class FormLayoutFactory extends AccountFormLayoutFactory {

			@Override
			protected Layout createTopPanel() {
				return (new PreviewFormControlsGenerator<Account>(
						PreviewForm.this)).createButtonControls();
			}

			@Override
			protected Layout createBottomPanel() {
				VerticalLayout relatedItemsPanel = new VerticalLayout();
				VerticalLayout test = new VerticalLayout();
				test.addComponent(new Label("AAA"));
				AccountReadViewImpl.this.contactView = new Depot("Contacts", test);
				relatedItemsPanel.addComponent(AccountReadViewImpl.this.contactView);
				
				return relatedItemsPanel;
			}
		}
	}

}
