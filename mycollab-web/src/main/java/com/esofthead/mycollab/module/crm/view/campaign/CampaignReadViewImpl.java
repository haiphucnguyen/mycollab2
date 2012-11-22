package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.domain.Campaign;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
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
				HorizontalLayout layout = new HorizontalLayout();
				layout.setSpacing(true);
				layout.setStyleName("addNewControl");
				Button editBtn = new Button(EDIT_ACTION,
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								@SuppressWarnings("unchecked")
								Campaign campaign = ((BeanItem<Campaign>) PreviewForm.this
										.getItemDataSource()).getBean();
								fireEditForm(campaign);
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
								Campaign campaign = ((BeanItem<Campaign>) PreviewForm.this
										.getItemDataSource()).getBean();
								fireDeleteForm(campaign);
							}
						});

				layout.addComponent(deleteBtn);
				layout.setComponentAlignment(deleteBtn, Alignment.MIDDLE_CENTER);

				Button cloneBtn = new Button(CLONE_ACTION,
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								@SuppressWarnings("unchecked")
								Campaign campaign = ((BeanItem<Campaign>) PreviewForm.this
										.getItemDataSource()).getBean();
								fireCloneForm(campaign);
							}
						});

				layout.addComponent(cloneBtn);
				layout.setComponentAlignment(cloneBtn, Alignment.MIDDLE_CENTER);
				return layout;
			}
		}
	}

}
