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
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.MyCollabClassRunner;
import com.esofthead.mycollab.test.service.ServiceTest;

@RunWith(MyCollabClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/service-context-test.xml" })
public class RiskServiceTest extends ServiceTest {

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

	@DataSet
	@Test
	public void testInsertAndReturnKey() {
		Risk record = new Risk();
		record.setProjectid(1);
		record.setRiskname("New projectMember");
		record.setDescription("aaa");
		record.setSaccountid(1);
		int newId = riskService.saveWithSession(record, "hainguyen");

		Risk risk = riskService.findByPrimaryKey(newId, 1);
		Assert.assertEquals("New projectMember", risk.getRiskname());
	}
}
