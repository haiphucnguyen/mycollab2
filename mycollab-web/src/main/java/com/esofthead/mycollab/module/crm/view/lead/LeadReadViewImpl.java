package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.view.IRelatedListHandlers;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

@ViewComponent
public class LeadReadViewImpl extends AbstractView implements LeadReadView {

    private static final long serialVersionUID = 1L;
    private LeadPreviewBuilder leadPreview;

    public LeadReadViewImpl() {
        super();
        leadPreview = new LeadPreviewBuilder.ReadView();
        this.addComponent(leadPreview);
    }

    @Override
    public void previewItem(SimpleLead lead) {
        leadPreview.previewItem(lead);
    }

    @Override
    public HasPreviewFormHandlers<Lead> getPreviewFormHandlers() {
        return leadPreview.getPreviewForm();
    }

    @Override
    public SimpleLead getItem() {
        return leadPreview.getLead();
    }

    @Override
    public IRelatedListHandlers getRelatedActivityHandlers() {
        return leadPreview.getAssociateActivityList();
    }

    @Override
    public IRelatedListHandlers<SimpleCampaign> getRelatedCampaignHandlers() {
        return leadPreview.getAssociateCampaignList();
    }
}
