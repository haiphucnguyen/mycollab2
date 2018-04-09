package com.mycollab.premium.mobile.module.crm.view.campaign;

import com.mycollab.mobile.ui.AbstractSelectionCustomField;
import com.mycollab.module.crm.domain.CampaignWithBLOBs;
import com.mycollab.module.crm.domain.SimpleCampaign;
import com.mycollab.module.crm.service.CampaignService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */

// TODO: revise this class
public class CampaignSelectionField extends AbstractSelectionCustomField<Integer, CampaignWithBLOBs> {

    public CampaignSelectionField() {
        super(CampaignSelectionView.class);
    }

//    @Override
//    public void setPropertyDataSource(Property newDataSource) {
//        Object value = newDataSource.getValue();
//        if (value instanceof Integer) {
//            setCampaignByVal((Integer) value);
//        }
//        super.setPropertyDataSource(newDataSource);
//    }

    @Override
    public void setValue(Integer value) {
        this.setCampaignByVal(value);
        super.setValue(value);
    }

    @Override
    public Integer getValue() {
        return null;
    }

    @Override
    protected void doSetValue(Integer integer) {

    }

    private void setCampaignByVal(Integer campaignId) {
        CampaignService campaignService = AppContextUtil.getSpringBean(CampaignService.class);
        SimpleCampaign campaign = campaignService.findById(campaignId, AppUI.getAccountId());
        if (campaign != null) {
            setInternalCampaign(campaign);
        }
    }

    private void setInternalCampaign(CampaignWithBLOBs campaign) {
        this.beanItem = campaign;
        navButton.setCaption(beanItem.getCampaignname());
    }

    @Override
    public void fireValueChange(CampaignWithBLOBs data) {
        setInternalCampaign(data);
//        setInternalValue(data.getId());
    }

}
