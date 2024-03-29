package com.mycollab.pro.module.project.view.task;

import com.google.common.base.MoreObjects;
import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import com.mycollab.db.arguments.BooleanSearchField;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.ItemTimeLogging;
import com.mycollab.module.project.domain.SimpleTask;
import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.mycollab.module.project.event.TimeTrackingEvent.TimeLoggingChangedEvent;
import com.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.mycollab.module.project.service.TaskService;
import com.mycollab.module.project.view.task.TaskTimeLogSheet;
import com.mycollab.pro.module.project.ui.components.TimeLogEditWindow;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.ApplicationEventListener;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.ui.UI;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class TaskTimeLogSheetImpl extends TaskTimeLogSheet {
    private static final long serialVersionUID = 1L;

    private ApplicationEventListener<TimeLoggingChangedEvent> timeChangeHandler = new
            ApplicationEventListener<TimeLoggingChangedEvent>() {
                @Override
                @Subscribe
                @AllowConcurrentEvents
                public void handle(TimeLoggingChangedEvent event) {
                    reloadTimeInfos();
                }
            };

    @Override
    public void attach() {
        EventBusFactory.getInstance().register(timeChangeHandler);
        super.attach();
    }

    @Override
    public void detach() {
        EventBusFactory.getInstance().unregister(timeChangeHandler);
        super.detach();
    }

    private void reloadTimeInfos() {
        Double billableHours = loadTotalBillableHours();
        Double nonBillableHours = loadTotalNonBillableHours();
        beanItem.setBillableHours(billableHours);
        beanItem.setNonBillableHours(nonBillableHours);
        displayTime(beanItem);
    }

    private Double loadTotalBillableHours() {
        ItemTimeLoggingSearchCriteria criteria = new ItemTimeLoggingSearchCriteria();
        criteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
        criteria.setType(StringSearchField.and(ProjectTypeConstants.TASK));
        criteria.setTypeId(new NumberSearchField(beanItem.getId()));
        criteria.setBillable(new BooleanSearchField(true));
        return itemTimeLoggingService.getTotalHoursByCriteria(criteria);
    }

    private Double loadTotalNonBillableHours() {
        ItemTimeLoggingSearchCriteria criteria = new ItemTimeLoggingSearchCriteria();
        criteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
        criteria.setType(StringSearchField.and(ProjectTypeConstants.TASK));
        criteria.setTypeId(new NumberSearchField(beanItem.getId()));
        criteria.setBillable(new BooleanSearchField(false));
        return itemTimeLoggingService.getTotalHoursByCriteria(criteria);
    }

    @Override
    protected Double getTotalBillableHours(SimpleTask bean) {
        return beanItem.getBillableHours();
    }

    @Override
    protected Double getTotalNonBillableHours(SimpleTask bean) {
        return beanItem.getNonBillableHours();
    }

    @Override
    protected Double getRemainedHours(SimpleTask bean) {
        if (bean.getRemainestimate() != null) {
            return bean.getRemainestimate();
        }
        return 0d;
    }

    @Override
    protected boolean hasEditPermission() {
        return CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS);
    }

    @Override
    protected void showEditTimeWindow(SimpleTask bean) {
        UI.getCurrent().addWindow(new TaskTimeLogEditWindow(bean));
    }

    private class TaskTimeLogEditWindow extends TimeLogEditWindow<SimpleTask> {
        TaskTimeLogEditWindow(SimpleTask bean) {
            super(bean);
            this.setCaption(UserUIContext.getMessage(TimeTrackingI18nEnum.DIALOG_LOG_TIME_ENTRY_TITLE));
            this.addCloseListener(closeEvent -> displayTime(bean));
        }

        @Override
        protected void saveTimeInvest() {
            ItemTimeLogging item = new ItemTimeLogging();
            item.setLoguser(UserUIContext.getUsername());
            item.setLogvalue(getInvestValue());
            item.setTypeid(bean.getId());
            item.setType(ProjectTypeConstants.TASK);
            item.setSaccountid(AppUI.getAccountId());
            item.setProjectid(CurrentProjectVariables.getProjectId());
            item.setLogforday(forLogDate());
            item.setIsbillable(isBillableHours());
            item.setIsovertime(isOvertimeHours());
            itemTimeLoggingService.saveWithSession(item, UserUIContext.getUsername());
        }

        @Override
        protected void updateTimeRemain() {
            TaskService taskService = AppContextUtil.getSpringBean(TaskService.class);
            bean.setRemainestimate(getUpdateRemainTime());
            taskService.updateWithSession(bean, UserUIContext.getUsername());
        }

        @Override
        protected ItemTimeLoggingSearchCriteria getItemSearchCriteria() {
            ItemTimeLoggingSearchCriteria searchCriteria = new ItemTimeLoggingSearchCriteria();
            searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
            searchCriteria.setType(StringSearchField.and(ProjectTypeConstants.TASK));
            searchCriteria.setTypeId(new NumberSearchField(bean.getId()));
            return searchCriteria;
        }

        @Override
        protected double getEstimateRemainTime() {
            return MoreObjects.firstNonNull(bean.getRemainestimate(), 0d);
        }

        @Override
        protected boolean isEnableAdd() {
            return CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS);
        }
    }

}
