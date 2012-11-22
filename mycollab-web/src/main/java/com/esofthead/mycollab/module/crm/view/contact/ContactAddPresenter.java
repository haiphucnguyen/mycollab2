package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.web.AppContext;

public class ContactAddPresenter extends CrmGenericPresenter<ContactAddView> {

	public ContactAddPresenter(ContactAddView view) {
		this.view = view;
		bind();
	}

	private void bind() {
		view.getEditFormHandlers().addFormHandler(new EditFormHandler<Contact>() {

			@Override
			public void onSave(final Contact account) {
				saveContact(account);
				EventBus.getInstance().fireEvent(
						new ContactEvent.GotoList(this, null));
			}

			@Override
			public void onCancel() {
				EventBus.getInstance().fireEvent(
						new ContactEvent.GotoList(this, null));
			}

			@Override
			public void onSaveAndNew(final Contact contact) {
				saveContact(contact);
				EventBus.getInstance().fireEvent(
						new ContactEvent.GotoAdd(this, null));
			}
		});
	}

	public void saveContact(Contact contact) {
		ContactService contactService = AppContext
				.getSpringBean(ContactService.class);

		contact.setSaccountid(AppContext.getAccountId());
		if (contact.getId() == null) {
			contactService.saveWithSession(contact, AppContext.getUsername());
		} else {
			contactService.updateWithSession(contact, AppContext.getUsername());
		}

	}
}
