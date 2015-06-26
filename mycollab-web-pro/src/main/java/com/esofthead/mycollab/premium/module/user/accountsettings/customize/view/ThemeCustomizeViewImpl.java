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
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.components.colorpicker.ColorChangeEvent;
import com.vaadin.ui.components.colorpicker.ColorChangeListener;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

import java.util.Iterator;

/**
 * @author MyCollab Ltd.
 * @since 4.1.2
 */

@ViewComponent
public class ThemeCustomizeViewImpl extends AbstractPageView implements
        ThemeCustomizeView {
    private static final long serialVersionUID = 1181278209875228643L;

    private AddViewLayout2 mainLayout;
    private AccountTheme accountTheme;

    public ThemeCustomizeViewImpl() {
        super();
        this.setMargin(new MarginInfo(false, true, true, true));
    }

    private AddViewLayout2 initUI() {
        AddViewLayout2 mainLayout = new AddViewLayout2("Theme Customization",
                SettingAssetsManager.getAsset(SettingUIConstants.GENERAL_SETTING));
        mainLayout.setWidth("100%");
        mainLayout.addStyleName("theme-customize-view");
        return mainLayout;
    }

    @Override
    public void customizeTheme(AccountTheme theme) {
        this.removeAllComponents();
        mainLayout = initUI();
        this.addComponent(mainLayout);
        accountTheme = theme;
        ThemeManager.loadDemoTheme(accountTheme);
        mainLayout.getBody().removeAllComponents();

        VerticalLayout mainBody = new VerticalLayout();
        mainBody.setSpacing(true);

        // Add customizable blocks
        mainBody.addComponent(constructTopMenuCustomizeBlock());
        mainBody.addComponent(constructTabsheetCustomizeBlock());
        mainBody.addComponent(constructVTabsheetCustomizeBlock());
        mainBody.addComponent(constructButtonCustomizeBlock());

        MHorizontalLayout controlButton = new MHorizontalLayout();

        Button saveBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_SAVE),
                new Button.ClickListener() {
                    private static final long serialVersionUID = -6901103392231786935L;

                    @Override
                    public void buttonClick(ClickEvent event) {
                        EventBusFactory.getInstance().post(
                                new SettingEvent.SaveTheme(this, accountTheme));
                    }
                });
        saveBtn.setIcon(FontAwesome.SAVE);
        saveBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
        saveBtn.setEnabled(AppContext.canBeYes(RolePermissionCollections.ACCOUNT_THEME));
        controlButton.addComponent(saveBtn);

        Button resetToDefaultBtn = new Button(AppContext.getMessage(SettingCommonI18nEnum.BUTTON_RESET_DEFAULT),
                new Button.ClickListener() {
                    private static final long serialVersionUID = 5182152510759528123L;

                    @Override
                    public void buttonClick(ClickEvent event) {
                        EventBusFactory.getInstance().post(
                                new SettingEvent.ResetTheme(ThemeCustomizeViewImpl.this, null));
                    }
                });
        resetToDefaultBtn.setStyleName(UIConstants.THEME_RED_LINK);
        resetToDefaultBtn.setEnabled(AppContext.canBeYes(RolePermissionCollections.ACCOUNT_THEME));
        controlButton.addComponent(resetToDefaultBtn);
        controlButton.setExpandRatio(resetToDefaultBtn, 1.0f);

        mainLayout.addBody(mainBody);
        mainLayout.addControlButtons(controlButton);
    }

    private Component constructTopMenuCustomizeBlock() {
        VerticalLayout blockLayout = new VerticalLayout();
        Label blockHeader = new Label(AppContext.getMessage(SettingCommonI18nEnum.FORM_TOP_MENU));
        blockHeader.setStyleName("block-hdr");
        blockHeader.addStyleName("h2");
        blockLayout.addComponent(blockHeader);
        blockLayout.setSpacing(true);

        MHorizontalLayout blockBody = new MHorizontalLayout().withMargin(new MarginInfo(false, true, false, true))
                .withWidth("100%");
        blockLayout.addComponent(blockBody);

        GridLayout propertyLayout = new GridLayout(2, 4);
        propertyLayout.setSpacing(true);
        propertyLayout.setStyleName("no-border");
        propertyLayout.setColumnExpandRatio(0, 1.0f);
        propertyLayout.setDefaultComponentAlignment(Alignment.TOP_RIGHT);
        propertyLayout.setWidth("250px");

        CustomColorPickerArea topMenuBg = new CustomColorPickerArea(
                AppContext.getMessage(SettingCommonI18nEnum.FORM_NORMAL_TAB),
                accountTheme.getTopmenubg());
        topMenuBg.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = -3462278784149813444L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                accountTheme.setTopmenubg(event.getColor().getCSS()
                        .substring(1).toUpperCase());
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        propertyLayout.addComponent(new Label(AppContext
                .getMessage(SettingCommonI18nEnum.FORM_NORMAL_MENU)), 0, 0);
        propertyLayout.addComponent(topMenuBg, 1, 0);

        CustomColorPickerArea topMenuText = new CustomColorPickerArea(
                AppContext.getMessage(SettingCommonI18nEnum.FORM_NORMAL_TAB_TEXT),
                accountTheme.getTopmenutext());
        topMenuText.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = -1370026552930193996L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                accountTheme.setTopmenutext(event.getColor().getCSS()
                        .substring(1).toUpperCase());
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        propertyLayout.addComponent(new Label(AppContext
                        .getMessage(SettingCommonI18nEnum.FORM_NORMAL_MENU_TEXT)),
                0, 1);
        propertyLayout.addComponent(topMenuText, 1, 1);

        CustomColorPickerArea topMenuBgSelected = new CustomColorPickerArea(
                "Selected Tab", accountTheme.getTopmenubgselected());
        topMenuBgSelected.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = -7897981001643385884L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                accountTheme.setTopmenubgselected(event.getColor().getCSS()
                        .substring(1).toUpperCase());
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        propertyLayout.addComponent(new Label("Selected Menu"), 0, 2);
        propertyLayout.addComponent(topMenuBgSelected, 1, 2);

        CustomColorPickerArea topMenuTextSelected = new CustomColorPickerArea(
                "Selected Tab Text", accountTheme.getTopmenutextselected());
        topMenuTextSelected.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = -5381166604049121169L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                accountTheme.setTopmenutextselected(event.getColor().getCSS()
                        .substring(1).toUpperCase());
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        propertyLayout.addComponent(new Label("Selected Menu Text"), 0, 3);
        propertyLayout.addComponent(topMenuTextSelected, 1, 3);

        blockBody.addComponent(propertyLayout);

        CustomLayout previewLayout = CustomLayoutExt
                .createLayout("topNavigation");
        previewLayout.setStyleName("example-block");
        previewLayout.setHeight("40px");
        previewLayout.setWidth("520px");

        Button currentLogo = AccountAssetsResolver.createAccountLogoImageComponent(
                null, 150);
        previewLayout.addComponent(currentLogo, "mainLogo");
        final ServiceMenu serviceMenu = new ServiceMenu();
        serviceMenu.addStyleName("topNavPopup");

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

        serviceMenu.addService(
                AppContext.getMessage(GenericI18Enum.MODULE_CRM),
                new AssetResource(WebResourceIds._16_customer),
                clickListener);

        serviceMenu.selectService(0);

        serviceMenu.addService(
                AppContext.getMessage(GenericI18Enum.MODULE_PROJECT),
                new AssetResource(WebResourceIds._16_project),
                clickListener);

        serviceMenu.addService(
                AppContext.getMessage(GenericI18Enum.MODULE_DOCUMENT),
                new AssetResource(WebResourceIds._16_document),
                clickListener);

        previewLayout.addComponent(serviceMenu, "serviceMenu");
        blockBody.addComponent(previewLayout);
        blockBody.setComponentAlignment(previewLayout, Alignment.MIDDLE_CENTER);
        blockBody.setExpandRatio(previewLayout, 1.0f);

        return blockLayout;
    }

    private Component constructTabsheetCustomizeBlock() {
        VerticalLayout blockLayout = new VerticalLayout();
        Label blockHeader = new Label("Tab Sheet");
        blockHeader.setStyleName("block-hdr");
        blockHeader.addStyleName("h2");
        blockLayout.addComponent(blockHeader);
        blockLayout.setSpacing(true);

        HorizontalLayout blockBody = new HorizontalLayout();
        blockBody.setMargin(new MarginInfo(false, true, false, true));
        blockBody.setWidth("100%");
        blockBody.setSpacing(true);
        blockLayout.addComponent(blockBody);

        GridLayout propertyLayout = new GridLayout(2, 4);
        propertyLayout.setSpacing(true);
        propertyLayout.setStyleName("no-border");
        propertyLayout.setColumnExpandRatio(0, 1.0f);
        propertyLayout.setDefaultComponentAlignment(Alignment.TOP_RIGHT);
        propertyLayout.setWidth("250px");

        CustomColorPickerArea tabsheetBg = new CustomColorPickerArea(
                "Normal Tab", accountTheme.getTabsheetbg());
        tabsheetBg.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = -675674373089622451L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                accountTheme.setTabsheetbg(event.getColor().getCSS()
                        .substring(1).toUpperCase());
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        propertyLayout.addComponent(new Label("Normal Tab"), 0, 0);
        propertyLayout.addComponent(tabsheetBg, 1, 0);

        CustomColorPickerArea tabsheetText = new CustomColorPickerArea(
                "Normal Tab Text", accountTheme.getTabsheettext());
        tabsheetText.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = 3487570137637191332L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                accountTheme.setTabsheettext(event.getColor().getCSS()
                        .substring(1).toUpperCase());
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        propertyLayout.addComponent(new Label("Normal Tab Text"), 0, 1);
        propertyLayout.addComponent(tabsheetText, 1, 1);

        CustomColorPickerArea tabsheetBgSelected = new CustomColorPickerArea(
                "Selected Tab", accountTheme.getTabsheetbgselected());
        tabsheetBgSelected.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = -2435453092194064504L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                accountTheme.setTabsheetbgselected(event.getColor().getCSS()
                        .substring(1).toUpperCase());
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        propertyLayout.addComponent(new Label("Selected Tab"), 0, 2);
        propertyLayout.addComponent(tabsheetBgSelected, 1, 2);

        CustomColorPickerArea tabsheetTextSelected = new CustomColorPickerArea(
                "Selected Tab Text", accountTheme.getTabsheettextselected());
        tabsheetTextSelected.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = 3190273972739835060L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                accountTheme.setTabsheettextselected(event.getColor().getCSS()
                        .substring(1).toUpperCase());
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
        VerticalLayout blockLayout = new VerticalLayout();
        Label blockHeader = new Label("Vertical Menu");
        blockHeader.setStyleName("block-hdr");
        blockHeader.addStyleName("h2");
        blockLayout.addComponent(blockHeader);
        blockLayout.setSpacing(true);

        HorizontalLayout blockBody = new HorizontalLayout();
        blockBody.setMargin(new MarginInfo(false, true, false, true));
        blockBody.setWidth("100%");
        blockBody.setSpacing(true);
        blockLayout.addComponent(blockBody);

        GridLayout propertyLayout = new GridLayout(2, 4);
        propertyLayout.setSpacing(true);
        propertyLayout.setStyleName("no-border");
        propertyLayout.setColumnExpandRatio(0, 1.0f);
        propertyLayout.setDefaultComponentAlignment(Alignment.TOP_RIGHT);
        propertyLayout.setWidth("250px");

        CustomColorPickerArea vTabsheetBg = new CustomColorPickerArea(
                "Normal Menu", accountTheme.getVtabsheetbg());
        vTabsheetBg.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = -675674373089622451L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                accountTheme.setVtabsheetbg(event.getColor().getCSS()
                        .substring(1).toUpperCase());
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        propertyLayout.addComponent(new Label("Normal Menu"), 0, 0);
        propertyLayout.addComponent(vTabsheetBg, 1, 0);

        CustomColorPickerArea vTabsheetText = new CustomColorPickerArea(
                "Normal Menu Text", accountTheme.getVtabsheettext());
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

        CustomColorPickerArea vTabsheetBgSelected = new CustomColorPickerArea(
                "Selected Menu", accountTheme.getVtabsheetbgselected());
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

        CustomColorPickerArea vTabsheetTextSelected = new CustomColorPickerArea(
                "Selected Menu Text", accountTheme.getVtabsheettextselected());
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
        verticalTabsheetFix.setStyleName("verticaltabsheet-fix");
        verticalTabsheetFix.addStyleName("is-left");
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
        MVerticalLayout blockLayout = new MVerticalLayout().withMargin(new MarginInfo(false, false, true, false));
        Label blockHeader = new Label("Buttons");
        blockHeader.setStyleName("block-hdr");
        blockHeader.addStyleName("h2");
        blockLayout.addComponent(blockHeader);

        VerticalLayout blockBody = new VerticalLayout();
        blockBody.setMargin(new MarginInfo(false, true, false, true));
        blockLayout.addComponent(blockBody);

        GridLayout propertyLayout = new GridLayout(3, 2);
        propertyLayout.setStyleName("example-block");
        propertyLayout.addStyleName("no-border");
        propertyLayout.setSpacing(true);
        blockBody.addComponent(propertyLayout);

        // Action Button

        VerticalLayout actionBtnPanel = new VerticalLayout();
        actionBtnPanel.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        actionBtnPanel.setSizeUndefined();
        actionBtnPanel.setSpacing(true);
        propertyLayout.addComponent(actionBtnPanel, 0, 0);

        Button exampleActionBtn = new Button("Button");
        exampleActionBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
        actionBtnPanel.addComponent(exampleActionBtn);

        HorizontalLayout actionBtnColorPane = new HorizontalLayout();
        actionBtnColorPane
                .setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        actionBtnColorPane.setSpacing(true);
        actionBtnPanel.addComponent(actionBtnColorPane);

        CustomColorPickerArea actionBtnBg = new CustomColorPickerArea(
                "Button Background", accountTheme.getActionbtn());
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

        CustomColorPickerArea actionBtnText = new CustomColorPickerArea(
                "Button Text", accountTheme.getActionbtntext());
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
        optionBtnPanel.setMargin(new MarginInfo(false, false, false, true));
        propertyLayout.addComponent(optionBtnPanel, 1, 0);

        Button exampleOptionBtn = new Button("Button");
        exampleOptionBtn.setStyleName(UIConstants.THEME_GRAY_LINK);
        optionBtnPanel.addComponent(exampleOptionBtn);

        HorizontalLayout optionBtnColorPane = new HorizontalLayout();
        optionBtnColorPane
                .setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        optionBtnColorPane.setSpacing(true);
        optionBtnPanel.addComponent(optionBtnColorPane);

        CustomColorPickerArea optionBtnBg = new CustomColorPickerArea(
                "Button Background", accountTheme.getOptionbtn());
        optionBtnBg.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = -3852566371241071966L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                String colorHexString = event.getColor().getCSS().substring(1)
                        .toUpperCase();
                accountTheme.setOptionbtn(colorHexString);
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        optionBtnColorPane.addComponent(optionBtnBg);

        CustomColorPickerArea optionBtnText = new CustomColorPickerArea(
                "Button Text", accountTheme.getOptionbtntext());
        optionBtnText.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = 7947045019055649130L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                String colorHexString = event.getColor().getCSS().substring(1)
                        .toUpperCase();
                accountTheme.setOptionbtntext(colorHexString);
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        optionBtnColorPane.addComponent(optionBtnText);

        // Control Button

        VerticalLayout controlBtnPanel = new VerticalLayout();
        controlBtnPanel.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        controlBtnPanel.setSizeUndefined();
        controlBtnPanel.setSpacing(true);
        controlBtnPanel.setMargin(new MarginInfo(false, false, false, true));
        propertyLayout.addComponent(controlBtnPanel, 2, 0);

        Button exampleControlBtn = new Button("Button");
        exampleControlBtn.setStyleName(UIConstants.THEME_BROWN_LINK);
        controlBtnPanel.addComponent(exampleControlBtn);

        HorizontalLayout controlBtnColorPane = new HorizontalLayout();
        controlBtnColorPane
                .setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        controlBtnColorPane.setSpacing(true);
        controlBtnPanel.addComponent(controlBtnColorPane);

        CustomColorPickerArea controlBtnBg = new CustomColorPickerArea(
                "Button Background", accountTheme.getControlbtn());
        controlBtnBg.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = -3852566371241071966L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                String colorHexString = event.getColor().getCSS().substring(1)
                        .toUpperCase();
                accountTheme.setControlbtn(colorHexString);
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        controlBtnColorPane.addComponent(controlBtnBg);

        CustomColorPickerArea controlBtnText = new CustomColorPickerArea(
                "Button Text", accountTheme.getControlbtntext());
        controlBtnText.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = 7947045019055649130L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                String colorHexString = event.getColor().getCSS().substring(1)
                        .toUpperCase();
                accountTheme.setControlbtntext(colorHexString);
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        controlBtnColorPane.addComponent(controlBtnText);

        // Danger Button

        VerticalLayout dangerBtnPanel = new VerticalLayout();
        dangerBtnPanel.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        dangerBtnPanel.setSizeUndefined();
        dangerBtnPanel.setSpacing(true);
        dangerBtnPanel.setMargin(new MarginInfo(true, false, false, false));
        propertyLayout.addComponent(dangerBtnPanel, 0, 1);

        Button exampleDangerBtn = new Button("Button");
        exampleDangerBtn.setStyleName(UIConstants.THEME_RED_LINK);
        dangerBtnPanel.addComponent(exampleDangerBtn);

        HorizontalLayout dangerBtnColorPane = new HorizontalLayout();
        dangerBtnColorPane
                .setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        dangerBtnColorPane.setSpacing(true);
        dangerBtnPanel.addComponent(dangerBtnColorPane);

        CustomColorPickerArea dangerBtnBg = new CustomColorPickerArea(
                "Button Background", accountTheme.getDangerbtn());
        dangerBtnBg.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = -3852566371241071966L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                String colorHexString = event.getColor().getCSS().substring(1)
                        .toUpperCase();
                accountTheme.setDangerbtn(colorHexString);
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        dangerBtnColorPane.addComponent(dangerBtnBg);

        CustomColorPickerArea dangerBtnText = new CustomColorPickerArea(
                "Button Text", accountTheme.getDangerbtntext());
        dangerBtnText.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = 7947045019055649130L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                String colorHexString = event.getColor().getCSS().substring(1)
                        .toUpperCase();
                accountTheme.setDangerbtntext(colorHexString);
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        dangerBtnColorPane.addComponent(dangerBtnText);

        // Clear Button

        VerticalLayout clearBtnPanel = new VerticalLayout();
        clearBtnPanel.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        clearBtnPanel.setSizeUndefined();
        clearBtnPanel.setSpacing(true);
        clearBtnPanel.setMargin(new MarginInfo(true, false, false, true));
        propertyLayout.addComponent(clearBtnPanel, 1, 1);

        Button exampleClearBtn = new Button("Button");
        exampleClearBtn.setStyleName(UIConstants.THEME_BLANK_LINK);
        clearBtnPanel.addComponent(exampleClearBtn);

        HorizontalLayout clearBtnColorPane = new HorizontalLayout();
        clearBtnColorPane.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        clearBtnColorPane.setSpacing(true);
        clearBtnPanel.addComponent(clearBtnColorPane);

        CustomColorPickerArea clearBtnBg = new CustomColorPickerArea(
                "Button Background", accountTheme.getClearbtn());
        clearBtnBg.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = -3852566371241071966L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                String colorHexString = event.getColor().getCSS().substring(1)
                        .toUpperCase();
                accountTheme.setClearbtn(colorHexString);
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        clearBtnColorPane.addComponent(clearBtnBg);

        CustomColorPickerArea clearBtnText = new CustomColorPickerArea(
                "Button Text", accountTheme.getClearbtntext());
        clearBtnText.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = 7947045019055649130L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                String colorHexString = event.getColor().getCSS().substring(1)
                        .toUpperCase();
                accountTheme.setClearbtntext(colorHexString);
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        clearBtnColorPane.addComponent(clearBtnText);

        // Toggle Button

        VerticalLayout toggleBtnPanel = new VerticalLayout();
        toggleBtnPanel.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        toggleBtnPanel.setSizeUndefined();
        toggleBtnPanel.setSpacing(true);
        toggleBtnPanel.setMargin(new MarginInfo(true, false, false, true));
        propertyLayout.addComponent(toggleBtnPanel, 2, 1);

        final ToggleButtonGroup exampleToggleBtn = new ToggleButtonGroup();
        toggleBtnPanel.addComponent(exampleToggleBtn);

        Button firstBtn = new Button("Button 1");
        firstBtn.addStyleName(UIConstants.BTN_ACTIVE);
        exampleToggleBtn.addButton(firstBtn);
        exampleToggleBtn.addButton(new Button("Button 2"));

        GridLayout toggleBtnColorPane = new GridLayout(2, 2);
        toggleBtnColorPane
                .setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        toggleBtnColorPane.setSpacing(true);
        toggleBtnColorPane.addStyleName("no-border");
        toggleBtnPanel.addComponent(toggleBtnColorPane);

        CustomColorPickerArea toggleBtnBgSelected = new CustomColorPickerArea(
                "Selected Button Background",
                accountTheme.getTogglebtnselected());
        toggleBtnBgSelected.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = -3852566371241071966L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                String colorHexString = event.getColor().getCSS().substring(1)
                        .toUpperCase();
                accountTheme.setTogglebtnselected(colorHexString);
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        toggleBtnColorPane.addComponent(toggleBtnBgSelected, 0, 0);

        CustomColorPickerArea toggleBtnBg = new CustomColorPickerArea(
                "Button Background", accountTheme.getTogglebtn());
        toggleBtnBg.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = -3852566371241071966L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                String colorHexString = event.getColor().getCSS().substring(1)
                        .toUpperCase();
                accountTheme.setTogglebtn(colorHexString);
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        toggleBtnColorPane.addComponent(toggleBtnBg, 1, 0);

        CustomColorPickerArea toggleBtnText = new CustomColorPickerArea(
                "Button Text", accountTheme.getTogglebtntext());
        toggleBtnText.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = 7947045019055649130L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                String colorHexString = event.getColor().getCSS().substring(1)
                        .toUpperCase();
                accountTheme.setTogglebtntext(colorHexString);
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        toggleBtnColorPane.addComponent(toggleBtnText, 0, 1);

        CustomColorPickerArea toggleBtnTextSelected = new CustomColorPickerArea(
                "Selected Button Text", accountTheme.getTogglebtntextselected());
        toggleBtnTextSelected.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = 7947045019055649130L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                String colorHexString = event.getColor().getCSS().substring(1)
                        .toUpperCase();
                accountTheme.setTogglebtntextselected(colorHexString);
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        toggleBtnColorPane.addComponent(toggleBtnTextSelected, 1, 1);

        return blockLayout;
    }
}
