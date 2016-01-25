package com.esofthead.mycollab.premium.shell.view.components;

import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.license.LicenseResolver;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AbstractLicenseActivationWindow;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.vaadin.data.Property;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.easyuploads.UploadField;
import org.vaadin.hene.flexibleoptiongroup.FlexibleOptionGroup;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.io.InputStream;

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
@ViewComponent
public class LicenseActivationWindow extends AbstractLicenseActivationWindow {
    private static final Logger LOG = LoggerFactory.getLogger(LicenseActivationWindow.class);

    private static final String ACT_CODE = "ACT_CODE";
    private static final String LICENSE_FILE = "LICENSE_FILE";

    private TextField uploadFilenameField;
    private TextArea activationField;
    private LicenseUploadField licenseUploadField;
    private Button changeLicenseBtn;

    public LicenseActivationWindow() {
        super();
        this.setClosable(false);
        this.setModal(true);
        this.setResizable(false);
        this.setWidth("600px");
        MVerticalLayout content = new MVerticalLayout();
        Label titleLbl = new Label("MyCollab activated");
        titleLbl.addStyleName(ValoTheme.LABEL_H2);
        titleLbl.addStyleName(ValoTheme.LABEL_NO_MARGIN);

        content.with(titleLbl);
        final FlexibleOptionGroup optionGroup = new FlexibleOptionGroup();
        optionGroup.setItemCaptionMode(AbstractSelect.ItemCaptionMode.EXPLICIT);
        optionGroup.addItems(ACT_CODE, LICENSE_FILE);
        activationField = new TextArea();
        activationField.setImmediate(true);
        activationField.setTextChangeEventMode(AbstractTextField.TextChangeEventMode.EAGER);
        activationField.setTextChangeTimeout(2000);
        activationField.setWidth("100%");
        content.with(new MHorizontalLayout(optionGroup.getItemComponent(ACT_CODE), new Label("Activation code")), activationField);

        uploadFilenameField = new TextField();
        uploadFilenameField.setReadOnly(true);
        licenseUploadField = new LicenseUploadField();
        licenseUploadField.setEnabled(false);
        MHorizontalLayout licenseFileUploadLayout = new MHorizontalLayout().with(uploadFilenameField, licenseUploadField).expand
                (uploadFilenameField);

        content.with(new MHorizontalLayout(optionGroup.getItemComponent(LICENSE_FILE), new Label("License file from the hard drive (mycollab.lic)")),
                licenseFileUploadLayout);

        changeLicenseBtn = new Button("Change license", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                String val = (String) optionGroup.getValue();
                LicenseResolver licenseResolver = ApplicationContextUtil.getSpringBean(LicenseResolver.class);
                if (ACT_CODE.equals(val)) {
                    String licenseText = activationField.getValue();
                    licenseResolver.checkAndSaveLicenseInfo(licenseText);
                    close();
                } else {
                    InputStream inputStream = licenseUploadField.getContentAsStream();
                    if (inputStream == null) {
                        throw new UserInvalidInputException("No valid license file");
                    } else {
                        try {
                            byte[] licenseByes = IOUtils.toByteArray(inputStream);
                            licenseResolver.checkLicenseInfo(licenseByes, true);
                            close();
                        } catch (Exception e) {
                            LOG.error("Error", e);
                            throw new UserInvalidInputException("No valid license file");
                        }
                    }
                }
            }
        });
        optionGroup.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                String val = (String) optionGroup.getValue();
                if (ACT_CODE.equals(val)) {
                    uploadFilenameField.setReadOnly(false);
                    uploadFilenameField.setValue("");
                    uploadFilenameField.setReadOnly(true);
                    activationField.setReadOnly(false);
                } else {
                    activationField.setReadOnly(false);
                    activationField.setValue("");
                    activationField.setReadOnly(true);
                    licenseUploadField.setEnabled(true);
                    licenseUploadField.markAsDirtyRecursive();
                }
                changeLicenseBtn.setEnabled(false);
            }
        });
        activationField.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                if (StringUtils.isNotBlank(activationField.getValue())) {
                    changeLicenseBtn.setEnabled(true);
                }
            }
        });
        optionGroup.setValue(ACT_CODE);
        changeLicenseBtn.setEnabled(false);
        changeLicenseBtn.addStyleName(UIConstants.BUTTON_ACTION);
        content.with(changeLicenseBtn).withAlign(changeLicenseBtn, Alignment.BOTTOM_RIGHT);
        setContent(content);
    }

    private class LicenseUploadField extends UploadField {
        LicenseUploadField() {
            this.setButtonCaption("Browse");
            this.setAcceptFilter("*.lic");
            this.setDisplayUpload(false);
        }

        @Override
        public void uploadFinished(Upload.FinishedEvent event) {
            super.uploadFinished(event);
            uploadFilenameField.setReadOnly(false);
            uploadFilenameField.setValue(event.getFilename());
            uploadFilenameField.setReadOnly(true);
            changeLicenseBtn.setEnabled(true);
        }
    }
}
