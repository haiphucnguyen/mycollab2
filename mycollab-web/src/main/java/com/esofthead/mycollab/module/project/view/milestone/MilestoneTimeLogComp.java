package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.service.MilestoneService;
import com.esofthead.mycollab.module.project.ui.components.TimeLogComp;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

/**
 * @author MyCollab Ltd
 * @since 5.0.5
 */
public class MilestoneTimeLogComp  extends TimeLogComp<SimpleMilestone> {
    private MilestoneService milestoneService = ApplicationContextUtil.getSpringBean(MilestoneService.class);

    @Override
    protected Double getTotalBillableHours(SimpleMilestone bean) {
        return milestoneService.getTotalBillableHours(bean.getId());
    }

    @Override
    protected Double getTotalNonBillableHours(SimpleMilestone bean) {
        return milestoneService.getTotalNonBillableHours(bean.getId());
    }

    @Override
    protected Double getRemainedHours(SimpleMilestone bean) {
        return milestoneService.getRemainHours(bean.getId());
    }

    @Override
    protected boolean hasEditPermission() {
        return false;
    }

    @Override
    protected void showEditTimeWindow(SimpleMilestone bean) {

    }
}
