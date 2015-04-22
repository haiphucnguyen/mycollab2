package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.module.project.ui.components.TimeLogComp;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;

/**
 * @author MyCollab Ltd
 * @since 5.0.5
 */
public class MilestoneTimeLogComp extends TimeLogComp<SimpleMilestone> {
    private ItemTimeLoggingService itemLogService = ApplicationContextUtil.getSpringBean(ItemTimeLoggingService.class);

    @Override
    protected Double getTotalBillableHours(SimpleMilestone bean) {
        return itemLogService.getTotalBillableHoursByMilestone(bean.getId(), AppContext.getAccountId());
    }

    @Override
    protected Double getTotalNonBillableHours(SimpleMilestone bean) {
        return itemLogService.getTotalNonBillableHoursByMilestone(bean.getId(), AppContext.getAccountId());
    }

    @Override
    protected Double getRemainedHours(SimpleMilestone bean) {
        return itemLogService.getRemainHoursByMilestone(bean.getId(), AppContext.getAccountId());
    }

    @Override
    protected boolean hasEditPermission() {
        return false;
    }

    @Override
    protected void showEditTimeWindow(SimpleMilestone bean) {

    }
}
