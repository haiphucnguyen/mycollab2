package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.module.project.ProjectDataTypeFactory;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.ui.chart.PieChartWrapper;
import com.esofthead.mycollab.web.AppContext;
import java.util.List;
import org.jfree.data.general.DefaultPieDataset;

public class StatusSummaryWidget extends PieChartWrapper<BugSearchCriteria> {

    private static final long serialVersionUID = 1L;

    public StatusSummaryWidget() {
        super("Bugs By Status", 450, 300);
    }

    @Override
    protected DefaultPieDataset createDataset(BugSearchCriteria criteria) {
        // create the dataset...
        final DefaultPieDataset dataset = new DefaultPieDataset();

        BugService bugService = AppContext.getSpringBean(BugService.class);
        List<GroupItem> groupItems = bugService.getStatusSummary(criteria);
        
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
