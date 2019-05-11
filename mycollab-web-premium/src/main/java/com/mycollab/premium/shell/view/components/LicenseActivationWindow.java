package com.mycollab.premium.shell.view.components;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.common.i18n.LicenseI18nEnum;
import com.mycollab.core.UserInvalidInputException;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.license.LicenseInfo;
import com.mycollab.license.service.LicenseResolver;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.ui.UIUtils;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.easyuploads.UploadField;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MCssLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

import java.io.OutputStream;

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
public class LicenseActivationWindow extends MWindow {
    private static final Logger LOG = LoggerFactory.getLogger(LicenseActivationWindow.class);

    private static final String ACT_CODE = "ACT_CODE";
    private static final String LICENSE_FILE = "LICENSE_FILE";

    private MHorizontalLayout actionLayout;
    private TextField uploadFilenameField;
    private TextArea activationField;
    private LicenseUploadField licenseUploadField;
    private MButton changeLicenseBtn;

    public LicenseActivationWindow() {
        super("Activate MyCollab");
        this.withModal(true).withResizable(false).withWidth("700px");
        MVerticalLayout content = new MVerticalLayout();
        ELabel titleLbl = ELabel.h2(UserUIContext.getMessage(LicenseI18nEnum.OPT_LICENSE_ACTIVATED));

        content.with(titleLbl);
        final RadioButtonGroup<String> optionGroup = new RadioButtonGroup<>();
        optionGroup.setItems(ACT_CODE, LICENSE_FILE);
        optionGroup.setItemCaptionGenerator((ItemCaptionGenerator<String>) item -> {
            if (item.equals(ACT_CODE)) {
                return UserUIContext.getMessage(LicenseI18nEnum.OPT_ACTIVATION_CODE);
            } else {
                return UserUIContext.getMessage(LicenseI18nEnum.OPT_BROWSE_LICENSE_HELP);
            }
        });
        actionLayout = new MHorizontalLayout().withMargin(new MarginInfo(true, false, true, false));
        content.with(optionGroup, actionLayout);

        changeLicenseBtn = new MButton(UserUIContext.getMessage(LicenseI18nEnum.ACTION_CHANGE_LICENSE), clickEvent -> {
            String val = optionGroup.getValue();
            LicenseResolver licenseResolver = AppContextUtil.getSpringBean(LicenseResolver.class);
            if (ACT_CODE.equals(val)) {
                String licenseText = activationField.getValue();
                licenseResolver.checkAndSaveLicenseInfo(licenseText);
                if (licenseResolver.getLicenseInfo().isInvalid()) {
                    NotificationUtil.showErrorNotification(UserUIContext.getMessage(LicenseI18nEnum.ERROR_LICENSE_INVALID));
                } else {
                    close();
                    UIUtils.reloadPage();
                }

            } else {
                byte[] licenseByes = licenseUploadField.getValue();
                if (licenseByes == null) {
                    throw new UserInvalidInputException(UserUIContext.getMessage(LicenseI18nEnum.ERROR_LICENSE_FILE_INVALID));
                } else {
                    try {
                        LicenseInfo licenseInfo = licenseResolver.checkLicenseInfo(licenseByes, true);
                        if (licenseInfo.isExpired() || licenseInfo.isInvalid() || licenseInfo.isTrial()) {
                            NotificationUtil.showErrorNotification(UserUIContext.getMessage(LicenseI18nEnum.ERROR_LICENSE_INVALID));
                        } else {
                            close();
                            UIUtils.reloadPage();
                        }
                    } catch (Exception e) {
                        LOG.error("Error", e);
                        throw new UserInvalidInputException(UserUIContext.getMessage(LicenseI18nEnum.ERROR_LICENSE_FILE_INVALID));
                    }
                }
            }
        }).withStyleName(WebThemes.BUTTON_ACTION);
        changeLicenseBtn.setEnabled(false);

        optionGroup.addValueChangeListener(valueChangeEvent -> {
            String val = optionGroup.getValue();
            if (ACT_CODE.equals(val)) {
                displayActivationCode();
            } else {
                displayUploadLicenseFile();
            }
            changeLicenseBtn.setEnabled(false);
        });

        optionGroup.setValue(ACT_CODE);

        MButton getLicenseLink = new MButton(UserUIContext.getMessage(LicenseI18nEnum.OPT_BUY_LICENSE), clickEvent -> {
            UI.getCurrent().addWindow(new BuyPremiumSoftwareWindow());
            close();
        }).withStyleName(WebThemes.BUTTON_LINK);

        MHorizontalLayout buttonControls = new MHorizontalLayout(getLicenseLink, changeLicenseBtn);
        content.with(buttonControls).withAlign(buttonControls, Alignment.BOTTOM_RIGHT);
        setContent(content);

        LicenseResolver licenseResolver = AppContextUtil.getSpringBean(LicenseResolver.class);
        LicenseInfo licenseInfo = licenseResolver.getLicenseInfo();
        if (licenseInfo != null) {
            this.setClosable(!licenseInfo.isExpired());
        } else {
            this.setClosable(false);
        }
    }

    private void displayActivationCode() {
        actionLayout.removeAllComponents();
        activationField = new TextArea();
        activationField.setWidth("100%");
        activationField.setHeight("70px");
        activationField.addValueChangeListener(valueChangeEvent -> {
            if (StringUtils.isNotBlank(activationField.getValue())) {
                changeLicenseBtn.setEnabled(true);
            }
        });
        actionLayout.with(activationField).expand(activationField);
    }

    private void displayUploadLicenseFile() {
        actionLayout.removeAllComponents();
        uploadFilenameField = new TextField();
        uploadFilenameField.setReadOnly(true);
        licenseUploadField = new LicenseUploadField();
        actionLayout.with(uploadFilenameField, new MCssLayout(licenseUploadField)).expand(uploadFilenameField);
    }

    private class LicenseUploadField extends UploadField {
        LicenseUploadField() {
            this.addStyleName("upload-field");
            this.setButtonCaption(UserUIContext.getMessage(GenericI18Enum.ACTION_BROWSE));
            this.setAcceptFilter("*.lic");
            this.setDisplayUpload(false);
            this.addStyleName(WebThemes.BUTTON_ACTION);
        }

        @Override
        public OutputStream receiveUpload(String filename, String mimeType) {
            changeLicenseBtn.setEnabled(true);
            uploadFilenameField.setReadOnly(false);
            uploadFilenameField.setValue(filename);
            uploadFilenameField.setReadOnly(true);
            return super.receiveUpload(filename, mimeType);
        }
    }
}
