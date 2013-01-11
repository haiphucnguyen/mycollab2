package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectDataTypeFactory;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.ui.JFreeChartWrapper;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class StatusSummaryWidget extends VerticalLayout {

    private static final long serialVersionUID = 1L;

    public StatusSummaryWidget() {
        this.addComponent(new Label("Status"));

        // create the chart...
        final JFreeChart chart = ChartFactory.createBarChart(
                "", // chart title
                "", // domain axis label
                "", // range axis label
                createDataset(), // data
                PlotOrientation.HORIZONTAL, // orientation
                false, // include legend
                false, // tooltips?
                false // URLs?
                );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);


        // get a reference to the plot for further customisation...
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        BarRenderer br = (BarRenderer) plot.getRenderer();
        br.setItemMargin(.2);
        final GradientPaint gp0 = new GradientPaint(
                0.0f, 0.0f, Color.blue,
                0.0f, 0.0f, Color.lightGray);
        br.setSeriesPaint(0, gp0);

        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.

        JFreeChartWrapper wrapper = new JFreeChartWrapper(chart);
        wrapper.setHeight("300px");
        wrapper.setWidth("450px");
        wrapper.setGraphHeight(300);
        wrapper.setGraphWidth(450);
        /////////////////////////////////////////////
        this.addComponent(wrapper);
    }

    private CategoryDataset createDataset() {
        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        BugService bugService = AppContext.getSpringBean(BugService.class);

        SimpleProject project = (SimpleProject) AppContext
                .getVariable(ProjectContants.PROJECT_NAME);
        BugSearchCriteria recentDefectsCriteria = new BugSearchCriteria();
        recentDefectsCriteria.setProjectid(new NumberSearchField(project.getId()));
        List<GroupItem> groupItems = bugService.getStatusSummary(recentDefectsCriteria);

        final String series1 = "Bug";
        String[] bugPriorities = ProjectDataTypeFactory.getBugStatusList();
        for (String status : bugPriorities) {
            boolean isFound = false;
            for (GroupItem item : groupItems) {
                if (status.equals(item.getGroupid())) {
                    dataset.setValue(item.getValue(), series1, status);
                    isFound = true;
                    break;
                }
            }

            if (!isFound) {
                dataset.setValue(0, series1, status);
            }
        }


        return dataset;
    }
}
