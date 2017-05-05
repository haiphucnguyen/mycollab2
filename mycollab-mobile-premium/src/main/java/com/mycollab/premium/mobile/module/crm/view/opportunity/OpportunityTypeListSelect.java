package com.mycollab.premium.mobile.module.crm.view.opportunity;

import com.mycollab.mobile.ui.I18NValueListSelect;
import com.mycollab.module.crm.CrmDataTypeFactory;

import java.util.Arrays;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
public class OpportunityTypeListSelect extends I18NValueListSelect {
    private static final long serialVersionUID = 1L;

    public OpportunityTypeListSelect() {
        this.loadData(Arrays.asList(CrmDataTypeFactory.getOpportunityTypeList()));
    }
}
