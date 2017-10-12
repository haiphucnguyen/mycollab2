/**
 * Copyright © MyCollab
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.project.service;


import com.mycollab.core.cache.CacheEvict;
import com.mycollab.core.cache.CacheKey;
import com.mycollab.core.cache.Cacheable;
import com.mycollab.db.persistence.service.IService;
import com.mycollab.module.project.domain.AssignWithPredecessors;
import com.mycollab.module.project.domain.TaskPredecessor;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.0.0
 */
public interface GanttAssignmentService extends IService {
    @Cacheable
    List<AssignWithPredecessors> getTaskWithPredecessors(List<Integer> projectIds, @CacheKey Integer sAccountId);

    @CacheEvict
    void massUpdateGanttItems(List<AssignWithPredecessors> ganttItems, @CacheKey Integer sAccountId);

    @CacheEvict void massDeleteGanttItems(List<AssignWithPredecessors> ganttItems, @CacheKey Integer sAccountId);

    @CacheEvict
    void massUpdatePredecessors(Integer taskSourceId, List<TaskPredecessor> predecessors, @CacheKey Integer sAccountId);

    @CacheEvict
    void massDeletePredecessors(List<TaskPredecessor> predecessors, @CacheKey Integer sAccountId);
}
