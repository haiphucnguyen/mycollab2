package com.esofthead.mycollab.premium.module.user.accountsettings.customize.view;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.user.accountsettings.localization.SettingCommonI18nEnum;
import com.esofthead.mycollab.module.user.accountsettings.view.events.SettingEvent;
import com.esofthead.mycollab.module.user.domain.AccountTheme;
import com.esofthead.mycollab.module.user.ui.SettingAssetsManager;
import com.esofthead.mycollab.module.user.ui.SettingUIConstants;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.*;
import com.esofthead.mycollab.web.CustomLayoutExt;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.colorpicker.Color;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.components.colorpicker.ColorChangeEvent;
import com.vaadin.ui.components.colorpicker.ColorChangeListener;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.teemu.VaadinIcons;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.Iterator;

/**
 * @author MyCollab Ltd.
 * @since 4.1.2
 */

@ViewComponent
public class ThemeCustomizeViewImpl extends AbstractPageView implements ThemeCustomizeView {
    private static final long serialVersionUID = 1181278209875228643L;

    private AccountTheme accountTheme;

    public ThemeCustomizeViewImpl() {
        super();
        this.setMargin(true);
    }

    @Override
    public void customizeTheme(AccountTheme theme) {
        this.removeAllComponents();
        accountTheme = theme;
        ThemeManager.loadDemoTheme(accountTheme);

        MVerticalLayout mainBody = new MVerticalLayout().withMargin(new MarginInfo(false, true, true, true));

        // Add customizable blocks
        mainBody.with(constructTopMenuCustomizeBlock(), constructVTabsheetCustomizeBlock(), constructButtonCustomizeBlock());

        MHorizontalLayout controlButtons = new MHorizontalLayout().withWidth("100%").withMargin(new MarginInfo(true,
                true, false, true));

        Label viewTitle = new ELabel(SettingAssetsManager.getAsset(SettingUIConstants.GENERAL_SETTING).getHtml() + " " +
                "Theme Customization", ContentMode.HTML).withStyleName(ValoTheme.LABEL_H2);

        Button saveBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_SAVE), new Button.ClickListener() {
            private static final long serialVersionUID = -6901103392231786935L;

            @Override
            public void buttonClick(ClickEvent event) {
                EventBusFactory.getInstance().post(new SettingEvent.SaveTheme(this, accountTheme));
            }
        });
        saveBtn.setIcon(FontAwesome.SAVE);
        saveBtn.setStyleName(UIConstants.BUTTON_ACTION);
        saveBtn.setEnabled(AppContext.canBeYes(RolePermissionCollections.ACCOUNT_THEME));

        Button resetToDefaultBtn = new Button(AppContext.getMessage(SettingCommonI18nEnum.BUTTON_RESET_DEFAULT), new Button.ClickListener() {
            private static final long serialVersionUID = 5182152510759528123L;

            @Override
            public void buttonClick(ClickEvent event) {
                EventBusFactory.getInstance().post(new SettingEvent.ResetTheme(ThemeCustomizeViewImpl.this, null));
            }
        });
        resetToDefaultBtn.setStyleName(UIConstants.BUTTON_DANGER);
        resetToDefaultBtn.setEnabled(AppContext.canBeYes(RolePermissionCollections.ACCOUNT_THEME));
        controlButtons.with(viewTitle, resetToDefaultBtn, saveBtn).expand(viewTitle);

        this.with(controlButtons, mainBody);
    }

    private Component constructTopMenuCustomizeBlock() {
        FormContainer blockLayout = new FormContainer();

        MHorizontalLayout blockBody = new MHorizontalLayout().withMargin(true).withWidth("100%");
        blockLayout.addSection(AppContext.getMessage(SettingCommonI18nEnum.FORM_TOP_MENU), blockBody);

        GridLayout propertyLayout = new GridLayout(2, 4);
        propertyLayout.setSpacing(true);
        propertyLayout.setColumnExpandRatio(0, 1.0f);
        propertyLayout.setDefaultComponentAlignment(Alignment.TOP_RIGHT);
        propertyLayout.setWidth("250px");

        CustomColorPickerArea topMenuBg = new CustomColorPickerArea(accountTheme.getTopmenubg());
        topMenuBg.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = -3462278784149813444L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                accountTheme.setTopmenubg(event.getColor().getCSS().substring(1).toUpperCase());
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        propertyLayout.addComponent(new Label(AppContext.getMessage(SettingCommonI18nEnum.FORM_NORMAL_MENU)), 0, 0);
        propertyLayout.addComponent(topMenuBg, 1, 0);

        CustomColorPickerArea topMenuText = new CustomColorPickerArea(accountTheme.getTopmenutext());
        topMenuText.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = -1370026552930193996L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                accountTheme.setTopmenutext(event.getColor().getCSS().substring(1).toUpperCase());
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        propertyLayout.addComponent(new Label(AppContext.getMessage(SettingCommonI18nEnum.FORM_NORMAL_MENU_TEXT)), 0, 1);
        propertyLayout.addComponent(topMenuText, 1, 1);

        CustomColorPickerArea topMenuBgSelected = new CustomColorPickerArea(accountTheme.getTopmenubgselected());
        topMenuBgSelected.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = -7897981001643385884L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                accountTheme.setTopmenubgselected(event.getColor().getCSS().substring(1).toUpperCase());
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        propertyLayout.addComponent(new Label("Selected Menu"), 0, 2);
        propertyLayout.addComponent(topMenuBgSelected, 1, 2);

        CustomColorPickerArea topMenuTextSelected = new CustomColorPickerArea(accountTheme.getTopmenutextselected());
        topMenuTextSelected.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = -5381166604049121169L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                accountTheme.setTopmenutextselected(event.getColor().getCSS().substring(1).toUpperCase());
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        propertyLayout.addComponent(new Label("Selected Menu Text"), 0, 3);
        propertyLayout.addComponent(topMenuTextSelected, 1, 3);

        blockBody.addComponent(propertyLayout);

        CustomLayout previewLayout = CustomLayoutExt.createLayout("topNavigation");
        previewLayout.setStyleName("example-block");
        previewLayout.setHeight("40px");
        previewLayout.setWidth("520px");

        Button currentLogo = AccountAssetsResolver.createAccountLogoImageComponent(null, 150);
        previewLayout.addComponent(currentLogo, "mainLogo");
        final ServiceMenu serviceMenu = new ServiceMenu();

        Button.ClickListener clickListener = new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent event) {
                Iterator<Component> iterator = serviceMenu.iterator();

                while (iterator.hasNext()) {
                    Component comp = iterator.next();
                    if (comp != event.getButton()) {
                        comp.removeStyleName("selected");
                    }
                }
                event.getButton().addStyleName("selected");
            }
        };

        serviceMenu.addService(AppContext.getMessage(GenericI18Enum.MODULE_CRM), VaadinIcons.MONEY, clickListener);
        serviceMenu.addService(AppContext.getMessage(GenericI18Enum.MODULE_PROJECT), VaadinIcons.TASKS, clickListener);
        serviceMenu.addService(AppContext.getMessage(GenericI18Enum.MODULE_DOCUMENT), VaadinIcons.SUITCASE, clickListener);
        serviceMenu.selectService(0);

        previewLayout.addComponent(serviceMenu, "serviceMenu");
        blockBody.addComponent(previewLayout);
        blockBody.setComponentAlignment(previewLayout, Alignment.MIDDLE_CENTER);
        blockBody.setExpandRatio(previewLayout, 1.0f);

        return blockLayout;
    }

    private Component constructTabsheetCustomizeBlock() {
        FormContainer blockLayout = new FormContainer();

        MHorizontalLayout blockBody = new MHorizontalLayout().withMargin(new MarginInfo(false, true, false, true)).withWidth("100%");
        blockLayout.addSection("Tab Sheet", blockBody);

        GridLayout propertyLayout = new GridLayout(2, 4);
        propertyLayout.setSpacing(true);
        propertyLayout.setStyleName(UIConstants.THEME_NO_BORDER);
        propertyLayout.setColumnExpandRatio(0, 1.0f);
        propertyLayout.setDefaultComponentAlignment(Alignment.TOP_RIGHT);
        propertyLayout.setWidth("250px");

        CustomColorPickerArea tabsheetBg = new CustomColorPickerArea(accountTheme.getTabsheetbg());
        tabsheetBg.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = -675674373089622451L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                accountTheme.setTabsheetbg(event.getColor().getCSS().substring(1).toUpperCase());
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        propertyLayout.addComponent(new Label("Normal Tab"), 0, 0);
        propertyLayout.addComponent(tabsheetBg, 1, 0);

        CustomColorPickerArea tabsheetText = new CustomColorPickerArea(accountTheme.getTabsheettext());
        tabsheetText.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = 3487570137637191332L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                accountTheme.setTabsheettext(event.getColor().getCSS().substring(1).toUpperCase());
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        propertyLayout.addComponent(new Label("Normal Tab Text"), 0, 1);
        propertyLayout.addComponent(tabsheetText, 1, 1);

        CustomColorPickerArea tabsheetBgSelected = new CustomColorPickerArea(accountTheme.getTabsheetbgselected());
        tabsheetBgSelected.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = -2435453092194064504L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                accountTheme.setTabsheetbgselected(event.getColor().getCSS().substring(1).toUpperCase());
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        propertyLayout.addComponent(new Label("Selected Tab"), 0, 2);
        propertyLayout.addComponent(tabsheetBgSelected, 1, 2);

        CustomColorPickerArea tabsheetTextSelected = new CustomColorPickerArea(accountTheme.getTabsheettextselected());
        tabsheetTextSelected.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = 3190273972739835060L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                accountTheme.setTabsheettextselected(event.getColor().getCSS().substring(1).toUpperCase());
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        propertyLayout.addComponent(new Label("Selected Tab Text"), 0, 3);
        propertyLayout.addComponent(tabsheetTextSelected, 1, 3);

        blockBody.addComponent(propertyLayout);

        CssLayout previewLayout = new CssLayout();
        previewLayout.setStyleName("example-block");
        previewLayout.setWidth("520px");
        blockBody.addComponent(previewLayout);
        blockBody.setComponentAlignment(previewLayout, Alignment.MIDDLE_CENTER);
        blockBody.setExpandRatio(previewLayout, 1.0f);

        TabSheet tabsheetDemo = new TabSheet();
        tabsheetDemo.addStyleName(UIConstants.THEME_TAB_STYLE3);
        tabsheetDemo.setWidth("100%");

        tabsheetDemo.addTab(new VerticalLayout(), "Dashboard");
        tabsheetDemo.addTab(new VerticalLayout(), "Bugs");
        tabsheetDemo.addTab(new VerticalLayout(), "Components");
        tabsheetDemo.addTab(new VerticalLayout(), "Versions");
        previewLayout.addComponent(tabsheetDemo);

        return blockLayout;
    }

    private Component constructVTabsheetCustomizeBlock() {
        FormContainer blockLayout = new FormContainer();
        MHorizontalLayout blockBody = new MHorizontalLayout().withMargin(true).withWidth("100%");

        blockLayout.addSection("Vertical Menu", blockBody);

        GridLayout propertyLayout = new GridLayout(2, 4);
        propertyLayout.setSpacing(true);
        propertyLayout.setColumnExpandRatio(0, 1.0f);
        propertyLayout.setDefaultComponentAlignment(Alignment.TOP_RIGHT);
        propertyLayout.setWidth("250px");

        CustomColorPickerArea vTabsheetBg = new CustomColorPickerArea(accountTheme.getVtabsheetbg());
        vTabsheetBg.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = -675674373089622451L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                accountTheme.setVtabsheetbg(event.getColor().getCSS().substring(1).toUpperCase());
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        propertyLayout.addComponent(new Label("Normal Menu"), 0, 0);
        propertyLayout.addComponent(vTabsheetBg, 1, 0);

        CustomColorPickerArea vTabsheetText = new CustomColorPickerArea(accountTheme.getVtabsheettext());
        vTabsheetText.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = 3487570137637191332L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                accountTheme.setVtabsheettext(event.getColor().getCSS()
                        .substring(1).toUpperCase());
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        propertyLayout.addComponent(new Label("Normal Menu Text"), 0, 1);
        propertyLayout.addComponent(vTabsheetText, 1, 1);

        CustomColorPickerArea vTabsheetBgSelected = new CustomColorPickerArea(accountTheme.getVtabsheetbgselected());
        vTabsheetBgSelected.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = -2435453092194064504L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                accountTheme.setVtabsheetbgselected(event.getColor().getCSS()
                        .substring(1).toUpperCase());
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        propertyLayout.addComponent(new Label("Selected Menu"), 0, 2);
        propertyLayout.addComponent(vTabsheetBgSelected, 1, 2);

        CustomColorPickerArea vTabsheetTextSelected = new CustomColorPickerArea(accountTheme.getVtabsheettextselected());
        vTabsheetTextSelected.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = 3190273972739835060L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                accountTheme.setVtabsheettextselected(event.getColor().getCSS()
                        .substring(1).toUpperCase());
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        propertyLayout.addComponent(new Label("Selected Menu Text"), 0, 3);
        propertyLayout.addComponent(vTabsheetTextSelected, 1, 3);

        blockBody.addComponent(propertyLayout);

        CssLayout previewLayout = new CssLayout();
        previewLayout.setStyleName("example-block");
        previewLayout.setWidth("520px");
        CssLayout verticalTabsheetFix = new CssLayout();
        verticalTabsheetFix.setWidth("250px");
        previewLayout.addComponent(verticalTabsheetFix);
        blockBody.addComponent(previewLayout);
        blockBody.setComponentAlignment(previewLayout, Alignment.MIDDLE_CENTER);
        blockBody.setExpandRatio(previewLayout, 1.0f);

        VerticalTabsheet tabsheetDemo = new VerticalTabsheet();
        tabsheetDemo.setWidth("100%");
        tabsheetDemo.getNavigatorWrapper().addStyleName("sidebar-menu");
        tabsheetDemo.getNavigatorWrapper().setWidth("250px");

        tabsheetDemo.addTab(new VerticalLayout(), "dashboard", "Dashboard");
        tabsheetDemo.addTab(new VerticalLayout(), "bugs", "Bugs");
        tabsheetDemo.addTab(new VerticalLayout(), "components", "Components");

        tabsheetDemo.selectTab("dashboard");

        previewLayout.addComponent(tabsheetDemo);

        return blockLayout;
    }

    private Component constructButtonCustomizeBlock() {
        FormContainer blockLayout = new FormContainer();

        MVerticalLayout blockBody = new MVerticalLayout();
        blockLayout.addSection("Buttons", blockBody);

        GridLayout propertyLayout = new GridLayout(3, 1);
        propertyLayout.setStyleName("example-block");
        propertyLayout.addStyleName(UIConstants.THEME_NO_BORDER);
        propertyLayout.setSpacing(true);
        blockBody.addComponent(propertyLayout);

        // Action Button
        VerticalLayout actionBtnPanel = new VerticalLayout();
        actionBtnPanel.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        actionBtnPanel.setSizeUndefined();
        actionBtnPanel.setSpacing(true);
        propertyLayout.addComponent(actionBtnPanel, 0, 0);

        Button exampleActionBtn = new Button("Button");
        exampleActionBtn.setStyleName(UIConstants.BUTTON_ACTION);
        actionBtnPanel.addComponent(exampleActionBtn);

        HorizontalLayout actionBtnColorPane = new HorizontalLayout();
        actionBtnColorPane
                .setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        actionBtnColorPane.setSpacing(true);
        actionBtnPanel.addComponent(actionBtnColorPane);

        CustomColorPickerArea actionBtnBg = new CustomColorPickerArea(accountTheme.getActionbtn());
        actionBtnBg.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = -3852566371241071966L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                String colorHexString = event.getColor().getCSS().substring(1)
                        .toUpperCase();
                accountTheme.setActionbtn(colorHexString);
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        actionBtnColorPane.addComponent(actionBtnBg);

        CustomColorPickerArea actionBtnText = new CustomColorPickerArea(accountTheme.getActionbtntext());
        actionBtnText.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = 7947045019055649130L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                String colorHexString = event.getColor().getCSS().substring(1)
                        .toUpperCase();
                accountTheme.setActionbtntext(colorHexString);
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        actionBtnColorPane.addComponent(actionBtnText);

        // Option Button

        VerticalLayout optionBtnPanel = new VerticalLayout();
        optionBtnPanel.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        optionBtnPanel.setSizeUndefined();
        optionBtnPanel.setSpacing(true);
        optionBtnPanel.setMargin(false);
        propertyLayout.addComponent(optionBtnPanel, 1, 0);

        Button exampleOptionBtn = new Button("Button");
        exampleOptionBtn.setStyleName(UIConstants.THEME_GRAY_LINK);
        optionBtnPanel.addComponent(exampleOptionBtn);

        HorizontalLayout optionBtnColorPane = new HorizontalLayout();
        optionBtnColorPane.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        optionBtnColorPane.setSpacing(true);
        optionBtnPanel.addComponent(optionBtnColorPane);

        CustomColorPickerArea optionBtnBg = new CustomColorPickerArea(accountTheme.getOptionbtn());
        optionBtnBg.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = -3852566371241071966L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                String colorHexString = event.getColor().getCSS().substring(1).toUpperCase();
                accountTheme.setOptionbtn(colorHexString);
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        optionBtnColorPane.addComponent(optionBtnBg);

        CustomColorPickerArea optionBtnText = new CustomColorPickerArea(accountTheme.getOptionbtntext());
        optionBtnText.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = 7947045019055649130L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                String colorHexString = event.getColor().getCSS().substring(1).toUpperCase();
                accountTheme.setOptionbtntext(colorHexString);
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        optionBtnColorPane.addComponent(optionBtnText);

        // Danger Button
        MVerticalLayout dangerBtnPanel = new MVerticalLayout().withMargin(false);
        dangerBtnPanel.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        dangerBtnPanel.setSizeUndefined();
        propertyLayout.addComponent(dangerBtnPanel, 2, 0);

        Button exampleDangerBtn = new Button("Button");
        exampleDangerBtn.setStyleName(UIConstants.BUTTON_DANGER);
        dangerBtnPanel.addComponent(exampleDangerBtn);

        HorizontalLayout dangerBtnColorPane = new HorizontalLayout();
        dangerBtnColorPane
                .setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        dangerBtnColorPane.setSpacing(true);
        dangerBtnPanel.addComponent(dangerBtnColorPane);

        CustomColorPickerArea dangerBtnBg = new CustomColorPickerArea(accountTheme.getDangerbtn());
        dangerBtnBg.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = -3852566371241071966L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                String colorHexString = event.getColor().getCSS().substring(1).toUpperCase();
                accountTheme.setDangerbtn(colorHexString);
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        dangerBtnColorPane.addComponent(dangerBtnBg);

        CustomColorPickerArea dangerBtnText = new CustomColorPickerArea(accountTheme.getDangerbtntext());
        dangerBtnText.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = 7947045019055649130L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                String colorHexString = event.getColor().getCSS().substring(1).toUpperCase();
                accountTheme.setDangerbtntext(colorHexString);
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        dangerBtnColorPane.addComponent(dangerBtnText);

        return blockLayout;
    }

    private static class CustomColorPickerArea extends ColorPickerArea {
        private static final long serialVersionUID = -8631349584720412229L;

        public CustomColorPickerArea(String initialColor) {
            super();
            if (initialColor != null) {
                this.setColor(new Color(Integer.parseInt(initialColor, 16)));
            }

            this.setWidth("55px");
            this.setPopupStyle(PopupStyle.POPUP_NORMAL);
            this.setPosition(Page.getCurrent().getBrowserWindowWidth() / 2 - 248 / 2, Page
                    .getCurrent().getBrowserWindowHeight() / 2 - 508 / 2);
        }
    }
}
