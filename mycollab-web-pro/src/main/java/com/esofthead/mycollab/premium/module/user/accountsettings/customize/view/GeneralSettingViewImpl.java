package com.esofthead.mycollab.premium.module.user.accountsettings.customize.view;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.configuration.Storage;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.utils.ImageUtil;
import com.esofthead.mycollab.core.utils.TimezoneMapper;
import com.esofthead.mycollab.module.file.service.AccountFavIconService;
import com.esofthead.mycollab.module.user.domain.SimpleBillingAccount;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AccountAssetsResolver;
import com.esofthead.mycollab.vaadin.ui.AssetResource;
import com.esofthead.mycollab.vaadin.ui.ServiceMenu;
import com.esofthead.mycollab.vaadin.ui.WebResourceIds;
import com.esofthead.mycollab.vaadin.ui.grid.GridFormLayoutHelper;
import com.esofthead.mycollab.web.CustomLayoutExt;
import com.hp.gagawa.java.elements.Div;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.vaadin.easyuploads.UploadField;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
@ViewComponent
public class GeneralSettingViewImpl extends AbstractPageView implements GeneralSettingView {
    private SimpleBillingAccount billingAccount;

    public GeneralSettingViewImpl() {
        this.setMargin(true);
        this.addStyleName("userInfoContainer");
    }

    @Override
    public void displayView() {
        removeAllComponents();

        billingAccount = AppContext.getBillingAccount();
        MHorizontalLayout generalSettingHeader = new MHorizontalLayout();

        Label headerLbl = new Label("General Settings for your site");
        headerLbl.setStyleName("h1");

        Button editBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_EDIT), new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UI.getCurrent().addWindow(new AccountInfoChangeWindow());
            }
        });
        editBtn.setStyleName("link");

        generalSettingHeader.with(headerLbl, editBtn).withAlign(headerLbl, Alignment.MIDDLE_LEFT).withAlign(editBtn,
                Alignment.MIDDLE_LEFT);

        String separatorStyle = "width: 100%; height: 1px; background-color: #CFCFCF; margin-top: 0px; margin-bottom: 10px";
        Label divLabel1 = new Label(new Div().setStyle(separatorStyle).write(), ContentMode.HTML);

        GridFormLayoutHelper gridFormLayoutHelper = new GridFormLayoutHelper(1, 3, "100%", "160px");
        gridFormLayoutHelper.getLayout().setSpacing(true);
        gridFormLayoutHelper.addComponent(new Label(billingAccount.getSitename()), "Site Name", 0, 0);
        gridFormLayoutHelper.addComponent(new Label(String.format("https://%s.mycollab.com", billingAccount
                .getSubdomain())), "Site Address", 0, 1);
        gridFormLayoutHelper.addComponent(new Label(TimezoneMapper.getTimezoneExt(
                billingAccount.getDefaulttimezone()).getDisplayName()), "Default Time Zone", 0, 2);
        this.with(generalSettingHeader, divLabel1, gridFormLayoutHelper.getLayout());

        headerLbl = new Label("Logos");
        headerLbl.setStyleName("h1");
        MHorizontalLayout logoHeader = new MHorizontalLayout().withMargin(new MarginInfo(true, false, false, false)).with(headerLbl);
        Label divLabel2 = new Label(new Div().setStyle(separatorStyle).write(), ContentMode.HTML);
        this.with(logoHeader, divLabel2);

        buildLogoPanel();
        buildShortcutIconPanel();
    }

    private void buildLogoPanel() {
        MHorizontalLayout layout = new MHorizontalLayout();
        MVerticalLayout leftPanel = new MVerticalLayout().withMargin(false);
        Label logoHeaderLbl = new Label("Logo");
        logoHeaderLbl.addStyleName("h2");
        Label logoDesc = new Label("Logos are used in site menu and email notifications. Image type must be png or " +
                "jpg format");
        leftPanel.with(logoHeaderLbl, logoDesc).withWidth("250px");
        MVerticalLayout rightPanel = new MVerticalLayout().withMargin(false);

        CustomLayout previewLayout = CustomLayoutExt.createLayout("topNavigation");
        previewLayout.setStyleName("example-block");
        previewLayout.setHeight("40px");
        previewLayout.setWidth("520px");

        Button currentLogo = AccountAssetsResolver.createAccountLogoImageComponent(billingAccount.getLogopath(), 150);
        previewLayout.addComponent(currentLogo, "mainLogo");
        final ServiceMenu serviceMenu = new ServiceMenu();
        serviceMenu.addStyleName("topNavPopup");

        Button.ClickListener clickListener = new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final Button.ClickEvent event) {
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

        serviceMenu.addService(AppContext.getMessage(GenericI18Enum.MODULE_CRM),
                new AssetResource(WebResourceIds._16_customer), clickListener);

        serviceMenu.selectService(0);

        serviceMenu.addService(AppContext.getMessage(GenericI18Enum.MODULE_PROJECT),
                new AssetResource(WebResourceIds._16_project), clickListener);

        serviceMenu.addService(AppContext.getMessage(GenericI18Enum.MODULE_DOCUMENT),
                new AssetResource(WebResourceIds._16_document), clickListener);

        previewLayout.addComponent(serviceMenu, "serviceMenu");

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
                    UI.getCurrent().addWindow(new LogoEditWindow(imageData));
                } else {
                    throw new UserInvalidInputException(
                            "Upload file does not have valid image format. The supported formats are jpg/png");
                }
            }
        };
        logoUploadField.setButtonCaption("Change");
        logoUploadField.addStyleName("upload-field");
        logoUploadField.setSizeUndefined();
        logoUploadField.setFieldType(UploadField.FieldType.BYTE_ARRAY);
        logoUploadField.setEnabled(AppContext.canBeYes(RolePermissionCollections.ACCOUNT_THEME));
        rightPanel.with(previewLayout, logoUploadField);
        layout.with(leftPanel, rightPanel);
        this.with(layout);
    }

    private void buildShortcutIconPanel() {
        MHorizontalLayout layout = new MHorizontalLayout().withMargin(new MarginInfo(true, false, true, false));
        MVerticalLayout leftPanel = new MVerticalLayout().withMargin(false);
        Label logoHeaderLbl = new Label("Favicon");
        logoHeaderLbl.addStyleName("h2");
        Label logoDesc = new Label("Favicon appears in web browsers bar, bookmarks. The icon should be format png, " +
                "jpg and must be sizeable to 32x32 pixels");
        leftPanel.with(logoHeaderLbl, logoDesc).withWidth("250px");
        MVerticalLayout rightPanel = new MVerticalLayout().withMargin(false);
        final Image favIconRes = new Image("", new ExternalResource(Storage.getFavIconPath(billingAccount.getId(),
                billingAccount.getFaviconpath())));
        final UploadField favIconUploadField = new UploadField() {
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
                    try {
                        AccountFavIconService favIconService = ApplicationContextUtil.getSpringBean(AccountFavIconService.class);
                        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageData));
                        String newFavIconPath = favIconService.upload(AppContext.getUsername(), image, AppContext
                                .getAccountId());
                        favIconRes.setSource(new ExternalResource(Storage.getFavIconPath(billingAccount.getId(),
                                newFavIconPath)));
                        Page.getCurrent().getJavaScript().execute("window.location.reload();");
                    } catch (IOException e) {
                        throw new MyCollabException(e);
                    }
                } else {
                    throw new UserInvalidInputException(
                            "Upload file does not have valid image format. The supported formats are jpg/png");
                }
            }
        };
        favIconUploadField.setButtonCaption("Change");
        favIconUploadField.addStyleName("upload-field");
        favIconUploadField.setSizeUndefined();
        favIconUploadField.setFieldType(UploadField.FieldType.BYTE_ARRAY);
        favIconUploadField.setEnabled(AppContext.canBeYes(RolePermissionCollections.ACCOUNT_THEME));
        rightPanel.with(favIconRes, favIconUploadField);
        layout.with(leftPanel, rightPanel);
        this.with(layout);
    }
}
