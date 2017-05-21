package com.mycollab.premium.mobile.module.crm.view.activity;

import com.mycollab.mobile.ui.ValueListSelect;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
class MeetingStatusListSelect extends ValueListSelect {
    private static final long serialVersionUID = 1L;

    MeetingStatusListSelect() {
        setCaption(null);
        this.loadData("Planned", "Held", "Not Held");
    }
}
