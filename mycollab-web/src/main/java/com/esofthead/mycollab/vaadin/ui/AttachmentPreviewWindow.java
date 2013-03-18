package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.terminal.Resource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class AttachmentPreviewWindow extends Window {
	private static final long serialVersionUID = 1L;

	private final Resource previewResource;

	public AttachmentPreviewWindow(Resource previewResource) {
		this.previewResource = previewResource;
		initLayout();
	}

	private void initLayout() {
		this.setSizeUndefined();
		this.setCaption("Image Preview");
		initUI();
		center();
	}

	private void initUI() {
		VerticalLayout mainLayout = new VerticalLayout();
		Embedded previewImage = new Embedded(null, this.previewResource);
		mainLayout.addComponent(previewImage);
		mainLayout.setSizeUndefined();
		this.setContent(mainLayout);
		this.setResizable(false);
	}
}
