package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.domain.Campaign;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

@ViewComponent
public class CampaignReadViewImpl extends AbstractView implements
        CampaignReadView {

    private static final long serialVersionUID = 1L;
    private CampaignPreview campaignPreview;

    public CampaignReadViewImpl() {
        super();
        campaignPreview = new CampaignPreview(true);
        this.addComponent(campaignPreview);
    }

    @Override
    public void previewItem(SimpleCampaign campaign) {
        campaignPreview.previewItem(campaign);
    }

    @Override
    public HasPreviewFormHandlers<Campaign> getPreviewFormHandlers() {
        return campaignPreview.getPreviewForm();
    }

    @Override
    public SimpleCampaign getItem() {
        return campaignPreview.getCampaign();
    }
}
