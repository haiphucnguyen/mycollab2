/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.module.project.ProjectDataTypeFactory;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.ui.chart.PieChartWrapper;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;

import java.util.List;
import org.jfree.data.general.DefaultPieDataset;

/**
 * 
 * @author haiphucnguyen
 */
public class BugResolutionSummaryWidget extends
		PieChartWrapper<BugSearchCriteria> {

	private static final long serialVersionUID = 1L;

	public BugResolutionSummaryWidget() {
		super("Bugs By Resolution", 450, 300);

	}

	@Override
	protected DefaultPieDataset createDataset() {

		// create the dataset...
		final DefaultPieDataset dataset = new DefaultPieDataset();

		BugService bugService = AppContext.getSpringBean(BugService.class);

		List<GroupItem> groupItems = bugService
				.getResolutionDefectsSummary(searchCriteria);

		String[] bugPriorities = ProjectDataTypeFactory.getBugResolutionList();
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

	@Override
	protected ComponentContainer createLegendBox() {
		return new HorizontalLayout();
	}

}
