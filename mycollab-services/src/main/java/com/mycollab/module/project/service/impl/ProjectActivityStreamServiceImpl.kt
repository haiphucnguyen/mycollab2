/**
 * mycollab-services - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © 2017 MyCollab (support@mycollab.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * mycollab-services - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © ${project.inceptionYear} MyCollab (support@mycollab.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.project.service.impl

import com.mycollab.common.domain.criteria.ActivityStreamSearchCriteria
import com.mycollab.db.arguments.BasicSearchRequest
import com.mycollab.module.project.dao.ProjectMapperExt
import com.mycollab.module.project.domain.ProjectActivityStream
import com.mycollab.module.project.service.ProjectActivityStreamService
import org.apache.ibatis.session.RowBounds
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProjectActivityStreamServiceImpl(private val projectMapperExt: ProjectMapperExt) : ProjectActivityStreamService {

    override fun getTotalActivityStream(criteria: ActivityStreamSearchCriteria): Int {
        return projectMapperExt.getTotalActivityStream(criteria)
    }

    override fun getProjectActivityStreams(searchRequest: BasicSearchRequest<ActivityStreamSearchCriteria>): List<ProjectActivityStream> {
        return projectMapperExt.getProjectActivityStreams(searchRequest.searchCriteria,
                RowBounds((searchRequest.currentPage - 1) * searchRequest.numberOfItems,
                        searchRequest.numberOfItems))
    }
}
