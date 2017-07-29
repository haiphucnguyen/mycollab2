package com.mycollab.module.project.view.settings;

import com.mycollab.module.project.service.ItemTimeLoggingService;
import com.mycollab.module.project.ui.components.TimeLogComp;
import com.mycollab.module.tracker.domain.Version;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.MyCollabUI;

/**
 * @author MyCollab Ltd
 * @since 5.0.5
 */
public class VersionTimeLogComp extends TimeLogComp<Version> {
    private ItemTimeLoggingService itemTimeLoggingService = AppContextUtil.getSpringBean(ItemTimeLoggingService.class);

    @Override
    protected Double getTotalBillableHours(Version bean) {
        return itemTimeLoggingService.getTotalBillableHoursByVersion(bean.getId(), MyCollabUI.getAccountId());
    }

    @Override
    protected Double getTotalNonBillableHours(Version bean) {
        return itemTimeLoggingService.getTotalNonBillableHoursByVersion(bean.getId(), MyCollabUI.getAccountId());
    }

    @Override
    protected Double getRemainedHours(Version bean) {
        return itemTimeLoggingService.getRemainHoursByVersion(bean.getId(), MyCollabUI.getAccountId());
    }

    @Override
    protected boolean hasEditPermission() {
        return false;
    }

    @Override
    protected void showEditTimeWindow(Version bean) {

    }
}
