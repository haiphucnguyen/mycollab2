package com.esofthead.mycollab.module.crm.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.domain.criteria.TargetGroupSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.EngroupClassRunner;

@RunWith(EngroupClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:META-INF/spring/audit-context.xml",
		"classpath:META-INF/spring/file-context.xml",
		"classpath:META-INF/spring/crm-context.xml",
		"classpath:META-INF/spring/datasource-test-context.xml" })
public class TargetListServiceTest {

	@Autowired
	protected TargetGroupService targetListService;

	@DataSet
	@Test
	public void testGetAll() {
		Assert.assertEquals(
				3,
				targetListService.findPagableListByCriteria(null, 0,
						Integer.MAX_VALUE).size());
	}

	@DataSet
	@Test
	public void testSearchByCriteria() {
		TargetGroupSearchCriteria criteria = new TargetGroupSearchCriteria();
		criteria.setAssignUserName(new StringSearchField(SearchField.AND, "Hai"));
		criteria.setListName(new StringSearchField(SearchField.AND, "a"));
		criteria.setAssignUser(new StringSearchField(SearchField.AND, "admin"));

		Assert.assertEquals(
				1,
				targetListService.findPagableListByCriteria(criteria, 0,
						Integer.MAX_VALUE).size());

		Assert.assertEquals(1, targetListService.getTotalCount(criteria));
	}
}
