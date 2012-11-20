package com.esofthead.mycollab.module.crm.service;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.crm.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.EngroupClassRunner;

@RunWith(EngroupClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:META-INF/spring/audit-context.xml",
		"classpath:META-INF/spring/file-context.xml",
		"classpath:META-INF/spring/crm-context.xml",
		"classpath:META-INF/spring/crm-service-test-context.xml",
		"classpath:META-INF/spring/datasource-test-context.xml" })
public class TaskServiceTest {

	@Autowired
	protected TaskService taskService;

	@DataSet
	@Test
	public void testSearchByCriteria() {
		Assert.assertEquals(1,
				taskService.findPagableListByCriteria(new SearchRequest<TaskSearchCriteria>(getCriteria(), 0,
						2))
						.size());
	}

	@DataSet
	@Test
	public void testGetTotalCounts() {
		Assert.assertEquals(1, taskService.getTotalCount(getCriteria()));
	}

	private TaskSearchCriteria getCriteria() {
		TaskSearchCriteria criteria = new TaskSearchCriteria();
		return criteria;
	}
}
