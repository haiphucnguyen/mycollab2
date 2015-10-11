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

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.vaadin.ui.IInteractiveChartComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import org.jfree.chart.JFreeChart;

/**
 * @param <S>
 * @author MyCollab Ltd.
 * @since 1.0
 */
public abstract class GenericChartWrapper<S extends SearchCriteria> extends CssLayout implements
        IInteractiveChartComponent {
    private static final long serialVersionUID = 1L;

    protected static final String[] CHART_COLOR_STR = {ColorConstants.BLUE,
            ColorConstants.GREEN, ColorConstants.ORANGE, ColorConstants.BLACK,
            ColorConstants.DARK_ORANGE, ColorConstants.LIGHT_BLUE,
            ColorConstants.GRAY, ColorConstants.BRIGHT_TURQUOISE,
            ColorConstants.LIGHT_GRAY, ColorConstants.CHERRY,
            ColorConstants.CONGO_PINK, ColorConstants.COFFFE,
            ColorConstants.COPPER, ColorConstants.RED,
            ColorConstants.LIGHTER_GREEN, ColorConstants.INDIAN_RED,
            ColorConstants.LAVENDER, ColorConstants.LEMON,
            ColorConstants.BROWN, ColorConstants.LIVER, ColorConstants.LION};

    protected String title;
    protected int height;
    protected int width;
    protected S searchCriteria;

    public GenericChartWrapper(final String title, final int width, final int height) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    abstract protected JFreeChart createChart();

    protected abstract ComponentContainer createLegendBox();

    public void setSearchCriteria(final S criteria) {
        this.searchCriteria = criteria;
        removeAllComponents();

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
        this.addComponent(legendBox);
    }
}
