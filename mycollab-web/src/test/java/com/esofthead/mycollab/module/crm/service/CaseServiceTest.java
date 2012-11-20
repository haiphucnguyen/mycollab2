package com.esofthead.mycollab.module.crm.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.EngroupClassRunner;

@RunWith(EngroupClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:META-INF/spring/audit-context.xml",
		"classpath:META-INF/spring/file-context.xml",
		"classpath:META-INF/spring/crm-context.xml",
		"classpath:META-INF/spring/crm-service-test-context.xml",
		"classpath:META-INF/spring/datasource-test-context.xml" })
public class CaseServiceTest {
	@Autowired
	protected CaseService caseService;

	@DataSet
	@Test
	public void testGetAll() {
		Assert.assertEquals(
				2,
				caseService.findPagableListByCriteria(
						new SearchRequest<CaseSearchCriteria>(null, 0,
								Integer.MAX_VALUE)).size());
	}

	@DataSet
	@Test
	public void testGetSearchCriteria() {
		CaseSearchCriteria criteria = new CaseSearchCriteria();
		criteria.setAccountId(new NumberSearchField(SearchField.AND, 1));
		criteria.setAccountName(new StringSearchField(SearchField.AND, "a"));
		criteria.setAssignUserName(new StringSearchField(SearchField.AND, "Hai"));
		criteria.setAssignUser(new StringSearchField(SearchField.AND, "admin"));
		criteria.setSubject(new StringSearchField(SearchField.AND, "a"));
		criteria.setSaccountid(new NumberSearchField(SearchField.AND, 1));

		Assert.assertEquals(
				1,
				caseService.findPagableListByCriteria(
						new SearchRequest<CaseSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
		Assert.assertEquals(1, caseService.getTotalCount(criteria));
	}
}
