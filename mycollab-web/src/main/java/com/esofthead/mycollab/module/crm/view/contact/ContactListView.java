package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ListView;

public interface ContactListView extends
		ListView<ContactSearchCriteria, SimpleContact> {

	public static final String VIEW_DEF_ID = "crm-contact-list";
}
