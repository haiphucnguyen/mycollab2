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

import com.esofthead.mycollab.common.domain.criteria.MonitorSearchCriteria;
import com.esofthead.mycollab.common.i18n.FollowerI18nEnum;
import com.esofthead.mycollab.common.service.MonitorItemService;
import com.esofthead.mycollab.configuration.StorageManager;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.html.DivLessFormatter;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.ProjectGenericItem;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericItemSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectGenericItemService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.module.project.view.user.ActivityStreamComponent;
import com.esofthead.mycollab.module.project.view.user.MyProjectListComponent;
import com.esofthead.mycollab.module.project.view.user.TaskStatusComponent;
import com.esofthead.mycollab.module.user.AccountLinkGenerator;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.utils.TooltipHelper;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractLazyPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.*;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Img;
import com.hp.gagawa.java.elements.Text;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

import java.util.List;
import java.util.UUID;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class UserDashboardViewImpl extends AbstractLazyPageView implements UserDashboardView {
    private static final long serialVersionUID = 1L;

    private LabelLink followingTicketsLink;
    private List<Integer> prjKeys;

    public UserDashboardViewImpl() {
        this.withMargin(false).withWidth("100%");
    }

    @Override
    protected void displayView() {
        removeAllComponents();

        CssLayout headerWrapper = setupHeader();
        addComponent(headerWrapper);

        MHorizontalLayout layout = new MHorizontalLayout().withMargin(new MarginInfo(false, false, true, false)).withWidth("100%");

        ActivityStreamComponent activityStreamComponent = new ActivityStreamComponent();
        MVerticalLayout leftPanel = new MVerticalLayout().withSpacing(false).withMargin(new MarginInfo(false,
                true, false, false)).withWidth("100%").with(activityStreamComponent);

        MVerticalLayout rightPanel = new MVerticalLayout().withMargin(false).withWidth("565px");
        MyProjectListComponent myProjectListComponent = new MyProjectListComponent();
        TaskStatusComponent taskStatusComponent = new TaskStatusComponent();
        rightPanel.with(myProjectListComponent, taskStatusComponent);
        layout.with(leftPanel, rightPanel).expand(leftPanel);

        CssLayout contentWrapper = new CssLayout();
        contentWrapper.setWidth("100%");
        contentWrapper.addStyleName("content-wrapper");
        addComponent(contentWrapper);
        contentWrapper.addComponent(layout);

        ProjectService prjService = ApplicationContextUtil.getSpringBean(ProjectService.class);
        prjKeys = prjService.getProjectKeysUserInvolved(AppContext.getUsername(), AppContext.getAccountId());
        if (CollectionUtils.isNotEmpty(prjKeys)) {
            activityStreamComponent.showFeeds(prjKeys);
            myProjectListComponent.displayDefaultProjectsList();
            taskStatusComponent.showProjectTasksByStatus(prjKeys);
            displayFollowingTicketsCount();
        }

        if (AppContext.canBeYes(RolePermissionCollections.CREATE_NEW_PROJECT)) {
            int countActiveProjects = prjService.getTotalActiveProjectsOfInvolvedUsers(AppContext.getUsername(), AppContext.getAccountId());
            if (countActiveProjects == 0) {
                UI.getCurrent().addWindow(new AskCreateNewProjectWindow());
            }
        }
    }

    private CssLayout setupHeader() {
        CssLayout headerWrapper = new CssLayout();
        headerWrapper.setWidth("100%");
        headerWrapper.setStyleName("projectfeed-hdr-wrapper");

        MHorizontalLayout header = new MHorizontalLayout().withWidth("100%");
        header.addStyleName("projectfeed-hdr");

        Button avatar = UserAvatarControlFactory.createUserAvatarEmbeddedButton(AppContext.getUserAvatarId(), 64);
        avatar.addClickListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent event) {
                String userFullLinkStr = AccountLinkGenerator.generatePreviewFullUserLink(AppContext.getSiteUrl(),
                        AppContext.getUsername());
                getUI().getPage().open(userFullLinkStr, null);
            }
        });

        header.addComponent(avatar);

        MVerticalLayout headerContent = new MVerticalLayout().withMargin(new MarginInfo(false, false, false, true));
        headerContent.addStyleName("projectfeed-hdr-content");

        Label headerLabel = new Label(AppContext.getUser().getDisplayName());
        headerLabel.setStyleName("h1");
        headerLabel.addStyleName(UIConstants.WORD_WRAP);

        MHorizontalLayout headerContentTop = new MHorizontalLayout().withMargin(new MarginInfo(false, false, true,
                false)).withWidth("100%");
        headerContentTop.with(headerLabel).withAlign(headerLabel, Alignment.TOP_LEFT).expand(headerLabel);

        if (AppContext.canBeYes(RolePermissionCollections.CREATE_NEW_PROJECT)) {
            Button createProjectBtn = new Button(AppContext.getMessage(ProjectCommonI18nEnum.BUTTON_NEW_PROJECT),
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(final Button.ClickEvent event) {
                            UI.getCurrent().addWindow(new ProjectAddWindow());
                        }
                    });
            createProjectBtn.setIcon(FontAwesome.PLUS);
            createProjectBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
            headerContentTop.with(createProjectBtn).withAlign(createProjectBtn, Alignment.TOP_RIGHT);
        }

        SearchTextField searchTextField = new SearchTextField() {
            @Override
            public void doSearch(String value) {
                displaySearchResult(value);
            }
        };
        headerContentTop.with(searchTextField).withAlign(searchTextField, Alignment.TOP_RIGHT);

        followingTicketsLink = new LabelLink(AppContext.getMessage(
                FollowerI18nEnum.OPT_MY_FOLLOWING_TICKETS, 0), AppContext.getSiteUrl() + "#project/following");
        followingTicketsLink.setIconLink(FontAwesome.EYE);

        LabelLink timeTrackingLink = new LabelLink(AppContext.getMessage(TimeTrackingI18nEnum.TIME_RECORD_HEADER), AppContext.getSiteUrl()
                + "#project/timetracking");
        timeTrackingLink.setIconLink(ProjectAssetsManager.getAsset(ProjectTypeConstants.TIME));

        MHorizontalLayout headerContentBottom = new MHorizontalLayout().with(followingTicketsLink, timeTrackingLink);
        headerContent.with(headerContentTop, headerContentBottom);
        header.with(headerContent).expand(headerContent);
        headerWrapper.addComponent(header);
        return headerWrapper;
    }

    private static final String headerTitle = FontAwesome.SEARCH.getHtml() + " Search for '%s' (Found: %d)";

    private void displaySearchResult(String value) {
        removeAllComponents();

        CssLayout headerWrapper = setupHeader();
        addComponent(headerWrapper);

        MVerticalLayout layout = new MVerticalLayout().withWidth("100%").withStyleName("searchitems-layout");
        addComponent(layout);

        MHorizontalLayout headerComp = new MHorizontalLayout();
        Label headerLbl = new Label(String.format(headerTitle, value, 0), ContentMode.HTML);
        headerLbl.addStyleName("headerName");
        Button backDashboard = new Button("Back to workboard", new ClickListener() {
            @Override
            public void buttonClick(ClickEvent clickEvent) {
                displayView();
            }
        });
        backDashboard.setStyleName(UIConstants.THEME_GREEN_LINK);
        headerComp.with(headerLbl, backDashboard);
        layout.with(headerComp);

        ProjectService prjService = ApplicationContextUtil.getSpringBean(ProjectService.class);
        prjKeys = prjService.getProjectKeysUserInvolved(AppContext.getUsername(), AppContext.getAccountId());
        if (CollectionUtils.isNotEmpty(prjKeys)) {
            ProjectGenericItemSearchCriteria searchCriteria = new ProjectGenericItemSearchCriteria();
            searchCriteria.setPrjKeys(new SetSearchField<>(prjKeys.toArray(new Integer[prjKeys.size()])));
            searchCriteria.setTxtValue(new StringSearchField(value));

            DefaultBeanPagedList<ProjectGenericItemService, ProjectGenericItemSearchCriteria, ProjectGenericItem>
                    searchItemsTable = new DefaultBeanPagedList<>(ApplicationContextUtil.getSpringBean(ProjectGenericItemService.class), new
                    AssignmentRowDisplayHandler());
            searchItemsTable.setControlStyle("borderlessControl");
            int foundNum = searchItemsTable.setSearchCriteria(searchCriteria);
            headerLbl.setValue(String.format(headerTitle, value, foundNum));
            layout.with(searchItemsTable);
        }
    }

    private void displayFollowingTicketsCount() {
        // show following ticket numbers
        MonitorSearchCriteria searchCriteria = new MonitorSearchCriteria();
        searchCriteria.setUser(new StringSearchField(SearchField.AND, AppContext.getUsername()));
        searchCriteria.setExtraTypeIds(new SetSearchField<>(prjKeys.toArray(new Integer[prjKeys.size()])));
        MonitorItemService monitorService = ApplicationContextUtil.getSpringBean(MonitorItemService.class);
        int followingItemsCount = monitorService.getTotalCount(searchCriteria);
        followingTicketsLink.setTitle(AppContext.getMessage(FollowerI18nEnum.OPT_MY_FOLLOWING_TICKETS, followingItemsCount));
    }

    private static class AssignmentRowDisplayHandler implements AbstractBeanPagedList.RowDisplayHandler<ProjectGenericItem> {
        @Override
        public Component generateRow(AbstractBeanPagedList host, ProjectGenericItem projectItem, int rowIndex) {
            MVerticalLayout layout = new MVerticalLayout().withMargin(new MarginInfo(true, true, false, true)).withWidth("100%");
            Label link = new Label(ProjectLinkBuilder.generateProjectItemHtmlLink(projectItem.getProjectShortName(), projectItem
                    .getProjectId(), projectItem.getSummary(), projectItem.getType(), projectItem.getTypeId()), ContentMode.HTML);
            link.setStyleName("h2");

            String desc = (StringUtils.isBlank(projectItem.getDescription())) ? "&lt;&lt;No description&gt;&gt;" : projectItem
                    .getDescription();
            SafeHtmlLabel descLbl = new SafeHtmlLabel(desc);

            Div div = new Div().setStyle("width:100%").setCSSClass("footer");
            Div lastUpdatedOn = new Div().appendChild(new Text("Modified: " + AppContext.formatPrettyTime(projectItem.getLastUpdatedTime
                    ()))).setTitle(AppContext.formatDateTime(projectItem.getLastUpdatedTime())).setStyle("float:right;" +
                    "margin-right:5px");
            Text createdByTxt = new Text("Created by: ");
            if (StringUtils.isBlank(projectItem.getCreatedUser())) {
                div.appendChild(createdByTxt, new Text("None"), lastUpdatedOn);
            } else {
                String uid = UUID.randomUUID().toString();
                Img userAvatar = new Img("", StorageManager.getAvatarLink(projectItem.getCreatedUserAvatarId(), 16));
                A userLink = new A().setId("tag" + uid).setHref(ProjectLinkBuilder.generateProjectMemberFullLink(projectItem.getProjectId(), projectItem
                        .getCreatedUser())).appendText(projectItem.getCreatedUserDisplayName());
                userLink.setAttribute("onmouseover", TooltipHelper.userHoverJsDunction(uid, projectItem.getCreatedUser()));
                userLink.setAttribute("onmouseleave", TooltipHelper.itemMouseLeaveJsFunction(uid));
                Text belongPrjTxt = new Text(" - Project: ");
                A projectLink = new A().setHref(ProjectLinkBuilder.generateProjectFullLink(projectItem.getProjectId()))
                        .appendText(String.format("%s (%s)", projectItem.getProjectName(), projectItem.getProjectShortName()));

                div.appendChild(createdByTxt, DivLessFormatter.EMPTY_SPACE(), userAvatar, DivLessFormatter.EMPTY_SPACE(),
                        userLink, TooltipHelper.buildDivTooltipEnable(uid), DivLessFormatter.EMPTY_SPACE(), belongPrjTxt,
                        DivLessFormatter.EMPTY_SPACE(), projectLink,
                        lastUpdatedOn);
            }

            Label footer = new Label(div.write(), ContentMode.HTML);
            footer.setWidth("100%");
            layout.with(link, descLbl, footer);
            layout.addStyleName("project-item-search-box");
            return layout;
        }
    }

    private static class AskCreateNewProjectWindow extends Window {
        AskCreateNewProjectWindow() {
            super("Question");
            this.setWidth("600px");
            this.setResizable(false);
            this.setModal(true);
            this.center();
            MVerticalLayout content = new MVerticalLayout();
            this.setContent(content);

            content.with(new Label("You do not have any active project. Do you want to create a new one?"));

            MHorizontalLayout btnControls = new MHorizontalLayout();
            Button skipBtn = new Button("Skip", new ClickListener() {
                @Override
                public void buttonClick(ClickEvent event) {
                    AskCreateNewProjectWindow.this.close();
                }
            });
            skipBtn.setStyleName(UIConstants.THEME_GRAY_LINK);
            Button createNewBtn = new Button("New Project", new ClickListener() {
                @Override
                public void buttonClick(ClickEvent event) {
                    AskCreateNewProjectWindow.this.close();
                    UI.getCurrent().addWindow(new ProjectAddWindow());
                }
            });
            createNewBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
            btnControls.with(skipBtn, createNewBtn);
            content.with(btnControls).withAlign(btnControls, Alignment.MIDDLE_RIGHT);
        }
    }
}