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
import com.esofthead.mycollab.vaadin.mvp.HAbstractView;
import com.esofthead.mycollab.vaadin.ui.ByteArrayImageResource;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class ProfilePhotoUploadViewImpl extends HAbstractView implements
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
		try {
			originalImage = ImageIO.read(new ByteArrayInputStream(imageData));
		} catch (IOException e) {
			throw new MyCollabException("Invalid image type");
		}
		Panel currentPhotoBox = new Panel();
		Resource resource = new ByteArrayImageResource(imageData, "image/png");
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

		});
		currentPhotoBox.setWidth("500px");
		currentPhotoBox.setHeight("500px");
		currentPhotoBox.getContent().setSizeUndefined();
		currentPhotoBox.addComponent(cropField);
		this.addComponent(currentPhotoBox);

		VerticalLayout previewBox = new VerticalLayout();
		previewBox.setWidth("200px");
		previewBox.addComponent(new Label("AAA"));
		previewImage = new Embedded(null);
		previewImage.setWidth("150px");
		previewBox.addComponent(previewImage);
		this.addComponent(previewBox);
	}

	private void displayPreviewImage() {
		if (scaleImageData != null && scaleImageData.length > 0) {
			ByteArrayImageResource previewResource = new ByteArrayImageResource(
					scaleImageData, "image/png");
			previewImage.setIcon(previewResource);
		}
	}

}
