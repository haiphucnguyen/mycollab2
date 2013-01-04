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

    private CampaignPreview _campaignPreview;
    
    public CampaignReadViewImpl() {
        super();
        _campaignPreview = new CampaignPreview(true);
        this.addComponent(_campaignPreview);
    }

    @Override
    public void previewItem(SimpleCampaign campaign) {
        _campaignPreview.previewItem(campaign);
    }

    @Override
    public HasPreviewFormHandlers<Campaign> getPreviewFormHandlers() {
        return _campaignPreview.getPreviewForm();
    }

    @Override
    public SimpleCampaign getItem() {
        return _campaignPreview.getCampaign();
    }
}
