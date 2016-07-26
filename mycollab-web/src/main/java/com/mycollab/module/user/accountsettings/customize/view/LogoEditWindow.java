/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.user.accountsettings.customize.view;

import com.esofthead.vaadin.cropField.CropField;
import com.esofthead.vaadin.cropField.client.VCropSelection;
import com.mycollab.common.i18n.FileI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.MyCollabException;
import com.mycollab.core.UserInvalidInputException;
import com.mycollab.core.utils.ImageUtil;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.file.service.AccountLogoService;
import com.mycollab.module.user.accountsettings.view.events.SettingEvent;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.ui.AccountAssetsResolver;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.ByteArrayImageResource;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
public class LogoEditWindow extends MWindow {
    private static final long serialVersionUID = -5294741083557671011L;
    private static final Logger LOG = LoggerFactory.getLogger(LogoEditWindow.class);

    private MVerticalLayout content;

    private BufferedImage originalImage;
    private Embedded previewImage;
    private byte[] scaleImageData;

    public LogoEditWindow(byte[] imageData) {
        super(AppContext.getMessage(FileI18nEnum.ACTION_CHANGE_LOGO));
        this.withModal(true).withResizable(false).withWidth("800px").withHeight("800px");
        content = new MVerticalLayout();
        this.setContent(content);
        editPhoto(imageData);
    }

    private void editPhoto(byte[] imageData) {
        try {
            originalImage = ImageIO.read(new ByteArrayInputStream(imageData));
        } catch (IOException e) {
            throw new UserInvalidInputException("Invalid image type");
        }
        originalImage = ImageUtil.scaleImage(originalImage, 650, 650);

        MHorizontalLayout previewBox = new MHorizontalLayout().withMargin(new MarginInfo(false, true, true, false))
                .withFullWidth();

        final String logoPath = AppContext.getBillingAccount().getLogopath();
        Resource defaultPhoto = AccountAssetsResolver.createLogoResource(logoPath, 150);
        previewImage = new Embedded(null, defaultPhoto);
        previewImage.setWidth("100px");
        previewBox.addComponent(previewImage);
        previewBox.setComponentAlignment(previewImage, Alignment.TOP_LEFT);

        MVerticalLayout previewBoxRight = new MVerticalLayout().withSpacing(false).withMargin(new MarginInfo(false, true, false, true));

        Label lbPreview = new Label("<p style='margin: 0px;'><strong>To the below is what your logo will look like.</strong></p><p " +
                "style='margin-top: 0px;'>To make adjustment, you can drag around and resize the selection square below. " +
                "When you are happy with your photo, click the <strong>Accept</strong> button.</p>", ContentMode.HTML);
        previewBoxRight.addComponent(lbPreview);

        MButton cancelBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL),
                clickEvent -> EventBusFactory.getInstance().post(new SettingEvent.GotoGeneralSetting(LogoEditWindow.this, null)))
                .withStyleName(WebUIConstants.BUTTON_OPTION);

        MButton acceptBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_ACCEPT), clickEvent -> {
            if (scaleImageData != null && scaleImageData.length > 0) {
                try {
                    BufferedImage image = ImageIO.read(new ByteArrayInputStream(scaleImageData));
                    AccountLogoService accountLogoService = AppContextUtil.getSpringBean(AccountLogoService.class);
                    accountLogoService.upload(AppContext.getUsername(),
                            image, AppContext.getAccountId());
                    Page.getCurrent().getJavaScript().execute("window.location.reload();");
                } catch (IOException e) {
                    throw new MyCollabException("Error when saving account logo", e);
                }
            }
        }).withStyleName(WebUIConstants.BUTTON_ACTION);

        MHorizontalLayout controlBtns = new MHorizontalLayout(acceptBtn, cancelBtn);
        previewBoxRight.with(controlBtns).withAlign(controlBtns, Alignment.TOP_LEFT);
        previewBox.with(previewBoxRight).expand(previewBoxRight);
        content.addComponent(previewBox);

        CssLayout cropBox = new CssLayout();
        cropBox.setWidth("100%");
        VerticalLayout currentPhotoBox = new VerticalLayout();
        Resource resource = new ByteArrayImageResource(
                ImageUtil.convertImageToByteArray(originalImage), "image/png");
        CropField cropField = new CropField(resource);
        cropField.setImmediate(true);
        cropField.setSelectionAspectRatio(150 / 28);
        cropField.addValueChangeListener(valueChangeEvent -> {
            VCropSelection newSelection = (VCropSelection) valueChangeEvent.getProperty().getValue();
            int x1 = newSelection.getXTopLeft();
            int y1 = newSelection.getYTopLeft();
            int x2 = newSelection.getXBottomRight();
            int y2 = newSelection.getYBottomRight();
            if (x2 > x1 && y2 > y1) {
                BufferedImage subImage = originalImage.getSubimage(x1, y1, (x2 - x1), (y2 - y1));
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                try {
                    ImageIO.write(subImage, "png", outStream);
                    scaleImageData = outStream.toByteArray();
                    displayPreviewImage();
                } catch (IOException e) {
                    LOG.error("Error while scale image: ", e);
                }
            }
        });
        currentPhotoBox.setWidth("650px");
        currentPhotoBox.setHeight("650px");
        currentPhotoBox.addComponent(cropField);
        cropBox.addComponent(currentPhotoBox);

        content.with(previewBox, ELabel.hr(), cropBox);
    }

    private void displayPreviewImage() {
        if (scaleImageData != null && scaleImageData.length > 0) {
            ByteArrayImageResource previewResource = new ByteArrayImageResource(scaleImageData, "image/png");
            previewImage.setSource(previewResource);
        }
    }
}
