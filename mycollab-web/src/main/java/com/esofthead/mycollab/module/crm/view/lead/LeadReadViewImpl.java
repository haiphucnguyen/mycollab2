package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormLinkViewField;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class LeadReadViewImpl extends AbstractView implements LeadReadView {
	private static final long serialVersionUID = 1L;

	private PreviewForm previewForm;

	private SimpleLead lead;

	public LeadReadViewImpl() {
		super();
		previewForm = new PreviewForm();
		this.addComponent(previewForm);
	}

	@Override
	public void previewItem(SimpleLead lead) {
		this.lead = lead;
		previewForm.setItemDataSource(new BeanItem<Lead>(lead));
	}

	@Override
	public HasPreviewFormHandlers<Lead> getPreviewFormHandlers() {
		return previewForm;
	}

	private class PreviewForm extends AdvancedPreviewBeanForm<Lead> {
		private static final long serialVersionUID = 1L;

		@SuppressWarnings("serial")
		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory() {

				@Override
				protected Field onCreateField(Item item, Object propertyId,
						Component uiContext) {
					if (propertyId.equals("firstname")) {
						if (lead.getTitle() == null) {
							return new FormViewField(lead.getFirstname());
						} else {
							return new FormViewField(lead.getTitle() + " "
									+ lead.getFirstname());
						}
					} else if (propertyId.equals("email")) {
						return new FormEmailLinkViewField(lead.getEmail());
					} else if (propertyId.equals("accountid")) {
						FormLinkViewField field = new FormLinkViewField(lead
								.getAccountname(), new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								
							}
						});
						
						return field;
					}

					return super.onCreateField(item, propertyId, uiContext);
				}

			});
			super.setItemDataSource(newDataSource);
		}

		class FormLayoutFactory extends LeadFormLayoutFactory {

			@Override
			protected HorizontalLayout createButtonControls() {
				return (new PreviewFormControlsGenerator<Lead>(PreviewForm.this))
						.createButtonControls();
			}
		}
	}

	@Override
	public SimpleLead getItem() {
		return lead;
	}
}
