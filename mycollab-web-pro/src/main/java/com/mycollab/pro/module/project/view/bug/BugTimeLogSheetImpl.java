package com.mycollab.pro.module.project.view.bug;

import com.google.common.base.MoreObjects;
import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import com.mycollab.db.arguments.BooleanSearchField;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.eventmanager.ApplicationEventListener;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.ItemTimeLogging;
import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.mycollab.module.project.event.ProjectEvent;
import com.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.mycollab.module.project.view.bug.BugTimeLogSheet;
import com.mycollab.module.tracker.domain.SimpleBug;
import com.mycollab.module.tracker.service.BugService;
import com.mycollab.pro.module.project.ui.components.TimeLogEditWindow;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.ui.UI;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@SuppressWarnings("serial")
@ViewComponent
public class BugTimeLogSheetImpl extends BugTimeLogSheet {
    private ApplicationEventListener<ProjectEvent.TimeLoggingChangedEvent> timeChangeHandler = new
            ApplicationEventListener<ProjectEvent.TimeLoggingChangedEvent>() {
                @Override
                @Subscribe
                @AllowConcurrentEvents
                public void handle(ProjectEvent.TimeLoggingChangedEvent event) {
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

    @Override
    protected boolean hasEditPermission() {
        return CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS);
    }

    @Override
    protected Double getTotalBillableHours(SimpleBug bean) {
        return bean.getBillableHours();
    }

    @Override
    protected Double getTotalNonBillableHours(SimpleBug bean) {
        return bean.getNonBillableHours();
    }

    @Override
    protected Double getRemainedHours(SimpleBug bean) {
        return MoreObjects.firstNonNull(bean.getRemainestimate(), 0d);
    }

    private Double loadTotalBillableHours() {
        ItemTimeLoggingSearchCriteria criteria = new ItemTimeLoggingSearchCriteria();
        criteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
        criteria.setType(StringSearchField.and(ProjectTypeConstants.BUG));
        criteria.setTypeId(new NumberSearchField(beanItem.getId()));
        criteria.setIsBillable(new BooleanSearchField(true));
        return itemTimeLoggingService.getTotalHoursByCriteria(criteria);
    }

    private Double loadTotalNonBillableHours() {
        ItemTimeLoggingSearchCriteria criteria = new ItemTimeLoggingSearchCriteria();
        criteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
        criteria.setType(StringSearchField.and(ProjectTypeConstants.BUG));
        criteria.setTypeId(new NumberSearchField(beanItem.getId()));
        criteria.setIsBillable(new BooleanSearchField(false));
        return itemTimeLoggingService.getTotalHoursByCriteria(criteria);
    }

    @Override
    protected void showEditTimeWindow(SimpleBug bean) {
        UI.getCurrent().addWindow(new BugTimeLogEditWindow(bean));
    }

    private class BugTimeLogEditWindow extends TimeLogEditWindow<SimpleBug> {
        BugTimeLogEditWindow(final SimpleBug bean) {
            super(bean);
            this.setCaption(UserUIContext.getMessage(TimeTrackingI18nEnum.DIALOG_LOG_TIME_ENTRY_TITLE));
            this.setModal(true);
            this.setModal(true);
            this.addCloseListener(new CloseListener() {

                @Override
                public void windowClose(CloseEvent e) {
                    BugTimeLogSheetImpl.this.displayTime(bean);
                }
            });
        }

        @Override
        protected void saveTimeInvest() {
            ItemTimeLogging item = new ItemTimeLogging();
            item.setLoguser(UserUIContext.getUsername());
            item.setLogvalue(getInvestValue());
            item.setTypeid(bean.getId());
            item.setType(ProjectTypeConstants.BUG);
            item.setSaccountid(AppUI.getAccountId());
            item.setProjectid(CurrentProjectVariables.getProjectId());
            item.setLogforday(forLogDate());
            item.setIsbillable(isBillableHours());
            item.setIsovertime(isOvertimeHours());
            itemTimeLoggingService.saveWithSession(item, UserUIContext.getUsername());
        }

        @Override
        protected void updateTimeRemain() {
            BugService bugService = AppContextUtil.getSpringBean(BugService.class);
            bean.setRemainestimate(getUpdateRemainTime());
            bugService.updateWithSession(bean, UserUIContext.getUsername());
        }

        @Override
        protected ItemTimeLoggingSearchCriteria getItemSearchCriteria() {
            ItemTimeLoggingSearchCriteria searchCriteria = new ItemTimeLoggingSearchCriteria();
            searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
            searchCriteria.setType(StringSearchField.and(ProjectTypeConstants.BUG));
            searchCriteria.setTypeId(new NumberSearchField(bean.getId()));
            return searchCriteria;
        }

        @Override
        protected double getInvestValue() {
            return super.getInvestValue();
        }

        @Override
        protected double getEstimateRemainTime() {
            return MoreObjects.firstNonNull(bean.getRemainestimate(), 0d);
        }

        @Override
        protected boolean isEnableAdd() {
            return CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS);
        }
    }
}
