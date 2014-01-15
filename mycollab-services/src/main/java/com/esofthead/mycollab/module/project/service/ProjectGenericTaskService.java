/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.esofthead.mycollab.module.project.service;

import java.util.List;

import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTaskCount;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;

/**
 * 
 * @author MyCollab Ltd.
 */
public interface ProjectGenericTaskService extends
		ISearchableService<ProjectGenericTaskSearchCriteria> {
	
	List<ProjectGenericTaskCount> findPagableTaskCountListByCriteria(
			SearchRequest<ProjectGenericTaskSearchCriteria> searchRequest);
	
	List<ProjectGenericTask> findPagableBugAndTaskByCriteria(
			SearchRequest<ProjectGenericTaskSearchCriteria> searchRequest);
}
