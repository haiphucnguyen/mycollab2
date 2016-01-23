package com.esofthead.mycollab.premium.shell.view.components;

import com.esofthead.mycollab.vaadin.AbstractLicenseActivationWindow;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.esofthead.mycollab.vaadin.web.ui.ValueComboBox;
import com.vaadin.data.Property;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.easyuploads.UploadField;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
@ViewComponent
public class LicenseActivationWindow extends AbstractLicenseActivationWindow {
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
        CheckBox activationCodeSelection = new CheckBox("Activation code");
        TextArea activationField = new TextArea();
        activationField.setWidth("100%");
        content.with(activationCodeSelection, activationField);

        CheckBox licenseFileSelection = new CheckBox("License file from the hard drive (mycollab.lic)");
        TextField uploadFileField = new TextField();
        UploadField uploadField = new UploadField(UploadField.StorageMode.MEMORY);
        uploadField.setButtonCaption("Browse");
        uploadField.setAcceptFilter("*.lic");
        uploadField.setDisplayUpload(false);
        MHorizontalLayout licenseFileUploadLayout = new MHorizontalLayout().with(uploadFileField, uploadField).expand
                (uploadFileField);

        content.with(licenseFileSelection, licenseFileUploadLayout);

        Button changeLicenseBtn = new Button("Change license", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

            }
        });
        changeLicenseBtn.addStyleName(UIConstants.BUTTON_ACTION);
        content.with(changeLicenseBtn).withAlign(changeLicenseBtn, Alignment.BOTTOM_RIGHT);
        setContent(content);
    }
}
