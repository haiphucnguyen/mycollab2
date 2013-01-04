package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

@ViewComponent
public class OpportunityReadViewImpl extends AbstractView implements
        OpportunityReadView {

    private static final long serialVersionUID = 1L;

    private OpportunityPreview _opportunityPreview;
    
    public OpportunityReadViewImpl() {
        super();
        _opportunityPreview = new OpportunityPreview(true);
        this.addComponent(_opportunityPreview);
    }

    @Override
    public void previewItem(SimpleOpportunity item) {
    	_opportunityPreview.previewItem(item);
    }

    @Override
    public HasPreviewFormHandlers<Opportunity> getPreviewFormHandlers() {
        return _opportunityPreview.getPreviewForm();
    }

    @Override
    public SimpleOpportunity getItem() {
        return _opportunityPreview.getOpportunity();
    }
}
