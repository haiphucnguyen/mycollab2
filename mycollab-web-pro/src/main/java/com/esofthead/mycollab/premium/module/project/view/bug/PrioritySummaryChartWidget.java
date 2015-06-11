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

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum.BugPriority;
import com.esofthead.mycollab.module.project.view.bug.IPrioritySummaryChartWidget;
import com.esofthead.mycollab.module.project.view.parameters.BugFilterParameter;
import com.esofthead.mycollab.module.project.view.parameters.BugScreenData;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.LegendItemClickEvent;
import com.vaadin.addon.charts.LegendItemClickListener;
import com.vaadin.addon.charts.model.*;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class PrioritySummaryChartWidget extends CssLayout implements
        IPrioritySummaryChartWidget {

    private static final long serialVersionUID = 1L;

    public PrioritySummaryChartWidget() {
        this.setSizeFull();
    }

    @Override
    public void setSearchCriteria(BugSearchCriteria searchCriteria) {
        this.removeAllComponents();
        BugService bugService = ApplicationContextUtil.getSpringBean(BugService.class);

        final DataSeries series = new DataSeries("Priority");

        List<GroupItem> groupItems = bugService
                .getPrioritySummary(searchCriteria);
        BugPriority[] bugPriorities = OptionI18nEnum.bug_priorities;
        for (BugPriority priority : bugPriorities) {
            boolean isFound = false;
            for (GroupItem item : groupItems) {
                if (priority.name().equals(item.getGroupid())) {
                    series.add(new DataSeriesItem(AppContext
                            .getMessage(priority), item.getValue()));
                    isFound = true;
                    break;
                }
            }

            if (!isFound) {
                series.add(new DataSeriesItem(AppContext.getMessage(priority), 0));
            }
        }

        Chart chart = new Chart(ChartType.PIE);
        Configuration conf = chart.getConfiguration();
        chart.addLegendItemClickListener(new LegendItemClickListener() {
            @Override
            public void onClick(LegendItemClickEvent legendItemClickEvent) {
                DataSeriesItem dataSeries = series.get(legendItemClickEvent.getSeriesItemIndex());
                String priority = dataSeries.getName();
                BugSearchCriteria searchCriteria = new BugSearchCriteria();
                searchCriteria.setPriorities(new SetSearchField<>(priority));
                searchCriteria.setProjectId(new NumberSearchField(CurrentProjectVariables.getProjectId()));
                BugFilterParameter param = new BugFilterParameter(priority + " Bug List", searchCriteria);
                EventBusFactory.getInstance().post(new BugEvent.GotoList(this, new BugScreenData.Search(param)));
            }
        });

        conf.setTitle("");
        conf.setCredits(new Credits(""));

        Tooltip tooltip = new Tooltip();
        tooltip.setPointFormat("{point.y}");
        conf.setTooltip(tooltip);

        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setAllowPointSelect(true);
        plotOptions.setCursor(Cursor.POINTER);
        plotOptions.setShowInLegend(true);

        conf.setPlotOptions(plotOptions);

        conf.setSeries(series);
        chart.drawChart(conf);

        this.addComponent(chart);
    }

    @Override
    public ComponentContainer getWidget() {
        return this;
    }

    @Override
    public void addViewListener(ViewListener listener) {

    }
}
