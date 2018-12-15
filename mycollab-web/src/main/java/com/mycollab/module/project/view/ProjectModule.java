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
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.event.ClientEvent;
import com.mycollab.module.project.event.ProjectEvent;
import com.mycollab.module.project.event.ReportEvent;
import com.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.mycollab.module.project.i18n.ProjectI18nEnum;
import com.mycollab.module.project.i18n.TicketI18nEnum;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.module.project.view.service.TicketComponentFactory;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.*;
import com.mycollab.vaadin.web.ui.OptionPopupContent;
import com.mycollab.vaadin.web.ui.VerticalTabsheet;
import com.mycollab.web.IDesktopModule;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class ProjectModule extends AbstractSingleContainerPageView implements IDesktopModule {
    private static final long serialVersionUID = 1L;

    private MHorizontalLayout serviceMenuContainer;
    private VerticalTabsheet tabSheet;

    private UserProjectDashboardPresenter userProjectDashboardPresenter;

    private FollowingTicketPresenter followingTicketPresenter;

    private ProjectListPresenter projectListPresenter;

    public ProjectModule() {
        addStyleName("module");
        setSizeFull();
        ControllerRegistry.addController(new ProjectModuleController(this));

        tabSheet = new VerticalTabsheet();
        tabSheet.setSizeFull();
        tabSheet.setNavigatorStyleName("sidebar-menu");
        tabSheet.addToggleNavigatorControl();
        VerticalLayout contentWrapper = tabSheet.getContentWrapper();
        contentWrapper.addStyleName("main-content");

        this.buildComponents();
        this.setContent(tabSheet);
    }

    private void buildComponents() {
        tabSheet.addTab(constructDashboardComponent(), "Dashboard",
                UserUIContext.getMessage(GenericI18Enum.VIEW_DASHBOARD), VaadinIcons.DASHBOARD);

        tabSheet.addTab(constructProjectsViewComponent(), "Projects",
                UserUIContext.getMessage(ProjectI18nEnum.LIST), VaadinIcons.BUILDING_O);

        tabSheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                TabSheet.Tab tab = ((VerticalTabsheet) event.getSource()).getSelectedTab();
                String tabId = ((VerticalTabsheet.TabImpl) tab).getTabId();
                if ("Dashboard".equals(tabId)) {
                    userProjectDashboardPresenter.go(ProjectModule.this, null);
                } else if ("Projects".equals(tabId)) {
                    projectListPresenter.go(ProjectModule.this, null);
                }
            }
        });
    }

    private HasComponents constructDashboardComponent() {
        userProjectDashboardPresenter = PresenterResolver.getPresenter(UserProjectDashboardPresenter.class);
        return userProjectDashboardPresenter.getView();
    }

    private HasComponents constructFollowingTicketsComponent() {
        followingTicketPresenter = PresenterResolver.getPresenter(FollowingTicketPresenter.class);
        return followingTicketPresenter.getView();
    }

    private HasComponents constructProjectsViewComponent() {
        projectListPresenter = PresenterResolver.getPresenter(ProjectListPresenter.class);
        return projectListPresenter.getView();
    }

    @Override
    public MHorizontalLayout buildMenu() {
        if (serviceMenuContainer == null) {
            serviceMenuContainer = new MHorizontalLayout().withHeight("45px").withMargin(new MarginInfo(false, true,
                    false, true)).withStyleName("service-menu");
            serviceMenuContainer.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

            MButton boardBtn = new MButton(UserUIContext.getMessage(ProjectCommonI18nEnum.OPT_BOARD), clickEvent ->
                    EventBusFactory.getInstance().post(new ProjectEvent.GotoUserDashboard(this, null))
            );
            serviceMenuContainer.with(boardBtn);

            if (!SiteConfiguration.isCommunityEdition()) {
                MButton clientBtn = new MButton(UserUIContext.getMessage(ProjectCommonI18nEnum.VIEW_CLIENTS), clickEvent -> {
                    EventBusFactory.getInstance().post(new ClientEvent.GotoList(this, null));
                });

                MButton reportBtn = new MButton(UserUIContext.getMessage(ProjectCommonI18nEnum.VIEW_REPORTS), clickEvent -> {
                    EventBusFactory.getInstance().post(new ReportEvent.GotoConsole(this));
                });
                serviceMenuContainer.with(clientBtn, reportBtn);
            }

            PopupButton newBtn = new PopupButton(UserUIContext.getMessage(GenericI18Enum.ACTION_NEW));
            newBtn.addStyleName("add-btn-popup");
            newBtn.setIcon(VaadinIcons.PLUS_CIRCLE);
            OptionPopupContent contentLayout = new OptionPopupContent();

            if (UserUIContext.canBeYes(RolePermissionCollections.CREATE_NEW_PROJECT)) {
                MButton newPrjButton = new MButton(UserUIContext.getMessage(ProjectI18nEnum.SINGLE), clickEvent -> {
                    UI.getCurrent().addWindow(ViewManager.getCacheComponent(AbstractProjectAddWindow.class));
                    newBtn.setPopupVisible(false);
                }).withIcon(ProjectAssetsManager.getAsset(ProjectTypeConstants.PROJECT));
                contentLayout.addOption(newPrjButton);
            }

            MButton newTicketButton = new MButton(UserUIContext.getMessage(TicketI18nEnum.SINGLE), clickEvent -> {
                UI.getCurrent().addWindow(AppContextUtil.getSpringBean(TicketComponentFactory.class)
                        .createNewTicketWindow(null, null, null, false));
                newBtn.setPopupVisible(false);
            }).withIcon(ProjectAssetsManager.getAsset(ProjectTypeConstants.TICKET));
            contentLayout.addOption(newTicketButton);

            newBtn.setContent(contentLayout);

            serviceMenuContainer.with(newBtn).withAlign(newBtn, Alignment.MIDDLE_LEFT);
        }

        return serviceMenuContainer;
    }

    public void gotoSubView(String viewId) {
        tabSheet.selectTab(viewId);
    }
}
