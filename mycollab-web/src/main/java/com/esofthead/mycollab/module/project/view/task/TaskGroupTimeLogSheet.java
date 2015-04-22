package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.module.project.ui.components.TimeLogComp;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;

/**
 * @author MyCollab Ltd.
 * @since 5.0.5
 */
public class TaskGroupTimeLogSheet extends TimeLogComp<SimpleTaskList> {

    private ItemTimeLoggingService itemTimeLoggingService = ApplicationContextUtil.getSpringBean(ItemTimeLoggingService.class);

    @Override
    protected Double getTotalBillableHours(SimpleTaskList bean) {
        return itemTimeLoggingService.getTotalBillableHoursByTaskList(bean.getId(), AppContext.getAccountId());
    }

    @Override
    protected Double getTotalNonBillableHours(SimpleTaskList bean) {
        return itemTimeLoggingService.getTotalNonBillableHoursByTaskList(bean.getId(), AppContext.getAccountId());
    }

    @Override
    protected Double getRemainedHours(SimpleTaskList bean) {
        return itemTimeLoggingService.getRemainHoursByTaskList(bean.getId(), AppContext.getAccountId());
    }

    @Override
    protected boolean hasEditPermission() {
        return false;
    }

    @Override
    protected void showEditTimeWindow(SimpleTaskList bean) {

    }
}
