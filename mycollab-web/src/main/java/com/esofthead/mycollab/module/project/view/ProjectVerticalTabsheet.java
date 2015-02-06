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
package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.ui.VerticalTabsheet;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.ui.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public class ProjectVerticalTabsheet extends VerticalTabsheet {
    private static final long serialVersionUID = 1L;

    private static final Map<String, Resource> resources;

    static {
        resources = new HashMap<>();
        resources.put("dashboard", FontAwesome.DASHBOARD);
        resources.put("message", FontAwesome.COMMENT);
        resources.put("milestone", FontAwesome.FLAG_CHECKERED);
        resources.put("task", FontAwesome.TASKS);
        resources.put("page", FontAwesome.FILE);
        resources.put("bug", FontAwesome.BUG);
        resources.put("file", FontAwesome.BRIEFCASE);
        resources.put("risk", FontAwesome.SHIELD);
        resources.put("problem", FontAwesome.EXCLAMATION_TRIANGLE);
        resources.put("time", FontAwesome.CLOCK_O);
        resources.put("standup", FontAwesome.CUBES);
        resources.put("member", FontAwesome.USERS);
    }

    @Override
    protected void setDefaulButtonIcon(Component btn, Boolean selected) {
        ButtonTabImpl btnTabImpl = (ButtonTabImpl) btn;
        String tabId = btnTabImpl.getTabId();

        Resource resource = resources.get(tabId);
        btn.setIcon(resource);
    }
}
