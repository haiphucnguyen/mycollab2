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
package com.esofthead.mycollab.mobile.ui;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.PageView;
import com.esofthead.mycollab.vaadin.mvp.ViewEvent;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Label;
import org.vaadin.thomas.slidemenu.SlideMenu;
import org.vaadin.thomas.slidemenu.SlideMenuView;

import java.io.Serializable;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public abstract class AbstractMobilePageView extends NavigationView implements PageView, Serializable {
    private static final long serialVersionUID = 1L;

    public AbstractMobilePageView() {
        super();
        if (this.getLeftComponent() != null && this.getLeftComponent() instanceof NavigationButton) {
            this.getLeftComponent().setCaption(AppContext.getMessage(GenericI18Enum.M_BUTTON_BACK));
        }

//        buildMenu();
    }

    private void buildMenu() {

        // Just a normal Vaadin button
//        final Button close = new Button("close menu");
//        close.setWidth(null);
//        close.addClickListener(new Button.ClickListener() {
//
//            private static final long serialVersionUID = -1692006683791129470L;
//
//            @Override
//            public void buttonClick(Button.ClickEvent event) {
//                // Programmatic closing of the menu
//                getMenu().close();
//            }
//        });
//        getMenu().addComponent(close);
//
//        // Section labels have a bolded style
//        Label l = new Label("Sections:");
//        l.addStyleName(SlideMenu.STYLENAME_SECTIONLABEL);
//        getMenu().addComponent(l);
//
//        // Buttons with styling (slightly smaller with left-aligned text)
//        Button b = new Button("Dashboard");
//        b.addStyleName(SlideMenu.STYLENAME_BUTTON);
//        getMenu().addComponent(b);
//
//        b.addClickListener(new Button.ClickListener() {
//
//            private static final long serialVersionUID = -194718083859615332L;
//
//            @Override
//            public void buttonClick(Button.ClickEvent event) {
//
//                // TODO automate with the nav listener
//                getMenu().close();
//
//                // Only this button actually does something in the menu. Here we
//                // navigate to a dummy view.
//                getNavigationManager().navigateTo(new NavigationView() {
//                    private static final long serialVersionUID = 7226460754270812124L;
//
//                    {
//                        setContent(new Label("another view"));
//                        setCaption("DashBoard");
//                    }
//                });
//            }
//        });
//
//        // add more buttons for a more realistic look.
//        b = new Button("Inbox");
//        b.addStyleName(SlideMenu.STYLENAME_BUTTON);
//        getMenu().addComponent(b);
//
//        b = new Button("Admin");
//        b.addStyleName(SlideMenu.STYLENAME_BUTTON);
//        getMenu().addComponent(b);
//
//        l = new Label("Settings:");
//        l.addStyleName(SlideMenu.STYLENAME_SECTIONLABEL);
//        getMenu().addComponent(l);
//
//        b = new Button("Options");
//        b.addStyleName(SlideMenu.STYLENAME_BUTTON);
//        getMenu().addComponent(b);
//        b = new Button("Logout");
//        b.addStyleName(SlideMenu.STYLENAME_BUTTON);
//        getMenu().addComponent(b);
    }

    @Override
    public ComponentContainer getWidget() {
        return this;
    }

    @Override
    public void addViewListener(ViewListener listener) {
        addListener(ViewEvent.VIEW_IDENTIFIER(), ViewEvent.class, listener, ViewListener.viewInitMethod);
    }

//    @Override
//    public NavigationManager getNavigationManager() {
//        Component parent = this.getParent();
//        while (parent != null) {
//            if (parent instanceof NavigationManager)
//                return (NavigationManager) parent;
//            else
//                parent = parent.getParent();
//        }
//        return null;
//    }
}