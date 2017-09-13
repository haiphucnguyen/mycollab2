package com.mycollab.premium.mobile.module.crm.view.opportunity;

import com.mycollab.mobile.ui.I18NValueListSelect;
import com.mycollab.module.crm.CrmDataTypeFactory;

import java.util.Arrays;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
class OpportunitySalesStageListSelect extends I18NValueListSelect {

    OpportunitySalesStageListSelect() {
        this.loadData(Arrays.asList(CrmDataTypeFactory.opportunitySalesStageList));
        this.setNullSelectionAllowed(false);
    }
}
