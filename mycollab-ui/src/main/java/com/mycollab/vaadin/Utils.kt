/**
 * mycollab-ui - Parent pom providing dependency and plugin management for applications
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
 * mycollab-ui - Parent pom providing dependency and plugin management for applications
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
package com.mycollab.vaadin

import com.mycollab.configuration.SiteConfiguration
import com.vaadin.server.VaadinRequest
import com.vaadin.server.VaadinServletRequest

/**
 * @author MyCollab Ltd
 * @since 5.0.10
 */
object Utils {
    @JvmStatic fun getSubDomain(request: VaadinRequest): String {
        val servletRequest = request as VaadinServletRequest
        return when {
            SiteConfiguration.isDemandEdition() -> servletRequest.serverName.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
            else -> servletRequest.serverName
        }
    }

    @JvmStatic fun isTablet(request: VaadinRequest): Boolean {
        return try {
            val userAgent = request.getHeader("user-agent").toLowerCase()
            userAgent.contains("ipad")
        } catch (e: Exception) {
            false
        }

    }
}
