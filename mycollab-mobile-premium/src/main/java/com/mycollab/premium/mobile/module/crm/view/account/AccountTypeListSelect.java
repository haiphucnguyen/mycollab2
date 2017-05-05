package com.mycollab.premium.mobile.module.crm.view.account;

import com.mycollab.mobile.ui.I18NValueListSelect;
import com.mycollab.module.crm.CrmDataTypeFactory;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
class AccountTypeListSelect extends I18NValueListSelect {
    private static final long serialVersionUID = 1L;

    AccountTypeListSelect() {
        setCaption(null);
        this.loadData(CrmDataTypeFactory.getAccountTypeList());
    }
}
