/**
 * Engroup - Enterprise Groupware Platform Copyright (C) 2007-2009 eSoftHead
 * Company <engroup@esofthead.com> http://www.esofthead.com
 *
 * Licensed under the GPL, Version 3.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the
 * License at
 *
 * http://www.gnu.org/licenses/gpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.esofthead.mycollab.module.crm.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.MyCollabClassRunner;
import com.esofthead.mycollab.test.service.ServiceTest;

@RunWith(MyCollabClassRunner.class)
public class OpportunityServiceTest extends ServiceTest {

	@Autowired
	protected OpportunityService opportunityService;

	@DataSet
	@Test
	public void testSearchByCriteria() {
		Assert.assertEquals(
				2,
				opportunityService.findPagableListByCriteria(
						new SearchRequest<OpportunitySearchCriteria>(
								getCriteria(), 0, 2)).size());
	}

	@DataSet
	@Test
	public void testGetTotalCount() {
		Assert.assertEquals(2, opportunityService.getTotalCount(getCriteria()));
	}

	private OpportunitySearchCriteria getCriteria() {
		OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
		criteria.setAccountId(new NumberSearchField(SearchField.AND, 1));
		criteria.setAssignUserName(new StringSearchField(SearchField.AND,
				"Nguyen"));
		criteria.setCampaignName(new StringSearchField(SearchField.AND, "yz"));
		criteria.setOpportunityName(new StringSearchField(SearchField.AND, "aa"));
		criteria.setSaccountid(new NumberSearchField(SearchField.AND, 1));
		return criteria;
	}

	@Test
	@DataSet
	public void testSearchLeadSources() {
		OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
		criteria.setLeadSources(new SetSearchField<String>(SearchField.AND,
				new String[] { "Cold Call", "Employee" }));
		criteria.setSaccountid(new NumberSearchField(1));

		Assert.assertEquals(2, opportunityService.getTotalCount(criteria));
		Assert.assertEquals(
				2,
				opportunityService.findPagableListByCriteria(
						new SearchRequest<OpportunitySearchCriteria>(criteria,
								0, Integer.MAX_VALUE)).size());
	}

	@Test
	@DataSet
	public void testSearchSalesState() {
		OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
		criteria.setSalesStages(new SetSearchField<String>(SearchField.AND,
				new String[] { "1", "2" }));
		criteria.setSaccountid(new NumberSearchField(1));

		Assert.assertEquals(2, opportunityService.getTotalCount(criteria));
		Assert.assertEquals(
				2,
				opportunityService.findPagableListByCriteria(
						new SearchRequest<OpportunitySearchCriteria>(criteria,
								0, Integer.MAX_VALUE)).size());
	}

	@Test
	@DataSet
	public void testSearchAssignUsers() {
		OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
		criteria.setAssignUsers(new SetSearchField<String>(SearchField.AND,
				new String[] { "hai", "linh" }));
		criteria.setSaccountid(new NumberSearchField(1));

		Assert.assertEquals(2, opportunityService.getTotalCount(criteria));
		Assert.assertEquals(
				2,
				opportunityService.findPagableListByCriteria(
						new SearchRequest<OpportunitySearchCriteria>(criteria,
								0, Integer.MAX_VALUE)).size());
	}

	@Test
	@DataSet
	public void testSearchNextStep() {
		OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
		criteria.setNextStep(new StringSearchField(SearchField.AND, "ABC"));
		criteria.setSaccountid(new NumberSearchField(1));

		Assert.assertEquals(1, opportunityService.getTotalCount(criteria));
		Assert.assertEquals(
				1,
				opportunityService.findPagableListByCriteria(
						new SearchRequest<OpportunitySearchCriteria>(criteria,
								0, Integer.MAX_VALUE)).size());
	}
}
