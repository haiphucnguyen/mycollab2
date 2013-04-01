package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.module.crm.domain.CaseWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.view.IRelatedListHandlers;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IPreviewView;

public interface CaseReadView extends IPreviewView<SimpleCase> {

    HasPreviewFormHandlers<CaseWithBLOBs> getPreviewFormHandlers();

    IRelatedListHandlers getRelatedActivityHandlers();
    
    IRelatedListHandlers<SimpleContact> getRelatedContactHandlers();
}
