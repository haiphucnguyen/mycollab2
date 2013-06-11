package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.view.account.AccountSelectionField;
import com.esofthead.mycollab.module.crm.view.lead.LeadSourceComboBox;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.ui.CountryComboBox;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.vaadin.data.Item;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class ContactEditFormFieldFactory extends DefaultEditFormFieldFactory {
	private static final long serialVersionUID = 1L;
	private Contact contact;
	public ContactEditFormFieldFactory(Contact contact){
		this.contact = contact;
	}
	@Override
    protected Field onCreateField(Item item, Object propertyId,
            com.vaadin.ui.Component uiContext) {

        if (propertyId.equals("leadsource")) {
            LeadSourceComboBox leadSource = new LeadSourceComboBox();
            return leadSource;
        } else if (propertyId.equals("contactId")) {
            AccountSelectionField contactField = new AccountSelectionField();
            return contactField;
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
        } else if (propertyId.equals("assignuser")) {
            UserComboBox userBox = new UserComboBox();
            userBox.select(contact.getAssignuser());
            return userBox;
        } else if (propertyId.equals("primcountry")
                || propertyId.equals("othercountry")) {
        	CountryComboBox otherCountryComboBox = new CountryComboBox();
            return otherCountryComboBox;
        }
        return null;
    }
}
