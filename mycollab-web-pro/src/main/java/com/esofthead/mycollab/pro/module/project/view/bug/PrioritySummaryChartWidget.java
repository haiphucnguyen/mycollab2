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
package com.esofthead.mycollab.pro.module.project.view.bug;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum.BugPriority;
import com.esofthead.mycollab.module.project.view.bug.IPrioritySummaryChartWidget;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.pro.ui.chart.PieChartWrapper;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class PrioritySummaryChartWidget extends PieChartWrapper<BugSearchCriteria> implements IPrioritySummaryChartWidget {
    private static final long serialVersionUID = 1L;

    @Override
    protected List<GroupItem> loadGroupItems() {
        BugService bugService = ApplicationContextUtil.getSpringBean(BugService.class);
        return bugService.getPrioritySummary(searchCriteria);
    }

    @Override
    protected DataSeries buildChartSeries() {
        final DataSeries series = new DataSeries("Priority");
        BugPriority[] bugPriorities = OptionI18nEnum.bug_priorities;
        for (BugPriority priority : bugPriorities) {
            boolean isFound = false;
            for (GroupItem item : groupItems) {
                if (priority.name().equals(item.getGroupid())) {
                    series.add(new DataSeriesItem(AppContext.getMessage(priority), item.getValue()));
                    isFound = true;
                    break;
                }
            }

            if (!isFound) {
                series.add(new DataSeriesItem(AppContext.getMessage(priority), 0));
            }
        }
        return series;
    }

    @Override
    public void clickLegendItem(String key) {
        BugSearchCriteria cloneSearchCriteria = BeanUtility.deepClone(searchCriteria);
        cloneSearchCriteria.setPriorities(new SetSearchField<>(key));
        EventBusFactory.getInstance().post(new BugEvent.GotoList(this, cloneSearchCriteria));
    }
}
