package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.TemplateEditItemView;

public interface ContactAddView extends TemplateEditItemView<Contact> {
	HasEditFormHandlers<Contact> getEditFormHandlers();
}
