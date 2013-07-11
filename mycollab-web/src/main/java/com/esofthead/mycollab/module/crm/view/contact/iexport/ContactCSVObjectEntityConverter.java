package com.esofthead.mycollab.module.crm.view.contact.iexport;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.iexporter.CSVObjectEntityConverter;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.web.AppContext;

public class ContactCSVObjectEntityConverter implements
		CSVObjectEntityConverter<Contact> {
	@Override
	public Contact convert(ImportFieldDef unit) {
		StringBuffer errorStr = new StringBuffer("");
		Contact contact = new Contact();
		contact.setSaccountid(AppContext.getAccountId());
		String[] labelArray = unit.getLabel();
		String[] valueArray = unit.getValues();
		
		for (int i = 0; i < labelArray.length; i++) {
			if (i < valueArray.length) {
				errorStr.append(mapValueToContact(contact, labelArray[i],
						valueArray[i]));
			} else {
				errorStr.append(mapValueToContact(contact, labelArray[i], ""));
			}
		}
		if (errorStr.length() > 0)
			throw new MyCollabException(errorStr.toString());
		return contact;
	}

	private StringBuffer mapValueToContact(Contact contact, String label,
			String value) {
		StringBuffer errorStr = new StringBuffer("");
		if (label.equals("First Name")) {
			contact.setFirstname(value);
		} else if (label.equals("Last Name")) {
			contact.setLastname(value);
		} else if (label.equals("Title")) {
			contact.setTitle(value);
		} else if (label.equals("Department")) {
			contact.setDepartment(value);
		} else if (label.equals("Email")) {
			if (value.length() == 0 || value.trim().length() == 0) {
				contact.setEmail(value);
			} else {
				InternetAddress emailAddr;
				try {
					emailAddr = new InternetAddress(value);
					emailAddr.validate();
					ContactSearchCriteria criteria = new ContactSearchCriteria();
					criteria.setSaccountid(new NumberSearchField(AppContext
							.getAccountId()));
					criteria.setAnyEmail(new StringSearchField(value));
					ContactService service = AppContext
							.getSpringBean(ContactService.class);
					int count = service.getTotalCount(criteria);
					if (count > 0) {
						errorStr.append("This email has already exist on system.");
					} else
						contact.setEmail(value);
				} catch (AddressException e1) {
					errorStr.append("Email-address: '" + value
							+ "' is invalid on Internet.");
				}
			}
		} else if (label.equals("Assistant")) {
			contact.setAssistant(value);
		} else if (label.equals("Assistant Phone")) {
			contact.setAssistantphone(value);
		} else if (label.equals("Leader Source")) {
			contact.setLeadsource(value);
		} else if (label.equals("Phone Office")) {
			contact.setOfficephone(value);
		} else if (label.equals("Mobile")) {
			contact.setMobile(value);
		} else if (label.equals("Home phone")) {
			contact.setHomephone(value);
		} else if (label.equals("Other Phone")) {
			contact.setOfficephone(value);
		} else if (label.equals("Fax")) {
			contact.setFax(value);
		} else if (label.equals("Birthday")) {
			if (value.length() == 0 || value.trim().length() == 0) {
				contact.setBirthday(null);
			} else {
				try {
					DateFormat formatter = new SimpleDateFormat(
							AppContext.getDateFormat());
					formatter.setLenient(false);
					Date date = formatter.parse(value);
					contact.setBirthday(date);
				} catch (Exception e) {
					errorStr.append("Can't parse value = \'" + value
							+ "\' to DateType, please input follow mm/dd/yyyy.");
				}
			}
		} else if (label.equals("Callable")) {
			boolean val = (value.equals("true")) ? true : false;
			contact.setIscallable(val);
		} else if (label.equals("Assign User")) {
			contact.setAssignuser(value);
		} else if (label.equals("Address")) {
			contact.setPrimaddress(value);
		} else if (label.equals("City")) {
			contact.setPrimcity(value);
		} else if (label.equals("State")) {
			contact.setPrimstate(value);
		} else if (label.equals("Postal Code")) {
			contact.setPrimpostalcode(value);
		} else if (label.equals("Country")) {
			contact.setPrimcountry(value);
		} else if (label.equals("Other Address")) {
			contact.setOtheraddress(value);
		} else if (label.equals("Other City")) {
			contact.setOthercity(value);
		} else if (label.equals("Other State")) {
			contact.setOtherstate(value);
		} else if (label.equals("Other Postal Code")) {
			contact.setOtherpostalcode(value);
		} else if (label.equals("Other Country")) {
			contact.setOthercountry(value);
		} else if (label.equals("Description")) {
			contact.setDescription(value);
		}
		return errorStr;
	}
}
