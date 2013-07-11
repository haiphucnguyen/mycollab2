package com.esofthead.mycollab.module.crm.view.contact.iexport;

import com.esofthead.mycollab.iexporter.CSVObjectEntityConverter;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.web.AppContext;

public class ContactCSVObjectEntityConverter implements
		CSVObjectEntityConverter<Contact> {
	@Override
	public Contact convert(CSVItemMapperDef unit) {
		Contact contact = new Contact();
		contact.setSaccountid(AppContext.getAccountId());
		
		for(ImportFieldDef importFieldDef : unit.getFieldsDef()){
		
		}
		
		return contact;
	}
}
