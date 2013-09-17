package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.ui.components.IndustryComboBox;
import com.esofthead.mycollab.module.user.ui.components.ActiveUserComboBox;
import com.esofthead.mycollab.vaadin.ui.CountryComboBox;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PrefixListSelect;
import com.vaadin.data.Item;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class LeadEditFormFieldFactory extends DefaultEditFormFieldFactory {
	private static final long serialVersionUID = 1L;

	private Lead lead;

	public LeadEditFormFieldFactory(Lead lead) {
		this.lead = lead;
	}
	@Override
    protected Field onCreateField(Item item, Object propertyId,
            com.vaadin.ui.Component uiContext) {
        if (propertyId.equals("prefixname")) {
            return new PrefixListSelect();
        } else if (propertyId.equals("primcountry")
                || propertyId.equals("othercountry")) {
        	CountryComboBox otherCountryComboBox = new CountryComboBox();
            return otherCountryComboBox;
        } else if (propertyId.equals("status")) {
            LeadStatusComboBox statusComboBox = new LeadStatusComboBox();
            return statusComboBox;
        } else if (propertyId.equals("industry")) {
            IndustryComboBox industryComboBox = new IndustryComboBox();
            return industryComboBox;
        } else if (propertyId.equals("assignuser")) {
            ActiveUserComboBox userComboBox = new ActiveUserComboBox();
            return userComboBox;
        } else if (propertyId.equals("source")) {
            LeadSourceComboBox statusComboBox = new LeadSourceComboBox();
            return statusComboBox;
        } else if (propertyId.equals("lastname")) {
            TextField tf = new TextField();
            tf.setNullRepresentation("");
            tf.setRequired(true);
            tf.setRequiredError("Last name must not be null");
            return tf;
        } else if (propertyId.equals("description")) {
            TextArea descArea = new TextArea();
            descArea.setNullRepresentation("");
            return descArea;
        }

        return null;
    }
}
