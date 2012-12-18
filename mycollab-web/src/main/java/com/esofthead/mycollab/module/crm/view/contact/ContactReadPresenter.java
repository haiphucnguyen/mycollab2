package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.PreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class ContactReadPresenter extends CrmGenericPresenter<ContactReadView> {
	private static final long serialVersionUID = 1L;

	public ContactReadPresenter() {
		super(ContactReadView.class);
		bind();
	}

	private void bind() {
		view.getPreviewFormHandlers().addFormHandler(
				new PreviewFormHandlers<Contact>() {

					@Override
					public void onEdit(Contact data) {
						EventBus.getInstance().fireEvent(
								new ContactEvent.GotoEdit(this, data));
					}

					@Override
					public void onDelete(Contact data) {
						ContactService ContactService = AppContext
								.getSpringBean(ContactService.class);
						ContactService.removeWithSession(data.getId(),
								AppContext.getUsername());
						EventBus.getInstance().fireEvent(
								new ContactEvent.GotoList(this, null));
					}

					@Override
					public void onClone(Contact data) {
						Contact cloneData = (Contact) data.copy();
						cloneData.setId(null);
						EventBus.getInstance().fireEvent(
								new ContactEvent.GotoEdit(this, cloneData));
					}

					@Override
					public void onCancel() {
						EventBus.getInstance().fireEvent(
								new ContactEvent.GotoList(this, null));
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		super.onGo(container, data);

		if (data.getParams() instanceof Integer) {
			ContactService contactService = AppContext
					.getSpringBean(ContactService.class);
			SimpleContact contact = contactService
					.findContactById((Integer) data.getParams());
			view.previewItem(contact);
		}

	}
}
