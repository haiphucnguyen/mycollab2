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
package com.mycollab.vaadin.event

import com.mycollab.reporting.ReportExportType
import com.vaadin.server.StreamResource

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
interface MassItemActionHandler {
    fun onSelect(id: String)

    fun buildStreamResource(exportType: ReportExportType): StreamResource?
}

object ViewItemAction {
    @JvmField val MAIL_ACTION = "mail"
    @JvmField val DELETE_ACTION = "delete"
    @JvmField val MASS_UPDATE_ACTION = "massUpdate"
}