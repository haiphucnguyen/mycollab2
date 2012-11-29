package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;

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
	public void displayItem(SimpleLead lead) {
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
		public PreviewForm() {
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
					}
					return super.onCreateField(item, propertyId, uiContext);
				}

			});
		}

		class FormLayoutFactory extends LeadFormLayoutFactory {

			@Override
			protected HorizontalLayout createButtonControls() {
				return (new PreviewFormControlsGenerator<Lead>(PreviewForm.this))
						.createButtonControls();
			}
		}
	}
}
