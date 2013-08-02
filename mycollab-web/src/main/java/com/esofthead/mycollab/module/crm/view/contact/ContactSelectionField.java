package com.esofthead.mycollab.module.crm.view.contact;

import org.vaadin.addon.customfield.FieldWrapper;

import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.UIHelper;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Property;
import com.vaadin.event.MouseEvents;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class ContactSelectionField extends FieldWrapper<Contact> implements
		FieldSelection {
	private static final long serialVersionUID = 1L;

	private HorizontalLayout layout;

	private TextField contactName;

	private SimpleContact contact;

	private Embedded browseBtn;
	private Embedded clearBtn;

	@SuppressWarnings("serial")
	public ContactSelectionField() {
		super(new TextField(""), Contact.class);

		layout = new HorizontalLayout();
		layout.setSpacing(true);

		contactName = new TextField();
		contactName.setEnabled(true);
		layout.addComponent(contactName);
		layout.setComponentAlignment(contactName, Alignment.MIDDLE_LEFT);

		browseBtn = new Embedded(null,
				MyCollabResource.newResource("icons/16/browseItem.png"));
		layout.addComponent(browseBtn);
		layout.setComponentAlignment(browseBtn, Alignment.MIDDLE_LEFT);

		browseBtn.addListener(new MouseEvents.ClickListener() {

			@Override
			public void click(ClickEvent event) {
				ContactSelectionWindow accountWindow = new ContactSelectionWindow(
						ContactSelectionField.this);
				UIHelper.addWindowToRoot(ContactSelectionField.this,
						accountWindow);
				accountWindow.show();

			}
		});

		clearBtn = new Embedded(null,
				MyCollabResource.newResource("icons/16/clearItem.png"));

		clearBtn.addListener(new MouseEvents.ClickListener() {

			@Override
			public void click(ClickEvent event) {
				contactName.setValue("");
				ContactSelectionField.this.getWrappedField().setValue(null);
			}
		});
		layout.addComponent(clearBtn);
		layout.setComponentAlignment(clearBtn, Alignment.MIDDLE_LEFT);

		this.setCompositionRoot(layout);
		this.addListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				try {
					ContactService accountService = AppContext
							.getSpringBean(ContactService.class);
					Integer accountId = Integer.parseInt((String) event
							.getProperty().getValue());
					SimpleContact contact = accountService
							.findById(accountId);
					if (contact != null) {
						contactName.setValue(contact.getContactName());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	public void setContact(SimpleContact contact) {
		this.contact = contact;
		contactName.setValue(contact.getContactName());
	}

	@Override
	public void fireValueChange(Object data) {
		contact = (SimpleContact) data;
		if (contact != null) {
			contactName.setValue(contact.getContactName());
			this.getWrappedField().setValue(contact.getId());
		}

	}
}
