package com.mycollab.pro.module.user.accountsettings.customize.view;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.common.i18n.ThemeI18nEnum;
import com.mycollab.module.project.i18n.BugI18nEnum;
import com.mycollab.module.project.i18n.ComponentI18nEnum;
import com.mycollab.module.user.accountsettings.customize.view.IThemeCustomizeView;
import com.mycollab.module.user.accountsettings.localization.SettingCommonI18nEnum;
import com.mycollab.module.user.accountsettings.view.event.SettingEvent;
import com.mycollab.module.user.domain.AccountTheme;
import com.mycollab.module.user.ui.SettingAssetsManager;
import com.mycollab.module.user.ui.SettingUIConstants;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.AbstractVerticalPageView;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.AccountAssetsResolver;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.FormContainer;
import com.mycollab.vaadin.ui.ThemeManager;
import com.mycollab.vaadin.web.ui.ServiceMenu;
import com.mycollab.vaadin.web.ui.VerticalTabsheet;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.mycollab.web.CustomLayoutExt;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.colorpicker.Color;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 4.1.2
 */
@ViewComponent
public class ThemeCustomizeViewImpl extends AbstractVerticalPageView implements IThemeCustomizeView {
    private static final long serialVersionUID = 1181278209875228643L;

    private AccountTheme accountTheme;

    public ThemeCustomizeViewImpl() {
        this.setMargin(true);
    }

    @Override
    public void customizeTheme(AccountTheme theme) {
        this.removeAllComponents();
        accountTheme = theme;
        ThemeManager.loadDemoTheme(accountTheme);

        MVerticalLayout mainBody = new MVerticalLayout().withMargin(false);

        // Add customizable blocks
        mainBody.with(constructTopMenuCustomizeBlock(), constructVTabsheetCustomizeBlock(), constructButtonCustomizeBlock());


        ELabel viewTitle = ELabel.h2(SettingAssetsManager.getAsset(SettingUIConstants.THEME_CUSTOMIZE).getHtml() + " " +
                UserUIContext.getMessage(ThemeI18nEnum.OPT_THEME_CUSTOMIZATION));

        MButton saveBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SAVE),
                clickEvent -> EventBusFactory.getInstance().post(new SettingEvent.SaveTheme(this, accountTheme)))
                .withIcon(VaadinIcons.CLIPBOARD).withStyleName(WebThemes.BUTTON_ACTION)
                .withClickShortcut(KeyCode.ENTER)
                .withVisible(UserUIContext.canBeYes(RolePermissionCollections.ACCOUNT_THEME));

        MButton resetToDefaultBtn = new MButton(UserUIContext.getMessage(SettingCommonI18nEnum.BUTTON_RESET_DEFAULT),
                clickEvent -> EventBusFactory.getInstance().post(new SettingEvent.ResetTheme(ThemeCustomizeViewImpl.this, null)))
                .withStyleName(WebThemes.BUTTON_DANGER);
        resetToDefaultBtn.setVisible(UserUIContext.canBeYes(RolePermissionCollections.ACCOUNT_THEME));

        MHorizontalLayout controlButtons = new MHorizontalLayout(viewTitle, resetToDefaultBtn, saveBtn).withFullWidth()
                .expand(viewTitle);
        this.with(controlButtons, mainBody);
    }

    private Component constructTopMenuCustomizeBlock() {
        FormContainer blockLayout = new FormContainer();

        MHorizontalLayout blockBody = new MHorizontalLayout().withMargin(true).withFullWidth();
        blockLayout.addSection(UserUIContext.getMessage(SettingCommonI18nEnum.FORM_TOP_MENU), blockBody);

        GridLayout propertyLayout = new GridLayout(2, 4);
        propertyLayout.setSpacing(true);
        propertyLayout.setColumnExpandRatio(0, 1.0f);
        propertyLayout.setDefaultComponentAlignment(Alignment.TOP_RIGHT);
        propertyLayout.setWidth("250px");

        CustomColorPickerArea topMenuBg = new CustomColorPickerArea(accountTheme.getTopmenubg());
        topMenuBg.addValueChangeListener(colorChangeEvent -> {
            accountTheme.setTopmenubg(colorChangeEvent.getValue().getCSS().substring(1).toUpperCase());
            ThemeManager.loadDemoTheme(accountTheme);
        });
        propertyLayout.addComponent(new Label(UserUIContext.getMessage(SettingCommonI18nEnum.FORM_NORMAL_MENU)), 0, 0);
        propertyLayout.addComponent(topMenuBg, 1, 0);

        CustomColorPickerArea topMenuText = new CustomColorPickerArea(accountTheme.getTopmenutext());
        topMenuText.addValueChangeListener(colorChangeEvent -> {
            accountTheme.setTopmenutext(colorChangeEvent.getValue().getCSS().substring(1).toUpperCase());
            ThemeManager.loadDemoTheme(accountTheme);
        });
        propertyLayout.addComponent(new Label(UserUIContext.getMessage(SettingCommonI18nEnum.FORM_NORMAL_MENU_TEXT)), 0, 1);
        propertyLayout.addComponent(topMenuText, 1, 1);

        CustomColorPickerArea topMenuBgSelected = new CustomColorPickerArea(accountTheme.getTopmenubgselected());
        topMenuBgSelected.addValueChangeListener(colorChangeEvent -> {
            accountTheme.setTopmenubgselected(colorChangeEvent.getValue().getCSS().substring(1).toUpperCase());
            ThemeManager.loadDemoTheme(accountTheme);
        });
        propertyLayout.addComponent(new Label(UserUIContext.getMessage(ThemeI18nEnum.OPT_SELECTED_MENU)), 0, 2);
        propertyLayout.addComponent(topMenuBgSelected, 1, 2);

        CustomColorPickerArea topMenuTextSelected = new CustomColorPickerArea(accountTheme.getTopmenutextselected());
        topMenuTextSelected.addValueChangeListener(colorChangeEvent -> {
            accountTheme.setTopmenutextselected(colorChangeEvent.getValue().getCSS().substring(1).toUpperCase());
            ThemeManager.loadDemoTheme(accountTheme);
        });
        propertyLayout.addComponent(new Label(UserUIContext.getMessage(ThemeI18nEnum.OPT_SELECTED_MENU_TEXT)), 0, 3);
        propertyLayout.addComponent(topMenuTextSelected, 1, 3);

        blockBody.addComponent(propertyLayout);

        CustomLayout previewLayout = CustomLayoutExt.createLayout("topNavigation");
        previewLayout.setStyleName("example-block");
        previewLayout.setHeight("40px");
        previewLayout.setWidth("520px");

        Button currentLogo = AccountAssetsResolver.createAccountLogoImageComponent(null, 150);
        previewLayout.addComponent(currentLogo, "mainLogo");
        ServiceMenu serviceMenu = new ServiceMenu();

        Button.ClickListener clickListener = new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent event) {
                for (Component comp : serviceMenu) {
                    if (comp != event.getButton()) {
                        comp.removeStyleName("selected");
                    }
                }
                event.getButton().addStyleName("selected");
            }
        };

        serviceMenu.addService(UserUIContext.getMessage(GenericI18Enum.MODULE_CRM), VaadinIcons.MONEY, clickListener);
        serviceMenu.addService(UserUIContext.getMessage(GenericI18Enum.MODULE_PROJECT), VaadinIcons.TASKS, clickListener);
        serviceMenu.addService(UserUIContext.getMessage(GenericI18Enum.MODULE_DOCUMENT), VaadinIcons.SUITCASE, clickListener);
        serviceMenu.selectService(0);

        previewLayout.addComponent(serviceMenu, "serviceMenu");
        blockBody.with(previewLayout).withAlign(previewLayout, Alignment.MIDDLE_CENTER).expand(previewLayout);
        return blockLayout;
    }

    private Component constructVTabsheetCustomizeBlock() {
        FormContainer blockLayout = new FormContainer();
        MHorizontalLayout blockBody = new MHorizontalLayout().withMargin(true).withFullWidth();

        blockLayout.addSection(UserUIContext.getMessage(ThemeI18nEnum.OPT_VERTICAL_MENU), blockBody);

        GridLayout propertyLayout = new GridLayout(2, 4);
        propertyLayout.setSpacing(true);
        propertyLayout.setColumnExpandRatio(0, 1.0f);
        propertyLayout.setDefaultComponentAlignment(Alignment.TOP_RIGHT);
        propertyLayout.setWidth("250px");

        CustomColorPickerArea vTabsheetBg = new CustomColorPickerArea(accountTheme.getVtabsheetbg());
        vTabsheetBg.addValueChangeListener(colorChangeEvent -> {
            accountTheme.setVtabsheetbg(colorChangeEvent.getValue().getCSS().substring(1).toUpperCase());
            ThemeManager.loadDemoTheme(accountTheme);
        });
        propertyLayout.addComponent(new Label(UserUIContext.getMessage(ThemeI18nEnum.OPT_NORMAL_MENU)), 0, 0);
        propertyLayout.addComponent(vTabsheetBg, 1, 0);

        CustomColorPickerArea vTabsheetText = new CustomColorPickerArea(accountTheme.getVtabsheettext());
        vTabsheetText.addValueChangeListener(colorChangeEvent -> {
            accountTheme.setVtabsheettext(colorChangeEvent.getValue().getCSS().substring(1).toUpperCase());
            ThemeManager.loadDemoTheme(accountTheme);
        });
        propertyLayout.addComponent(new Label(UserUIContext.getMessage(ThemeI18nEnum.OPT_NORMAL_MENU_TEXT)), 0, 1);
        propertyLayout.addComponent(vTabsheetText, 1, 1);

        CustomColorPickerArea vTabsheetBgSelected = new CustomColorPickerArea(accountTheme.getVtabsheetbgselected());
        vTabsheetBgSelected.addValueChangeListener(colorChangeEvent -> {
            accountTheme.setVtabsheetbgselected(colorChangeEvent.getValue().getCSS().substring(1).toUpperCase());
            ThemeManager.loadDemoTheme(accountTheme);
        });
        propertyLayout.addComponent(new Label(UserUIContext.getMessage(ThemeI18nEnum.OPT_SELECTED_MENU)), 0, 2);
        propertyLayout.addComponent(vTabsheetBgSelected, 1, 2);

        CustomColorPickerArea vTabsheetTextSelected = new CustomColorPickerArea(accountTheme.getVtabsheettextselected());
        vTabsheetTextSelected.addValueChangeListener(colorChangeEvent -> {
            accountTheme.setVtabsheettextselected(colorChangeEvent.getValue().getCSS().substring(1).toUpperCase());
            ThemeManager.loadDemoTheme(accountTheme);
        });
        propertyLayout.addComponent(new Label(UserUIContext.getMessage(ThemeI18nEnum.OPT_SELECTED_MENU_TEXT)), 0, 3);
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
        tabsheetDemo.setNavigatorWidth("250px");

        tabsheetDemo.addTab("dashboard", UserUIContext.getMessage(GenericI18Enum.VIEW_DASHBOARD));
        tabsheetDemo.addTab("bugs", UserUIContext.getMessage(BugI18nEnum.LIST));
        tabsheetDemo.addTab("components", UserUIContext.getMessage(ComponentI18nEnum.LIST));

        tabsheetDemo.selectTab("dashboard");
        previewLayout.addComponent(tabsheetDemo);
        return blockLayout;
    }

    private Component constructButtonCustomizeBlock() {
        FormContainer blockLayout = new FormContainer();

        MVerticalLayout blockBody = new MVerticalLayout();
        blockLayout.addSection(UserUIContext.getMessage(ThemeI18nEnum.OPT_BUTTONS), blockBody);

        GridLayout propertyLayout = new GridLayout(3, 1);
        propertyLayout.setStyleName("example-block");
        propertyLayout.setSpacing(true);
        blockBody.addComponent(propertyLayout);

        // Action Button
        MVerticalLayout actionBtnPanel = new MVerticalLayout();
        actionBtnPanel.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        propertyLayout.addComponent(actionBtnPanel, 0, 0);

        Button exampleActionBtn = new Button(UserUIContext.getMessage(ThemeI18nEnum.OPT_BUTTON));
        exampleActionBtn.setStyleName(WebThemes.BUTTON_ACTION);
        actionBtnPanel.addComponent(exampleActionBtn);

        MHorizontalLayout actionBtnColorPane = new MHorizontalLayout();
        actionBtnColorPane.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        actionBtnPanel.addComponent(actionBtnColorPane);

        CustomColorPickerArea actionBtnBg = new CustomColorPickerArea(accountTheme.getActionbtn());
        actionBtnBg.addValueChangeListener(colorChangeEvent -> {
            String colorHexString = colorChangeEvent.getValue().getCSS().substring(1).toUpperCase();
            accountTheme.setActionbtn(colorHexString);
            ThemeManager.loadDemoTheme(accountTheme);
        });
        actionBtnColorPane.addComponent(actionBtnBg);

        CustomColorPickerArea actionBtnText = new CustomColorPickerArea(accountTheme.getActionbtntext());
        actionBtnText.addValueChangeListener(colorChangeEvent -> {
            String colorHexString = colorChangeEvent.getValue().getCSS().substring(1).toUpperCase();
            accountTheme.setActionbtntext(colorHexString);
            ThemeManager.loadDemoTheme(accountTheme);
        });
        actionBtnColorPane.addComponent(actionBtnText);

        CustomColorPickerArea actionBtnBorder = new CustomColorPickerArea(accountTheme.getActionbtnborder());
        actionBtnBorder.addValueChangeListener(colorChangeEvent -> {
            String colorHexString = colorChangeEvent.getValue().getCSS().substring(1).toUpperCase();
            accountTheme.setActionbtnborder(colorHexString);
            ThemeManager.loadDemoTheme(accountTheme);
        });
        actionBtnColorPane.addComponent(actionBtnBorder);

        // Option Button

        MVerticalLayout optionBtnPanel = new MVerticalLayout();
        optionBtnPanel.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        propertyLayout.addComponent(optionBtnPanel, 1, 0);

        Button exampleOptionBtn = new Button(UserUIContext.getMessage(ThemeI18nEnum.OPT_BUTTON));
        exampleOptionBtn.setStyleName(WebThemes.BUTTON_OPTION);
        optionBtnPanel.addComponent(exampleOptionBtn);

        HorizontalLayout optionBtnColorPane = new HorizontalLayout();
        optionBtnColorPane.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        optionBtnColorPane.setSpacing(true);
        optionBtnPanel.addComponent(optionBtnColorPane);

        CustomColorPickerArea optionBtnBg = new CustomColorPickerArea(accountTheme.getOptionbtn());
        optionBtnBg.addValueChangeListener(colorChangeEvent -> {
            String colorHexString = colorChangeEvent.getValue().getCSS().substring(1).toUpperCase();
            accountTheme.setOptionbtn(colorHexString);
            ThemeManager.loadDemoTheme(accountTheme);
        });
        optionBtnColorPane.addComponent(optionBtnBg);

        CustomColorPickerArea optionBtnText = new CustomColorPickerArea(accountTheme.getOptionbtntext());
        optionBtnText.addValueChangeListener(colorChangeEvent -> {
            String colorHexString = colorChangeEvent.getValue().getCSS().substring(1).toUpperCase();
            accountTheme.setOptionbtntext(colorHexString);
            ThemeManager.loadDemoTheme(accountTheme);
        });
        optionBtnColorPane.addComponent(optionBtnText);

        CustomColorPickerArea optionBtnBorder = new CustomColorPickerArea(accountTheme.getOptionbtnborder());
        optionBtnBorder.addValueChangeListener(colorChangeEvent -> {
            String colorHexString = colorChangeEvent.getValue().getCSS().substring(1).toUpperCase();
            accountTheme.setOptionbtnborder(colorHexString);
            ThemeManager.loadDemoTheme(accountTheme);
        });
        optionBtnColorPane.addComponent(optionBtnBorder);

        // Danger Button
        MVerticalLayout dangerBtnPanel = new MVerticalLayout();
        dangerBtnPanel.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        propertyLayout.addComponent(dangerBtnPanel, 2, 0);

        Button exampleDangerBtn = new Button(UserUIContext.getMessage(ThemeI18nEnum.OPT_BUTTON));
        exampleDangerBtn.setStyleName(WebThemes.BUTTON_DANGER);
        dangerBtnPanel.addComponent(exampleDangerBtn);

        MHorizontalLayout dangerBtnColorPane = new MHorizontalLayout();
        dangerBtnColorPane.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        dangerBtnPanel.addComponent(dangerBtnColorPane);

        CustomColorPickerArea dangerBtnBg = new CustomColorPickerArea(accountTheme.getDangerbtn());
        dangerBtnBg.addValueChangeListener(colorChangeEvent -> {
            String colorHexString = colorChangeEvent.getValue().getCSS().substring(1).toUpperCase();
            accountTheme.setDangerbtn(colorHexString);
            ThemeManager.loadDemoTheme(accountTheme);
        });
        dangerBtnColorPane.addComponent(dangerBtnBg);

        CustomColorPickerArea dangerBtnText = new CustomColorPickerArea(accountTheme.getDangerbtntext());
        dangerBtnText.addValueChangeListener(colorChangeEvent -> {
            String colorHexString = colorChangeEvent.getValue().getCSS().substring(1).toUpperCase();
            accountTheme.setDangerbtntext(colorHexString);
            ThemeManager.loadDemoTheme(accountTheme);
        });
        dangerBtnColorPane.addComponent(dangerBtnText);

        CustomColorPickerArea dangerBtnBorder = new CustomColorPickerArea(accountTheme.getDangerbtnborder());
        dangerBtnBorder.addValueChangeListener(colorChangeEvent -> {
            String colorHexString = colorChangeEvent.getValue().getCSS().substring(1).toUpperCase();
            accountTheme.setDangerbtnborder(colorHexString);
            ThemeManager.loadDemoTheme(accountTheme);
        });
        dangerBtnColorPane.addComponent(dangerBtnBorder);

        return blockLayout;
    }

    private static class CustomColorPickerArea extends ColorPickerArea {
        private static final long serialVersionUID = -8631349584720412229L;

        CustomColorPickerArea(String initialColor) {
            super();
            if (initialColor != null) {
                this.setValue(new Color(Integer.parseInt(initialColor, 16)));
            }

            this.setWidth("55px");
            this.setPopupStyle(PopupStyle.POPUP_NORMAL);
            this.setPosition(Page.getCurrent().getBrowserWindowWidth() / 2 - 248 / 2, Page
                    .getCurrent().getBrowserWindowHeight() / 2 - 508 / 2);
        }
    }
}
