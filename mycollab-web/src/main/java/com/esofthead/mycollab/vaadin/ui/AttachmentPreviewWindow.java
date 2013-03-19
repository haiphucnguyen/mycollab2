package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.Embedded;
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
		Embedded previewImage = new Embedded(null, this.previewResource);
		LazyLoadWrapper imageLazyLoader = new LazyLoadWrapper(previewImage);
		this.setContent(imageLazyLoader);
		this.setResizable(false);
	}
}
