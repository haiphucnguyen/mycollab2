package com.esofthead.mycollab.ondemand.module.user.accountsettings.billing.view;

import com.esofthead.mycollab.vaadin.mvp.PageView;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface BillingSummaryView extends PageView {
    void loadCurrentPlan();
}
