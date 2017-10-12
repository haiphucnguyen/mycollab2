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
import com.mycollab.db.persistence.service.IDefaultService;
import com.mycollab.module.project.domain.ItemTimeLogging;
import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface ItemTimeLoggingService extends IDefaultService<Integer, ItemTimeLogging, ItemTimeLoggingSearchCriteria> {

    @Cacheable
    Double getTotalHoursByCriteria(@CacheKey ItemTimeLoggingSearchCriteria criteria);

    @CacheEvict
    void batchSaveTimeLogging(List<ItemTimeLogging> timeLoggings, @CacheKey Integer sAccountId);

    @Cacheable
    Double getTotalBillableHoursByMilestone(Integer milestoneId, @CacheKey Integer sAccountId);

    @Cacheable
    Double getTotalNonBillableHoursByMilestone(Integer milestoneId, @CacheKey Integer sAccountId);

    @Cacheable
    Double getRemainHoursByMilestone(Integer milestoneId, @CacheKey Integer sAccountId);

    @Cacheable
    Double getTotalBillableHoursByComponent(Integer componentId, @CacheKey Integer sAccountId);

    @Cacheable
    Double getTotalNonBillableHoursByComponent(Integer componentId, @CacheKey Integer sAccountId);

    @Cacheable
    Double getRemainHoursByComponent(Integer componentId, @CacheKey Integer sAccountId);

    @Cacheable
    Double getTotalBillableHoursByVersion(Integer versionId, @CacheKey Integer sAccountId);

    @Cacheable
    Double getTotalNonBillableHoursByVersion(Integer versionId, @CacheKey Integer sAccountId);

    @Cacheable
    Double getRemainHoursByVersion(Integer versionId, @CacheKey Integer sAccountId);
}
