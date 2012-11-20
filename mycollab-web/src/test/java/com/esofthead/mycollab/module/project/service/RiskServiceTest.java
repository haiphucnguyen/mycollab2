package com.esofthead.mycollab.module.project.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.EngroupClassRunner;

@RunWith(EngroupClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:META-INF/spring/audit-context.xml",
		"classpath:META-INF/spring/common-context.xml",
		"classpath:META-INF/spring/file-context.xml",
		"classpath:META-INF/spring/tracker-context.xml",
		"classpath:META-INF/spring/project-context.xml",
		"classpath:META-INF/spring/project-service-test-context.xml",
		"classpath:META-INF/spring/datasource-test-context.xml" })
public class RiskServiceTest {
	@Autowired
	protected RiskService riskService;

	@DataSet
	@Test
	public void testGetListRisks() {
		List risks = riskService
				.findPagableListByCriteria(new SearchRequest<RiskSearchCriteria>(
						null, 0, Integer.MAX_VALUE));
		Assert.assertEquals(3, risks.size());
	}

	@DataSet
	@Test
	public void testSearchRisksByName() {
		RiskSearchCriteria criteria = new RiskSearchCriteria();
		criteria.setRiskname(new StringSearchField(SearchField.AND, "a"));
		List risks = riskService
				.findPagableListByCriteria(new SearchRequest<RiskSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));
		Assert.assertEquals(2, risks.size());
	}
}
