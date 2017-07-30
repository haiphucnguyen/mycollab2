package com.mycollab.premium.mobile.module.crm.view.contact;

import com.mycollab.mobile.ui.AbstractSelectionCustomField;
import com.mycollab.module.crm.domain.SimpleContact;
import com.mycollab.module.crm.service.ContactService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.vaadin.data.Property;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
public class ContactSelectionField extends AbstractSelectionCustomField<Integer, SimpleContact> {
    private static final long serialVersionUID = 1L;

    public ContactSelectionField() {
        super(ContactSelectionView.class);
    }

    @Override
    public void fireValueChange(SimpleContact data) {
        setInternalContact(data);
        setInternalValue(beanItem.getId());
    }

    @Override
    public void setPropertyDataSource(Property newDataSource) {
        final Object value = newDataSource.getValue();
        if (value instanceof Integer) {
            setContactByVal((Integer) value);
        }
        super.setPropertyDataSource(newDataSource);
    }

    @Override
    public void setValue(Integer value) {
        this.setContactByVal(value);
        super.setValue(value);
    }

    private void setContactByVal(Integer contactId) {
        ContactService contactService = AppContextUtil.getSpringBean(ContactService.class);
        SimpleContact contactVal = contactService.findById(contactId, AppUI.getAccountId());
        if (contactVal != null) {
            setInternalContact(contactVal);
        }
    }

    private void setInternalContact(SimpleContact contact) {
        this.beanItem = contact;
        navButton.setCaption(contact.getContactName());
    }

    public SimpleContact getContact() {
        return this.beanItem;
    }

    @Override
    public Class<Integer> getType() {
        return Integer.class;
    }
}
