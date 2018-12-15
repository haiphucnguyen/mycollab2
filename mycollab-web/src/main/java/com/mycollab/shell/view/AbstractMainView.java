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
package com.mycollab.shell.view;

import com.google.common.eventbus.Subscribe;
import com.mycollab.shell.event.ShellEvent;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.ApplicationEventListener;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.vaadin.mvp.AbstractVerticalPageView;
import com.mycollab.vaadin.mvp.ControllerRegistry;
import com.mycollab.vaadin.ui.AccountAssetsResolver;
import com.mycollab.vaadin.web.ui.ModuleHelper;
import com.mycollab.vaadin.web.ui.OptionPopupContent;
import com.mycollab.web.CustomLayoutExt;
import com.mycollab.web.IDesktopModule;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomLayout;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
public abstract class AbstractMainView extends AbstractVerticalPageView implements MainView {
    private static final long serialVersionUID = 1L;

    private CustomLayout headerLayout;
    private MHorizontalLayout bodyLayout;
    protected MHorizontalLayout accountLayout;

    private ApplicationEventListener<ShellEvent.RefreshPage> pageRefreshHandler = new ApplicationEventListener<ShellEvent.RefreshPage>() {
        @Override
        @Subscribe
        public void handle(ShellEvent.RefreshPage event) {
            buildAccountMenuLayout();
        }
    };

    public AbstractMainView() {
        this.setSizeFull();
        ControllerRegistry.addController(new MainViewController(this));
    }

    @Override
    public void attach() {
        super.attach();
        EventBusFactory.getInstance().register(pageRefreshHandler);
    }

    @Override
    public void detach() {
        EventBusFactory.getInstance().unregister(pageRefreshHandler);
        super.detach();
    }

    @Override
    public void display() {
        this.removeAllComponents();
        bodyLayout = new MHorizontalLayout().withSpacing(false).withStyleName("main-view").withId("main-body")
                .withFullHeight().withFullWidth();
        this.with(createMainMenu(), bodyLayout).expand(bodyLayout);
    }

    @Override
    public void addModule(IDesktopModule module) {
        headerLayout.removeComponent("serviceMenu");
        ModuleHelper.setCurrentModule(module);
        bodyLayout.removeAllComponents();
        bodyLayout.with(module).expand(module);

        MHorizontalLayout serviceMenu = module.buildMenu();
        if (serviceMenu != null) {
            headerLayout.addComponent(serviceMenu, "serviceMenu");
        }
    }

    private CustomLayout createMainMenu() {
        headerLayout = CustomLayoutExt.createLayout("topNavigation");
        headerLayout.setStyleName("topNavigation");
        headerLayout.setHeight("100%");
        headerLayout.setWidth("45px");

        final PopupButton modulePopup = new PopupButton("");
        modulePopup.setIcon(AccountAssetsResolver.createLogoResource(AppUI.getBillingAccount().getLogopath(), 150));
        modulePopup.setHeightUndefined();
        modulePopup.setDirection(Alignment.BOTTOM_LEFT);
        OptionPopupContent modulePopupContent = new OptionPopupContent();
        modulePopup.setContent(modulePopupContent);

        headerLayout.addComponent(new MHorizontalLayout(modulePopup).alignAll(Alignment.MIDDLE_LEFT).withFullHeight(), "mainLogo");

        accountLayout = new MHorizontalLayout().withMargin(new MarginInfo(false, true, false, false)).withHeight("45px");
        accountLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
//        buildAccountMenuLayout();

        headerLayout.addComponent(accountLayout, "accountMenu");
        return headerLayout;
    }

    protected abstract MHorizontalLayout buildAccountMenuLayout();
}
