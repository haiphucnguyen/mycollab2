package com.mycollab.premium.mobile.module.crm.view.campaign;

import com.mycollab.mobile.ui.I18NValueListSelect;
import com.mycollab.module.crm.CrmDataTypeFactory;

import java.util.Arrays;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
class CampaignStatusListSelect extends I18NValueListSelect {
    CampaignStatusListSelect() {
        this.loadData(Arrays.asList(CrmDataTypeFactory.campaignStatusList));
    }
}
