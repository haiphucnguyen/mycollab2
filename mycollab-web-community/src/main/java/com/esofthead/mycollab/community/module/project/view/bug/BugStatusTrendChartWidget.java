package com.esofthead.mycollab.community.module.project.view.bug;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.common.domain.criteria.TimelineTrackingSearchCriteria;
import com.esofthead.mycollab.common.service.TimelineTrackingService;
import com.esofthead.mycollab.community.ui.chart.GenericChartWrapper;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.esofthead.mycollab.module.project.view.bug.IBugStatusTrendChartWidget;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.ui.ComponentContainer;
import org.jfree.chart.JFreeChart;

import java.util.Arrays;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
@ViewComponent
public class BugStatusTrendChartWidget extends GenericChartWrapper implements IBugStatusTrendChartWidget {
    private TimelineTrackingSearchCriteria searchCriteria;
    private TimelineTrackingService timelineTrackingService;

    public BugStatusTrendChartWidget() {
        super(350, 280);
        timelineTrackingService = ApplicationContextUtil.getSpringBean(TimelineTrackingService.class);
    }

    @Override
    protected JFreeChart createChart() {
        return null;
    }

    @Override
    protected ComponentContainer createLegendBox() {
        return null;
    }

    @Override
    public void display(TimelineTrackingSearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
//        List<GroupItem> gropuItems = timelineTrackingService.findTimelineItems(Arrays.asList(OptionI18nEnum.BugStatus
//                .InProgress.name(), OptionI18nEnum
//                .BugStatus.Open.name(), OptionI18nEnum.BugStatus.ReOpened.name(), OptionI18nEnum.BugStatus.Resolved
//                .name(), OptionI18nEnum.BugStatus.Verified.name()), searchCriteria);
//        displayChart();
    }

    @Override
    public ComponentContainer getWidget() {
        return this;
    }

    @Override
    public <E> void addViewListener(ViewListener<E> listener) {

    }
}
