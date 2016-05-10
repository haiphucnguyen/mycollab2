package com.esofthead.mycollab.module.user.accountsettings.customize.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.web.ui.TabSheetDecorator;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
@ViewComponent
public class SettingContainer extends AbstractPageView {
    private static final long serialVersionUID = -1923841035522809056L;

    private GeneralSettingPresenter generalSettingPresenter;
    private IThemeCustomizePresenter themeCustomizePresenter;

    private final TabSheetDecorator settingTab;

    private String selectedTabId = "";

    public SettingContainer() {
        settingTab = new TabSheetDecorator();
        this.addComponent(settingTab);
        this.setWidth("100%");
        this.buildComponents();
    }

    private void buildComponents() {
        generalSettingPresenter = PresenterResolver.getPresenter(GeneralSettingPresenter.class);
        settingTab.addTab(this.generalSettingPresenter.getView(), "General Settings");

        themeCustomizePresenter = PresenterResolver.getPresenter(IThemeCustomizePresenter.class);
        settingTab.addTab(this.themeCustomizePresenter.getView(), "Theme");

        settingTab.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
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
