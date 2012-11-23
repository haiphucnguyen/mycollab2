package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.HorizontalLayout;

public class OpportunityReadViewImpl extends AbstractView implements
		OpportunityReadView {
	private static final long serialVersionUID = 1L;

	private PreviewForm previewForm;

	public OpportunityReadViewImpl() {
		super();
		previewForm = new PreviewForm();
		this.addComponent(previewForm);
	}

	@Override
	public void displayItem(Opportunity item) {
		previewForm.setItemDataSource(new BeanItem<Opportunity>(item));
	}

	@Override
	public HasPreviewFormHandlers<Opportunity> getPreviewFormHandlers() {
		return previewForm;
	}

	private static class PreviewForm extends
			AdvancedPreviewBeanForm<Opportunity> {
		private static final long serialVersionUID = 1L;

		public PreviewForm() {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory());
		}

		class FormLayoutFactory extends OpportunityFormLayoutFactory {

			@Override
			protected HorizontalLayout createButtonControls() {
				return (new PreviewFormControlsGenerator<Opportunity>(
						PreviewForm.this)).createButtonControls();
			}
		}
	}

}
