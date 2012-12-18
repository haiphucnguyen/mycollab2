package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class ContactAddPresenter extends CrmGenericPresenter<ContactAddView> {
	private static final long serialVersionUID = 1L;

	public ContactAddPresenter() {
		super(ContactAddView.class);
		bind();
	}

	private void bind() {
		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<Contact>() {

					@Override
					public void onSave(final Contact account) {
						saveContact(account);
						ViewState viewState = HistoryViewManager.back();

						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new ContactEvent.GotoList(this, null));
						}

					}

					@Override
					public void onCancel() {
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new ContactEvent.GotoList(this, null));
						}
					}

					@Override
					public void onSaveAndNew(final Contact contact) {
						saveContact(contact);
						EventBus.getInstance().fireEvent(
								new ContactEvent.GotoAdd(this, null));
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		super.onGo(container, data);
		view.editItem((Contact) data.getParams());
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
