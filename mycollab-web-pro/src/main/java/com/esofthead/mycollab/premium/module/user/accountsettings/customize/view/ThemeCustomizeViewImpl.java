/**
 * This file is part of mycollab-web-premium.
 * <p/>
 * mycollab-web-premium is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * mycollab-web-premium is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web-premium.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.premium.module.user.accountsettings.customize.view;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.utils.ImageUtil;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.user.accountsettings.localization.AdminI18nEnum;
import com.esofthead.mycollab.module.user.accountsettings.localization.SettingCommonI18nEnum;
import com.esofthead.mycollab.module.user.accountsettings.view.events.AccountCustomizeEvent;
import com.esofthead.mycollab.module.user.accountsettings.view.parameters.CustomizeScreenData;
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
import org.vaadin.easyuploads.UploadField;
import org.vaadin.easyuploads.UploadField.FieldType;
import org.vaadin.maddon.layouts.MHorizontalLayout;

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

        mainLayout = initUI();
        this.addComponent(mainLayout);
    }

    protected AddViewLayout2 initUI() {
        this.setMargin(new MarginInfo(false, true, true, true));

        AddViewLayout2 mainLayout = new AddViewLayout2(
                AppContext.getMessage(AdminI18nEnum.VIEW_CUSTOMIZE),
                SettingAssetsManager.getAsset(SettingUIConstants.CUSTOMIZATION));
        mainLayout.setWidth("100%");
        mainLayout.setStyleName("readview-layout");
        mainLayout.addStyleName("theme-customize-view");

        return mainLayout;
    }

    @Override
    public void customizeTheme(AccountTheme theme) {
        accountTheme = theme;
        ThemeManager.loadDemoTheme(accountTheme);
        mainLayout.getBody().removeAllComponents();

        VerticalLayout mainBody = new VerticalLayout();
        mainBody.setSpacing(true);

        // Add customizable blocks
        mainBody.addComponent(constructTopMenuCustomizeBlock());
        mainBody.addComponent(constructTabsheetCustomizeBlock());
        mainBody.addComponent(constructVTabsheetCustomizeBlock());
        mainBody.addComponent(constructTopNavigationBlock());
        mainBody.addComponent(constructButtonCustomizeBlock());

        MHorizontalLayout controlButton = new MHorizontalLayout().withWidth("100%");

        Button saveBtn = new Button(
                AppContext.getMessage(GenericI18Enum.BUTTON_SAVE),
                new Button.ClickListener() {
                    private static final long serialVersionUID = -6901103392231786935L;

                    @Override
                    public void buttonClick(ClickEvent event) {
                        EventBusFactory.getInstance().post(
                                new AccountCustomizeEvent.SaveTheme(this,
                                        accountTheme));
                    }
                });
        saveBtn.setIcon(FontAwesome.SAVE);
        saveBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
        saveBtn.setEnabled(AppContext
                .canBeYes(RolePermissionCollections.ACCOUNT_THEME));
        controlButton.addComponent(saveBtn);

        Button resetToDefaultBtn = new Button(
                AppContext
                        .getMessage(SettingCommonI18nEnum.BUTTON_RESET_DEFAULT),
                new Button.ClickListener() {
                    private static final long serialVersionUID = 5182152510759528123L;

                    @Override
                    public void buttonClick(ClickEvent event) {
                        EventBusFactory.getInstance().post(
                                new AccountCustomizeEvent.ResetTheme(
                                        ThemeCustomizeViewImpl.this, null));
                    }
                });
        resetToDefaultBtn.setStyleName(UIConstants.THEME_RED_LINK);
        resetToDefaultBtn.setEnabled(AppContext
                .canBeYes(RolePermissionCollections.ACCOUNT_THEME));
        controlButton.addComponent(resetToDefaultBtn);
        controlButton.setExpandRatio(resetToDefaultBtn, 1.0f);

        mainBody.addComponent(controlButton);

        mainLayout.addBody(mainBody);
    }

    private Component constructTopMenuCustomizeBlock() {
        VerticalLayout blockLayout = new VerticalLayout();
        Label blockHeader = new Label(
                AppContext.getMessage(SettingCommonI18nEnum.FORM_TOP_MENU));
        blockHeader.setStyleName("block-hdr");
        blockHeader.addStyleName("h2");
        blockLayout.addComponent(blockHeader);
        blockLayout.setSpacing(true);

        MHorizontalLayout blockBody = new MHorizontalLayout().withMargin(new MarginInfo(false, true, false, true))
                .withWidth("100%");
        blockLayout.addComponent(blockBody);

        final UploadField logoUploadField = new UploadField() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void updateDisplay() {
                byte[] imageData = (byte[]) this.getValue();
                String mimeType = this.getLastMimeType();
                if (mimeType.equals("image/jpeg")) {
                    imageData = ImageUtil.convertJpgToPngFormat(imageData);
                    if (imageData == null) {
                        throw new UserInvalidInputException(
                                "Do not support image format for logo");
                    } else {
                        mimeType = "image/png";
                    }
                }

                if (mimeType.equals("image/png")) {
                    EventBusFactory.getInstance().post(
                            new AccountCustomizeEvent.GotoUploadLogo(
                                    ThemeCustomizeViewImpl.this,
                                    new CustomizeScreenData.LogoUpload(
                                            imageData, accountTheme)));
                } else {
                    throw new UserInvalidInputException(
                            "Upload file does not have valid image format. The supported formats are jpg/png");
                }
            }
        };
        logoUploadField.setButtonCaption(AppContext
                .getMessage(SettingCommonI18nEnum.BUTTON_CHANGE_LOGO));
        logoUploadField.addStyleName("upload-field");
        logoUploadField.setSizeUndefined();
        logoUploadField.setFieldType(FieldType.BYTE_ARRAY);
        logoUploadField.setEnabled(AppContext
                .canBeYes(RolePermissionCollections.ACCOUNT_THEME));

        GridLayout propertyLayout = new GridLayout(2, 5);
        propertyLayout.setSpacing(true);
        propertyLayout.setStyleName("no-border");
        propertyLayout.setColumnExpandRatio(0, 1.0f);
        propertyLayout.setDefaultComponentAlignment(Alignment.TOP_RIGHT);
        propertyLayout.setWidth("250px");

        propertyLayout.addComponent(logoUploadField, 0, 0, 1, 0);
        propertyLayout.setComponentAlignment(logoUploadField,
                Alignment.TOP_LEFT);

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
        propertyLayout.addComponent(
                new Label(AppContext
                        .getMessage(SettingCommonI18nEnum.FORM_NORMAL_MENU)),
                0, 1);
        propertyLayout.addComponent(topMenuBg, 1, 1);

        CustomColorPickerArea topMenuText = new CustomColorPickerArea(
                AppContext
                        .getMessage(SettingCommonI18nEnum.FORM_NORMAL_TAB_TEXT),
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
        propertyLayout
                .addComponent(
                        new Label(
                                AppContext
                                        .getMessage(SettingCommonI18nEnum.FORM_NORMAL_MENU_TEXT)),
                        0, 2);
        propertyLayout.addComponent(topMenuText, 1, 2);

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
        propertyLayout.addComponent(new Label("Selected Menu"), 0, 3);
        propertyLayout.addComponent(topMenuBgSelected, 1, 3);

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
        propertyLayout.addComponent(new Label("Selected Menu Text"), 0, 4);
        propertyLayout.addComponent(topMenuTextSelected, 1, 4);

        blockBody.addComponent(propertyLayout);

        CustomLayout previewLayout = CustomLayoutExt
                .createLayout("topNavigation");
        previewLayout.setStyleName("example-block");
        previewLayout.setHeight("40px");
        previewLayout.setWidth("520px");

        Button currentLogo = AccountLogoFactory.createAccountLogoImageComponent(
                accountTheme.getLogopath(), 150);
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
                MyCollabResource.newResource("icons/16/customer.png"),
                clickListener);

        serviceMenu.selectService(0);

        serviceMenu.addService(
                AppContext.getMessage(GenericI18Enum.MODULE_PROJECT),
                MyCollabResource.newResource("icons/16/project.png"),
                clickListener);

        serviceMenu.addService(
                AppContext.getMessage(GenericI18Enum.MODULE_DOCUMENT),
                MyCollabResource.newResource("icons/16/document.png"),
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

    private Component constructTopNavigationBlock() {
        VerticalLayout blockLayout = new VerticalLayout();
        Label blockHeader = new Label("Top Navigation");
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

        CustomColorPickerArea hTopMenuBg = new CustomColorPickerArea(
                "Normal Menu", accountTheme.getHtopmenubg());
        hTopMenuBg.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = -675674373089622451L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                accountTheme.setHtopmenubg(event.getColor().getCSS()
                        .substring(1).toUpperCase());
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        propertyLayout.addComponent(new Label("Normal Menu"), 0, 0);
        propertyLayout.addComponent(hTopMenuBg, 1, 0);

        CustomColorPickerArea hTopMenuText = new CustomColorPickerArea(
                "Normal Menu Text", accountTheme.getHtopmenutext());
        hTopMenuText.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = 3487570137637191332L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                accountTheme.setHtopmenutext(event.getColor().getCSS()
                        .substring(1).toUpperCase());
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        propertyLayout.addComponent(new Label("Normal Menu Text"), 0, 1);
        propertyLayout.addComponent(hTopMenuText, 1, 1);

        CustomColorPickerArea hTopMenuBgSelected = new CustomColorPickerArea(
                "Selected Menu", accountTheme.getHtopmenubgselected());
        hTopMenuBgSelected.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = -2435453092194064504L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                accountTheme.setHtopmenubgselected(event.getColor().getCSS()
                        .substring(1).toUpperCase());
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        propertyLayout.addComponent(new Label("Selected Menu"), 0, 2);
        propertyLayout.addComponent(hTopMenuBgSelected, 1, 2);

        CustomColorPickerArea hTopMenuTextSelected = new CustomColorPickerArea(
                "Selected Menu Text", accountTheme.getHtopmenutextselected());
        hTopMenuTextSelected.addColorChangeListener(new ColorChangeListener() {
            private static final long serialVersionUID = 3190273972739835060L;

            @Override
            public void colorChanged(ColorChangeEvent event) {
                accountTheme.setHtopmenutextselected(event.getColor().getCSS()
                        .substring(1).toUpperCase());
                ThemeManager.loadDemoTheme(accountTheme);
            }
        });
        propertyLayout.addComponent(new Label("Selected Menu Text"), 0, 3);
        propertyLayout.addComponent(hTopMenuTextSelected, 1, 3);

        blockBody.addComponent(propertyLayout);

        CssLayout previewLayout = new CssLayout();
        previewLayout.setStyleName("example-block");
        previewLayout.setWidth("520px");
        blockBody.addComponent(previewLayout);
        blockBody.setComponentAlignment(previewLayout, Alignment.MIDDLE_CENTER);
        blockBody.setExpandRatio(previewLayout, 1.0f);

        final HorizontalLayout hTopMenuDemo = new HorizontalLayout();
        hTopMenuDemo.setWidth("100%");
        hTopMenuDemo.setStyleName("crm-toolbar");
        hTopMenuDemo.setStyleName("h-sidebar-menu");
        hTopMenuDemo.setMargin(new MarginInfo(false, true, false, true));

        Button.ClickListener clickListener = new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent event) {
                Iterator<Component> iterator = hTopMenuDemo.iterator();

                while (iterator.hasNext()) {
                    Component comp = iterator.next();
                    if (comp != event.getButton())
                        comp.removeStyleName("isSelected");
                }

                event.getButton().addStyleName("isSelected");
            }
        };

        hTopMenuDemo.addComponent(new ButtonLinkLegacy("Dashboard", clickListener,
                false));
        ButtonLinkLegacy accountLink = new ButtonLinkLegacy("Accounts", clickListener,
                false);
        accountLink.addStyleName("isSelected");
        hTopMenuDemo.addComponent(accountLink);
        hTopMenuDemo.addComponent(new ButtonLinkLegacy("Contacts", clickListener,
                false));
        Label spaceFix = new Label();
        hTopMenuDemo.addComponent(spaceFix);
        hTopMenuDemo.setExpandRatio(spaceFix, 1.0f);

        previewLayout.addComponent(hTopMenuDemo);

        return blockLayout;
    }

    private Component constructButtonCustomizeBlock() {
        VerticalLayout blockLayout = new VerticalLayout();
        Label blockHeader = new Label("Buttons Customize");
        blockHeader.setStyleName("block-hdr");
        blockHeader.addStyleName("h2");
        blockLayout.addComponent(blockHeader);
        blockLayout.setSpacing(true);

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
