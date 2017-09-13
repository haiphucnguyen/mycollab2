package com.mycollab.premium.mobile.module.project.view.risk;

import com.mycollab.db.arguments.BooleanSearchField;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.mobile.module.project.ui.TimeLogComp;
import com.mycollab.mobile.module.project.ui.TimeLogEditView;
import com.mycollab.mobile.shell.events.ShellEvent;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.ItemTimeLogging;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.mycollab.module.project.service.RiskService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;

import java.util.Date;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
public class RiskTimeLogComp extends TimeLogComp<SimpleRisk> {
    private static final long serialVersionUID = 8006444639083945910L;

    @Override
    protected Double getTotalBillableHours(SimpleRisk bean) {
        ItemTimeLoggingSearchCriteria criteria = new ItemTimeLoggingSearchCriteria();
        criteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
        criteria.setType(StringSearchField.and(ProjectTypeConstants.RISK));
        criteria.setTypeId(new NumberSearchField(bean.getId()));
        criteria.setIsBillable(new BooleanSearchField(true));
        return itemTimeLoggingService.getTotalHoursByCriteria(criteria);
    }

    @Override
    protected Double getTotalNonBillableHours(SimpleRisk bean) {
        ItemTimeLoggingSearchCriteria criteria = new ItemTimeLoggingSearchCriteria();
        criteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
        criteria.setType(StringSearchField.and(ProjectTypeConstants.RISK));
        criteria.setTypeId(new NumberSearchField(bean.getId()));
        criteria.setIsBillable(new BooleanSearchField(false));
        return itemTimeLoggingService.getTotalHoursByCriteria(criteria);
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
    protected void showEditTimeView(SimpleRisk bean) {
        EventBusFactory.getInstance().post(new ShellEvent.PushView(this, new RiskTimeLogView(bean)));
    }

    private static class RiskTimeLogView extends TimeLogEditView<SimpleRisk> {
        private static final long serialVersionUID = -5178708279456191875L;

        RiskTimeLogView(SimpleRisk bean) {
            super(bean);
        }

        @Override
        protected void saveTimeInvest(double spentHours, boolean isBillable, Date forDate) {
            ItemTimeLogging item = new ItemTimeLogging();
            item.setLoguser(UserUIContext.getUsername());
            item.setLogvalue(spentHours);
            item.setTypeid(bean.getId());
            item.setType(ProjectTypeConstants.RISK);
            item.setSaccountid(AppUI.getAccountId());
            item.setProjectid(CurrentProjectVariables.getProjectId());
            item.setLogforday(forDate);
            item.setIsbillable(isBillable);

            itemTimeLoggingService.saveWithSession(item, UserUIContext.getUsername());
        }

        @Override
        protected void updateTimeRemain(double newValue) {
            RiskService riskService = AppContextUtil.getSpringBean(RiskService.class);
            bean.setRemainestimate(newValue);
            riskService.updateWithSession(bean, UserUIContext.getUsername());
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
            if (bean.getRemainestimate() != null) {
                return bean.getRemainestimate();
            }
            return 0;
        }

        @Override
        protected boolean isEnableAdd() {
            return CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.RISKS);
        }
    }
}
