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
package com.mycollab.module.project.view;

import com.mycollab.module.project.view.milestone.AllMilestoneTimelineWidget;
import com.mycollab.module.project.view.user.ActivityStreamComponent;
import com.mycollab.module.project.view.user.MyProjectListComponent;
import com.mycollab.module.project.view.ticket.TicketOverdueWidget;
import com.mycollab.module.project.view.user.UserUnresolvedAssignmentWidget;
import com.mycollab.vaadin.mvp.AbstractVerticalPageView;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.UIUtils;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.vaadin.shared.ui.MarginInfo;
import org.apache.commons.collections.CollectionUtils;
import org.vaadin.viritin.layouts.MCssLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.4
 */
@ViewComponent
public class UserProjectDashboardViewImpl extends AbstractVerticalPageView implements UserProjectDashboardView {
    @Override
    public void display() {
        removeAllComponents();
        MHorizontalLayout layout = new MHorizontalLayout().withMargin(new MarginInfo(false, false, true, false)).withFullWidth();

        AllMilestoneTimelineWidget milestoneTimelineWidget = new AllMilestoneTimelineWidget();
        TicketOverdueWidget ticketOverdueWidget = new TicketOverdueWidget();
        ActivityStreamComponent activityStreamComponent = new ActivityStreamComponent();
        UserUnresolvedAssignmentWidget unresolvedAssignmentThisWeekWidget = new UserUnresolvedAssignmentWidget();
        UserUnresolvedAssignmentWidget unresolvedAssignmentNextWeekWidget = new UserUnresolvedAssignmentWidget();
        MVerticalLayout leftPanel = new MVerticalLayout(milestoneTimelineWidget,
                unresolvedAssignmentThisWeekWidget, unresolvedAssignmentNextWeekWidget, ticketOverdueWidget)
                .withMargin(new MarginInfo(true, true, false, false)).withFullWidth();

        MVerticalLayout rightPanel = new MVerticalLayout().withMargin(false).withWidth("550px");
        MyProjectListComponent myProjectListComponent = new MyProjectListComponent();

        rightPanel.with(myProjectListComponent, activityStreamComponent);
        layout.with(leftPanel, rightPanel).expand(leftPanel);

        MCssLayout contentWrapper = new MCssLayout().withStyleName(WebUIConstants.CONTENT_WRAPPER);
        addComponent(contentWrapper);
        contentWrapper.addComponent(layout);

        UserDashboardView userDashboardView = UIUtils.getRoot(this, UserDashboardView.class);
        List<Integer> prjKeys = userDashboardView.getInvolvedProjectKeys();
        if (CollectionUtils.isNotEmpty(prjKeys)) {
            activityStreamComponent.showFeeds(prjKeys);
            milestoneTimelineWidget.display();
            myProjectListComponent.displayDefaultProjectsList();
            ticketOverdueWidget.showProjectTasksByStatus(prjKeys);
            unresolvedAssignmentThisWeekWidget.displayUnresolvedAssignmentsThisWeek();
            unresolvedAssignmentNextWeekWidget.displayUnresolvedAssignmentsNextWeek();
        } else {
            unresolvedAssignmentThisWeekWidget.displayNoUnresolvedAssignmentsThisWeek();
            unresolvedAssignmentNextWeekWidget.displayNoUnresolvedAssignmentsNextWeek();
        }
    }
}
