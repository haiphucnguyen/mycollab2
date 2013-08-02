package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.domain.CampaignWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.view.IRelatedListHandlers;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

@ViewComponent
public class CampaignReadViewImpl extends AbstractView implements
        CampaignReadView {

    private static final long serialVersionUID = 1L;
    private CampaignPreviewBuilder campaignPreview;

    public CampaignReadViewImpl() {
        super();
        campaignPreview = new CampaignPreviewBuilder.ReadView();
        this.addComponent(campaignPreview);
    }

    @Override
    public void previewItem(SimpleCampaign campaign) {
        campaignPreview.previewItem(campaign);
    }

    @Override
    public HasPreviewFormHandlers<CampaignWithBLOBs> getPreviewFormHandlers() {
        return campaignPreview.getPreviewForm();
    }

    @Override
    public SimpleCampaign getItem() {
        return campaignPreview.getCampaign();
    }

    @Override
    public IRelatedListHandlers getRelatedActivityHandlers() {
        return campaignPreview.getAssociateActivityList();
    }

    @Override
    public IRelatedListHandlers<SimpleAccount> getRelatedAccountHandlers() {
        return campaignPreview.getAssociateAccountList();
    }

    @Override
    public IRelatedListHandlers<SimpleContact> getRelatedContactHandlers() {
        return campaignPreview.getAssociateContactList();
    }

    @Override
    public IRelatedListHandlers<SimpleLead> getRelatedLeadHandlers() {
        return campaignPreview.getAssociateLeadList();
    }
}
