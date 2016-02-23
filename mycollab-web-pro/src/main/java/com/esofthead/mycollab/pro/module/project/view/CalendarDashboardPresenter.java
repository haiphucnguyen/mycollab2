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
package com.esofthead.mycollab.pro.module.project.view;

import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.module.project.view.ICalendarDashboardPresenter;
import com.esofthead.mycollab.module.project.view.ICalendarDashboardView;
import com.esofthead.mycollab.module.project.view.ProjectModule;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.2.1
 */
public class CalendarDashboardPresenter extends AbstractPresenter<ICalendarDashboardView> implements ICalendarDashboardPresenter {
    public CalendarDashboardPresenter() {
        super(ICalendarDashboardView.class);
    }

    @Override
    protected void postInitView() {
        HasSearchHandlers<ProjectGenericTaskSearchCriteria> searchHandlers = view.getSearchHandlers();
        if (searchHandlers != null) {
            searchHandlers.addSearchHandler(new SearchHandler<ProjectGenericTaskSearchCriteria>() {
                @Override
                public void onSearch(ProjectGenericTaskSearchCriteria criteria) {
                    view.queryAssignments(criteria);
                }
            });
        }
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        ProjectModule prjContainer = (ProjectModule) container;
        prjContainer.removeAllComponents();
        prjContainer.with(view).withAlign(view, Alignment.TOP_CENTER);
        view.display();

        AppContext.addFragment("project/calendar", "Calendar");
    }
}
