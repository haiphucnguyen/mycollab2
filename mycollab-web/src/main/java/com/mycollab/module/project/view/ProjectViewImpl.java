/**
 * Copyright © MyCollab
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.project.view;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.configuration.SiteConfiguration;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.query.DateParam;
import com.mycollab.db.query.VariableInjector;
import com.mycollab.module.file.PathUtils;
import com.mycollab.module.project.*;
import com.mycollab.module.project.domain.SimpleProject;
import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.mycollab.module.project.event.ProjectMemberEvent;
import com.mycollab.module.project.i18n.*;
import com.mycollab.module.project.service.ProjectMemberService;
import com.mycollab.module.project.service.ProjectService;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.module.project.view.finance.IInvoiceListPresenter;
import com.mycollab.module.project.view.finance.ITimeTrackingPresenter;
import com.mycollab.module.project.view.message.MessagePresenter;
import com.mycollab.module.project.view.milestone.MilestonePresenter;
import com.mycollab.module.project.view.page.PagePresenter;
import com.mycollab.module.project.view.parameters.MilestoneScreenData;
import com.mycollab.module.project.view.parameters.PageScreenData;
import com.mycollab.module.project.view.parameters.TimeTrackingScreenData;
import com.mycollab.module.project.view.settings.UserSettingPresenter;
import com.mycollab.module.project.view.ticket.ITicketKanbanPresenter;
import com.mycollab.module.project.view.ticket.TicketPresenter;
import com.mycollab.module.project.view.user.ProjectDashboardPresenter;
import com.mycollab.module.project.view.user.ProjectInfoComponent;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.*;
import com.mycollab.vaadin.web.ui.VerticalTabsheet;
import com.mycollab.vaadin.web.ui.VerticalTabsheet.ButtonTab;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class ProjectViewImpl extends AbstractVerticalPageView implements ProjectView {
    private ProjectViewWrap viewWrap;

    @Override
    public void initView(SimpleProject project) {
        removeAllComponents();
        this.withFullWidth().withId("project-view");
        viewWrap = new ProjectViewWrap(project);
        ControllerRegistry.addController(new ProjectController(this));

        this.with(viewWrap);
    }

    @Override
    public void setNavigatorVisibility(boolean visibility) {
        viewWrap.setNavigatorVisibility(visibility);
    }

    public void addComponentToRightbar(Component component) {
        viewWrap.rightBarContainer.addViewComponent(component);
    }

    public void clearRightbar() {
        viewWrap.rightBarContainer.clearViewComponents();
    }

    @Override
    public Component gotoSubView(String viewId) {
        return viewWrap.gotoSubView(viewId, null);
    }

    @Override
    public Component gotoSubView(String viewId, Component viewDisplay) {
        return viewWrap.gotoSubView(viewId, viewDisplay);
    }

    @Override
    public void updateProjectFeatures() {
        viewWrap.buildComponents();
    }

    private class ProjectViewWrap extends MHorizontalLayout implements PageView {
        private ProjectRightBarContainer rightBarContainer;
        private VerticalTabsheet myProjectTab;

        private UserSettingPresenter userPresenter;

        ProjectViewWrap(SimpleProject project) {
            this.withSpacing(false).withFullWidth().withStyleName("project-view").withId("project-view-wrap");

            myProjectTab = new VerticalTabsheet();
            myProjectTab.setSizeFull();
            myProjectTab.setNavigatorWidth("100%");
            myProjectTab.setNavigatorStyleName("sidebar-menu");
            myProjectTab.addToggleNavigatorControl();

            myProjectTab.addSelectedTabChangeListener(selectedTabChangeEvent -> {
                ButtonTab tab = ((VerticalTabsheet) selectedTabChangeEvent.getSource()).getSelectedTab();
                String tabId = tab.getTabId();
                if (ProjectView.MESSAGE_ENTRY.equals(tabId)) {
                    MessagePresenter messagePresenter = PresenterResolver.getPresenter(MessagePresenter.class);
                    messagePresenter.go(ProjectViewImpl.this, null);
                } else if (ProjectView.MILESTONE_ENTRY.equals(tabId)) {
                    MilestonePresenter milestonePresenter = PresenterResolver.getPresenter(MilestonePresenter.class);
                    milestonePresenter.go(ProjectViewImpl.this, new MilestoneScreenData.Roadmap());
                } else if (ProjectView.TICKET_ENTRY.equals(tabId)) {
                    TicketPresenter ticketPresenter = PresenterResolver.getPresenter(TicketPresenter.class);
                    ticketPresenter.go(ProjectViewImpl.this, null);
                } else if (ProjectView.PAGE_ENTRY.equals(tabId)) {
                    PagePresenter pagePresenter = PresenterResolver.getPresenter(PagePresenter.class);
                    pagePresenter.go(ProjectViewImpl.this,
                            new PageScreenData.Search(PathUtils.getProjectDocumentPath(AppUI.getAccountId(), project.getId())));
                } else if (ProjectView.SUMMARY_ENTRY.equals(tabId)) {
                    ProjectDashboardPresenter dashboardPresenter = PresenterResolver.getPresenter(ProjectDashboardPresenter.class);
                    dashboardPresenter.go(ProjectViewImpl.this, null);
                } else if (ProjectView.KANBAN_ENTRY.equals(tabId)) {
                    ITicketKanbanPresenter kanbanPresenter = PresenterResolver.getPresenter(ITicketKanbanPresenter.class);
                    kanbanPresenter.go(ProjectViewImpl.this, null);
                } else if (ProjectView.TIME_TRACKING_ENTRY.equals(tabId)) {
                    ITimeTrackingPresenter timeTrackingListPresenter = PresenterResolver.getPresenter(ITimeTrackingPresenter.class);
                    ItemTimeLoggingSearchCriteria searchCriteria = new ItemTimeLoggingSearchCriteria();
                    searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
                    searchCriteria.addExtraField(DateParam.inRangeDate(ItemTimeLoggingSearchCriteria.p_logDates,
                            VariableInjector.THIS_WEEK));
                    timeTrackingListPresenter.go(ProjectViewImpl.this, new TimeTrackingScreenData.Search(searchCriteria));
                } else if (ProjectView.INVOICE_ENTRY.equals(tabId)) {
                    IInvoiceListPresenter invoicePresenter = PresenterResolver.getPresenter(IInvoiceListPresenter.class);
                    invoicePresenter.go(ProjectViewImpl.this, null);
                }
                else if (ProjectView.USERS_ENTRY.equals(tabId)) {
//                    ProjectMemberSearchCriteria criteria = new ProjectMemberSearchCriteria();
//                    criteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
//                    criteria.setStatuses(new SetSearchField<>(ProjectMemberStatusConstants.ACTIVE, ProjectMemberStatusConstants.NOT_ACCESS_YET));
//                    userPresenter.go(ProjectViewImpl.this, new ProjectMemberScreenData.Search(criteria));
                }
            });

            CssLayout contentWrapper = myProjectTab.getContentWrapper();
            contentWrapper.addStyleName("main-content");
            contentWrapper.addStyleName("content-height");
            contentWrapper.addComponentAsFirst(new ProjectInfoComponent(project));

            buildComponents();

            rightBarContainer = new ProjectRightBarContainer(project);
            rightBarContainer.setWidth("300px");
            this.with(myProjectTab, rightBarContainer).expand(myProjectTab);

            if (project.getContextask() == null || project.getContextask()) {
                ProjectMemberSearchCriteria searchCriteria = new ProjectMemberSearchCriteria();
                searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
                searchCriteria.setStatuses(new SetSearchField<>(ProjectMemberStatusConstants.ACTIVE, ProjectMemberStatusConstants.NOT_ACCESS_YET));
                ProjectMemberService prjMemberService = AppContextUtil.getSpringBean(ProjectMemberService.class);
                int totalMembers = prjMemberService.getTotalCount(searchCriteria);
                if (totalMembers < 2) {
                    UI.getCurrent().addWindow(new AskToAddMoreMembersWindow());
                }
            }
        }

        void setNavigatorVisibility(boolean visibility) {
            myProjectTab.setNavigatorVisibility(visibility);
        }

        Component gotoSubView(String viewId, Component viewDisplay) {
            return myProjectTab.selectTab(viewId, viewDisplay);
        }

        private void buildComponents() {
            int prjId = CurrentProjectVariables.getProjectId();

            myProjectTab.addTab(null, ProjectView.SUMMARY_ENTRY,
                    UserUIContext.getMessage(ProjectCommonI18nEnum.VIEW_DASHBOARD),
                    ProjectLinkGenerator.generateProjectLink(prjId),
                    ProjectAssetsManager.getAsset(ProjectTypeConstants.DASHBOARD));

            if (CurrentProjectVariables.hasMessageFeature()) {
                myProjectTab.addTab(null, ProjectView.MESSAGE_ENTRY,
                        UserUIContext.getMessage(MessageI18nEnum.LIST),
                        ProjectLinkGenerator.generateMessagesLink(prjId),
                        ProjectAssetsManager.getAsset(ProjectTypeConstants.MESSAGE));
            } else {
                myProjectTab.removeTab(ProjectView.MESSAGE_ENTRY);
            }

            if (CurrentProjectVariables.hasPhaseFeature()) {
                myProjectTab.addTab(null, ProjectView.MILESTONE_ENTRY,
                        UserUIContext.getMessage(MilestoneI18nEnum.LIST),
                        ProjectLinkGenerator.generateMilestonesLink(prjId),
                        ProjectAssetsManager.getAsset(ProjectTypeConstants.MILESTONE));
            } else {
                myProjectTab.removeTab(ProjectView.MILESTONE_ENTRY);
            }

            if (CurrentProjectVariables.hasTicketFeature()) {
                myProjectTab.addTab(null,
                        ProjectView.TICKET_ENTRY, UserUIContext.getMessage(TicketI18nEnum.LIST),
                        ProjectLinkGenerator.generateTicketDashboardLink(prjId),
                        ProjectAssetsManager.getAsset(ProjectTypeConstants.TICKET));

                myProjectTab.addTab(ProjectView.TICKET_ENTRY, null, ProjectView.KANBAN_ENTRY,
                        UserUIContext.getMessage(ProjectCommonI18nEnum.OPT_KANBAN), VaadinIcons.GRID_SMALL_O);
            } else {
                myProjectTab.removeTab(ProjectView.TICKET_ENTRY);
                myProjectTab.removeTab(ProjectView.KANBAN_ENTRY);
            }

            if (CurrentProjectVariables.hasPageFeature()) {
                myProjectTab.addTab(null, ProjectView.PAGE_ENTRY,
                        UserUIContext.getMessage(PageI18nEnum.LIST),
                        ProjectLinkGenerator.generateProjectLink(prjId),
                        ProjectAssetsManager.getAsset(ProjectTypeConstants.PAGE));
            } else {
                myProjectTab.removeTab(ProjectView.PAGE_ENTRY);
            }

            if ((CurrentProjectVariables.hasTimeFeature() || CurrentProjectVariables.hasInvoiceFeature())
                    && !SiteConfiguration.isCommunityEdition()) {
                myProjectTab.addTab(null, ProjectView.FINANCE_ENTRY,
                        UserUIContext.getMessage(ProjectCommonI18nEnum.VIEW_FINANCE),
                        ProjectLinkGenerator.generateTimeReportLink(prjId),
                        ProjectAssetsManager.getAsset(ProjectTypeConstants.FINANCE));

                myProjectTab.addTab(ProjectView.FINANCE_ENTRY, null, ProjectView.TIME_TRACKING_ENTRY,
                        UserUIContext.getMessage(ProjectCommonI18nEnum.VIEW_TIME),
                        ProjectAssetsManager.getAsset(ProjectTypeConstants.TIME));

                myProjectTab.addTab(ProjectView.FINANCE_ENTRY, null, ProjectView.INVOICE_ENTRY,
                        UserUIContext.getMessage(InvoiceI18nEnum.LIST),
                        ProjectAssetsManager.getAsset(ProjectTypeConstants.INVOICE));
            } else {
                myProjectTab.removeTab(ProjectView.FINANCE_ENTRY);
                myProjectTab.removeTab(ProjectView.TIME_TRACKING_ENTRY);
                myProjectTab.removeTab(ProjectView.INVOICE_ENTRY);
            }

            myProjectTab.addTab(null, ProjectView.SETTING,
                    UserUIContext.getMessage(ProjectCommonI18nEnum.VIEW_MEMBER), null,
                    ProjectAssetsManager.getAsset(ProjectTypeConstants.MEMBER));

            if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.USERS)) {
                myProjectTab.addTab(ProjectView.SETTING, null, ProjectView.USERS_ENTRY,
                        UserUIContext.getMessage(ProjectMemberI18nEnum.LIST),
                        ProjectLinkGenerator.generateUsersLink(prjId),
                        ProjectAssetsManager.getAsset(ProjectTypeConstants.MEMBER));
            }

            if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.ROLES)) {
                myProjectTab.addTab(ProjectView.SETTING, null, ProjectView.ROLE_ENTRY,
                        UserUIContext.getMessage(ProjectRoleI18nEnum.LIST),
                        ProjectLinkGenerator.generateUsersLink(prjId),
                        ProjectAssetsManager.getAsset(ProjectTypeConstants.PROJECT_ROLE));
            }

            if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.COMPONENTS)) {
                myProjectTab.addTab(ProjectView.SETTING, null, ProjectView.COMPONENT_ENTRY,
                        UserUIContext.getMessage(ComponentI18nEnum.LIST),
                        ProjectLinkGenerator.generateUsersLink(prjId),
                        ProjectAssetsManager.getAsset(ProjectTypeConstants.BUG_COMPONENT));
            }

            if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.VERSIONS)) {
                myProjectTab.addTab(ProjectView.SETTING, null, ProjectView.VERSION_ENTRY,
                        UserUIContext.getMessage(VersionI18nEnum.LIST),
                        ProjectLinkGenerator.generateUsersLink(prjId),
                        ProjectAssetsManager.getAsset(ProjectTypeConstants.BUG_VERSION));
            }

            myProjectTab.addTab(ProjectView.SETTING, null, ProjectView.CUSTOM_ENTRY,
                    UserUIContext.getMessage(ProjectCommonI18nEnum.VIEW_SETTINGS),
                    ProjectLinkGenerator.generateUsersLink(prjId),
                    VaadinIcons.COG);

        }

        private Component constructProjectUsers() {
            userPresenter = PresenterResolver.getPresenter(UserSettingPresenter.class);
            return userPresenter.getView();
        }
    }

    private static class AskToAddMoreMembersWindow extends MWindow {
        AskToAddMoreMembersWindow() {
            super(UserUIContext.getMessage(GenericI18Enum.OPT_QUESTION));
            MVerticalLayout content = new MVerticalLayout();
            this.withWidth("600px").withResizable(false).withModal(true).withContent(content).withCenter();

            content.with(new Label(UserUIContext.getMessage(ProjectI18nEnum.OPT_ASK_TO_ADD_MEMBERS)));

            MButton skipBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.ACTION_SKIP), clickEvent -> {
                ProjectService projectService = AppContextUtil.getSpringBean(ProjectService.class);
                SimpleProject project = CurrentProjectVariables.getProject();
                project.setContextask(false);
                projectService.updateSelectiveWithSession(project, UserUIContext.getUsername());
                close();
            }).withStyleName(WebThemes.BUTTON_OPTION);

            MButton addNewMembersBtn = new MButton(UserUIContext.getMessage(ProjectI18nEnum.ACTION_ADD_MEMBERS), clickEvent -> {
                close();
                EventBusFactory.getInstance().post(new ProjectMemberEvent.GotoInviteMembers(this, null));
            }).withStyleName(WebThemes.BUTTON_ACTION);

            MHorizontalLayout btnControls = new MHorizontalLayout(skipBtn, addNewMembersBtn);
            content.with(btnControls).withAlign(btnControls, Alignment.MIDDLE_RIGHT);
        }
    }
}
