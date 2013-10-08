package com.esofthead.mycollab.module.project.view.time;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.addons.lazyquerycontainer.AbstractBeanQuery;
import org.vaadin.addons.lazyquerycontainer.QueryDefinition;

import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;

public class TimeTrackingLazyBeanQuery extends AbstractBeanQuery<SimpleBug> {

	@Autowired
	private BugService bugService;

	private Object[] sortPropertyIds;
	private boolean[] sortStates;

	public TimeTrackingLazyBeanQuery(QueryDefinition definition,
			Map<String, Object> queryConfiguration, Object[] sortPropertyIds,
			boolean[] sortStates) {
		super(definition, queryConfiguration, sortPropertyIds, sortStates);

		this.sortPropertyIds = sortPropertyIds;
		this.sortStates = sortStates;
	}

	@Override
	protected SimpleBug constructBean() {
		return new SimpleBug();
	}

	@Override
	protected List<SimpleBug> loadBeans(int startIndex, int count) {
		return bugService
				.findPagableListByCriteria(new SearchRequest<BugSearchCriteria>(
						new BugSearchCriteria(), startIndex, startIndex + count));
	}

	@Override
	protected void saveBeans(List<SimpleBug> addedTasks,
			List<SimpleBug> modifiedTasks, List<SimpleBug> removedTasks) {
	}

	@Override
	public int size() {
		return bugService.findPagableListByCriteria(
				new SearchRequest<BugSearchCriteria>(new BugSearchCriteria(),
						0, Integer.MAX_VALUE)).size();
	}

}
