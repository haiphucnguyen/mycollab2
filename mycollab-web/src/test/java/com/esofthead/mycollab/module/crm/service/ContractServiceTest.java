package com.esofthead.mycollab.module.crm.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.domain.criteria.ContractSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.EngroupClassRunner;

@RunWith(EngroupClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:META-INF/spring/audit-context.xml",
		"classpath:META-INF/spring/file-context.xml",
		"classpath:META-INF/spring/crm-context.xml",
		"classpath:META-INF/spring/datasource-test-context.xml" })
public class ContractServiceTest {

	@Autowired
	protected ContractService contractService;

	@DataSet
	@Test
	public void testGetAll() {
		List contracts = contractService.findPagableListByCriteria(null, 0,
				Integer.MAX_VALUE);
		Assert.assertEquals(2, contracts.size());
	}

	@DataSet
	@Test
	public void testSearchByCriteria() {
		ContractSearchCriteria criteria = new ContractSearchCriteria();
		criteria.setContractName(new StringSearchField(SearchField.AND, "a"));
		criteria.setAssignUserName(new StringSearchField(SearchField.AND,
				"lastname"));
		criteria.setAssignUser(new StringSearchField(SearchField.AND, "admin"));
		criteria.setAccountId(new NumberSearchField(SearchField.AND, 1));
		criteria.setAccountName(new StringSearchField(SearchField.AND, "x"));
		criteria.setOpportunityName(new StringSearchField(SearchField.AND, "y"));
		criteria.setOpportunityId(new NumberSearchField(SearchField.AND, 1));
		criteria.setSaccountid(new NumberSearchField(SearchField.AND, 1));

		List contracts = contractService.findPagableListByCriteria(criteria, 0,
				100);
		Assert.assertEquals(1, contracts.size());
		Assert.assertEquals(1, contractService.getTotalCount(criteria));

	}
}
