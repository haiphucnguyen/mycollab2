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

import com.esofthead.mycollab.common.i18n.OptionI18nEnum;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.view.user.ProjectPagedList;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ControllerRegistry;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.web.ui.OptionPopupContent;
import com.esofthead.mycollab.vaadin.web.ui.ServiceMenu;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.esofthead.mycollab.web.IDesktopModule;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.teemu.VaadinIcons;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class ProjectModule extends AbstractPageView implements IDesktopModule {
    private static final long serialVersionUID = 1L;

    private MHorizontalLayout serviceMenuContainer;

    public ProjectModule() {
        setStyleName("project-module");
        setSizeFull();
        ControllerRegistry.addController(new ProjectModuleController(this));
    }

    @Override
    public MHorizontalLayout buildMenu() {
        if (serviceMenuContainer == null) {
            serviceMenuContainer = new MHorizontalLayout();
            final ServiceMenu serviceMenu = new ServiceMenu();
            serviceMenu.addService("Projects", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    EventBusFactory.getInstance().post(new ShellEvent.GotoProjectModule(this, new String[]{"dashboard"}));
                    serviceMenu.selectService(0);
                }
            });
            serviceMenu.addService("Clients", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    EventBusFactory.getInstance().post(new ShellEvent.GotoProjectModule(this, new String[]{"client"}));
                    serviceMenu.selectService(1);
                }
            });

            serviceMenuContainer.with(serviceMenu);

            Button newPrjBtn = new Button(AppContext.getMessage(ProjectCommonI18nEnum.BUTTON_NEW_PROJECT), new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    UI.getCurrent().addWindow(new ProjectAddWindow());
                }
            });
            newPrjBtn.addStyleName("add-btn-popup");
            newPrjBtn.setIcon(FontAwesome.PLUS_CIRCLE);
            newPrjBtn.setEnabled(AppContext.canBeYes(RolePermissionCollections.CREATE_NEW_PROJECT));
            serviceMenuContainer.with(newPrjBtn).withAlign(newPrjBtn, Alignment.MIDDLE_LEFT);

            Button switchPrjBtn = new SwitchProjectPopupButton();
            serviceMenuContainer.with(switchPrjBtn).withAlign(switchPrjBtn, Alignment.MIDDLE_LEFT);
        }

        return serviceMenuContainer;
    }

    private class SwitchProjectPopupButton extends PopupButton {
        private boolean isSortAsc = true;
        private ProjectSearchCriteria searchCriteria;

        private ELabel titleLbl;
        private ProjectPagedList projectList;

        SwitchProjectPopupButton() {
            super("Switch Project");
            setStyleName("myprojectlist");
            addStyleName("add-btn-popup");
            setIcon(VaadinIcons.ARROW_CIRCLE_RIGHT_O);
            projectList = new ProjectPagedList();

            searchCriteria = new ProjectSearchCriteria();
            searchCriteria.setInvolvedMember(StringSearchField.and(AppContext.getUsername()));
            searchCriteria.setProjectStatuses(new SetSearchField<>(OptionI18nEnum.StatusI18nEnum.Open.name()));

            titleLbl = ELabel.h2(AppContext.getMessage(ProjectCommonI18nEnum.WIDGET_ACTIVE_PROJECTS_TITLE, 0));
            OptionPopupContent contentLayout = new OptionPopupContent();
            contentLayout.setWidth("550px");

            final Button sortBtn = new Button();
            sortBtn.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    isSortAsc = !isSortAsc;
                    if (searchCriteria != null) {
                        if (isSortAsc) {
                            sortBtn.setIcon(FontAwesome.SORT_ALPHA_ASC);
                            searchCriteria.setOrderFields(Collections.singletonList(new SearchCriteria.OrderField("name", SearchCriteria.ASC)));
                        } else {
                            sortBtn.setIcon(FontAwesome.SORT_ALPHA_DESC);
                            searchCriteria.setOrderFields(Collections.singletonList(new SearchCriteria.OrderField("name", SearchCriteria.DESC)));
                        }
                        displayResults();
                    }
                }
            });
            sortBtn.setIcon(FontAwesome.SORT_ALPHA_ASC);
            sortBtn.addStyleName(UIConstants.BUTTON_ICON_ONLY);

            final TextField searchField = new TextField();
            searchField.setInputPrompt("Search");
            searchField.setWidth("200px");
            Button searchBtn = new Button("", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    searchCriteria.setProjectName(StringSearchField.and(searchField.getValue()));
                    displayResults();
                }
            });
            searchBtn.setStyleName(UIConstants.BUTTON_ACTION);
            searchBtn.setIcon(FontAwesome.SEARCH);

            MHorizontalLayout popupHeader = new MHorizontalLayout().withMargin(new MarginInfo(false, true, false, true))
                    .withWidth("100%").withStyleName("border-bottom");
            MHorizontalLayout searchPanel = new MHorizontalLayout().withMargin(true);
            searchPanel.with(searchField, searchBtn);
            popupHeader.with(titleLbl, sortBtn, searchPanel).expand(titleLbl).alignAll(Alignment.MIDDLE_LEFT);
            contentLayout.addBlankOption(popupHeader);
            contentLayout.addBlankOption(projectList);
            setContent(contentLayout);

            addPopupVisibilityListener(new PopupButton.PopupVisibilityListener() {
                @Override
                public void popupVisibilityChange(PopupButton.PopupVisibilityEvent event) {
                    if (event.isPopupVisible()) {
                        displayResults();
                    }
                }
            });
        }

        private void displayResults() {
            int count = projectList.setSearchCriteria(searchCriteria);
            titleLbl.setValue(AppContext.getMessage(ProjectCommonI18nEnum.WIDGET_ACTIVE_PROJECTS_TITLE, count));
        }
    }
}
