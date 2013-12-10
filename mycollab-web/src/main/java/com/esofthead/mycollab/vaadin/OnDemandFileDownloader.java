package com.esofthead.mycollab.vaadin;

import com.vaadin.server.FileDownloader;

public class OnDemandFileDownloader extends FileDownloader {
	private static final long serialVersionUID = 1L;

	public OnDemandFileDownloader(StreamResourceFactory factory) {
		super(factory.getStreamResource());
	}
}
