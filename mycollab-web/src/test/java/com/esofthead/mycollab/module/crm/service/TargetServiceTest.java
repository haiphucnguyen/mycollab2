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

import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.domain.criteria.TargetSearchCriteria;
import com.esofthead.test.DataSet;
import com.esofthead.test.EngroupClassRunner;

@RunWith(EngroupClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:META-INF/spring/audit-context.xml",
		"classpath:META-INF/spring/file-context.xml",
		"classpath:META-INF/spring/crm-context.xml",
		"classpath:META-INF/spring/datasource-test-context.xml" })
public class TargetServiceTest {
	@Autowired
	protected TargetService targetService;

	@DataSet
	@Test
	public void testSearchByCriteria() {
		Assert.assertEquals(1,
				targetService.findPagableListByCriteria(getCriteria(), 0, 2)
						.size());
	}

	@DataSet
	@Test
	public void testGetTotalCounts() {
		Assert.assertEquals(1, targetService.getTotalCount(getCriteria()));
	}

	private TargetSearchCriteria getCriteria() {
		TargetSearchCriteria criteria = new TargetSearchCriteria();
		criteria.setAccountName(new StringSearchField(SearchField.AND, "A"));
		criteria.setAssignUserName(new StringSearchField(SearchField.AND, "Duong"));
		criteria.setTargetName(new StringSearchField(SearchField.AND, "Nguyen"));
		return criteria;
	}
}
