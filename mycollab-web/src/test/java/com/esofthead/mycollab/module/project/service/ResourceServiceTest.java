package com.esofthead.mycollab.module.project.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.domain.criteria.ResourceSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.EngroupClassRunner;

@RunWith(EngroupClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:META-INF/spring/audit-context.xml",
		"classpath:META-INF/spring/common-context.xml",
		"classpath:META-INF/spring/file-context.xml",
		"classpath:META-INF/spring/project-context.xml",
		"classpath:META-INF/spring/datasource-test-context.xml" })
public class ResourceServiceTest {
	@Autowired
	protected ResourceService resourceService;

	@DataSet
	@Test
	public void testGetListResources() {
		List resources = resourceService.findPagableListByCriteria(null, 0,
				Integer.MAX_VALUE);
		Assert.assertEquals(3, resources.size());
	}

	@DataSet
	@Test
	public void testSearchResourcesByName() {
		ResourceSearchCriteria criteria = new ResourceSearchCriteria();
		criteria.setResourcename(new StringSearchField(SearchField.AND, "A"));
		List resources = resourceService.findPagableListByCriteria(criteria, 0,
				Integer.MAX_VALUE);
		Assert.assertEquals(2, resources.size());
	}

	@DataSet
	@Test
	public void testGetListResourceNamesByProjectId() {
		Assert.assertEquals(3, resourceService.getResourceNamesByProjectId(1)
				.size());
	}
}
