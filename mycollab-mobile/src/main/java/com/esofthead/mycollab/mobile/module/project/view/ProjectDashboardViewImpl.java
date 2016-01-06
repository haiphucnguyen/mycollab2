/**
 * This file is part of mycollab-mobile.
 *
 * mycollab-mobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-mobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-mobile.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.mobile.module.project.view;

import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.module.project.events.*;
import com.esofthead.mycollab.mobile.ui.AbstractMobilePageView;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 4.4.0
 */

@ViewComponent
public class ProjectDashboardViewImpl extends AbstractMobilePageView implements ProjectDashboardView {
    private static final long serialVersionUID = 2364544271302929730L;

    private final CssLayout mainLayout;

    public ProjectDashboardViewImpl() {
        super();
        this.setCaption(AppContext.getMessage(ProjectCommonI18nEnum.VIEW_DASHBOARD));
        this.setRightComponent(new Label("AAAAAAAAAAAAAA"));
        mainLayout = new CssLayout();
        mainLayout.setSizeFull();
        mainLayout.setStyleName("project-dashboard");
        this.setContent(mainLayout);
    }

    @Override
    public void displayDashboard() {
        mainLayout.removeAllComponents();
        SimpleProject currentProject = CurrentProjectVariables.getProject();
        VerticalLayout projectInfo = new VerticalLayout();
        projectInfo.setStyleName("project-info-layout");
        projectInfo.setWidth("100%");
        projectInfo.setDefaultComponentAlignment(Alignment.TOP_CENTER);

        ELabel projectIcon = ELabel.h2(FontAwesome.BUILDING_O.getHtml());
        projectIcon.setStyleName("project-icon");
        projectIcon.setContentMode(ContentMode.HTML);
        projectIcon.setWidthUndefined();
        projectInfo.addComponent(projectIcon);

        Label projectName = new Label(StringUtils.trim(currentProject.getName(), 50, true));
        projectName.setWidth("100%");
        projectName.setStyleName("project-name");
        projectInfo.addComponent(projectName);

        mainLayout.addComponent(projectInfo);

        VerticalComponentGroup btnGroup = new VerticalComponentGroup();

        NavigationButton messageBtn = new NavigationButton(AppContext.getMessage(ProjectCommonI18nEnum.VIEW_MESSAGE));
        messageBtn.addClickListener(new NavigationButton.NavigationButtonClickListener() {
            @Override
            public void buttonClick(NavigationButton.NavigationButtonClickEvent navigationButtonClickEvent) {
                EventBusFactory.getInstance().post(new MessageEvent.GotoList(this, null));
            }
        });
        btnGroup.addComponent(messageBtn);

        NavigationButton milestoneBtn = new NavigationButton(AppContext.getMessage(ProjectCommonI18nEnum.VIEW_MILESTONE));
        milestoneBtn.addClickListener(new NavigationButton.NavigationButtonClickListener() {
            @Override
            public void buttonClick(NavigationButton.NavigationButtonClickEvent navigationButtonClickEvent) {
                EventBusFactory.getInstance().post(new MilestoneEvent.GotoList(this, null));
            }
        });
        btnGroup.addComponent(milestoneBtn);

        NavigationButton taskBtn = new NavigationButton(AppContext.getMessage(ProjectCommonI18nEnum.VIEW_TASK));
        taskBtn.addClickListener(new NavigationButton.NavigationButtonClickListener() {
            @Override
            public void buttonClick(NavigationButton.NavigationButtonClickEvent navigationButtonClickEvent) {
                EventBusFactory.getInstance().post(new TaskEvent.GotoList(this, null));
            }
        });
        btnGroup.addComponent(taskBtn);

        NavigationButton bugBtn = new NavigationButton(AppContext.getMessage(ProjectCommonI18nEnum.VIEW_BUG));
        bugBtn.addClickListener(new NavigationButton.NavigationButtonClickListener() {
            @Override
            public void buttonClick(NavigationButton.NavigationButtonClickEvent navigationButtonClickEvent) {
                EventBusFactory.getInstance().post(new BugEvent.GotoList(this, null));
            }
        });
        btnGroup.addComponent(bugBtn);

        NavigationButton userBtn = new NavigationButton(AppContext.getMessage(ProjectCommonI18nEnum.VIEW_USERS));
        userBtn.addClickListener(new NavigationButton.NavigationButtonClickListener() {
            @Override
            public void buttonClick(NavigationButton.NavigationButtonClickEvent navigationButtonClickEvent) {
                EventBusFactory.getInstance().post(new ProjectMemberEvent.GotoList(this, null));
            }
        });
        btnGroup.addComponent(userBtn);

        mainLayout.addComponent(btnGroup);
    }
}
