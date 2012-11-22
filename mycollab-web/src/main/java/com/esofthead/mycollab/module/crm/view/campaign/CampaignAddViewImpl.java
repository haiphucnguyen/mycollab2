package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.domain.Campaign;
import com.esofthead.mycollab.module.crm.ui.components.CampaignStatusComboBox;
import com.esofthead.mycollab.module.crm.ui.components.CampaignTypeComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;

public class CampaignAddViewImpl extends AbstractView implements
		CampaignAddView {
	private static final long serialVersionUID = 1L;

	private EditForm editForm;

	public CampaignAddViewImpl() {
		super();
		editForm = new EditForm();
		this.addComponent(editForm);
	}

	@Override
	public void addNewItem() {
		editForm.setItemDataSource(new BeanItem<Campaign>(new Campaign()));
	}

	@Override
	public void editItem(Campaign campaign) {
		editForm.setItemDataSource(new BeanItem<Campaign>(campaign));
	}

	public static class EditForm extends AdvancedEditBeanForm<Campaign> {
		private static final long serialVersionUID = 1L;

		public EditForm() {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
		}

		class FormLayoutFactory extends CampaignFormLayoutFactory {
			@SuppressWarnings("serial")
			@Override
			protected HorizontalLayout createButtonControls() {
				HorizontalLayout layout = new HorizontalLayout();
				layout.setSpacing(true);
				layout.setStyleName("addNewControl");
				Button saveBtn = new Button(SAVE_ACTION,
						new Button.ClickListener() {

							@Override
							public void buttonClick(ClickEvent event) {
								@SuppressWarnings("unchecked")
								Campaign campaign = ((BeanItem<Campaign>) EditForm.this
										.getItemDataSource()).getBean();
								if (EditForm.this.validateForm(campaign)) {
									EditForm.this.fireSaveForm(campaign);
								}
							}
						});
				layout.addComponent(saveBtn);
				layout.setComponentAlignment(saveBtn, Alignment.MIDDLE_CENTER);

				Button saveAndNewBtn = new Button(SAVE_AND_NEW_ACTION,
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								@SuppressWarnings("unchecked")
								Campaign campaign = ((BeanItem<Campaign>) EditForm.this
										.getItemDataSource()).getBean();

								EditForm.this.fireSaveAndNewForm(campaign);
							}
						});

				layout.addComponent(saveAndNewBtn);
				layout.setComponentAlignment(saveAndNewBtn,
						Alignment.MIDDLE_CENTER);

				Button cancelBtn = new Button(CANCEL_ACTION,
						new Button.ClickListener() {

							@Override
							public void buttonClick(ClickEvent event) {
								EditForm.this.fireCancelForm();
							}
						});
				layout.addComponent(cancelBtn);
				layout.setComponentAlignment(cancelBtn, Alignment.MIDDLE_CENTER);

				return layout;
			}
		}

		private class EditFormFieldFactory extends DefaultFormEditFieldFactory {
			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(Item item, Object propertyId,
					com.vaadin.ui.Component uiContext) {

				if ("type".equals(propertyId)) {
					CampaignTypeComboBox typeCombo = new CampaignTypeComboBox();
					typeCombo.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
					return typeCombo;
				} else if ("status".equals(propertyId)) {
					CampaignStatusComboBox statusCombo = new CampaignStatusComboBox();
					statusCombo.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
					return statusCombo;
				}

				return null;
			}
		}
	}

	@Override
	public HasEditFormHandlers<Campaign> getEditFormHandlers() {
		return editForm;
	}
}
