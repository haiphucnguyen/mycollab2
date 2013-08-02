package com.esofthead.mycollab.module.project.view.bug;

import java.util.List;

import org.jfree.data.general.DefaultPieDataset;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectDataTypeFactory;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.localization.BugI18nEnum;
import com.esofthead.mycollab.module.project.view.parameters.BugScreenData;
import com.esofthead.mycollab.module.project.view.parameters.BugSearchParameter;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.chart.PieChartDescriptionBox;
import com.esofthead.mycollab.vaadin.ui.chart.PieChartWrapper;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.ui.ComponentContainer;

public class PrioritySummaryChartWidget extends
		PieChartWrapper<BugSearchCriteria> {

	private static final long serialVersionUID = 1L;

	public PrioritySummaryChartWidget(int width, int height) {
		super(LocalizationHelper.getMessage(BugI18nEnum.CHART_PRIORIY_TITLE),
				width, height);
	}

	public PrioritySummaryChartWidget() {
		super(LocalizationHelper.getMessage(BugI18nEnum.CHART_PRIORIY_TITLE),
				400, 280);

	}

	@Override
	protected DefaultPieDataset createDataset() {

		// create the dataset...
		final DefaultPieDataset dataset = new DefaultPieDataset();

		BugService bugService = AppContext.getSpringBean(BugService.class);

		List<GroupItem> groupItems = bugService
				.getPrioritySummary(searchCriteria);

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

	@Override
	protected ComponentContainer createLegendBox() {
		return PieChartDescriptionBox.createLegendBox(this, pieDataSet);
	}

	@Override
	protected void onClickedDescription(String key) {
		BugSearchCriteria searchCriteria = new BugSearchCriteria();
		searchCriteria.setPriorities(new SetSearchField<String>(
				SearchField.AND, new String[] { key }));
		searchCriteria.setProjectId(new NumberSearchField(
				CurrentProjectVariables.getProjectId()));
		BugSearchParameter param = new BugSearchParameter(key + " Bug List",
				searchCriteria);
		EventBus.getInstance().fireEvent(
				new BugEvent.GotoList(this, new BugScreenData.Search(param)));
	}
}
