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
package com.esofthead.mycollab.community.ui.chart;

import com.esofthead.mycollab.vaadin.mvp.PageView;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import org.jfree.chart.JFreeChart;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public abstract class GenericChartWrapper extends CssLayout implements PageView {
    private static final long serialVersionUID = 1L;

    private int height;
    private int width;

    public GenericChartWrapper(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    abstract protected JFreeChart createChart();

    protected abstract ComponentContainer createLegendBox();

    final protected void displayChart() {
        final JFreeChart chart = createChart();
        final JFreeChartWrapper chartWrapper = new JFreeChartWrapper(chart);

        final CssLayout borderWrap = new CssLayout();
        borderWrap.addComponent(chartWrapper);
        borderWrap.setStyleName("chart-wrapper");
        borderWrap.setHeight(height + "px");
        borderWrap.setWidth(width + "px");
        chartWrapper.setHeight(height + "px");
        chartWrapper.setWidth(width + "px");
        chartWrapper.setGraphHeight(height);
        chartWrapper.setGraphWidth(width);
        this.addComponent(borderWrap);
        final Component legendBox = createLegendBox();
        if (legendBox != null) {
            this.addComponent(legendBox);
        }
    }
}
