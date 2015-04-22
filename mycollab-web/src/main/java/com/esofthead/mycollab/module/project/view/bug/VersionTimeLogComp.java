package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.module.project.ui.components.TimeLogComp;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;

/**
 * @author MyCollab Ltd
 * @since 5.0.5
 */
public class VersionTimeLogComp extends TimeLogComp<Version> {
    private ItemTimeLoggingService itemTimeLoggingService = ApplicationContextUtil.getSpringBean(ItemTimeLoggingService.class);

    @Override
    protected Double getTotalBillableHours(Version bean) {
        return itemTimeLoggingService.getTotalBillableHoursByVersion(bean.getId(), AppContext.getAccountId());
    }

    @Override
    protected Double getTotalNonBillableHours(Version bean) {
        return itemTimeLoggingService.getTotalNonBillableHoursByVersion(bean.getId(), AppContext.getAccountId());
    }

    @Override
    protected Double getRemainedHours(Version bean) {
        return itemTimeLoggingService.getRemainHoursByVersion(bean.getId(), AppContext.getAccountId());
    }

    @Override
    protected boolean hasEditPermission() {
        return false;
    }

    @Override
    protected void showEditTimeWindow(Version bean) {

    }
}
