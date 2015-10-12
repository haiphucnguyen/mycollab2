/**
 * This file is part of mycollab-web-community.
 *
 * mycollab-web-community is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web-community is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web-community.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.community.module.project.view.bug;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.community.ui.chart.PieChartWrapper;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.i18n.BugI18nEnum;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum.BugResolution;
import com.esofthead.mycollab.module.project.view.bug.IBugResolutionSummaryChartWidget;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.ui.ComponentContainer;
import org.jfree.data.general.DefaultPieDataset;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class ResolutionSummaryChartWidget extends PieChartWrapper<BugSearchCriteria> implements IBugResolutionSummaryChartWidget {
    private static final long serialVersionUID = 1L;

    public ResolutionSummaryChartWidget() {
        super(AppContext.getMessage(BugI18nEnum.WIDGET_CHART_RESOLUTION_TITLE), BugResolution.class, 400, 280);
    }

    @Override
    public ComponentContainer getWidget() {
        return this;
    }

    @Override
    public void addViewListener(ViewListener listener) {
    }

    @Override
    protected DefaultPieDataset createDataset() {
        // create the dataset...
        final DefaultPieDataset dataset = new DefaultPieDataset();

        BugService bugService = ApplicationContextUtil.getSpringBean(BugService.class);

        List<GroupItem> groupItems = bugService.getResolutionDefectsSummary(searchCriteria);

        BugResolution[] bugResolutions = OptionI18nEnum.bug_resolutions;
        for (BugResolution resolution : bugResolutions) {
            boolean isFound = false;
            for (GroupItem item : groupItems) {
                if (resolution.name().equals(item.getGroupid())) {
                    dataset.setValue(resolution.name(), item.getValue());
                    isFound = true;
                    break;
                }
            }

            if (!isFound) {
                dataset.setValue(resolution.name(), 0);
            }
        }

        return dataset;
    }

    @Override
    public void clickLegendItem(String key) {
        BugSearchCriteria searchCriteria = new BugSearchCriteria();
        searchCriteria.setResolutions(new SetSearchField<>(key));
        searchCriteria.setProjectId(new NumberSearchField(CurrentProjectVariables.getProjectId()));
        EventBusFactory.getInstance().post(new BugEvent.GotoList(this, searchCriteria));
    }
}