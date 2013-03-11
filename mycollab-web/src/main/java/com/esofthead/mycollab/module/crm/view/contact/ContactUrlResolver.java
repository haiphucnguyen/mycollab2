package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.UrlResolver;

public class ContactUrlResolver extends UrlResolver {
	public ContactUrlResolver() {
		this.addSubResolver("list", new ContactListUrlResolver());
		this.addSubResolver("preview", new ContactPreviewUrlResolver());
		this.addSubResolver("add", new ContactAddUrlResolver());
		this.addSubResolver("edit", new ContactEditUrlResolver());
	}

	public static class ContactListUrlResolver extends UrlResolver {
		@Override
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new ContactEvent.GotoList(this, null));
		}
	}

	public static class ContactAddUrlResolver extends UrlResolver {
		@Override
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new ContactEvent.GotoAdd(this, new Contact()));
		}
	}

	public static class ContactEditUrlResolver extends UrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int contactId = Integer.parseInt(decodeUrl);
			EventBus.getInstance().fireEvent(
					new ContactEvent.GotoEdit(this, contactId));
		}
	}

	public static class ContactPreviewUrlResolver extends UrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int contactId = Integer.parseInt(decodeUrl);
			EventBus.getInstance().fireEvent(
					new ContactEvent.GotoRead(this, contactId));
		}
	}

}
