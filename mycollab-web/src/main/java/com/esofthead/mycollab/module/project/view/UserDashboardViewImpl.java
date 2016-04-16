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

import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.domain.ProjectGenericItem;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericItemSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectGenericItemService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.ui.components.GenericItemRowDisplayHandler;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.mvp.view.AbstractLazyPageView;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.vaadin.web.ui.DefaultBeanPagedList;
import com.esofthead.mycollab.vaadin.web.ui.SearchTextField;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.hp.gagawa.java.elements.A;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.apache.commons.collections.CollectionUtils;
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

    private UserProjectDashboardPresenter userProjectDashboardPresenter;
    private ProjectListPresenter projectListPresenter;
    private FollowingTicketPresenter followingTicketPresenter;
    private TimeTrackingPresenter timeTrackingPresenter;
    private ICalendarDashboardPresenter calendarPresenter;
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
        tabSheet.addTab(buildProjectListComp(), "Projects", FontAwesome.BUILDING_O);
        tabSheet.addTab(buildFollowingTicketComp(), "Following Items", FontAwesome.EYE);
        tabSheet.addTab(buildCalendarComp(), "Calendar", FontAwesome.CALENDAR);
        tabSheet.addTab(buildTimesheetComp(), "Time", FontAwesome.CLOCK_O);
//        tabSheet.addTab(buildSettingComp(), "Settings", FontAwesome.COG);

        tabSheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            @Override
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                Component comp = tabSheet.getSelectedTab();
                if (comp instanceof UserProjectDashboardView) {
                    userProjectDashboardPresenter.onGo(UserDashboardViewImpl.this, null);
                } else if (comp instanceof FollowingTicketView) {
                    followingTicketPresenter.onGo(UserDashboardViewImpl.this, null);
                } else if (comp instanceof ITimeTrackingView) {
                    timeTrackingPresenter.onGo(UserDashboardViewImpl.this, null);
                } else if (comp instanceof SettingView) {
                    settingPresenter.onGo(UserDashboardViewImpl.this, null);
                } else if (comp instanceof ICalendarDashboardView) {
                    calendarPresenter.go(UserDashboardViewImpl.this, null);
                } else if (comp instanceof ProjectListView) {
                    projectListPresenter.go(UserDashboardViewImpl.this, null);
                }
            }
        });

        this.with(setupHeader(), tabSheet).expand(tabSheet);
        userProjectDashboardPresenter.onGo(UserDashboardViewImpl.this, null);

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
        userProjectDashboardPresenter = PresenterResolver.getPresenter(UserProjectDashboardPresenter.class);
        return userProjectDashboardPresenter.getView();
    }

    private Component buildProjectListComp() {
        projectListPresenter = PresenterResolver.getPresenter(ProjectListPresenter.class);
        return projectListPresenter.getView();
    }

    private Component buildFollowingTicketComp() {
        followingTicketPresenter = PresenterResolver.getPresenter(FollowingTicketPresenter.class);
        return followingTicketPresenter.getView();
    }

    private Component buildTimesheetComp() {
        timeTrackingPresenter = PresenterResolver.getPresenter(TimeTrackingPresenter.class);
        return timeTrackingPresenter.getView();
    }

    private Component buildCalendarComp() {
        calendarPresenter = PresenterResolver.getPresenter(ICalendarDashboardPresenter.class);
        return calendarPresenter.getView();
    }

    private Component buildSettingComp() {
        settingPresenter = PresenterResolver.getPresenter(SettingPresenter.class);
        return settingPresenter.getView();
    }

    private ComponentContainer setupHeader() {
        HorizontalLayout headerWrapper = new HorizontalLayout();
        headerWrapper.setWidth("100%");
        headerWrapper.setStyleName("projectfeed-hdr-wrapper");

        Image avatar = UserAvatarControlFactory.createUserAvatarEmbeddedComponent(AppContext.getUserAvatarId(), 64);
        avatar.setStyleName(UIConstants.CIRCLE_BOX);
        headerWrapper.addComponent(avatar);

        MVerticalLayout headerContent = new MVerticalLayout().withMargin(new MarginInfo(false, false, false, true));

        ELabel headerLabel = ELabel.h2(AppContext.getUser().getDisplayName()).withStyleName(UIConstants.TEXT_ELLIPSIS);
        MHorizontalLayout headerContentTop = new MHorizontalLayout();
        headerContentTop.with(headerLabel).withAlign(headerLabel, Alignment.TOP_LEFT).expand(headerLabel);

        SearchTextField searchTextField = new SearchTextField() {
            @Override
            public void doSearch(String value) {
                displaySearchResult(value);
            }

            @Override
            public void emptySearch() {

            }
        };
        headerContentTop.with(searchTextField).withAlign(searchTextField, Alignment.TOP_RIGHT);
        headerContent.with(headerContentTop);
        MHorizontalLayout metaInfoLayout = new MHorizontalLayout().with(new ELabel("Email:").withStyleName
                (UIConstants.LABEL_META_INFO), new ELabel(new A(String.format("mailto:%s", AppContext.getUsername()))
                .appendText(AppContext.getUsername()).write(), ContentMode.HTML));
        metaInfoLayout.with(new ELabel("Member since: ").withStyleName(UIConstants.LABEL_META_INFO),
                new ELabel(AppContext.formatPrettyTime(AppContext.getUser().getRegisteredtime())));
        metaInfoLayout.with(new ELabel("Logged in: ").withStyleName(UIConstants.LABEL_META_INFO),
                new ELabel(AppContext.formatPrettyTime(AppContext.getUser().getLastaccessedtime())));
        metaInfoLayout.alignAll(Alignment.TOP_LEFT);
        headerContent.addComponent(metaInfoLayout);
        headerWrapper.addComponent(headerContent);
        headerWrapper.setExpandRatio(headerContent, 1.0f);
        return headerWrapper;
    }

    private static final String headerTitle = FontAwesome.SEARCH.getHtml() + " Search for '%s' (Found: %d)";

    private void displaySearchResult(String value) {
        removeAllComponents();
        Component headerWrapper = setupHeader();

        MVerticalLayout layout = new MVerticalLayout().withWidth("100%");
        with(headerWrapper, layout).expand(layout);

        MHorizontalLayout headerComp = new MHorizontalLayout();
        ELabel headerLbl = ELabel.h2(String.format(headerTitle, value, 0));
        Button backDashboard = new Button("Back to workboard", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                displayView();
            }
        });
        backDashboard.setStyleName(UIConstants.BUTTON_ACTION);
        headerComp.with(headerLbl, backDashboard).alignAll(Alignment.MIDDLE_LEFT);
        layout.with(headerComp);

        ProjectService prjService = ApplicationContextUtil.getSpringBean(ProjectService.class);
        prjKeys = prjService.getProjectKeysUserInvolved(AppContext.getUsername(), AppContext.getAccountId());
        if (CollectionUtils.isNotEmpty(prjKeys)) {
            ProjectGenericItemSearchCriteria searchCriteria = new ProjectGenericItemSearchCriteria();
            searchCriteria.setPrjKeys(new SetSearchField<>(prjKeys.toArray(new Integer[prjKeys.size()])));
            searchCriteria.setTxtValue(StringSearchField.and(value));

            DefaultBeanPagedList<ProjectGenericItemService, ProjectGenericItemSearchCriteria, ProjectGenericItem>
                    searchItemsTable = new DefaultBeanPagedList<>(ApplicationContextUtil.getSpringBean(ProjectGenericItemService.class),
                    new GenericItemRowDisplayHandler());
            searchItemsTable.setControlStyle("borderlessControl");
            int foundNum = searchItemsTable.setSearchCriteria(searchCriteria);
            headerLbl.setValue(String.format(headerTitle, value, foundNum));
            layout.with(searchItemsTable).expand(searchItemsTable);
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
            Button skipBtn = new Button("Skip", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    AskCreateNewProjectWindow.this.close();
                }
            });
            skipBtn.setStyleName(UIConstants.BUTTON_OPTION);
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