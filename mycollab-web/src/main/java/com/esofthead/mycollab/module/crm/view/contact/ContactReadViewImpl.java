package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

@ViewComponent
public class ContactReadViewImpl extends AbstractView implements
        ContactReadView {

    private static final long serialVersionUID = 1L;
    
    ContactPreview contactPreview;

    public ContactReadViewImpl() {
        super();
        contactPreview = new ContactPreview(true);
        this.addComponent(contactPreview);
    }

    @Override
    public void previewItem(SimpleContact item) {
    	contactPreview.previewItem(item);
    }

    @Override
    public HasPreviewFormHandlers<Contact> getPreviewFormHandlers() {
        return contactPreview.getPreviewForm();
    }

    @Override
    public SimpleContact getItem() {
        return contactPreview.getContact();
    }
}
