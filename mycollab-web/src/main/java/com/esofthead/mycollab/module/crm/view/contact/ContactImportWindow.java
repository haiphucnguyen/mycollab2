package com.esofthead.mycollab.module.crm.view.contact;

import java.util.Arrays;
import java.util.List;

import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.iexporter.CSVObjectEntityConverter.FieldMapperDef;
import com.esofthead.mycollab.iexporter.csv.CSVBooleanFormatter;
import com.esofthead.mycollab.iexporter.csv.CSVDateFormatter;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.ui.components.EntityImportWindow;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.web.AppContext;

public class ContactImportWindow extends EntityImportWindow<Contact> {
	private static final long serialVersionUID = 1L;

	public ContactImportWindow() {
		super(true, "Import Contact Window", AppContext
				.getSpringBean(ContactService.class), Contact.class);
	}

	@Override
	protected List<FieldMapperDef> constructCSVFieldMapper() {
		FieldMapperDef[] fields = {
				new FieldMapperDef("firstname", "First Name"),
				new FieldMapperDef("lastname", "Last Name"),
				new FieldMapperDef("account", "Account"),
				new FieldMapperDef("title", "Title"),
				new FieldMapperDef("department", "Department"),
				new FieldMapperDef("email", "Email"),
				new FieldMapperDef("assistant", "Assistant"),
				new FieldMapperDef("assistantphone", "Assistant Phone"),
				new FieldMapperDef("leadsource", "Leader Source"),
				new FieldMapperDef("officephone", "Phone Office"),
				new FieldMapperDef("mobile", "Mobile"),
				new FieldMapperDef("homephone", "Home phone"),
				new FieldMapperDef("otherphone", "Other Phone"),
				new FieldMapperDef("fax", "Fax"),
				new FieldMapperDef("birthday", "Birthday",
						new CSVDateFormatter()),
				new FieldMapperDef("iscallable", "Callable",
						new CSVBooleanFormatter()),
				new FieldMapperDef("assignuser", "Assign User"),
				new FieldMapperDef("primaddress", "Address"),
				new FieldMapperDef("primcity", "City"),
				new FieldMapperDef("primstate", "State"),
				new FieldMapperDef("primpostalcode", "Postal Code"),
				new FieldMapperDef("primcountry", "Country"),
				new FieldMapperDef("otheraddress", "Other Address"),
				new FieldMapperDef("othercity", "Other City"),
				new FieldMapperDef("otherstate", "Other State"),
				new FieldMapperDef("otherpostalcode", "Other Postal Code"),
				new FieldMapperDef("othercountry", "Other Country"),
				new FieldMapperDef("description", "Description") };
		return Arrays.asList(fields);
	}

	@Override
	protected void reloadWhenBackToListView() {
		ContactSearchCriteria contactSearchCriteria = new ContactSearchCriteria();
		contactSearchCriteria.setContactName(new StringSearchField(""));
		EventBus.getInstance().fireEvent(
				new ContactEvent.GotoList(ContactListView.class,contactSearchCriteria));
	}

}
