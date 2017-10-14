package com.mycollab.pro.module.project.view.risk;

import com.google.common.base.MoreObjects;
import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import com.mycollab.db.arguments.BooleanSearchField;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.vaadin.ApplicationEventListener;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.ItemTimeLogging;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.mycollab.module.project.event.ProjectEvent;
import com.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.mycollab.module.project.service.RiskService;
import com.mycollab.pro.module.project.ui.components.TimeLogEditWindow;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.ui.UI;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
@ViewComponent
public class RiskTimeLogSheetImpl extends RiskTimeLogSheet {
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

    private Double loadTotalBillableHours() {
        ItemTimeLoggingSearchCriteria criteria = new ItemTimeLoggingSearchCriteria();
        criteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
        criteria.setType(StringSearchField.and(ProjectTypeConstants.RISK));
        criteria.setTypeId(new NumberSearchField(beanItem.getId()));
        criteria.setBillable(new BooleanSearchField(true));
        return itemTimeLoggingService.getTotalHoursByCriteria(criteria);
    }

    private Double loadTotalNonBillableHours() {
        ItemTimeLoggingSearchCriteria criteria = new ItemTimeLoggingSearchCriteria();
        criteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
        criteria.setType(StringSearchField.and(ProjectTypeConstants.RISK));
        criteria.setTypeId(new NumberSearchField(beanItem.getId()));
        criteria.setBillable(new BooleanSearchField(false));
        return itemTimeLoggingService.getTotalHoursByCriteria(criteria);
    }

    @Override
    protected Double getTotalBillableHours(SimpleRisk bean) {
        return beanItem.getBillableHours();
    }

    @Override
    protected Double getTotalNonBillableHours(SimpleRisk bean) {
        return beanItem.getNonBillableHours();
    }

    @Override
    protected Double getRemainedHours(SimpleRisk bean) {
        if (bean.getRemainestimate() != null) {
            return bean.getRemainestimate();
        }
        return 0d;
    }

    @Override
    protected boolean hasEditPermission() {
        return CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.RISKS);
    }

    @Override
    protected void showEditTimeWindow(SimpleRisk bean) {
        UI.getCurrent().addWindow(new TicketTimeLogEditWindow(bean));
    }

    private class TicketTimeLogEditWindow extends TimeLogEditWindow<SimpleRisk> {
        TicketTimeLogEditWindow(SimpleRisk bean) {
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
            item.setType(ProjectTypeConstants.RISK);
            item.setSaccountid(AppUI.getAccountId());
            item.setProjectid(CurrentProjectVariables.getProjectId());
            item.setLogforday(forLogDate());
            item.setIsbillable(isBillableHours());
            item.setIsovertime(isOvertimeHours());
            itemTimeLoggingService.saveWithSession(item, UserUIContext.getUsername());
        }

        @Override
        protected void updateTimeRemain() {
            RiskService riskService = AppContextUtil.getSpringBean(RiskService.class);
            bean.setRemainestimate(getUpdateRemainTime());
            riskService.updateSelectiveWithSession(bean, UserUIContext.getUsername());
        }

        @Override
        protected ItemTimeLoggingSearchCriteria getItemSearchCriteria() {
            ItemTimeLoggingSearchCriteria searchCriteria = new ItemTimeLoggingSearchCriteria();
            searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
            searchCriteria.setType(StringSearchField.and(ProjectTypeConstants.RISK));
            searchCriteria.setTypeId(new NumberSearchField(bean.getId()));
            return searchCriteria;
        }

        @Override
        protected double getEstimateRemainTime() {
            return MoreObjects.firstNonNull(bean.getRemainestimate(), 0d);
        }

        @Override
        protected boolean isEnableAdd() {
            return CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.RISKS);
        }
    }
}
