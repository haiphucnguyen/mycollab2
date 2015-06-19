/**
 * This file is part of mycollab-web-premium.
 *
 * mycollab-web-premium is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web-premium is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web-premium.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.premium.module.user.accountsettings.customize.view;

import com.esofthead.mycollab.module.user.accountsettings.customize.view.ICustomizeContainer;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountModule;
import com.esofthead.mycollab.premium.module.user.accountsettings.view.UserAccountExtController;
import com.esofthead.mycollab.vaadin.desktop.ui.ModuleHelper;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ControllerRegistry;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.TabSheetDecorator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
@ViewComponent
public class CustomizeContainer extends AbstractPageView implements ICustomizeContainer {
    private static final long serialVersionUID = -1923841035522809056L;

    private GeneralSettingReadPresenter generalSettingReadPresenter;
    private LogoChangePresenter logoChangePresenter;
    private ColorThemePresenter colorThemePresenter;

    private TabSheetDecorator tabSheetDecorator;

    private String selectedTabId = "";

    public CustomizeContainer() {
        AccountModule accountModule = (AccountModule) ModuleHelper.getCurrentModule();
        ControllerRegistry.addController(new UserAccountExtController(accountModule));
        this.tabSheetDecorator = new TabSheetDecorator();
        this.tabSheetDecorator.setStyleName(UIConstants.THEME_TAB_STYLE3);
        this.addComponent(tabSheetDecorator);
        this.setWidth("100%");
        this.buildComponents();
    }

    private void buildComponents() {
        generalSettingReadPresenter = PresenterResolver.getPresenter(GeneralSettingReadPresenter.class);
        logoChangePresenter = PresenterResolver.getPresenter(LogoChangePresenter.class);
        colorThemePresenter = PresenterResolver.getPresenter(ColorThemePresenter.class);

        this.tabSheetDecorator.addTab(generalSettingReadPresenter.getView(), "General");
        this.tabSheetDecorator.addTab(logoChangePresenter.getView(), "Logo");
        this.tabSheetDecorator.addTab(colorThemePresenter.getView(), "Theme");

        tabSheetDecorator.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                TabSheet.Tab tab = ((TabSheetDecorator) event.getTabSheet())
                        .getSelectedTabInfo();
                String caption = tab.getCaption();
                if ("General".equals(caption)) {
                    generalSettingReadPresenter.go(CustomizeContainer.this, null);
                } else if ("Logo".equals(caption)) {
                    logoChangePresenter.go(CustomizeContainer.this, null);
                } else if ("Theme".equals(caption)) {
                    colorThemePresenter.go(CustomizeContainer.this, null);
                }
            }
        });

    }

    public Component gotoSubView(String name) {
        selectedTabId = name;
        return tabSheetDecorator.selectTab(name).getComponent();
    }
}
