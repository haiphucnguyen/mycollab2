package com.esofthead.mycollab.community.module.project.view.bug;

import java.util.List;

import org.jfree.data.general.DefaultPieDataset;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.community.ui.chart.PieChartDescriptionBox;
import com.esofthead.mycollab.community.ui.chart.PieChartWrapper;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectDataTypeFactory;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.localization.BugI18nEnum;
import com.esofthead.mycollab.module.project.view.bug.IStatusSummaryChartWidget;
import com.esofthead.mycollab.module.project.view.parameters.BugScreenData;
import com.esofthead.mycollab.module.project.view.parameters.BugSearchParameter;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.ComponentContainer;

@ViewComponent
public class StatusSummaryChartWidget extends
		PieChartWrapper<BugSearchCriteria> implements IStatusSummaryChartWidget {
	private static final long serialVersionUID = 1L;

	public StatusSummaryChartWidget(int width, int height) {
		super(LocalizationHelper.getMessage(BugI18nEnum.CHART_STATUS_TITLE),
				width, height);
	}

	public StatusSummaryChartWidget() {
		super(LocalizationHelper.getMessage(BugI18nEnum.CHART_STATUS_TITLE),
				400, 280);
	}

	@Override
	public ComponentContainer getWidget() {
		return this;
	}

	@Override
	public void addViewListener(
			ApplicationEventListener<? extends ApplicationEvent> listener) {

	}

	@Override
	protected DefaultPieDataset createDataset() {
		// create the dataset...
		final DefaultPieDataset dataset = new DefaultPieDataset();

		BugService bugService = ApplicationContextUtil
				.getSpringBean(BugService.class);
		List<GroupItem> groupItems = bugService
				.getStatusSummary(searchCriteria);

		String[] bugStatuses = ProjectDataTypeFactory.getBugStatusList();
		for (String status : bugStatuses) {
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

	@Override
	protected void onClickedDescription(String key) {
		BugSearchCriteria searchCriteria = new BugSearchCriteria();
		searchCriteria.setStatuses(new SetSearchField<String>(SearchField.AND,
				new String[] { key }));
		searchCriteria.setProjectId(new NumberSearchField(
				CurrentProjectVariables.getProjectId()));
		BugSearchParameter param = new BugSearchParameter(key + " Bug List",
				searchCriteria);
		EventBus.getInstance().fireEvent(
				new BugEvent.GotoList(this, new BugScreenData.Search(param)));

	}

	@Override
	protected ComponentContainer createLegendBox() {
		return PieChartDescriptionBox.createLegendBox(this, pieDataSet);
	}

}
