/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.vaadin.data.Property;
import com.vaadin.event.MouseEvents;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class ContactSelectionField extends CustomField<Object> implements
		FieldSelection<Contact> {
	private static final long serialVersionUID = 1L;

	private HorizontalLayout layout;

	private TextField contactName;

	private SimpleContact contact;

	private Image browseBtn;
	private Image clearBtn;

	public ContactSelectionField() {
		contactName = new TextField();
		contactName.setNullRepresentation("");
		contactName.setWidth("100%");
		browseBtn = new Image(null,
				MyCollabResource.newResource("icons/16/browseItem.png"));
		browseBtn.addClickListener(new MouseEvents.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void click(ClickEvent event) {
				ContactSelectionWindow contactWindow = new ContactSelectionWindow(
						ContactSelectionField.this);
				UI.getCurrent().addWindow(contactWindow);
				contactWindow.show();
			}
		});

		clearBtn = new Image(null,
				MyCollabResource.newResource("icons/16/clearItem.png"));

		clearBtn.addClickListener(new MouseEvents.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void click(ClickEvent event) {
				contactName.setValue("");
				contact = null;
			}
		});
	}

	@Override
	public void fireValueChange(Contact data) {
		contact = (SimpleContact) data;
		if (contact != null) {
			contactName.setValue(contact.getContactName());
			setInternalValue(contact.getId());
		}

	}

	@Override
	public void setPropertyDataSource(Property newDataSource) {
		Object value = newDataSource.getValue();
		if (value instanceof Integer) {
			ContactService contactService = ApplicationContextUtil
					.getSpringBean(ContactService.class);
			SimpleContact contactVal = contactService.findById((Integer) value,
					AppContext.getAccountId());
			if (contactVal != null) {
				setInternalContact(contactVal);
			}

		} else if (value instanceof SimpleContact) {
			setInternalContact((SimpleContact) value);
		}
		super.setPropertyDataSource(newDataSource);
	}

	private void setInternalContact(SimpleContact contact) {
		this.contact = contact;
		contactName.setValue(contact.getContactName());
	}

	public SimpleContact getContact() {
		return this.contact;
	}

	@Override
	protected Component initContent() {
		layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.setWidth("100%");

		layout.addComponent(contactName);
		layout.setComponentAlignment(contactName, Alignment.MIDDLE_LEFT);
		layout.setExpandRatio(contactName, 1.0f);

		layout.addComponent(browseBtn);
		layout.setComponentAlignment(browseBtn, Alignment.MIDDLE_LEFT);

		layout.addComponent(clearBtn);
		layout.setComponentAlignment(clearBtn, Alignment.MIDDLE_LEFT);

		return layout;
	}

	@Override
	public Class<Object> getType() {
		return Object.class;
	}
}
