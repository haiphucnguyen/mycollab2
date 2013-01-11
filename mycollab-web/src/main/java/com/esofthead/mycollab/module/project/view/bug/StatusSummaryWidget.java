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
import com.vaadin.ui.Panel;
import java.awt.Color;
import java.awt.GradientPaint;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class StatusSummaryWidget extends Panel {

    private static final long serialVersionUID = 1L;

    public StatusSummaryWidget() {
        this.addComponent(new Label("Status"));
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createBarChart(
            "Bar Chart Demo",         // chart title
            "Category",               // domain axis label
            "Value",                  // range axis label
            createDataset(),                  // data
            PlotOrientation.HORIZONTAL, // orientation
            true,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        
        // set up gradient paints for series...
        final GradientPaint gp0 = new GradientPaint(
            0.0f, 0.0f, Color.blue, 
            0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp1 = new GradientPaint(
            0.0f, 0.0f, Color.green, 
            0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp2 = new GradientPaint(
            0.0f, 0.0f, Color.red, 
            0.0f, 0.0f, Color.lightGray
        );
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);

        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        // OPTIONAL CUSTOMISATION COMPLETED.
        JFreeChartWrapper wrapper = new JFreeChartWrapper(chart);
        /////////////////////////////////////////////
        this.addComponent(wrapper);
        wrapper.setWidth("100%");
        this.setWidth("100%");
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
