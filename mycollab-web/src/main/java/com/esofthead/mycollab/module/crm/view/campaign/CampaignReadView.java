package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.domain.Campaign;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.view.IRelatedListHandlers;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IPreviewView;

public interface CampaignReadView extends IPreviewView<SimpleCampaign> {
	HasPreviewFormHandlers<Campaign> getPreviewFormHandlers();
        
        IRelatedListHandlers getRelatedActivityHandlers();
        
        IRelatedListHandlers<SimpleAccount> getRelatedAccountHandlers();
        
        IRelatedListHandlers<SimpleContact> getRelatedContactHandlers();
        
        IRelatedListHandlers<SimpleLead> getRelatedLeadHandlers();
}
