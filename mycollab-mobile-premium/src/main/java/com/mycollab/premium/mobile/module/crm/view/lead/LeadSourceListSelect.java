package com.mycollab.premium.mobile.module.crm.view.lead;

import com.mycollab.mobile.ui.I18NValueListSelect;
import com.mycollab.module.crm.CrmDataTypeFactory;

import java.util.Arrays;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public class LeadSourceListSelect extends I18NValueListSelect {
    private static final long serialVersionUID = 1L;

    public LeadSourceListSelect() {
        this.loadData(Arrays.asList(CrmDataTypeFactory.leadSourceList));
    }
}
