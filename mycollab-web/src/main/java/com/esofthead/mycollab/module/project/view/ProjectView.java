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

import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.PageView;
import com.vaadin.ui.Component;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public interface ProjectView extends PageView {

    void constructProjectHeaderPanel(SimpleProject project, PageActionChain pageActionChain);

    void gotoUsersAndGroup(ScreenData<?> data);

    void gotoMilestoneView(ScreenData<?> data);
    
    void gotoStandupReportView(ScreenData<?> data);

    void gotoRiskView(ScreenData<?> data);

    void gotoBugView(ScreenData<?> data);

    void gotoTaskList(ScreenData<?> data);

    Component gotoSubView(String name);
}
