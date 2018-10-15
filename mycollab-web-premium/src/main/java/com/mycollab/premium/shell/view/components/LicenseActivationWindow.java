package com.mycollab.premium.shell.view.components;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.common.i18n.LicenseI18nEnum;
import com.mycollab.core.UserInvalidInputException;
import com.mycollab.license.LicenseInfo;
import com.mycollab.license.service.LicenseResolver;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.easyuploads.UploadField;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

import java.io.InputStream;

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
// TODO
public class LicenseActivationWindow extends MWindow {
    private static final Logger LOG = LoggerFactory.getLogger(LicenseActivationWindow.class);

    private static final String ACT_CODE = "ACT_CODE";
    private static final String LICENSE_FILE = "LICENSE_FILE";

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
//        final FlexibleOptionGroup optionGroup = new FlexibleOptionGroup();
//        optionGroup.setItemCaptionMode(AbstractSelect.ItemCaptionMode.EXPLICIT);
//        optionGroup.addItems(ACT_CODE, LICENSE_FILE);
//        activationField = new TextArea();
//        activationField.setImmediate(true);
//        activationField.setTextChangeEventMode(AbstractTextField.TextChangeEventMode.EAGER);
//        activationField.setTextChangeTimeout(2000);
        activationField.setWidth("100%");
//        content.with(new MHorizontalLayout(optionGroup.getItemComponent(ACT_CODE), new Label(UserUIContext.getMessage
//                (LicenseI18nEnum.OPT_ACTIVATION_CODE))), activationField);

        uploadFilenameField = new TextField();
        uploadFilenameField.setReadOnly(true);
        licenseUploadField = new LicenseUploadField();
        licenseUploadField.setEnabled(false);
        MHorizontalLayout licenseFileUploadLayout = new MHorizontalLayout(uploadFilenameField, licenseUploadField)
                .expand(uploadFilenameField);

//        content.with(new MHorizontalLayout(optionGroup.getItemComponent(LICENSE_FILE),
//                new Label(UserUIContext.getMessage(LicenseI18nEnum.OPT_BROWSE_LICENSE_HELP))), licenseFileUploadLayout);

        changeLicenseBtn = new MButton(UserUIContext.getMessage(LicenseI18nEnum.ACTION_CHANGE_LICENSE), clickEvent -> {
            String val = ""; //(String) optionGroup.getValue();
            LicenseResolver licenseResolver = AppContextUtil.getSpringBean(LicenseResolver.class);
            if (ACT_CODE.equals(val)) {
                String licenseText = activationField.getValue();
                licenseResolver.checkAndSaveLicenseInfo(licenseText);
                if (licenseResolver.getLicenseInfo().isInvalid()) {
                    NotificationUtil.showErrorNotification(UserUIContext.getMessage(LicenseI18nEnum.ERROR_LICENSE_INVALID));
                } else {
                    close();
                    Page.getCurrent().getJavaScript().execute("window.location.reload();");
                }

            } else {
//                InputStream inputStream = licenseUploadField.getContentAsStream();
//                if (inputStream == null) {
//                    throw new UserInvalidInputException(UserUIContext.getMessage(LicenseI18nEnum.ERROR_LICENSE_FILE_INVALID));
//                } else {
//                    try {
//                        byte[] licenseByes = IOUtils.toByteArray(inputStream);
//                        LicenseInfo licenseInfo = licenseResolver.checkLicenseInfo(licenseByes, true);
//                        if (licenseInfo.isExpired() || licenseInfo.isInvalid() || licenseInfo.isTrial()) {
//                            NotificationUtil.showErrorNotification(UserUIContext.getMessage(LicenseI18nEnum.ERROR_LICENSE_INVALID));
//                        } else {
//                            close();
//                            Page.getCurrent().getJavaScript().execute("window.location.reload();");
//                        }
//                    } catch (Exception e) {
//                        LOG.error("Error", e);
//                        throw new UserInvalidInputException(UserUIContext.getMessage(LicenseI18nEnum.ERROR_LICENSE_FILE_INVALID));
//                    }
//                }
            }
        }).withStyleName(WebThemes.BUTTON_ACTION);
        changeLicenseBtn.setEnabled(false);

//        optionGroup.addValueChangeListener(valueChangeEvent -> {
//            String val = (String) optionGroup.getValue();
//            if (ACT_CODE.equals(val)) {
//                uploadFilenameField.setReadOnly(false);
//                uploadFilenameField.setValue("");
//                uploadFilenameField.setReadOnly(true);
//                activationField.setReadOnly(false);
//            } else {
//                activationField.setReadOnly(false);
//                activationField.setValue("");
//                activationField.setReadOnly(true);
//                licenseUploadField.setEnabled(true);
//                licenseUploadField.markAsDirtyRecursive();
//            }
//            changeLicenseBtn.setEnabled(false);
//        });
//        activationField.addValueChangeListener(valueChangeEvent -> {
//            if (StringUtils.isNotBlank(activationField.getValue())) {
//                changeLicenseBtn.setEnabled(true);
//            }
//        });
//        optionGroup.setValue(ACT_CODE);

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

    private class LicenseUploadField extends CssLayout {
        LicenseUploadField() {
//            this.setButtonCaption(UserUIContext.getMessage(GenericI18Enum.ACTION_BROWSE));
//            this.setAcceptFilter("*.lic");
//            this.setDisplayUpload(false);
//            this.addStyleName(WebThemes.BUTTON_ACTION);
        }

//        @Override
//        public void uploadFinished(Upload.FinishedEvent event) {
//            super.uploadFinished(event);
//            uploadFilenameField.setReadOnly(false);
//            uploadFilenameField.setValue(event.getFilename());
//            uploadFilenameField.setReadOnly(true);
//            changeLicenseBtn.setEnabled(true);
//        }
    }
}
