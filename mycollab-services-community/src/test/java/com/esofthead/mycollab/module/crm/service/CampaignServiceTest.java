/**
 * This file is part of mycollab-services-community.
 *
 * mycollab-services-community is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services-community is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services-community.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.crm.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.MyCollabSpringRunner;
import com.esofthead.mycollab.test.service.ServiceTest;

@RunWith(MyCollabSpringRunner.class)
public class CampaignServiceTest extends ServiceTest {

	@Autowired
	protected CampaignService campaignService;

	@SuppressWarnings("unchecked")
	@DataSet
	@Test
	public void testSearchByCriteria() {
		List<SimpleCampaign> campaigns = campaignService
				.findPagableListByCriteria(new SearchRequest<CampaignSearchCriteria>(
						getCriteria(), 0, Integer.MAX_VALUE));

		assertThat(campaigns.size()).isEqualTo(2);
		assertThat(campaigns).extracting("id", "campaignname", "status")
				.contains(tuple(1, "AAA", "a"), tuple(2, "ABB", "b"));
	}

	@SuppressWarnings("unchecked")
	@DataSet
	@Test
	public void testGetTotalCounts() {
		List<SimpleCampaign> campaigns = campaignService
				.findPagableListByCriteria(new SearchRequest<CampaignSearchCriteria>(
						getCriteria(), 0, Integer.MAX_VALUE));

		assertThat(campaigns.size()).isEqualTo(2);
	}

	private CampaignSearchCriteria getCriteria() {
		CampaignSearchCriteria criteria = new CampaignSearchCriteria();
		criteria.setAssignUser(new StringSearchField(SearchField.AND, "hai79"));
		criteria.setCampaignName(new StringSearchField(SearchField.AND, "A"));
		criteria.setSaccountid(new NumberSearchField(SearchField.AND, 1));
		criteria.setAssignUsers(new SetSearchField<String>(SearchField.AND,
				new String[]{"hai79", "linh"}));
		criteria.setStatuses(new SetSearchField<String>(SearchField.AND,
				new String[]{"a", "b"}));
		criteria.setTypes(new SetSearchField<String>(SearchField.AND,
				new String[]{"a", "b"}));
		return criteria;
	}
}
