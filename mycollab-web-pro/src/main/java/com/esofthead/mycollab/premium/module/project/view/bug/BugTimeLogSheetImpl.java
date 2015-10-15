/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.premium.module.project.view.bug;

import com.esofthead.mycollab.core.arguments.BooleanSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.ItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.esofthead.mycollab.module.project.ui.components.TimeLogEditWindow;
import com.esofthead.mycollab.module.project.view.bug.BugTimeLogSheet;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
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
        if (bean.getEstimateremaintime() != null) {
            return bean.getEstimateremaintime();
        }
        return 0d;
    }

    private Double loadTotalBillableHours() {
        ItemTimeLoggingSearchCriteria criteria = new ItemTimeLoggingSearchCriteria();
        criteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
        criteria.setType(new StringSearchField(ProjectTypeConstants.BUG));
        criteria.setTypeId(new NumberSearchField(beanItem.getId()));
        criteria.setIsBillable(new BooleanSearchField(true));
        return itemTimeLoggingService.getTotalHoursByCriteria(criteria);
    }

    private Double loadTotalNonBillableHours() {
        ItemTimeLoggingSearchCriteria criteria = new ItemTimeLoggingSearchCriteria();
        criteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
        criteria.setType(new StringSearchField(ProjectTypeConstants.BUG));
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
            this.setCaption(AppContext.getMessage(TimeTrackingI18nEnum.DIALOG_LOG_TIME_ENTRY_TITLE));
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
            item.setLoguser(AppContext.getUsername());
            item.setLogvalue(getInvestValue());
            item.setTypeid(bean.getId());
            item.setType(ProjectTypeConstants.BUG);
            item.setSaccountid(AppContext.getAccountId());
            item.setProjectid(CurrentProjectVariables.getProjectId());
            item.setLogforday(forLogDate());
            item.setIsbillable(isBillableHours());
            itemTimeLoggingService.saveWithSession(item, AppContext.getUsername());
        }

        @Override
        protected void updateTimeRemain() {
            BugService bugService = ApplicationContextUtil.getSpringBean(BugService.class);
            bean.setEstimateremaintime(getUpdateRemainTime());
            bugService.updateWithSession(bean, AppContext.getUsername());
        }

        @Override
        protected ItemTimeLoggingSearchCriteria getItemSearchCriteria() {
            ItemTimeLoggingSearchCriteria searchCriteria = new ItemTimeLoggingSearchCriteria();
            searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
            searchCriteria.setType(new StringSearchField(ProjectTypeConstants.BUG));
            searchCriteria.setTypeId(new NumberSearchField(bean.getId()));
            return searchCriteria;
        }

        @Override
        protected double getEstimateRemainTime() {
            if (bean.getEstimateremaintime() != null) {
                return bean.getEstimateremaintime();
            }
            return 0;
        }

        @Override
        protected boolean isEnableAdd() {
            return CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS);
        }
    }
}