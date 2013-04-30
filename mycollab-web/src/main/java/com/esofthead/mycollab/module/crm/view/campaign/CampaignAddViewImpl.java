package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.domain.CampaignWithBLOBs;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.CurrencyComboBox;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

@ViewComponent
public class CampaignAddViewImpl extends AbstractView implements
		IFormAddView<CampaignWithBLOBs>, CampaignAddView {

	private static final long serialVersionUID = 1L;
	private EditForm editForm;
	private CampaignWithBLOBs campaign;

	public CampaignAddViewImpl() {
		super();
		editForm = new EditForm();
		this.addComponent(editForm);
	}

	@Override
	public void editItem(CampaignWithBLOBs campaign) {
		this.campaign = campaign;
		editForm.setItemDataSource(new BeanItem<CampaignWithBLOBs>(campaign));
	}

	private class EditForm extends AdvancedEditBeanForm<CampaignWithBLOBs> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource);
		}

		class FormLayoutFactory extends CampaignFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super((campaign.getId() == null) ? "Create Campaign" : campaign
						.getCampaignname());
			}

			private HorizontalLayout createButtonControls() {
				return (new EditFormControlsGenerator<CampaignWithBLOBs>(
						EditForm.this)).createButtonControls();
			}

			@Override
			protected Layout createTopPanel() {
				return createButtonControls();
			}

			@Override
			protected Layout createBottomPanel() {
				return createButtonControls();
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
				} else if ("campaignname".equals(propertyId)) {
					TextField tf = new TextField();
					tf.setNullRepresentation("");
					tf.setRequired(true);
					tf.setRequiredError("Name must not be null");
					return tf;
				} else if ("description".equals(propertyId)) {
					TextArea descArea = new TextArea();
					descArea.setNullRepresentation("");
					return descArea;
				} else if ("assignuser".equals(propertyId)) {
					UserComboBox userBox = new UserComboBox();
					userBox.select(campaign.getAssignuser());
					return userBox;
				} else if (propertyId.equals("currencyid")) {
					return new CurrencyComboBox();
				}

				return null;
			}
		}
	}

	@Override
	public HasEditFormHandlers<CampaignWithBLOBs> getEditFormHandlers() {
		return editForm;
	}
}
