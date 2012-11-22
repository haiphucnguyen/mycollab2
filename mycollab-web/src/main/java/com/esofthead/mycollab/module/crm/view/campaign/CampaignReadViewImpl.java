package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.domain.Campaign;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;

public class CampaignReadViewImpl extends AbstractView implements
		CampaignReadView {
	private static final long serialVersionUID = 1L;

	private PreviewForm previewForm;

	public CampaignReadViewImpl() {
		super();
		previewForm = new PreviewForm();
		this.addComponent(previewForm);
	}

	@Override
	public void displayItem(Campaign campaign) {
		previewForm.setItemDataSource(new BeanItem<Campaign>(campaign));
	}

	@Override
	public HasPreviewFormHandlers<Campaign> getPreviewFormHandlers() {
		return previewForm;
	}

	private static class PreviewForm extends AdvancedPreviewBeanForm<Campaign> {
		private static final long serialVersionUID = 1L;

		public PreviewForm() {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory());
		}

		class FormLayoutFactory extends CampaignFormLayoutFactory {

			@Override
			protected HorizontalLayout createButtonControls() {
				return (new PreviewFormControlsGenerator<Campaign>(
						PreviewForm.this)).createButtonControls();
			}
		}
	}

}
