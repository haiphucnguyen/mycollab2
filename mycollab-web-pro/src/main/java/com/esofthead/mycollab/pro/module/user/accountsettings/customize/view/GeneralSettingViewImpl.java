package com.esofthead.mycollab.pro.module.user.accountsettings.customize.view;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.configuration.StorageFactory;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.utils.ImageUtil;
import com.esofthead.mycollab.core.utils.TimezoneMapper;
import com.esofthead.mycollab.module.file.service.AccountFavIconService;
import com.esofthead.mycollab.module.user.domain.SimpleBillingAccount;
import com.esofthead.mycollab.module.user.service.BillingAccountService;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AccountAssetsResolver;
import com.esofthead.mycollab.vaadin.ui.FormContainer;
import com.esofthead.mycollab.vaadin.web.ui.ServiceMenu;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.esofthead.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.esofthead.mycollab.web.CustomLayoutExt;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.vaadin.easyuploads.UploadField;
import org.vaadin.teemu.VaadinIcons;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

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
        FormContainer formContainer = new FormContainer();
        this.addComponent(formContainer);

        MHorizontalLayout generalSettingHeader = new MHorizontalLayout();
        Label headerLbl = new Label("General Settings for your site");

        Button editBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_EDIT), new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UI.getCurrent().addWindow(new AccountInfoChangeWindow());
            }
        });
        editBtn.setStyleName(UIConstants.BUTTON_LINK);

        generalSettingHeader.with(headerLbl, editBtn).alignAll(Alignment.MIDDLE_LEFT);


        GridFormLayoutHelper gridFormLayoutHelper = GridFormLayoutHelper.defaultFormLayoutHelper(1, 3);
        gridFormLayoutHelper.addComponent(new Label(billingAccount.getSitename()), "Site Name", 0, 0);
        gridFormLayoutHelper.addComponent(new Label(String.format("https://%s.mycollab.com", billingAccount
                .getSubdomain())), "Site Address", 0, 1);
        gridFormLayoutHelper.addComponent(new Label(TimezoneMapper.getTimezoneExt(
                billingAccount.getDefaulttimezone()).getDisplayName()), "Default Time Zone", 0, 2);
        formContainer.addSection(new CssLayout(generalSettingHeader), gridFormLayoutHelper.getLayout());

        buildLogoPanel();
        buildShortcutIconPanel();
    }

    private void buildLogoPanel() {
        FormContainer formContainer = new FormContainer();
        MHorizontalLayout layout = new MHorizontalLayout().withWidth("100%").withMargin(true);
        MVerticalLayout leftPanel = new MVerticalLayout().withMargin(false);
        Label logoDesc = new Label("Logos are used in site menu and email notifications. Image type must be png or jpg format");
        leftPanel.with(logoDesc).withWidth("250px");

        MVerticalLayout rightPanel = new MVerticalLayout().withMargin(false);
        CustomLayout previewLayout = CustomLayoutExt.createLayout("topNavigation");
        previewLayout.setStyleName("example-block");
        previewLayout.setHeight("40px");
        previewLayout.setWidth("520px");

        Button currentLogo = AccountAssetsResolver.createAccountLogoImageComponent(billingAccount.getLogopath(), 150);
        previewLayout.addComponent(currentLogo, "mainLogo");
        final ServiceMenu serviceMenu = new ServiceMenu();

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

        serviceMenu.addService(AppContext.getMessage(GenericI18Enum.MODULE_CRM), VaadinIcons.MONEY, clickListener);
        serviceMenu.addService(AppContext.getMessage(GenericI18Enum.MODULE_PROJECT), VaadinIcons.TASKS, clickListener);
        serviceMenu.addService(AppContext.getMessage(GenericI18Enum.MODULE_DOCUMENT), VaadinIcons.SUITCASE, clickListener);
        serviceMenu.selectService(0);

        previewLayout.addComponent(serviceMenu, "serviceMenu");

        MHorizontalLayout buttonControls = new MHorizontalLayout().withMargin(new MarginInfo(true, false, false, false));
        buttonControls.setDefaultComponentAlignment(Alignment.TOP_LEFT);
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

        Button resetButton = new Button("Reset", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                BillingAccountService billingAccountService = ApplicationContextUtil.getSpringBean
                        (BillingAccountService.class);
                billingAccount.setLogopath(null);
                billingAccountService.updateWithSession(billingAccount, AppContext.getUsername());
                Page.getCurrent().getJavaScript().execute("window.location.reload();");
            }
        });
        resetButton.setEnabled(AppContext.canBeYes(RolePermissionCollections.ACCOUNT_THEME));
        resetButton.setStyleName(UIConstants.BUTTON_OPTION);
        buttonControls.with(logoUploadField, resetButton);
        rightPanel.with(previewLayout, buttonControls);
        layout.with(leftPanel, rightPanel).expand(rightPanel);
        formContainer.addSection("Logo", layout);
        this.with(formContainer);
    }

    private void buildShortcutIconPanel() {
        FormContainer formContainer = new FormContainer();
        MHorizontalLayout layout = new MHorizontalLayout().withWidth("100%").withMargin(new MarginInfo(true));
        MVerticalLayout leftPanel = new MVerticalLayout().withMargin(false);
        Label logoDesc = new Label("Favicon appears in web browsers bar, bookmarks. The icon should be format png, " +
                "jpg and must be sizable to 32x32 pixels");
        leftPanel.with(logoDesc).withWidth("250px");
        MVerticalLayout rightPanel = new MVerticalLayout().withMargin(false);
        final Image favIconRes = new Image("", new ExternalResource(StorageFactory.getInstance().getFavIconPath(billingAccount.getId(),
                billingAccount.getFaviconpath())));

        MHorizontalLayout buttonControls = new MHorizontalLayout().withMargin(new MarginInfo(true, false, false, false));
        buttonControls.setDefaultComponentAlignment(Alignment.BOTTOM_LEFT);
        final UploadField favIconUploadField = new UploadField() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void updateDisplay() {
                byte[] imageData = (byte[]) this.getValue();
                String mimeType = this.getLastMimeType();
                if (mimeType.equals("image/jpeg")) {
                    imageData = ImageUtil.convertJpgToPngFormat(imageData);
                    if (imageData == null) {
                        throw new UserInvalidInputException("Do not support image format for logo");
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
                        favIconRes.setSource(new ExternalResource(StorageFactory.getInstance().getFavIconPath(billingAccount.getId(),
                                newFavIconPath)));
                        Page.getCurrent().getJavaScript().execute("window.location.reload();");
                    } catch (IOException e) {
                        throw new MyCollabException(e);
                    }
                } else {
                    throw new UserInvalidInputException("Upload file does not have valid image format. The supported formats are jpg/png");
                }
            }
        };
        favIconUploadField.setButtonCaption("Change");
        favIconUploadField.addStyleName("upload-field");
        favIconUploadField.setSizeUndefined();
        favIconUploadField.setFieldType(UploadField.FieldType.BYTE_ARRAY);
        favIconUploadField.setEnabled(AppContext.canBeYes(RolePermissionCollections.ACCOUNT_THEME));

        Button resetButton = new Button("Reset", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                BillingAccountService billingAccountService = ApplicationContextUtil.getSpringBean(BillingAccountService.class);
                billingAccount.setFaviconpath(null);
                billingAccountService.updateWithSession(billingAccount, AppContext.getUsername());
                Page.getCurrent().getJavaScript().execute("window.location.reload();");
            }
        });
        resetButton.setEnabled(AppContext.canBeYes(RolePermissionCollections.ACCOUNT_THEME));
        resetButton.setStyleName(UIConstants.BUTTON_OPTION);

        buttonControls.with(favIconUploadField, resetButton);
        rightPanel.with(favIconRes, buttonControls);
        layout.with(leftPanel, rightPanel).expand(rightPanel);
        formContainer.addSection("Favicon", layout);
        this.with(formContainer);
    }
}
