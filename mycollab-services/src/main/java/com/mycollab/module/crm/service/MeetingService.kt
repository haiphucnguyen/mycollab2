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
package com.mycollab.module.crm.service

import com.mycollab.core.cache.CacheKey
import com.mycollab.core.cache.Cacheable
import com.mycollab.db.persistence.service.IDefaultService
import com.mycollab.module.crm.domain.MeetingWithBLOBs
import com.mycollab.module.crm.domain.SimpleMeeting
import com.mycollab.module.crm.domain.criteria.MeetingSearchCriteria

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
interface MeetingService : IDefaultService<Int, MeetingWithBLOBs, MeetingSearchCriteria> {

    @Cacheable
    fun findById(meetingId: Int?, @CacheKey sAccountId: Int?): SimpleMeeting
}
