package com.esofthead.mycollab.module.project.view.bug.components;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.i18n.BugI18nEnum;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.esofthead.mycollab.module.project.view.bug.IStatusSummaryChartWidget;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.ButtonI18nComp;
import com.esofthead.mycollab.vaadin.ui.DepotWithChart;
import com.esofthead.mycollab.vaadin.ui.ProgressBarIndicator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Button;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
public class UnresolvedBugsByStatusWidget extends DepotWithChart {
    private BugSearchCriteria searchCriteria;
    private int totalCount;
    private List<GroupItem> groupItems;

    public void setSearchCriteria(final BugSearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;

        BugService bugService = ApplicationContextUtil.getSpringBean(BugService.class);
        totalCount = bugService.getTotalCount(searchCriteria);
        this.setTitle(AppContext.getMessage(BugI18nEnum.WIDGET_UNRESOLVED_BY_STATUS_TITLE) + " (" + totalCount + ")");
        groupItems = bugService.getStatusSummary(searchCriteria);
        displayPlainMode();
    }

    @Override
    protected void displayChartMode() {
        this.bodyContent.removeAllComponents();
        IStatusSummaryChartWidget statusSummaryChartWidget = ViewManager.getCacheComponent(IStatusSummaryChartWidget.class);
        statusSummaryChartWidget.displayChart(searchCriteria);
        bodyContent.addComponent(statusSummaryChartWidget);
    }

    @Override
    protected void displayPlainMode() {
        this.bodyContent.removeAllComponents();
        BugStatusClickListener listener = new BugStatusClickListener();
        if (!groupItems.isEmpty()) {
            for (OptionI18nEnum.BugStatus status : OptionI18nEnum.bug_statuses) {
                boolean isFound = false;
                for (GroupItem item : groupItems) {
                    if (status.name().equals(item.getGroupid())) {
                        isFound = true;
                        MHorizontalLayout statusLayout = new MHorizontalLayout().withWidth("100%");
                        ButtonI18nComp statusLink = new ButtonI18nComp(status.name(), status, listener);
                        statusLink.setWidth("110px");
                        statusLink.setStyleName(UIConstants.THEME_LINK);

                        ProgressBarIndicator indicator = new ProgressBarIndicator(totalCount, totalCount - item.getValue(), false);
                        indicator.setWidth("100%");
                        statusLayout.with(statusLink, indicator).expand(indicator);
                        this.bodyContent.addComponent(statusLayout);
                    }
                }

                if (!isFound) {
                    MHorizontalLayout statusLayout = new MHorizontalLayout().withWidth("100%");
                    Button statusLink = new ButtonI18nComp(status.name(), status, listener);
                    statusLink.setWidth("110px");
                    statusLink.setStyleName(UIConstants.THEME_LINK);
                    ProgressBarIndicator indicator = new ProgressBarIndicator(totalCount, totalCount, false);
                    indicator.setWidth("100%");
                    statusLayout.with(statusLink, indicator).expand(indicator);
                    this.bodyContent.addComponent(statusLayout);
                }
            }
        }
    }

    private class BugStatusClickListener implements Button.ClickListener {
        private static final long serialVersionUID = 1L;

        @Override
        public void buttonClick(final Button.ClickEvent event) {
            String key = ((ButtonI18nComp) event.getButton()).getKey();
            searchCriteria.setStatuses(new SetSearchField<>(new String[]{key}));
            EventBusFactory.getInstance().post(new BugEvent.SearchRequest(this, searchCriteria));
        }
    }
}
