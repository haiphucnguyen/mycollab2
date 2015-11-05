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
package com.esofthead.mycollab.module.project.view.task.components;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.common.domain.OptionVal;
import com.esofthead.mycollab.common.domain.criteria.TimelineTrackingSearchCriteria;
import com.esofthead.mycollab.common.service.OptionValService;
import com.esofthead.mycollab.common.service.TimelineTrackingService;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.html.DivLessFormatter;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.ui.chart.GenericChartWrapper;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.hp.gagawa.java.elements.Div;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
public class TaskStatusTrendChartWidget extends Depot {
    private static final DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd").withZone(DateTimeZone.UTC);

    public TaskStatusTrendChartWidget() {
        super("Trend in 30 days", new MVerticalLayout());
        setContentBorder(true);
    }

    public void display(TimelineTrackingSearchCriteria searchCriteria) {
        MVerticalLayout content = (MVerticalLayout) getContent();
        content.removeAllComponents();
        TaskStatusChartWrapper chartWrapper = new TaskStatusChartWrapper();
        content.addComponent(chartWrapper);
        chartWrapper.display(searchCriteria);
    }

    private static class TaskStatusChartWrapper extends GenericChartWrapper {
        private TimelineTrackingSearchCriteria searchCriteria;
        private TimelineTrackingService timelineTrackingService;
        private Map<String, List<GroupItem>> groupItems;
        private TimeSeriesCollection dataset;

        public TaskStatusChartWrapper() {
            super(350, 280);
            timelineTrackingService = ApplicationContextUtil.getSpringBean(TimelineTrackingService.class);
        }

        @Override
        protected JFreeChart createChart() {
            dataset = new TimeSeriesCollection();
            Set<String> statuses = groupItems.keySet();
            for (String status : statuses) {
                TimeSeries series = new TimeSeries(status);
                List<GroupItem> groupItems = this.groupItems.get(status);
                for (GroupItem item : groupItems) {
                    series.add(new Day(formatter.parseDateTime(item.getGroupname()).toDate()), item.getValue());
                }
                dataset.addSeries(series);
            }

            JFreeChart chart = ChartFactory.createTimeSeriesChart("", "", "", dataset,
                    false, true, false);
            chart.setBackgroundPaint(Color.white);

            XYPlot plot = (XYPlot) chart.getPlot();
            plot.setBackgroundPaint(Color.white);
            plot.setDomainGridlinePaint(Color.lightGray);
            plot.setRangeGridlinePaint(Color.cyan);
            plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
            plot.setDomainCrosshairVisible(true);
            plot.setRangeCrosshairVisible(true);
            XYItemRenderer r = plot.getRenderer();
            if (r instanceof XYLineAndShapeRenderer) {
                XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
                renderer.setBaseShapesVisible(true);
                renderer.setBaseShapesFilled(true);
                renderer.setDrawSeriesLineAsPath(true);
                for (int i = 0; i < statuses.size(); i++) {
                    int colorIndex = i % CHART_COLOR_STR.size();
                    renderer.setSeriesPaint(i, Color.decode("0x" + CHART_COLOR_STR.get(colorIndex)));
                }
            }
            NumberAxis valueAxis = (NumberAxis) plot.getRangeAxis();
            valueAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            return chart;
        }

        @Override
        protected final ComponentContainer createLegendBox() {
            final CssLayout mainLayout = new CssLayout();
            mainLayout.addStyleName("legendBoxContent");
            final List series = dataset.getSeries();

            for (int i = 0; i < series.size(); i++) {
                final TimeSeries key = (TimeSeries) series.get(i);
                int colorIndex = i % CHART_COLOR_STR.size();
                final Button btnLink = new Button(AppContext.getMessage(OptionI18nEnum.BugStatus.class, (String) key.getKey()));
//                btnLink.addStyleName(UIConstants.THEME_LINK);
                Div div = new DivLessFormatter().appendChild(new Div().setStyle("width:13px;height:13px;" +
                        "background: #" + CHART_COLOR_STR.get(colorIndex)));
                MHorizontalLayout wrapper = new MHorizontalLayout().withSpacing(false).with(new Label(div.write(), ContentMode.HTML), btnLink);
                wrapper.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
                mainLayout.addComponent(wrapper);
            }
            return mainLayout;
        }

        void display(TimelineTrackingSearchCriteria searchCriteria) {
            searchCriteria.setType(new StringSearchField(ProjectTypeConstants.TASK));
            searchCriteria.setFieldgroup(new StringSearchField("status"));
            this.searchCriteria = searchCriteria;
            LocalDate endDate = new LocalDate(new GregorianCalendar().getTime());
            LocalDate startDate = endDate.minusDays(30);
            OptionValService optionValService = ApplicationContextUtil.getSpringBean(OptionValService.class);
            List<OptionVal> optionVals = optionValService.findOptionVals(ProjectTypeConstants.TASK,
                    CurrentProjectVariables.getProjectId(), AppContext.getAccountId());
            List<String> options = new ArrayList<>();
            for (OptionVal optionVal : optionVals) {
                options.add(optionVal.getTypeval());
            }
            groupItems = timelineTrackingService.findTimelineItems(options,
                    startDate.toDate(), endDate.toDate(), searchCriteria);
            displayChart();
        }
    }
}
