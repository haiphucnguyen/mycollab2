package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.module.project.ui.components.TimeLogComp;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;

/**
 * @author MyCollab Ltd.
 * @since 5.0.5
 */
public class TaskGroupTimeLogSheet extends TimeLogComp<SimpleTaskList> {

    private ProjectTaskListService projectTaskListService = ApplicationContextUtil.getSpringBean(ProjectTaskListService.class);

    @Override
    protected Double getTotalBillableHours(SimpleTaskList bean) {
        return projectTaskListService.getTotalBillableHours(bean.getId(), AppContext.getAccountId());
    }

    @Override
    protected Double getTotalNonBillableHours(SimpleTaskList bean) {
        return projectTaskListService.getTotalNonBillableHours(bean.getId(), AppContext.getAccountId());
    }

    @Override
    protected Double getRemainedHours(SimpleTaskList bean) {
        return projectTaskListService.getRemainHours(bean.getId(), AppContext.getAccountId());
    }

    @Override
    protected boolean hasEditPermission() {
        return false;
    }

    @Override
    protected void showEditTimeWindow(SimpleTaskList bean) {

    }
}
