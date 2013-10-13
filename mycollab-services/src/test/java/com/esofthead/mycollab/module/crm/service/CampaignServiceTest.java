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

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.MyCollabClassRunner;
import com.esofthead.mycollab.test.service.ServiceTest;
import com.esofthead.mycollab.test.util.DateTimeUtilsForTest;

@RunWith(MyCollabClassRunner.class)
public class CampaignServiceTest extends ServiceTest {

	@Autowired
	protected CampaignService campaignService;

	@DataSet
	@Test
	public void testSearchByCriteria() {
		Assert.assertEquals(
				10,
				campaignService.findPagableListByCriteria(
						new SearchRequest<CampaignSearchCriteria>(
								getCriteria(), 0, Integer.MAX_VALUE)).size());
	}

	@DataSet
	@Test
	public void testGetTotalCounts() {
		Assert.assertEquals(10, campaignService.getTotalCount(getCriteria()));
	}

	private CampaignSearchCriteria getCriteria() {
		CampaignSearchCriteria criteria = new CampaignSearchCriteria();
		criteria.setAssignUserName(new StringSearchField(SearchField.AND, "Hai"));
		criteria.setCampaignName(new StringSearchField(SearchField.AND, "A"));
		criteria.setSaccountid(new NumberSearchField(SearchField.AND, 1));
		criteria.setAssignUsers(new SetSearchField<String>(SearchField.AND,
				new String[] { "hai79", "linh" }));
		criteria.setStatuses(new SetSearchField<String>(SearchField.AND,
				new String[] { "a", "b" }));
		criteria.setTypes(new SetSearchField<String>(SearchField.AND,
				new String[] { "a", "b" }));
		return criteria;
	}

	@Test
	@DataSet
	public void testSearchStartDateRangeNext7Days() {
		CampaignSearchCriteria criteria = new CampaignSearchCriteria();
		Date startDate = DateTimeUtilsForTest.getDateByString("2012-12-20");
		Date endDate = DateTimeUtilsForTest.subtractOrAddDayDuration(
				DateTimeUtilsForTest.getDateByString("2012-12-21"), 7);
		criteria.setStartDateRange(new RangeDateSearchField(startDate, endDate));
		criteria.setSaccountid(new NumberSearchField(1));

		Assert.assertEquals(1, campaignService.getTotalCount(criteria));
		Assert.assertEquals(
				1,
				campaignService.findPagableListByCriteria(
						new SearchRequest<CampaignSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}

	@Test
	@DataSet
	public void testSearchStartDateRangeLast7Days() {
		CampaignSearchCriteria criteria = new CampaignSearchCriteria();
		Date startDate = DateTimeUtilsForTest.subtractOrAddDayDuration(
				DateTimeUtilsForTest.getDateByString("2012-12-21"), -7);
		Date endDate = DateTimeUtilsForTest.getDateByString("2012-12-21");
		criteria.setStartDateRange(new RangeDateSearchField(startDate, endDate));
		criteria.setSaccountid(new NumberSearchField(1));

		Assert.assertEquals(4, campaignService.getTotalCount(criteria));
		Assert.assertEquals(
				4,
				campaignService.findPagableListByCriteria(
						new SearchRequest<CampaignSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}

	@Test
	@DataSet
	public void testSearchEndDateLessThanEqual() {
		CampaignSearchCriteria criteria = new CampaignSearchCriteria();
		Date startDate = DateTimeUtilsForTest.getDateByString("2012-12-21");
		criteria.setEndDate(new DateSearchField(SearchField.AND,
				DateSearchField.LESSTHANEQUAL, startDate));
		criteria.setSaccountid(new NumberSearchField(1));
		Assert.assertEquals(5, campaignService.getTotalCount(criteria));
		Assert.assertEquals(
				5,
				campaignService.findPagableListByCriteria(
						new SearchRequest<CampaignSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}

	@Test
	@DataSet
	public void testSearchEndDateLessThan() {
		CampaignSearchCriteria criteria = new CampaignSearchCriteria();
		Date startDate = DateTimeUtilsForTest.getDateByString("2012-12-21");
		criteria.setEndDate(new DateSearchField(SearchField.AND,
				DateSearchField.LESSTHAN, startDate));
		criteria.setSaccountid(new NumberSearchField(1));
		Assert.assertEquals(4, campaignService.getTotalCount(criteria));
		Assert.assertEquals(
				4,
				campaignService.findPagableListByCriteria(
						new SearchRequest<CampaignSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}

	@Test
	@DataSet
	public void testSearchEndDateGreaterThanEqual() {
		CampaignSearchCriteria criteria = new CampaignSearchCriteria();
		Date startDate = DateTimeUtilsForTest.getDateByString("2012-12-20");
		criteria.setEndDate(new DateSearchField(SearchField.AND,
				DateSearchField.GREATERTHANEQUAL, startDate));
		criteria.setSaccountid(new NumberSearchField(1));

		Assert.assertEquals(7, campaignService.getTotalCount(criteria));
		Assert.assertEquals(
				7,
				campaignService.findPagableListByCriteria(
						new SearchRequest<CampaignSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}

	@Test
	@DataSet
	public void testSearchEndDateGreaterThan() {
		CampaignSearchCriteria criteria = new CampaignSearchCriteria();
		Date startDate = DateTimeUtilsForTest.getDateByString("2012-12-20");
		criteria.setEndDate(new DateSearchField(SearchField.AND,
				DateSearchField.GREATERTHAN, startDate));
		criteria.setSaccountid(new NumberSearchField(1));

		Assert.assertEquals(6, campaignService.getTotalCount(criteria));
		Assert.assertEquals(
				6,
				campaignService.findPagableListByCriteria(
						new SearchRequest<CampaignSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}

	@Test
	@DataSet
	public void testSearchEndDateNotEqual() {
		CampaignSearchCriteria criteria = new CampaignSearchCriteria();
		Date startDate = DateTimeUtilsForTest.getDateByString("2012-12-20");
		criteria.setEndDate(new DateSearchField(SearchField.AND,
				DateSearchField.NOTEQUAL, startDate));
		criteria.setSaccountid(new NumberSearchField(1));

		Assert.assertEquals(9, campaignService.getTotalCount(criteria));
		Assert.assertEquals(
				9,
				campaignService.findPagableListByCriteria(
						new SearchRequest<CampaignSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}

	@Test
	@DataSet
	public void testSearchEndDateEqual() {
		CampaignSearchCriteria criteria = new CampaignSearchCriteria();
		Date startDate = DateTimeUtilsForTest.getDateByString("2012-12-20");
		criteria.setEndDate(new DateSearchField(SearchField.AND,
				DateSearchField.EQUAL, startDate));
		criteria.setSaccountid(new NumberSearchField(1));

		Assert.assertEquals(1, campaignService.getTotalCount(criteria));
		Assert.assertEquals(
				1,
				campaignService.findPagableListByCriteria(
						new SearchRequest<CampaignSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}

	@Test
	@DataSet
	public void testSearchEndDateBetween() {
		CampaignSearchCriteria criteria = new CampaignSearchCriteria();
		Date from = DateTimeUtilsForTest.getDateByString("2012-12-20");
		Date to = DateTimeUtilsForTest.getDateByString("2012-12-21");
		criteria.setEndDateRange(new RangeDateSearchField(from, to));
		criteria.setSaccountid(new NumberSearchField(1));

		Assert.assertEquals(2, campaignService.getTotalCount(criteria));
		Assert.assertEquals(
				2,
				campaignService.findPagableListByCriteria(
						new SearchRequest<CampaignSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}

	@Test
	@DataSet
	public void testSearchStartDateLessThanEqual() {
		CampaignSearchCriteria criteria = new CampaignSearchCriteria();
		Date startDate = DateTimeUtilsForTest.getDateByString("2012-12-16");
		criteria.setStartDate(new DateSearchField(SearchField.AND,
				DateSearchField.LESSTHANEQUAL, startDate));
		criteria.setSaccountid(new NumberSearchField(1));

		Assert.assertEquals(5, campaignService.getTotalCount(criteria));
		Assert.assertEquals(
				5,
				campaignService.findPagableListByCriteria(
						new SearchRequest<CampaignSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}

	@Test
	@DataSet
	public void testSearchStartDateLessThan() {
		CampaignSearchCriteria criteria = new CampaignSearchCriteria();
		Date startDate = DateTimeUtilsForTest.getDateByString("2012-12-16");
		criteria.setStartDate(new DateSearchField(SearchField.AND,
				DateSearchField.LESSTHAN, startDate));
		criteria.setSaccountid(new NumberSearchField(1));

		Assert.assertEquals(4, campaignService.getTotalCount(criteria));
		Assert.assertEquals(
				4,
				campaignService.findPagableListByCriteria(
						new SearchRequest<CampaignSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}

	@Test
	@DataSet
	public void testSearchStartDateGreaterThanEqual() {
		CampaignSearchCriteria criteria = new CampaignSearchCriteria();
		Date startDate = DateTimeUtilsForTest.getDateByString("2012-12-15");
		criteria.setStartDate(new DateSearchField(SearchField.AND,
				DateSearchField.GREATERTHANEQUAL, startDate));
		criteria.setSaccountid(new NumberSearchField(1));

		Assert.assertEquals(7, campaignService.getTotalCount(criteria));
		Assert.assertEquals(
				7,
				campaignService.findPagableListByCriteria(
						new SearchRequest<CampaignSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}

	@Test
	@DataSet
	public void testSearchStartDateGreaterThan() {
		CampaignSearchCriteria criteria = new CampaignSearchCriteria();
		Date startDate = DateTimeUtilsForTest.getDateByString("2012-12-15");
		criteria.setStartDate(new DateSearchField(SearchField.AND,
				DateSearchField.GREATERTHAN, startDate));
		criteria.setSaccountid(new NumberSearchField(1));

		Assert.assertEquals(6, campaignService.getTotalCount(criteria));
		Assert.assertEquals(
				6,
				campaignService.findPagableListByCriteria(
						new SearchRequest<CampaignSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}

	@Test
	@DataSet
	public void testSearchStartDateNotEqual() {
		CampaignSearchCriteria criteria = new CampaignSearchCriteria();
		Date startDate = DateTimeUtilsForTest.getDateByString("2012-12-15");
		criteria.setStartDate(new DateSearchField(SearchField.AND,
				DateSearchField.NOTEQUAL, startDate));
		criteria.setSaccountid(new NumberSearchField(1));

		Assert.assertEquals(9, campaignService.getTotalCount(criteria));
		Assert.assertEquals(
				9,
				campaignService.findPagableListByCriteria(
						new SearchRequest<CampaignSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}

	@Test
	@DataSet
	public void testSearchStartDateEqual() {
		CampaignSearchCriteria criteria = new CampaignSearchCriteria();
		Date startDate = DateTimeUtilsForTest.getDateByString("2012-12-15");
		criteria.setStartDate(new DateSearchField(SearchField.AND,
				DateSearchField.EQUAL, startDate));
		criteria.setSaccountid(new NumberSearchField(1));

		Assert.assertEquals(1, campaignService.getTotalCount(criteria));
		Assert.assertEquals(
				1,
				campaignService.findPagableListByCriteria(
						new SearchRequest<CampaignSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}

	@Test
	@DataSet
	public void testSearchStartDateBetween() {
		CampaignSearchCriteria criteria = new CampaignSearchCriteria();
		Date from = DateTimeUtilsForTest.getDateByString("2012-12-15");
		Date to = DateTimeUtilsForTest.getDateByString("2012-12-17");
		criteria.setStartDateRange(new RangeDateSearchField(from, to));
		criteria.setSaccountid(new NumberSearchField(1));

		Assert.assertEquals(3, campaignService.getTotalCount(criteria));
		Assert.assertEquals(
				3,
				campaignService.findPagableListByCriteria(
						new SearchRequest<CampaignSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}
}
