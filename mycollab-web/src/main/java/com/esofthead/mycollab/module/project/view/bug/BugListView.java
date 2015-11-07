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
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.desktop.ui.ListView;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface BugListView extends ListView<BugSearchCriteria, SimpleBug> {

    String GROUP_DUE_DATE = "Due Date";
    String GROUP_START_DATE = "Start Date";
    String GROUP_CREATED_DATE = "Created Date";
    String PLAIN_LIST = "Plain";

    void queryBug(BugSearchCriteria searchCriteria);
}
