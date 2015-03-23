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
package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.module.project.view.ProjectAddWindow;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.SearchTextField;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.maddon.button.MButton;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public class ProjectListComponent extends MVerticalLayout {
    private static final long serialVersionUID = 6930971885172125913L;

    private PopupButton headerPopupButton;
    private ProjectPagedList projectList;
    private Label projectDesc;
    private Label titleLbl;

    public ProjectListComponent() {
        super();
        withMargin(false).withWidth("100%").withStyleName("project-list-comp");

        MHorizontalLayout headerBar = new MHorizontalLayout().withWidth("100%");

        headerPopupButton = new PopupButton();
        headerPopupButton.setStyleName("myprojectlist");
        headerPopupButton.setWidth("100%");

        projectList = new ProjectPagedList();
        projectList.addStyleName("contentWrapper");
        titleLbl = new Label(AppContext.getMessage(
                ProjectCommonI18nEnum.WIDGET_ACTIVE_PROJECTS_TITLE, 0));
        titleLbl.setStyleName("h2");
        MVerticalLayout contentLayout = new MVerticalLayout().withWidth("500px");

        SearchTextField searchField = new SearchTextField() {
            @Override
            public void doSearch(String value) {
                ProjectSearchCriteria searchCriteria = new ProjectSearchCriteria();
                searchCriteria.setInvolvedMember(new StringSearchField(SearchField.AND,
                        AppContext.getUsername()));
                searchCriteria.setProjectStatuses(new SetSearchField<>(
                        new String[]{StatusI18nEnum.Open.name()}));
                searchCriteria.setProjectName(new StringSearchField(value));
                int count = projectList.setSearchCriteria(searchCriteria);
                titleLbl.setValue(AppContext.getMessage(
                        ProjectCommonI18nEnum.WIDGET_ACTIVE_PROJECTS_TITLE, count));
            }
        };

        MHorizontalLayout popupHeader = new MHorizontalLayout().withWidth("100%");
        popupHeader.with(titleLbl, searchField).withAlign(titleLbl, Alignment.MIDDLE_LEFT).withAlign(searchField, Alignment
                .MIDDLE_RIGHT);
        contentLayout.with(popupHeader, projectList);
        headerPopupButton.setContent(contentLayout);

        Label componentHeader = new Label();
        componentHeader.setStyleName("h2");

        headerPopupButton.setIcon(ProjectAssetsManager.getAsset(ProjectTypeConstants.PROJECT));
        headerBar.with(headerPopupButton).expand(headerPopupButton);

        if (AppContext.canBeYes(RolePermissionCollections.CREATE_NEW_PROJECT)) {
            final MButton createProjectBtn = new MButton("+",
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(final Button.ClickEvent event) {
                            final ProjectAddWindow projectNewWindow = new ProjectAddWindow();
                            UI.getCurrent().addWindow(projectNewWindow);
                        }
                    });
            createProjectBtn.withStyleName("add-project-btn").withDescription("New Project");
            createProjectBtn.setWidth("20px");
            createProjectBtn.setHeight("20px");
            headerBar.with(createProjectBtn).withAlign(createProjectBtn, Alignment.MIDDLE_RIGHT);
        }

        this.addComponent(headerBar);

        projectDesc = new Label("", ContentMode.HTML);
        projectDesc.setStyleName("project-description");
        addComponent(projectDesc);
    }

    public void showProjects() {
        if (headerPopupButton.isPopupVisible()) {
            headerPopupButton.setPopupVisible(false);
        }
        headerPopupButton.setCaption(CurrentProjectVariables.getProject().getName());
        ProjectSearchCriteria searchCriteria = new ProjectSearchCriteria();
        searchCriteria.setInvolvedMember(new StringSearchField(SearchField.AND,
                AppContext.getUsername()));
        searchCriteria.setProjectStatuses(new SetSearchField<>(
                new String[]{StatusI18nEnum.Open.name()}));
        int count = projectList.setSearchCriteria(searchCriteria);
        titleLbl.setValue(AppContext.getMessage(
                ProjectCommonI18nEnum.WIDGET_ACTIVE_PROJECTS_TITLE, count));

        String desc = CurrentProjectVariables.getProject().getDescription();
        desc = StringUtils.trim(desc, 150, true);
        projectDesc.setValue(desc);
    }
}