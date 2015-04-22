package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.module.project.ui.components.TimeLogComp;
import com.esofthead.mycollab.module.tracker.domain.SimpleComponent;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;

/**
 * @author MyCollab Ltd
 * @since 5.0.5
 */
public class ComponentTimeLogComp extends TimeLogComp<SimpleComponent> {
    private ItemTimeLoggingService itemTimeLoggingService = ApplicationContextUtil.getSpringBean(ItemTimeLoggingService.class);

    @Override
    protected Double getTotalBillableHours(SimpleComponent bean) {
        return itemTimeLoggingService.getTotalBillableHoursByComponent(bean.getId(), AppContext.getAccountId());
    }

    @Override
    protected Double getTotalNonBillableHours(SimpleComponent bean) {
        return itemTimeLoggingService.getTotalNonBillableHoursByComponent(bean.getId(), AppContext.getAccountId());
    }

    @Override
    protected Double getRemainedHours(SimpleComponent bean) {
        return itemTimeLoggingService.getRemainHoursByComponent(bean.getId(), AppContext.getAccountId());
    }

    @Override
    protected boolean hasEditPermission() {
        return false;
    }

    @Override
    protected void showEditTimeWindow(SimpleComponent bean) {

    }
}
