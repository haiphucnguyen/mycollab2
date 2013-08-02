package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.view.IRelatedListHandlers;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IPreviewView;

public interface OpportunityReadView extends IPreviewView<SimpleOpportunity> {

    HasPreviewFormHandlers<Opportunity> getPreviewFormHandlers();

    IRelatedListHandlers getRelatedActivityHandlers();
    
    IRelatedListHandlers<SimpleContact> getRelatedContactHandlers();
    
    IRelatedListHandlers<SimpleLead> getRelatedLeadHandlers();
}
