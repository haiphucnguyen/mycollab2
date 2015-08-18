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

import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ControllerRegistry;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.OptionPopupContent;
import com.esofthead.mycollab.vaadin.ui.ServiceMenu;
import com.esofthead.mycollab.web.IDesktopModule;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class ProjectModule extends AbstractPageView implements IDesktopModule {
    private static final long serialVersionUID = 1L;

    public ProjectModule() {
        setStyleName("project-module");
        setSizeFull();
        ControllerRegistry.addController(new ProjectModuleController(this));
    }

    public void gotoProjectPage() {
        UserDashboardPresenter presenter = PresenterResolver.getPresenter(UserDashboardPresenter.class);
        presenter.go(this, null);
    }

    @Override
    public MHorizontalLayout buildMenu() {
        MHorizontalLayout container = new MHorizontalLayout();
        ServiceMenu serviceMenu = new ServiceMenu();
        serviceMenu.addService("Dashboard", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

            }
        });
        serviceMenu.addService("Timesheet", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

            }
        });
        serviceMenu.addService("People", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

            }
        });

        PopupButton addPopup = new PopupButton("Quick Add");
        addPopup.addStyleName("add-project-popup");
        addPopup.setIcon(FontAwesome.PLUS_CIRCLE);
        OptionPopupContent content = new OptionPopupContent();
        addPopup.setContent(content);

        Button newPrjBtn = new Button("Project", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

            }
        });
        content.addOption(newPrjBtn);

        container.with(serviceMenu, addPopup).withAlign(addPopup, Alignment.MIDDLE_LEFT);
        return container;
    }
}
