package com.esofthead.mycollab.module.crm.view.contact;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.web.AppContext;

@SuppressWarnings("serial")
public class ContactAddPresenter {

	@Autowired
	private ContactService contactService;

	@PostConstruct
	private void initListeners() {
//		eventBus.addListener(new ApplicationEventListener<ContactEvent.Save>() {
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public Class<? extends ApplicationEvent> getEventType() {
//				return ContactEvent.Save.class;
//			}
//
//			@Override
//			public void handle(ContactEvent.Save event) {
//				Contact contact = (Contact) event.getData();
//				saveContact(contact);
//			}
//		});
	}

	public void saveContact(Contact contact) {
		contact.setSaccountid(AppContext.getAccountId());
		System.out.println("Accountid " + contact.getAccountid());
		if (contact.getId() == null) {
			contactService.saveWithSession(contact, AppContext.getUsername());
		} else {
			contactService.updateWithSession(contact, AppContext.getUsername());
		}

		
	}
}
