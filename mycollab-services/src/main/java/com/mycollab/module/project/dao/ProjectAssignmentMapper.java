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

package com.mycollab.module.project.dao;

import com.mycollab.common.domain.GroupItem;
import com.mycollab.core.cache.CacheKey;
import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.module.project.domain.ProjectAssignment;
import com.mycollab.module.project.domain.criteria.ProjectAssignmentSearchCriteria;
import com.mycollab.module.user.domain.BillingAccount;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface ProjectAssignmentMapper extends ISearchableDAO<ProjectAssignmentSearchCriteria> {

    Integer getTotalCountFromRisk(@Param("searchCriteria") ProjectAssignmentSearchCriteria criteria);

    Integer getTotalCountFromBug(@Param("searchCriteria") ProjectAssignmentSearchCriteria criteria);

    Integer getTotalCountFromTask(@Param("searchCriteria") ProjectAssignmentSearchCriteria criteria);

    Integer getTotalCountFromMilestone(@Param("searchCriteria") ProjectAssignmentSearchCriteria criteria);

    List<BillingAccount> getAccountsHasOverdueAssignments(@Param("searchCriteria") ProjectAssignmentSearchCriteria criteria);

    List<Integer> getProjectsHasOverdueAssignments(@Param("searchCriteria") ProjectAssignmentSearchCriteria criteria);

    List<GroupItem> getAssigneeSummary(@Param("searchCriteria") ProjectAssignmentSearchCriteria criteria);

    List<ProjectAssignment> findAssignmentsByCriteria(@Param("searchCriteria") ProjectAssignmentSearchCriteria criteria,
                                                      RowBounds rowBounds);
}
