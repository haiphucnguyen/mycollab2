/**
 * Copyright © MyCollab
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.crm.view.contact;

import com.mycollab.module.crm.domain.Contact;
import com.mycollab.module.crm.domain.SimpleContact;
import com.mycollab.module.crm.service.ContactService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.ui.FieldSelection;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
// TODO: revise
public class ContactSelectionField extends CustomField<Integer> implements FieldSelection<Contact> {
    private static final long serialVersionUID = 1L;

    private TextField contactName;
    private MButton browseBtn, clearBtn;

    private SimpleContact contact;

    public ContactSelectionField() {
        contactName = new TextField();
        contactName.setWidth("100%");
        browseBtn = new MButton("", clickEvent -> {
            ContactSelectionWindow contactWindow = new ContactSelectionWindow(ContactSelectionField.this);
            UI.getCurrent().addWindow(contactWindow);
            contactWindow.show();
        }).withIcon(FontAwesome.ELLIPSIS_H).withStyleName(WebThemes.BUTTON_OPTION, WebThemes.BUTTON_SMALL_PADDING);

        clearBtn = new MButton("", clickEvent -> {
            contactName.setValue("");
            contact = null;
        }).withIcon(FontAwesome.TRASH_O).withStyleName(WebThemes.BUTTON_OPTION, WebThemes.BUTTON_SMALL_PADDING);
    }

    @Override
    protected void doSetValue(Integer integer) {

    }

    @Override
    public void fireValueChange(Contact data) {
        contact = (SimpleContact) data;
        if (contact != null) {
            contactName.setValue(contact.getContactName());
        }
    }

//    @Override
//    public void setPropertyDataSource(Property newDataSource) {
//        final Object value = newDataSource.getValue();
//        if (value instanceof Integer) {
//            setContactByVal((Integer) value);
//        }
//        super.setPropertyDataSource(newDataSource);
//    }
//
//    @Override
//    public void commit() throws SourceException, Validator.InvalidValueException {
//        if (contact != null) {
//            setInternalValue(contact.getId());
//        } else {
//            setInternalValue(null);
//        }
//        super.commit();
//    }

    private void setContactByVal(Integer contactId) {
        ContactService contactService = AppContextUtil.getSpringBean(ContactService.class);
        SimpleContact contactVal = contactService.findById(contactId, AppUI.getAccountId());
        if (contactVal != null) {
            this.contact = contact;
            contactName.setValue(contact.getContactName());
        }
    }

    public SimpleContact getContact() {
        return this.contact;
    }

    @Override
    protected Component initContent() {
        return new MHorizontalLayout(contactName, browseBtn, clearBtn).expand(contactName)
                .alignAll(Alignment.MIDDLE_LEFT).withFullWidth();
    }

    @Override
    public Integer getValue() {
        return null;
    }
}
