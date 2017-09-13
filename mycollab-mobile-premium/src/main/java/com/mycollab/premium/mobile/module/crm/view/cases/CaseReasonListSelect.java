package com.mycollab.premium.mobile.module.crm.view.cases;

import com.mycollab.mobile.ui.I18NValueListSelect;
import com.mycollab.module.crm.CrmDataTypeFactory;

import java.util.Arrays;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
class CaseReasonListSelect extends I18NValueListSelect {
    private static final long serialVersionUID = 1L;

    CaseReasonListSelect() {
        setCaption(null);
        this.loadData(Arrays.asList(CrmDataTypeFactory.casesReason));
    }
}
