/**
 * Engroup - Enterprise Groupware Platform
 * Copyright (C) 2007-2009 eSoftHead Company <engroup@esofthead.com>
 * http://www.esofthead.com
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.esofthead.mycollab.module.crm.service;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.EngroupClassRunner;

@RunWith(EngroupClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:META-INF/spring/audit-context.xml",
		"classpath:META-INF/spring/common-context.xml",
		"classpath:META-INF/spring/file-context.xml",
		"classpath:META-INF/spring/crm-context.xml",
		"classpath:META-INF/spring/crm-service-test-context.xml",
		"classpath:META-INF/spring/datasource-test-context.xml" })
public class LeadServiceTest {
	@Autowired
	protected LeadService leadService;

	@DataSet
	@Test
	public void testSearchByCriteria() {
		Assert.assertEquals(
				1,
				leadService.findPagableListByCriteria(
						new SearchRequest<LeadSearchCriteria>(getCriteria(), 0,
								2)).size());
	}

	@DataSet
	@Test
	public void testGetTotalCounts() {
		Assert.assertEquals(1, leadService.getTotalCount(getCriteria()));
	}

	private LeadSearchCriteria getCriteria() {
		LeadSearchCriteria criteria = new LeadSearchCriteria();
		criteria.setAccountName(new StringSearchField(SearchField.AND, "A"));
		criteria.setCampaignName(new StringSearchField(SearchField.AND, "B"));
		criteria.setLeadName(new StringSearchField(SearchField.AND, "Nguyen"));
		criteria.setReferredBy(new StringSearchField(SearchField.AND, "xy"));
		criteria.setSaccountid(new NumberSearchField(SearchField.AND, 1));
		return criteria;
	}
	
	@Test
	@DataSet
	public void testSearchLeadName() {
		LeadSearchCriteria criteria = new LeadSearchCriteria();
		criteria.setLeadName(new StringSearchField(SearchField.AND, "Nguyen Hai"));
		Assert.assertEquals(2, leadService.getTotalCount(criteria));
		Assert.assertEquals(
				2,
				leadService.findPagableListByCriteria(
						new SearchRequest<LeadSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}
	
	@Test
	@DataSet
	public void testSearchStatuses() {
		LeadSearchCriteria criteria = new LeadSearchCriteria();
		criteria.setStatuses(new SetSearchField<String>(SearchField.AND, new String[]{"New", "Test status"}));
		Assert.assertEquals(2, leadService.getTotalCount(criteria));
		Assert.assertEquals(
				2,
				leadService.findPagableListByCriteria(
						new SearchRequest<LeadSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}
	
	@Test
	@DataSet
	public void testSearchAssignUser() {
		LeadSearchCriteria criteria = new LeadSearchCriteria();
		criteria.setAssignUsers(new SetSearchField<String>(SetSearchField.AND, new String[]{"linh", "Hai"}));
		Assert.assertEquals(2, leadService.getTotalCount(criteria));
		Assert.assertEquals(
				2,
				leadService.findPagableListByCriteria(
						new SearchRequest<LeadSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}
	
	@Test
	@DataSet
	public void testSearchAnyState() {
		LeadSearchCriteria criteria = new LeadSearchCriteria();
		criteria.setAnyState(new StringSearchField(SearchField.AND, "HCM"));
		Assert.assertEquals(2, leadService.getTotalCount(criteria));
		Assert.assertEquals(
				2,
				leadService.findPagableListByCriteria(
						new SearchRequest<LeadSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}
	
	@Test
	@DataSet
	public void testSearchAnyCity() {
		LeadSearchCriteria criteria = new LeadSearchCriteria();
		criteria.setAnyCity(new StringSearchField(SearchField.AND, "HCM"));
		Assert.assertEquals(2, leadService.getTotalCount(criteria));
		Assert.assertEquals(
				2,
				leadService.findPagableListByCriteria(
						new SearchRequest<LeadSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}
	
	@Test
	@DataSet
	public void testSearchAnyPhone() {
		LeadSearchCriteria criteria = new LeadSearchCriteria();
		criteria.setAnyPhone(new StringSearchField(SearchField.AND, "1234"));
		Assert.assertEquals(2, leadService.getTotalCount(criteria));
		Assert.assertEquals(
				2,
				leadService.findPagableListByCriteria(
						new SearchRequest<LeadSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}
	
	@Test
	@DataSet
	public void testSearchSources() {
		LeadSearchCriteria criteria = new LeadSearchCriteria();
		criteria.setSources(new SetSearchField<String>(SearchField.AND, new String[]{"Cold Call", "Employee"}));
		Assert.assertEquals(2, leadService.getTotalCount(criteria));
		Assert.assertEquals(
				2,
				leadService.findPagableListByCriteria(
						new SearchRequest<LeadSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}
	
	@Test
	@DataSet
	public void testSearchAnyCountry() {
		LeadSearchCriteria criteria = new LeadSearchCriteria();
		criteria.setAnyCountry(new StringSearchField(SearchField.AND, "viet nam"));
		Assert.assertEquals(2, leadService.getTotalCount(criteria));
		Assert.assertEquals(
				2,
				leadService.findPagableListByCriteria(
						new SearchRequest<LeadSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}
	
	@Test
	@DataSet
	public void testSearchAnyAddress() {
		LeadSearchCriteria criteria = new LeadSearchCriteria();
		criteria.setAnyAddress(new StringSearchField(SearchField.AND, "abcd"));
		Assert.assertEquals(2, leadService.getTotalCount(criteria));
		Assert.assertEquals(
				2,
				leadService.findPagableListByCriteria(
						new SearchRequest<LeadSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}
	
	@Test
	@DataSet
	public void testSearchAnyEmail() {
		LeadSearchCriteria criteria = new LeadSearchCriteria();
		criteria.setAnyEmail(new StringSearchField(SearchField.AND, "manhlinh@y.co"));
		Assert.assertEquals(2, leadService.getTotalCount(criteria));
		Assert.assertEquals(
				2,
				leadService.findPagableListByCriteria(
						new SearchRequest<LeadSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}
	
	@Test
	@DataSet
	public void testSearchLastname() {
		LeadSearchCriteria criteria = new LeadSearchCriteria();
		criteria.setLastname(new StringSearchField(SearchField.AND, "Nguyen"));
		Assert.assertEquals(1, leadService.getTotalCount(criteria));
		Assert.assertEquals(
				1,
				leadService.findPagableListByCriteria(
						new SearchRequest<LeadSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}
	
	@Test
	@DataSet
	public void testSearchFirstname() {
		LeadSearchCriteria criteria = new LeadSearchCriteria();
		criteria.setFirstname(new StringSearchField(SearchField.AND, "Linh"));
		Assert.assertEquals(1, leadService.getTotalCount(criteria));
		Assert.assertEquals(
				1,
				leadService.findPagableListByCriteria(
						new SearchRequest<LeadSearchCriteria>(criteria, 0,
								Integer.MAX_VALUE)).size());
	}
}
