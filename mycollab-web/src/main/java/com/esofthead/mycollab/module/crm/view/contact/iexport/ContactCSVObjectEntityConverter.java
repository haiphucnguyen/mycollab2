package com.esofthead.mycollab.module.crm.view.contact.iexport;

import org.apache.commons.beanutils.PropertyUtils;

import com.esofthead.mycollab.iexporter.CSVObjectEntityConverter;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.web.AppContext;

public class ContactCSVObjectEntityConverter implements
		CSVObjectEntityConverter<Contact> {
	@Override
	public Contact convert(CSVItemMapperDef unit) {
		Contact contact = new Contact();
		contact.setSaccountid(AppContext.getAccountId());
		String[] csvLine = unit.getCsvLine();

		for (ImportFieldDef importFieldDef : unit.getFieldsDef()) {
			try {
				String csvFieldItem = csvLine[importFieldDef.getColumnIndex()];
				if (importFieldDef.getFieldFormatter() != null) {
					PropertyUtils.setProperty(contact, importFieldDef
							.getFieldname(), importFieldDef.getFieldFormatter()
							.format(csvFieldItem));
				} else
					PropertyUtils.setProperty(contact,
							importFieldDef.getFieldname(), csvFieldItem);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return contact;
	}
}
