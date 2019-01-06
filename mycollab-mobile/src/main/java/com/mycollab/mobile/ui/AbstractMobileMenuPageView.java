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
package com.mycollab.mobile.ui;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.PageView;
import com.mycollab.vaadin.ui.ELabel;
import com.vaadin.ui.Component;
import org.vaadin.thomas.slidemenu.SlideMenu;
import org.vaadin.thomas.slidemenu.SlideMenuView;
import org.vaadin.touchkit.ui.NavigationButton;
import org.vaadin.touchkit.ui.NavigationView;

import java.io.Serializable;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
// TODO (should extend SlideMenu - v7)
public abstract class AbstractMobileMenuPageView extends NavigationView implements PageView, Serializable {
    private static final long serialVersionUID = 1L;


    public AbstractMobileMenuPageView() {
        if (this.getLeftComponent() != null && this.getLeftComponent() instanceof NavigationButton) {
            this.getLeftComponent().setCaption(UserUIContext.getMessage(GenericI18Enum.M_BUTTON_BACK));
        }

        buildNavigateMenu();
    }

    public void closeMenu() {
//        getMenu().close();
    }

    public void addSection(String title) {
//        getMenu().addComponent(new ELabel(title).withStyleName(SlideMenu.STYLENAME_SECTIONLABEL));
    }

    public void addMenuItem(Component comp) {
        comp.addStyleName(SlideMenu.STYLENAME_BUTTON);
//        getMenu().addComponent(comp);
    }

    protected abstract void buildNavigateMenu();

//    @Override
//    public void addViewListener(ViewListener listener) {
//        addListener(ViewEvent.VIEW_IDENTIFIER, ViewEvent.class, listener, ViewListener.Companion.getViewInitMethod());
//    }
//
//    @Override
//    public NavigationManager getNavigationManager() {
//        UI ui = UI.getCurrent();
//        if (ui instanceof MobileApplication) {
//            return (NavigationManager) (ui.getContent());
//        }
//        return null;
//    }
}