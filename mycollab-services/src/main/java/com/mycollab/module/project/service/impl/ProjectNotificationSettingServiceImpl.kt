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
package com.mycollab.module.project.service.impl

import com.mycollab.common.NotificationType
import com.mycollab.core.cache.CacheKey
import com.mycollab.db.persistence.ICrudGenericDAO
import com.mycollab.db.persistence.service.DefaultCrudService
import com.mycollab.module.project.dao.ProjectNotificationSettingMapper
import com.mycollab.module.project.domain.ProjectNotificationSetting
import com.mycollab.module.project.domain.ProjectNotificationSettingExample
import com.mycollab.module.project.service.ProjectNotificationSettingService
import org.springframework.stereotype.Service

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Service
class ProjectNotificationSettingServiceImpl(private val projectNotificationSettingMapper: ProjectNotificationSettingMapper) : DefaultCrudService<Int, ProjectNotificationSetting>(), ProjectNotificationSettingService {


    override val crudMapper: ICrudGenericDAO<Int, ProjectNotificationSetting>
        get() = projectNotificationSettingMapper as ICrudGenericDAO<Int, ProjectNotificationSetting>

    override fun findNotification(username: String, projectId: Int?, @CacheKey sAccountId: Int?): ProjectNotificationSetting {
        val ex = ProjectNotificationSettingExample()
        ex.createCriteria().andUsernameEqualTo(username).andProjectidEqualTo(projectId).andSaccountidEqualTo(sAccountId)
        val settings = projectNotificationSettingMapper.selectByExample(ex)
        if (settings.isNotEmpty()) {
            return settings[0]
        } else {
            val setting = ProjectNotificationSetting()
            setting.level = NotificationType.Default.name
            setting.projectid = projectId
            setting.saccountid = sAccountId
            setting.username = username
            return setting
        }
    }

    override fun findNotifications(projectId: Int?, @CacheKey sAccountId: Int?): List<ProjectNotificationSetting> {
        val ex = ProjectNotificationSettingExample()
        ex.createCriteria().andProjectidEqualTo(projectId).andSaccountidEqualTo(sAccountId)
        return projectNotificationSettingMapper.selectByExample(ex)
    }

}
