/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.crm.events;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@SuppressWarnings("serial")
public class AccountEvent {

    public static class Save extends ApplicationEvent {

        public Save(Object source, Object data) {
            super(source, data);
        }
    }

    public static class Search extends ApplicationEvent {

        public Search(Object source, Object data) {
            super(source, data);
        }
    }

    public static class GotoList extends ApplicationEvent {

        public GotoList(Object source, Object data) {
            super(source, data);
        }
    }

    public static class GotoAdd extends ApplicationEvent {

        public GotoAdd(Object source, Object data) {
            super(source, data);
        }
    }

    public static class GotoRead extends ApplicationEvent {

        public GotoRead(Object source, Object data) {
            super(source, data);
        }
    }

    public static class GotoEdit extends ApplicationEvent {

        public GotoEdit(Object source, Object data) {
            super(source, data);
        }
    }
}
