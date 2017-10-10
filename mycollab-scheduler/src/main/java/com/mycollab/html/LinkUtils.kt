/**
 * mycollab-scheduler - Parent pom providing dependency and plugin management for applications
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
 * mycollab-scheduler - Parent pom providing dependency and plugin management for applications
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
package com.mycollab.html

import com.hp.gagawa.java.elements.Img
import com.mycollab.module.file.service.AbstractStorageService
import com.mycollab.spring.AppContextUtil

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
object LinkUtils {
    @JvmStatic
    fun storageService() = AppContextUtil.getSpringBean(AbstractStorageService::class.java)

    @JvmStatic
    fun newAvatar(avatarId: String) = Img("", storageService().getAvatarPath(avatarId, 16)).setWidth("16").
            setHeight("16").setStyle("display: inline-block; vertical-align: top;").setCSSClass("circle-box")

    @JvmStatic
    fun accountLogoPath(accountId: Int, logoId: String?) = if (logoId == null) storageService().generateAssetRelativeLink("icons/logo.png") else
        storageService().getLogoPath(accountId, logoId, 150)
}