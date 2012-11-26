package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.domain.Campaign;
import com.esofthead.mycollab.module.crm.ui.components.CampaignStatusComboBox;
import com.esofthead.mycollab.module.crm.ui.components.CampaignTypeComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.FormAddView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;

public class CampaignAddViewImpl extends FormAddView<Campaign> implements
		CampaignAddView {
	private static final long serialVersionUID = 1L;

	private EditForm editForm;

	public CampaignAddViewImpl() {
		super();
		editForm = new EditForm();
		this.addComponent(editForm);
	}

	@Override
	protected void onNewItem() {
		editForm.setItemDataSource(new BeanItem<Campaign>(new Campaign()));
	}

	@Override
	protected void onEditItem(Campaign campaign) {
		editForm.setItemDataSource(new BeanItem<Campaign>(campaign));
	}

	private static class EditForm extends AdvancedEditBeanForm<Campaign> {
		private static final long serialVersionUID = 1L;

		public EditForm() {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
		}

		class FormLayoutFactory extends CampaignFormLayoutFactory {
			@Override
			protected HorizontalLayout createButtonControls() {
				return (new EditFormControlsGenerator<Campaign>(EditForm.this))
						.createButtonControls();
			}
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {
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
