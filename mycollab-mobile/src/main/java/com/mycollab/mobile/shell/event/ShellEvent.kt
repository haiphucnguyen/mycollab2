/**
 * mycollab-mobile - Parent pom providing dependency and plugin management for applications
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
package com.mycollab.mobile.shell.event

import com.mycollab.vaadin.event.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
object ShellEvent {
    class GotoLoginView(source: Any) : ApplicationEvent(source)

    class GotoMainPage(source: Any, val data: Any?) : ApplicationEvent(source)

    class GotoCrmModule(source: Any, val data: Any?) : ApplicationEvent(source)

    class GotoProjectModule(source: Any, val data: Any?) : ApplicationEvent(source)

    class GotoUserAccountModule(source: Any, val data: Any?) : ApplicationEvent(source)

    class PushView(source: Any, val data: Any?) : ApplicationEvent(source)

    class NavigateBack(source: Any, val data: Any?) : ApplicationEvent(source)

    class LogOut(source: Any) : ApplicationEvent(source)
}