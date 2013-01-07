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

    private OpportunityPreviewBuilder opportunityPreview;
    
    public OpportunityReadViewImpl() {
        super();
        opportunityPreview = new OpportunityPreviewBuilder.ReadView();
        this.addComponent(opportunityPreview);
    }

    @Override
    public void previewItem(SimpleOpportunity item) {
    	opportunityPreview.previewItem(item);
    }

    @Override
    public HasPreviewFormHandlers<Opportunity> getPreviewFormHandlers() {
        return opportunityPreview.getPreviewForm();
    }

    @Override
    public SimpleOpportunity getItem() {
        return opportunityPreview.getOpportunity();
    }
}
