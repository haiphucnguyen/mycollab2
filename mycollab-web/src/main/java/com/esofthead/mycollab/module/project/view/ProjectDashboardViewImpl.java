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

import com.esofthead.mycollab.module.project.view.milestone.AllMilestoneTimelineWidget;
import com.esofthead.mycollab.module.project.view.user.ActivityStreamComponent;
import com.esofthead.mycollab.module.project.view.user.MyProjectListComponent;
import com.esofthead.mycollab.module.project.view.user.TaskStatusComponent;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.UIUtils;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.CssLayout;
import org.apache.commons.collections.CollectionUtils;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.4
 */
@ViewComponent
public class ProjectDashboardViewImpl extends AbstractPageView implements ProjectDashboardView {
    @Override
    public void display() {
        removeAllComponents();
        MHorizontalLayout layout = new MHorizontalLayout().withMargin(new MarginInfo(false, false, true, false)).withWidth("100%");

        AllMilestoneTimelineWidget milestoneTimelineWidget = new AllMilestoneTimelineWidget();

        ActivityStreamComponent activityStreamComponent = new ActivityStreamComponent();
        MVerticalLayout leftPanel = new MVerticalLayout().withSpacing(false).withMargin(new MarginInfo(true,
                true, false, false)).withWidth("100%").with(milestoneTimelineWidget, activityStreamComponent);

        MVerticalLayout rightPanel = new MVerticalLayout().withMargin(false).withWidth("550px");
        MyProjectListComponent myProjectListComponent = new MyProjectListComponent();
        TaskStatusComponent taskStatusComponent = new TaskStatusComponent();
        rightPanel.with(myProjectListComponent, taskStatusComponent);
        layout.with(leftPanel, rightPanel).expand(leftPanel);

        CssLayout contentWrapper = new CssLayout();
        contentWrapper.setWidth("100%");
        contentWrapper.addStyleName("content-wrapper");
        addComponent(contentWrapper);
        contentWrapper.addComponent(layout);

        UserDashboardView userDashboardView = UIUtils.getRoot(this, UserDashboardView.class);
        List<Integer> prjKeys = userDashboardView.getInvoledProjKeys();
        if (CollectionUtils.isNotEmpty(prjKeys)) {
            activityStreamComponent.showFeeds(prjKeys);
            milestoneTimelineWidget.display();
            myProjectListComponent.displayDefaultProjectsList();
            taskStatusComponent.showProjectTasksByStatus(prjKeys);
        }
    }
}
