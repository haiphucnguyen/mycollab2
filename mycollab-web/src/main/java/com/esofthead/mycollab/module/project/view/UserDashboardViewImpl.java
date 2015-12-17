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

import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.user.AccountLinkGenerator;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractLazyPageView;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.ui.SearchTextField;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class UserDashboardViewImpl extends AbstractLazyPageView implements UserDashboardView {
    private static final long serialVersionUID = 1L;

    private List<Integer> prjKeys;

    private ProjectDashboardPresenter projectDashboardPresenter;
    private FollowingTicketPresenter followingTicketPresenter;
    private TimeTrackingPresenter timeTrackingPresenter;
    private SettingPresenter settingPresenter;

    public UserDashboardViewImpl() {
        this.withMargin(false).withWidth("100%");
    }

    @Override
    protected void displayView() {
        removeAllComponents();
        ProjectService prjService = ApplicationContextUtil.getSpringBean(ProjectService.class);
        prjKeys = prjService.getProjectKeysUserInvolved(AppContext.getUsername(), AppContext.getAccountId());

        final TabSheet tabSheet = new TabSheet();
        tabSheet.addTab(buildDashboardComp(), "Dashboard", FontAwesome.DASHBOARD);
        tabSheet.addTab(buildFollowingTicketComp(), "Following Items", FontAwesome.EYE);
        tabSheet.addTab(buildTimesheetComp(), "Time", FontAwesome.CLOCK_O);
//        tabSheet.addTab(buildSettingComp(), "Settings", FontAwesome.COG);
        tabSheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            @Override
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                Component comp = tabSheet.getSelectedTab();
                if (comp instanceof ProjectDashboardView) {
                    projectDashboardPresenter.onGo(UserDashboardViewImpl.this, null);
                } else if (comp instanceof FollowingTicketView) {
                    followingTicketPresenter.onGo(UserDashboardViewImpl.this, null);
                } else if (comp instanceof ITimeTrackingView) {
                    timeTrackingPresenter.onGo(UserDashboardViewImpl.this, null);
                } else if (comp instanceof SettingView) {
                    settingPresenter.onGo(UserDashboardViewImpl.this, null);
                }
            }
        });
        this.with(setupHeader(), tabSheet).expand(tabSheet);
        projectDashboardPresenter.onGo(UserDashboardViewImpl.this, null);

        if (AppContext.canBeYes(RolePermissionCollections.CREATE_NEW_PROJECT)) {
            int countActiveProjects = prjService.getTotalActiveProjectsOfInvolvedUsers(AppContext.getUsername(), AppContext.getAccountId());
            if (countActiveProjects == 0) {
                UI.getCurrent().addWindow(new AskCreateNewProjectWindow());
            }
        }
    }

    @Override
    public List<Integer> getInvoledProjKeys() {
        return prjKeys;
    }

    private Component buildDashboardComp() {
        projectDashboardPresenter = PresenterResolver.getPresenter(ProjectDashboardPresenter.class);
        return projectDashboardPresenter.getView();
    }

    private Component buildFollowingTicketComp() {
        followingTicketPresenter = PresenterResolver.getPresenter(FollowingTicketPresenter.class);
        return followingTicketPresenter.getView();
    }

    private Component buildTimesheetComp() {
        timeTrackingPresenter = PresenterResolver.getPresenter(TimeTrackingPresenter.class);
        return timeTrackingPresenter.getView();
    }

    private Component buildSettingComp() {
        settingPresenter = PresenterResolver.getPresenter(SettingPresenter.class);
        return settingPresenter.getView();
    }

    private CssLayout setupHeader() {
        CssLayout headerWrapper = new CssLayout();
        headerWrapper.setWidth("100%");
        headerWrapper.setStyleName("projectfeed-hdr-wrapper");

        MHorizontalLayout header = new MHorizontalLayout().withWidth("100%");
        header.addStyleName("projectfeed-hdr");

        Button avatar = UserAvatarControlFactory.createUserAvatarEmbeddedButton(AppContext.getUserAvatarId(), 32);
        avatar.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final Button.ClickEvent event) {
                String userFullLinkStr = AccountLinkGenerator.generatePreviewFullUserLink(AppContext.getSiteUrl(),
                        AppContext.getUsername());
                getUI().getPage().open(userFullLinkStr, null);
            }
        });

        header.addComponent(avatar);

        MVerticalLayout headerContent = new MVerticalLayout().withMargin(new MarginInfo(false, false, false, true));
        headerContent.addStyleName("projectfeed-hdr-content");

        ELabel headerLabel = ELabel.h2(AppContext.getUser().getDisplayName());
        headerLabel.addStyleName(UIConstants.LABEL_WORD_WRAP);

        MHorizontalLayout headerContentTop = new MHorizontalLayout().withMargin(new MarginInfo(false, false, true,
                false)).withWidth("100%");
        headerContentTop.with(headerLabel).withAlign(headerLabel, Alignment.TOP_LEFT).expand(headerLabel);

        SearchTextField searchTextField = new SearchTextField() {
            @Override
            public void doSearch(String value) {
                displaySearchResult(value);
            }
        };
        headerContentTop.with(searchTextField).withAlign(searchTextField, Alignment.TOP_RIGHT);

        headerContent.with(headerContentTop);
        header.with(headerContent).expand(headerContent);
        headerWrapper.addComponent(header);
        return headerWrapper;
    }

    private void displaySearchResult(String value) {

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
            Button skipBtn = new Button("Skip", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    AskCreateNewProjectWindow.this.close();
                }
            });
            skipBtn.setStyleName(UIConstants.THEME_GRAY_LINK);
            Button createNewBtn = new Button("New Project", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    AskCreateNewProjectWindow.this.close();
                    UI.getCurrent().addWindow(new ProjectAddWindow());
                }
            });
            createNewBtn.setStyleName(UIConstants.BUTTON_ACTION);
            btnControls.with(skipBtn, createNewBtn);
            content.with(btnControls).withAlign(btnControls, Alignment.MIDDLE_RIGHT);
        }
    }
}