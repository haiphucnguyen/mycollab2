package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
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

public class OpportunityReadViewImpl extends AbstractView implements
		OpportunityReadView {
	private static final long serialVersionUID = 1L;

	private PreviewForm previewForm;

	private SimpleOpportunity opportunity;

	public OpportunityReadViewImpl() {
		super();
		previewForm = new PreviewForm();
		this.addComponent(previewForm);
	}

	@Override
	public void previewItem(SimpleOpportunity item) {
		this.opportunity = item;
		previewForm.setItemDataSource(new BeanItem<Opportunity>(opportunity));
	}

	@Override
	public HasPreviewFormHandlers<Opportunity> getPreviewFormHandlers() {
		return previewForm;
	}

	private class PreviewForm extends AdvancedPreviewBeanForm<Opportunity> {
		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
				private static final long serialVersionUID = 1L;

				@Override
				protected Field onCreateField(Item item, Object propertyId,
						Component uiContext) {
					Field field = null;
					if (propertyId.equals("accountid")) {
						field = new FormViewField(opportunity.getAccountName());
					} else if (propertyId.equals("campaignid")) {
						field = new FormViewField(opportunity.getCampaignName());
					}
					return field;
				}

			});
			super.setItemDataSource(newDataSource);
		}

		class FormLayoutFactory extends OpportunityFormLayoutFactory {

			@Override
			protected HorizontalLayout createButtonControls() {
				return (new PreviewFormControlsGenerator<Opportunity>(
						PreviewForm.this)).createButtonControls();
			}
		}
	}

	@Override
	public SimpleOpportunity getItem() {
		return opportunity;
	}

}
