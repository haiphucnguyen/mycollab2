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

public class StatusSummaryWidget extends VerticalLayout {

    private static final long serialVersionUID = 1L;

    public StatusSummaryWidget() {
        this.addComponent(new Label("Status"));

        // create the chart...
        final JFreeChart chart = ChartFactory.createPieChart(
                "Status", // chart title
                createDataset(), // data
                true, // include legend
                true, // tooltips?
                false // URLs?
                );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);
        // OPTIONAL CUSTOMISATION COMPLETED.

        JFreeChartWrapper wrapper = new JFreeChartWrapper(chart);
        wrapper.setHeight("300px");
        wrapper.setWidth("450px");
        wrapper.setGraphHeight(300);
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
        List<GroupItem> groupItems = bugService.getStatusSummary(recentDefectsCriteria);
        
        String[] bugPriorities = ProjectDataTypeFactory.getBugStatusList();
        for (String status : bugPriorities) {
            boolean isFound = false;
            for (GroupItem item : groupItems) {
                if (status.equals(item.getGroupid())) {
                    dataset.setValue(status, item.getValue());
                    isFound = true;
                    break;
                }
            }

            if (!isFound) {
                dataset.setValue(status, 0);
            }
        }


        return dataset;
    }
}
