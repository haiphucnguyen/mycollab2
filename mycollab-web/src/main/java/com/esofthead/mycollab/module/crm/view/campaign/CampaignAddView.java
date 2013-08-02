package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.domain.CampaignWithBLOBs;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;

public interface CampaignAddView extends IFormAddView<CampaignWithBLOBs> {

    HasEditFormHandlers<CampaignWithBLOBs> getEditFormHandlers();
    
    
}
