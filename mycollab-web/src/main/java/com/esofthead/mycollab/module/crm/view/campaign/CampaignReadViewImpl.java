package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.domain.Campaign;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.HorizontalLayout;

public class CampaignReadViewImpl extends AbstractView implements
		CampaignReadView {
	private static final long serialVersionUID = 1L;

	private PreviewForm previewForm;
	
	private SimpleCampaign campaign;

	public CampaignReadViewImpl() {
		super();
		previewForm = new PreviewForm();
		this.addComponent(previewForm);
	}

	@Override
	public void previewItem(SimpleCampaign campaign) {
		this.campaign = campaign;
		previewForm.setItemDataSource(new BeanItem<Campaign>(campaign));
	}

	@Override
	public HasPreviewFormHandlers<Campaign> getPreviewFormHandlers() {
		return previewForm;
	}

	private static class PreviewForm extends AdvancedPreviewBeanForm<Campaign> {
		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory());
			super.setItemDataSource(newDataSource);
		}

		class FormLayoutFactory extends CampaignFormLayoutFactory {
			private static final long serialVersionUID = 1L;

			@Override
			protected HorizontalLayout createButtonControls() {
				return (new PreviewFormControlsGenerator<Campaign>(
						PreviewForm.this)).createButtonControls();
			}
		}
	}

	@Override
	public SimpleCampaign getItem() {
		return campaign;
	}

}
