package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.domain.CampaignWithBLOBs;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.ui.CurrencyComboBox;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.data.Item;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class CampaignEditFormFieldFactory extends DefaultEditFormFieldFactory{
	private static final long serialVersionUID = 1L;
	
	private CampaignWithBLOBs campaign;
	
	public CampaignEditFormFieldFactory(CampaignWithBLOBs campaign){
		this.campaign = campaign;
	}
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
