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
package com.mycollab.module.project.service.impl;

import com.mycollab.common.domain.GroupItem;
import com.mycollab.core.cache.CacheKey;
import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.db.persistence.service.DefaultSearchService;
import com.mycollab.module.project.dao.ProjectAssignmentMapper;
import com.mycollab.module.project.domain.ProjectAssignment;
import com.mycollab.module.project.domain.criteria.ProjectAssignmentSearchCriteria;
import com.mycollab.module.project.service.ProjectAssignmentService;
import com.mycollab.module.user.domain.BillingAccount;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public abstract class AbstractProjectAssignmentServiceImpl extends DefaultSearchService<ProjectAssignmentSearchCriteria>
        implements ProjectAssignmentService {

    @Autowired
    private ProjectAssignmentMapper projectAssignmentMapper;

    @Override
    public ISearchableDAO<ProjectAssignmentSearchCriteria> getSearchMapper() {
        return projectAssignmentMapper;
    }

    @Override
    public Integer getTotalCount(ProjectAssignmentSearchCriteria criteria) {
        return projectAssignmentMapper.getTotalCountFromRisk(criteria)
                + projectAssignmentMapper.getTotalCountFromBug(criteria)
                + projectAssignmentMapper.getTotalCountFromTask(criteria)
                + projectAssignmentMapper.getTotalCountFromMilestone(criteria);
    }

    @Override
    public Integer getTotalAssignmentsCount(@CacheKey ProjectAssignmentSearchCriteria criteria) {
        return projectAssignmentMapper.getTotalCountFromRisk(criteria)
                + projectAssignmentMapper.getTotalCountFromBug(criteria)
                + projectAssignmentMapper.getTotalCountFromTask(criteria);
    }

    @Override
    public List<BillingAccount> getAccountsHasOverdueAssignments(ProjectAssignmentSearchCriteria searchCriteria) {
        return projectAssignmentMapper.getAccountsHasOverdueAssignments(searchCriteria);
    }

    @Override
    public List<Integer> getProjectsHasOverdueAssignments(ProjectAssignmentSearchCriteria searchCriteria) {
        return projectAssignmentMapper.getProjectsHasOverdueAssignments(searchCriteria);
    }

    @Override
    public ProjectAssignment findAssignment(String type, Integer typeId) {
        ProjectAssignmentSearchCriteria searchCriteria = new ProjectAssignmentSearchCriteria();
        searchCriteria.setTypes(new SetSearchField<>(type));
        searchCriteria.setTypeIds(new SetSearchField<>(typeId));
        List<ProjectAssignment> assignments = findAbsoluteListByCriteria(searchCriteria, 0, 1);
        return (assignments.size() > 0) ? assignments.get(0) : null;
    }

    @Override
    public List<GroupItem> getAssigneeSummary(@CacheKey ProjectAssignmentSearchCriteria criteria) {
        return projectAssignmentMapper.getAssigneeSummary(criteria);
    }

    @Override
    public List findAssignmentsByCriteria(@CacheKey BasicSearchRequest<ProjectAssignmentSearchCriteria> searchRequest) {
        return projectAssignmentMapper.findAssignmentsByCriteria(searchRequest.getSearchCriteria(),
                new RowBounds((searchRequest.getCurrentPage() - 1) * searchRequest.getNumberOfItems(),
                        searchRequest.getNumberOfItems()));
    }
}
