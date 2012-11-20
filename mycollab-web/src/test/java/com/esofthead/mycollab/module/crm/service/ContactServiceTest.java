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

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.EngroupClassRunner;

@RunWith(EngroupClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:META-INF/spring/audit-context.xml",
		"classpath:META-INF/spring/file-context.xml",
		"classpath:META-INF/spring/crm-context.xml",
		"classpath:META-INF/spring/crm-service-test-context.xml",
		"classpath:META-INF/spring/datasource-test-context.xml" })
public class ContactServiceTest {
	@Autowired
	protected ContactService contactService;

	@DataSet
	@Test
	public void testGetFindByCriteria() {
		Assert.assertEquals(
				1,
				contactService.findPagableListByCriteria(
						new SearchRequest<ContactSearchCriteria>(getCriteria(),
								0, 2)).size());
	}

	@DataSet
	@Test
	public void testGetTotalCount() {
		Assert.assertEquals(1, contactService.getTotalCount(getCriteria()));
	}

	private ContactSearchCriteria getCriteria() {
		ContactSearchCriteria criteria = new ContactSearchCriteria();
		criteria.setAssignUserName(new StringSearchField(SearchField.AND,
				"Duong"));
		criteria.setAssignUser(new StringSearchField(SearchField.AND, "linh"));
		criteria.setOpportunityId(new NumberSearchField(SearchField.AND, 1));
		criteria.setAccountName(new StringSearchField(SearchField.AND, "x"));
		criteria.setContactName(new StringSearchField(SearchField.AND, "Hai"));
		criteria.setSaccountid(new NumberSearchField(SearchField.AND, 1));
		return criteria;
	}
}
