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

import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.i18n.ProjectI18nEnum;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.web.ui.VerticalTabsheet;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public class ProjectVerticalTabsheet extends VerticalTabsheet {
    private static final long serialVersionUID = 1L;

    private Button toggleBtn;

    @Override
    public void setNavigatorVisibility(boolean visibility) {
        if (!visibility) {
            navigatorWrapper.setWidth("65px");
            navigatorContainer.setWidth("65px");
            this.hideTabsCaption();

            navigatorContainer.setComponentAlignment(toggleBtn, Alignment.MIDDLE_CENTER);
            toggleBtn.setIcon(FontAwesome.ANGLE_DOUBLE_RIGHT);
            toggleBtn.setStyleName(WebUIConstants.BUTTON_ICON_ONLY + " expand-button");
            toggleBtn.setDescription(UserUIContext.getMessage(ProjectI18nEnum.ACTION_EXPAND_MENU));
            toggleBtn.setCaption("");
        } else {
            navigatorWrapper.setWidth("200px");
            navigatorContainer.setWidth("200px");
            this.showTabsCaption();

            toggleBtn.setStyleName(WebUIConstants.BUTTON_ICON_ONLY + " closed-button");
            navigatorContainer.setComponentAlignment(toggleBtn, Alignment.TOP_RIGHT);
            toggleBtn.setIcon(FontAwesome.TIMES);
            toggleBtn.setDescription("");
        }

        CurrentProjectVariables.setProjectToggleMenu(visibility);
    }

    public void addToggleNavigatorControl() {
        if (getButtonById("button") == null) {
            toggleBtn = new ButtonTabImpl("button", 0, "", "");
            toggleBtn.setStyleName(WebUIConstants.BUTTON_ICON_ONLY + " closed-button");
            toggleBtn.addClickListener(clickEvent -> {
                boolean visibility = CurrentProjectVariables.getProjectToggleMenu();
                setNavigatorVisibility(!visibility);
            });
            navigatorContainer.addComponent(toggleBtn, 0);
            navigatorContainer.setComponentAlignment(toggleBtn, Alignment.TOP_RIGHT);
        }

        boolean visibility = CurrentProjectVariables.getProjectToggleMenu();
        setNavigatorVisibility(visibility);
    }
}
