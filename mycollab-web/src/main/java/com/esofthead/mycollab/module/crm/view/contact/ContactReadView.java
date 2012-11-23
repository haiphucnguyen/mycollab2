package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.View;

public interface ContactReadView extends View{
	HasPreviewFormHandlers<Contact> getPreviewFormHandlers();

	void displayItem(Contact contact);
}
