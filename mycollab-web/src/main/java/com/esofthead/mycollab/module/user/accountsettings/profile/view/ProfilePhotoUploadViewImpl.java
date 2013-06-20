package com.esofthead.mycollab.module.user.accountsettings.profile.view;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.davengo.web.vaadin.crop.CropField;
import com.davengo.web.vaadin.crop.widgetset.client.ui.VCropSelection;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.file.service.UserAvatarService;
import com.esofthead.mycollab.module.user.accountsettings.view.events.ProfileEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ByteArrayImageResource;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class ProfilePhotoUploadViewImpl extends AbstractView implements
		ProfilePhotoUploadView {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(ProfilePhotoUploadViewImpl.class);

	private BufferedImage originalImage;
	private Embedded previewImage;
	private byte[] scaleImageData;

	public ProfilePhotoUploadViewImpl() {
		this.setMargin(true);
	}

	@SuppressWarnings("serial")
	@Override
	public void editPhoto(final byte[] imageData) {
		this.removeAllComponents();
		log.debug("Receive avatar upload with size: " + imageData.length);
		try {
			originalImage = ImageIO.read(new ByteArrayInputStream(imageData));
		} catch (IOException e) {
			throw new MyCollabException("Invalid image type");
		}
		originalImage = ImageUtil.scaleImage(originalImage, 650, 650);

		HorizontalLayout previewBox = new HorizontalLayout();
		previewBox.setSpacing(true);
		previewBox.setMargin(false, true, true, false);
		previewBox.setWidth("100%");
		previewBox.setHeight(SIZE_UNDEFINED, 0);

		Resource defaultPhoto = UserAvatarControlFactory.createAvatarResource(
				AppContext.getUserAvatarId(), 100);
		previewImage = new Embedded(null, defaultPhoto);
		previewImage.setWidth("100px");
		previewBox.addComponent(previewImage);
		previewBox.setComponentAlignment(previewImage, Alignment.TOP_LEFT);

		VerticalLayout previewBoxRight = new VerticalLayout();
		previewBoxRight.setMargin(false, true, false, true);
		Label lbPreview = new Label(
				"<p style='margin: 0px;'><strong>To the left is what your profile photo will look like.</strong></p><p style='margin-top: 0px;'>To make adjustments, you can drag around and resize the selection square below. When you are happy with your photo click the &ldquo;Accept&ldquo; button.</p>",
				Label.CONTENT_XHTML);
		previewBoxRight.addComponent(lbPreview);

		HorizontalLayout controlBtns = new HorizontalLayout();
		controlBtns.setSpacing(true);
		controlBtns.setSizeUndefined();

		Button cancelBtn = new Button("Cancel", new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new ProfileEvent.GotoProfileView(
								ProfilePhotoUploadViewImpl.this, null));
			}
		});
		cancelBtn.setStyleName("link");
		controlBtns.addComponent(cancelBtn);
		controlBtns.setComponentAlignment(cancelBtn, Alignment.MIDDLE_LEFT);

		Button acceptBtn = new Button("Accept", new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				if (scaleImageData != null && scaleImageData.length > 0) {

					try {
						BufferedImage image = ImageIO
								.read(new ByteArrayInputStream(scaleImageData));
						UserAvatarService userAvatarService = AppContext
								.getSpringBean(UserAvatarService.class);
						userAvatarService.uploadAvatar(image,
								AppContext.getUsername(),
								AppContext.getUserAvatarId());
						EventBus.getInstance().fireEvent(
								new ProfileEvent.GotoProfileView(
										ProfilePhotoUploadViewImpl.this, null));
					} catch (IOException e) {
						throw new MyCollabException(
								"Error when saving user avatar", e);
					}

				}

			}
		});
		acceptBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		controlBtns.addComponent(acceptBtn);
		controlBtns.setComponentAlignment(acceptBtn, Alignment.TOP_LEFT);

		previewBoxRight.addComponent(controlBtns);
		previewBoxRight.setComponentAlignment(controlBtns, Alignment.TOP_LEFT);

		previewBox.addComponent(previewBoxRight);
		previewBox.setExpandRatio(previewBoxRight, 1.0f);

		this.addComponent(previewBox);

		CssLayout cropBox = new CssLayout();
		cropBox.addStyleName(UIConstants.PHOTO_CROPBOX);
		cropBox.setWidth("100%");
		Panel currentPhotoBox = new Panel();
		Resource resource = new ByteArrayImageResource(
				ImageUtil.convertImageToByteArray(originalImage), "image/png");
		CropField cropField = new CropField(resource);
		cropField.setImmediate(true);
		cropField.setSelectionAspectRatio(1.0f);
		cropField.addListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
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
						log.error("Error while scale image: ", e);
					}
				}

			}

		});
		currentPhotoBox.setWidth("650px");
		currentPhotoBox.setHeight("650px");
		currentPhotoBox.addStyleName(UIConstants.PANEL_WITHOUT_BORDER);
		currentPhotoBox.getContent().setSizeUndefined();
		currentPhotoBox.addComponent(cropField);
		((VerticalLayout) currentPhotoBox.getContent()).setMargin(false);

		cropBox.addComponent(currentPhotoBox);

		this.addComponent(previewBox);
		this.addComponent(cropBox);
		this.setExpandRatio(cropBox, 1.0f);
	}

	private void displayPreviewImage() {
		if (scaleImageData != null && scaleImageData.length > 0) {
			ByteArrayImageResource previewResource = new ByteArrayImageResource(
					scaleImageData, "image/png");
			previewImage.setSource(previewResource);
		}
	}

}
