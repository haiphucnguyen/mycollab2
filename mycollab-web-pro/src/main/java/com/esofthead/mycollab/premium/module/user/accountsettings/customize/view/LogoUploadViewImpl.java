/**
 * This file is part of mycollab-web-premium.
 *
 * mycollab-web-premium is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web-premium is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web-premium.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.premium.module.user.accountsettings.customize.view;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.utils.ImageUtil;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.file.service.AccountLogoService;
import com.esofthead.mycollab.module.user.accountsettings.view.events.AccountCustomizeEvent;
import com.esofthead.mycollab.module.user.domain.AccountTheme;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AccountLogoFactory;
import com.esofthead.mycollab.vaadin.ui.ByteArrayImageResource;
import com.esofthead.mycollab.vaadin.ui.ThemeManager;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.vaadin.cropField.CropField;
import com.esofthead.vaadin.cropField.client.VCropSelection;
import com.vaadin.data.Property;
import com.vaadin.server.Resource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.1
 * 
 */

@ViewComponent
public class LogoUploadViewImpl extends AbstractPageView implements
		LogoUploadView {
	private static final long serialVersionUID = -5294741083557671011L;

	private static final Logger LOG = LoggerFactory.getLogger(LogoUploadViewImpl.class);

	private BufferedImage originalImage;
	private Image previewImage;
	private byte[] scaleImageData;

	public LogoUploadViewImpl() {
		this.setMargin(true);
	}

	@SuppressWarnings("serial")
	@Override
	public void editPhoto(byte[] imageData, final AccountTheme accountTheme) {
		this.removeAllComponents();
		LOG.debug("Receive logo upload with size: " + imageData.length);
		try {
			originalImage = ImageIO.read(new ByteArrayInputStream(imageData));
		} catch (IOException e) {
			throw new UserInvalidInputException("Invalid image type");
		}
		originalImage = ImageUtil.scaleImage(originalImage, 650, 650);

		MHorizontalLayout previewBox = new MHorizontalLayout().withMargin(new MarginInfo(false, true, true, false))
				.withWidth("100%");

		final String logoId = ThemeManager.loadLogoPath(AppContext
				.getAccountId());
		Resource defaultPhoto = AccountLogoFactory.createLogoResource(logoId,
				150);
		previewImage = new Image(null, defaultPhoto);
		previewImage.setWidth("100px");
		previewBox.addComponent(previewImage);
		previewBox.setComponentAlignment(previewImage, Alignment.TOP_LEFT);

		MVerticalLayout previewBoxRight = new MVerticalLayout().withSpacing(false).withMargin(new MarginInfo(false, true, false, true));

		Label lbPreview = new Label(
				"<p style='margin: 0px;'><strong>To the left is what your logo will look like.</strong></p><p style='margin-top: 0px;'>To make adjustment, you can drag around and resize the selection square below. When you are happy with your photo, click the &ldquo;Accept&ldquo; button.</p>",
				ContentMode.HTML);
		previewBoxRight.addComponent(lbPreview);

		MHorizontalLayout controlBtns = new MHorizontalLayout();
		controlBtns.setSizeUndefined();
		controlBtns.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

		Button cancelBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL),
				new Button.ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						EventBusFactory.getInstance().post(
								new AccountCustomizeEvent.GotoMainPage(
										LogoUploadViewImpl.this, null));
					}
				});
		cancelBtn.setStyleName(UIConstants.THEME_GRAY_LINK);
		controlBtns.with(cancelBtn);

		Button acceptBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_ACCEPT),
				new Button.ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						if (scaleImageData != null && scaleImageData.length > 0) {

							try {
								BufferedImage image = ImageIO
										.read(new ByteArrayInputStream(
												scaleImageData));
								AccountLogoService accountLogoService = ApplicationContextUtil
										.getSpringBean(AccountLogoService.class);
								String newlogoId = accountLogoService
										.uploadLogo(AppContext.getUsername(),
												image, logoId,
												AppContext.getAccountId());
								accountTheme.setLogopath(newlogoId);
								EventBusFactory
										.getInstance()
										.post(new AccountCustomizeEvent.GotoMainPage(
												LogoUploadViewImpl.this,
												accountTheme));
							} catch (IOException e) {
								throw new MyCollabException(
										"Error when saving account logo", e);
							}

						}

					}
				});
		acceptBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
		controlBtns.with(acceptBtn);

		previewBoxRight.with(controlBtns).withAlign(controlBtns, Alignment.TOP_LEFT);
		previewBox.with(previewBoxRight).expand(previewBoxRight);
		this.addComponent(previewBox);

		CssLayout cropBox = new CssLayout();
		cropBox.addStyleName(UIConstants.PHOTO_CROPBOX);
		cropBox.setWidth("100%");
		VerticalLayout currentPhotoBox = new VerticalLayout();
		Resource resource = new ByteArrayImageResource(
				ImageUtil.convertImageToByteArray(originalImage), "image/png");
		CropField cropField = new CropField(resource);
		cropField.setImmediate(true);
		cropField.setSelectionAspectRatio(150 / 28);
		cropField.addValueChangeListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				VCropSelection newSelection = (VCropSelection) event
						.getProperty().getValue();
				int x1 = newSelection.getXTopLeft();
				int y1 = newSelection.getYTopLeft();
				int x2 = newSelection.getXBottomRight();
				int y2 = newSelection.getYBottomRight();
				if (x2 > x1 && y2 > y1) {
					BufferedImage subImage = originalImage.getSubimage(x1, y1,
							(x2 - x1), (y2 - y1));
					ByteArrayOutputStream outStream = new ByteArrayOutputStream();
					try {
						ImageIO.write(subImage, "png", outStream);
						scaleImageData = outStream.toByteArray();
						displayPreviewImage();
					} catch (IOException e) {
						LOG.error("Error while scale image: ", e);
					}
				}

			}

		});
		currentPhotoBox.setWidth("650px");
		currentPhotoBox.setHeight("650px");

		currentPhotoBox.addComponent(cropField);

		cropBox.addComponent(currentPhotoBox);

		this.with(previewBox, cropBox).expand(cropBox);
	}

	private void displayPreviewImage() {
		if (scaleImageData != null && scaleImageData.length > 0) {
			ByteArrayImageResource previewResource = new ByteArrayImageResource(
					scaleImageData, "image/png");
			previewImage.setSource(previewResource);
		}
	}
}
