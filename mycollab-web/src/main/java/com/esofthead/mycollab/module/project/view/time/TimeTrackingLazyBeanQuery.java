package com.esofthead.mycollab.module.project.view.time;

import java.util.List;
import java.util.Map;

import org.vaadin.addons.lazyquerycontainer.AbstractBeanQuery;
import org.vaadin.addons.lazyquerycontainer.QueryDefinition;

import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.crm.service.TaskService;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

public class TimeTrackingLazyBeanQuery extends AbstractBeanQuery<SimpleBug> {

	private BugService bugService;
	private TaskService taskService;

	public TimeTrackingLazyBeanQuery(QueryDefinition definition,
			Map<String, Object> queryConfiguration, Object[] sortPropertyIds,
			boolean[] sortStates) {
		super(definition, queryConfiguration, sortPropertyIds, sortStates);

		this.bugService = ApplicationContextUtil
				.getSpringBean(BugService.class);
		this.taskService = ApplicationContextUtil
				.getSpringBean(TaskService.class);
	}

	@Override
	protected SimpleBug constructBean() {
		return new SimpleBug();
	}

	@Override
	protected List<SimpleBug> loadBeans(int startIndex, int count) {
		int paging = startIndex / count;
		BugSearchCriteria criteria = new BugSearchCriteria();

		SearchRequest<BugSearchCriteria> request = new SearchRequest<BugSearchCriteria>(
				criteria, paging, count);
		return (List<SimpleBug>) bugService.findPagableListByCriteria(request);
	}

	@Override
	protected void saveBeans(List<SimpleBug> addedTasks,
			List<SimpleBug> modifiedTasks, List<SimpleBug> removedTasks) {
	}

	@Override
	public int size() {
		BugSearchCriteria criteria = new BugSearchCriteria();
		SearchRequest<BugSearchCriteria> request = new SearchRequest<BugSearchCriteria>(
				criteria, 0, Integer.MAX_VALUE);

		return bugService.findPagableListByCriteria(request).size();
	}
}
