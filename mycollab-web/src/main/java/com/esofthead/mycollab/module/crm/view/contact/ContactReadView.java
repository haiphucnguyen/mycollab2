package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.view.IRelatedListHandlers;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IPreviewView;

public interface ContactReadView extends IPreviewView<SimpleContact> {

    HasPreviewFormHandlers<Contact> getPreviewFormHandlers();

    IRelatedListHandlers getRelatedActivityHandlers();
    
    IRelatedListHandlers<SimpleOpportunity> getRelatedOpportunityHandlers();
}
