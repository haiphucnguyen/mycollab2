package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;

public class AccountReadViewImpl extends AbstractView implements
		AccountReadView {
	private static final long serialVersionUID = 1L;

	private PreviewForm previewForm;

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

	private static class PreviewForm extends AdvancedPreviewBeanForm<Account> {
		private static final long serialVersionUID = 1L;

		public PreviewForm() {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory());
		}

		class FormLayoutFactory extends AccountFormLayoutFactory {

			@Override
			protected HorizontalLayout createButtonControls() {
				HorizontalLayout layout = new HorizontalLayout();
				layout.setSpacing(true);
				layout.setStyleName("addNewControl");
				Button editBtn = new Button(EDIT_ACTION,
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								@SuppressWarnings("unchecked")
								Account account = ((BeanItem<Account>) PreviewForm.this
										.getItemDataSource()).getBean();
								fireEditForm(account);
							}
						});
				layout.addComponent(editBtn);
				layout.setComponentAlignment(editBtn, Alignment.MIDDLE_CENTER);

				Button deleteBtn = new Button(DELETE_ACTION,
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								@SuppressWarnings("unchecked")
								Account account = ((BeanItem<Account>) PreviewForm.this
										.getItemDataSource()).getBean();
								fireDeleteForm(account);
							}
						});

				layout.addComponent(deleteBtn);
				layout.setComponentAlignment(deleteBtn,
						Alignment.MIDDLE_CENTER);

				Button cloneBtn = new Button(CLONE_ACTION,
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								@SuppressWarnings("unchecked")
								Account account = ((BeanItem<Account>) PreviewForm.this
										.getItemDataSource()).getBean();
								fireCloneForm(account);
							}
						});

				layout.addComponent(cloneBtn);
				layout.setComponentAlignment(cloneBtn, Alignment.MIDDLE_CENTER);
				return layout;
			}
		}
	}

}
