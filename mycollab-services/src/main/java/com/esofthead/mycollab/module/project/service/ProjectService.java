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
package com.esofthead.mycollab.module.project.service;

import java.util.List;

import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.common.domain.criteria.MonitorSearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.project.domain.FollowingTicket;
import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.domain.ProjectActivityStream;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;

public interface ProjectService extends
		IDefaultService<Integer, Project, ProjectSearchCriteria> {

	@Cacheable
	int getTotalActivityStream(ActivityStreamSearchCriteria criteria);

	@Cacheable
	List<Integer> getUserProjectKeys(String username,
			@CacheKey Integer sAccountId);

	@Cacheable
	List<ProjectActivityStream> getProjectActivityStreams(
			SearchRequest<ActivityStreamSearchCriteria> searchRequest);

	@Cacheable
	SimpleProject findById(int projectId, @CacheKey Integer sAccountId);

	String getSubdomainOfProject(int projectId);

	@Cacheable
	int getTotalFollowingTickets(MonitorSearchCriteria searchRequest);

	@Cacheable
	List<FollowingTicket> getProjectFollowingTickets(
			SearchRequest<MonitorSearchCriteria> searchRequest);
}
