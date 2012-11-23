package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.PreviewFormHandlers;

public class ContactReadPresenter  extends CrmGenericPresenter<ContactReadView> {

	public ContactReadPresenter(ContactReadView view) {
		this.view = view;
		bind();
	}


	private void bind() {
		view.getPreviewFormHandlers().addFormHandler(
				new PreviewFormHandlers<Contact>() {

					@Override
					public void onEdit(Contact data) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onDelete(Contact data) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onClone(Contact data) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onCancel() {
						// TODO Auto-generated method stub

					}
				});
	}
}
