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

import com.esofthead.mycollab.module.user.accountsettings.customize.view.ISettingContainer;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
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
public class SettingContainer extends AbstractPageView implements ISettingContainer {
    private static final long serialVersionUID = -1923841035522809056L;

    private GeneralSettingPresenter generalSettingPresenter;
    private ThemeCustomizePresenter themeCustomizePresenter;

    private final TabSheetDecorator settingTab;

    private String selectedTabId = "";

    public SettingContainer() {
        this.settingTab = new TabSheetDecorator();
        this.settingTab.setStyleName(UIConstants.THEME_TAB_STYLE3);
        this.addComponent(settingTab);
        this.setWidth("100%");
        this.buildComponents();
    }

    private void buildComponents() {
        generalSettingPresenter = PresenterResolver.getPresenter(GeneralSettingPresenter.class);

        this.settingTab.addTab(this.generalSettingPresenter.getView(), "General Settings");

        themeCustomizePresenter = PresenterResolver.getPresenter(ThemeCustomizePresenter.class);
        this.settingTab.addTab(this.themeCustomizePresenter.getView(), "Theme");

        this.settingTab.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                TabSheet.Tab tab = ((TabSheetDecorator) event.getTabSheet()).getSelectedTabInfo();
                String caption = tab.getCaption();
                if ("General Settings".equals(caption) && !"General Settings".equals(selectedTabId)) {
                    generalSettingPresenter.go(SettingContainer.this, null);
                } else if ("Theme".equals(caption) && !"Theme".equals(selectedTabId)) {
                    themeCustomizePresenter.go(SettingContainer.this, null);
                }
                selectedTabId = "";
            }
        });
    }

    public Component gotoSubView(String name) {
        selectedTabId = name;
        return settingTab.selectTab(name).getComponent();
    }
}
