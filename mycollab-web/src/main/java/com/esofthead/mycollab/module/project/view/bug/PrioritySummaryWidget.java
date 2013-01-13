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
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class PrioritySummaryWidget extends VerticalLayout {

    private static final long serialVersionUID = 1L;

    public PrioritySummaryWidget() {
        this.addComponent(new Label("Priority"));

        // create the chart...
        final JFreeChart chart = ChartFactory.createPieChart(
                "Priority", // chart title
                createDataset(), // data
                true, // include legend
                true, // tooltips?
                false // URLs?
                );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);


        // get a reference to the plot for further customisation...
        // OPTIONAL CUSTOMISATION COMPLETED.

        JFreeChartWrapper wrapper = new JFreeChartWrapper(chart);
        wrapper.setHeight("200px");
        wrapper.setWidth("450px");
        wrapper.setGraphHeight(200);
        wrapper.setGraphWidth(450);
        /////////////////////////////////////////////
        this.addComponent(wrapper);

    }

    private DefaultPieDataset createDataset() {

        // create the dataset...
        final DefaultPieDataset dataset = new DefaultPieDataset();

        BugService bugService = AppContext.getSpringBean(BugService.class);

        SimpleProject project = (SimpleProject) AppContext
                .getVariable(ProjectContants.PROJECT_NAME);
        BugSearchCriteria recentDefectsCriteria = new BugSearchCriteria();
        recentDefectsCriteria.setProjectid(new NumberSearchField(project.getId()));
        List<GroupItem> groupItems = bugService.getPrioritySummary(recentDefectsCriteria);
        
        String[] bugPriorities = ProjectDataTypeFactory.getBugPriorityList();
        for (String priority : bugPriorities) {
            boolean isFound = false;
            for (GroupItem item : groupItems) {
                if (priority.equals(item.getGroupid())) {
                    dataset.setValue(priority, item.getValue());
                    isFound = true;
                    break;
                }
            }

            if (!isFound) {
                dataset.setValue(priority, 0);
            }
        }


        return dataset;

    }
}
