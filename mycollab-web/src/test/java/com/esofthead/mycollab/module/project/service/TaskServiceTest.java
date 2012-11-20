package com.esofthead.mycollab.module.project.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.esofthead.mycollab.core.arguments.BooleanSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.EngroupClassRunner;

@RunWith(EngroupClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:META-INF/spring/audit-context.xml",
		"classpath:META-INF/spring/common-context.xml",
		"classpath:META-INF/spring/file-context.xml",
		"classpath:META-INF/spring/project-context.xml",
		"classpath:META-INF/spring/tracker-context.xml",
		"classpath:META-INF/spring/project-service-test-context.xml",
		"classpath:META-INF/spring/datasource-test-context.xml" })
public class TaskServiceTest {
	@Autowired
	protected ProjectTaskService projectTaskService;

	@DataSet
	@Test
	public void testGetUnCompletedTaskOfUser() {
		TaskSearchCriteria criteria = new TaskSearchCriteria();
		criteria.setIsmilestone(new BooleanSearchField(SearchField.AND, true));
		criteria.setProjectid(new NumberSearchField(SearchField.AND, 1));

		Assert.assertEquals(1, projectTaskService.getTotalCount(criteria));
		Assert.assertEquals(
				1,
				projectTaskService.findPagableListByCriteria(
						new SearchRequest<TaskSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}
}
