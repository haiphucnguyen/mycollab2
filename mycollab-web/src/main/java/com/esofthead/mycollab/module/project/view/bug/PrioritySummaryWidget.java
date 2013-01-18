package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.module.project.ProjectDataTypeFactory;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.ui.chart.PieChartWrapper;
import com.esofthead.mycollab.web.AppContext;
import java.util.List;
import org.jfree.data.general.DefaultPieDataset;

public class PrioritySummaryWidget extends PieChartWrapper<BugSearchCriteria> {

    private static final long serialVersionUID = 1L;

    public PrioritySummaryWidget() {
        super("Bugs By Priority", 450, 300);

    }

    @Override
    protected DefaultPieDataset createDataset(BugSearchCriteria criteria) {

        // create the dataset...
        final DefaultPieDataset dataset = new DefaultPieDataset();

        BugService bugService = AppContext.getSpringBean(BugService.class);
        
        List<GroupItem> groupItems = bugService.getPrioritySummary(criteria);

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
