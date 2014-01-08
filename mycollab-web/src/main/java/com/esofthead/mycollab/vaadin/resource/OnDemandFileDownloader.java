package com.esofthead.mycollab.vaadin.resource;

import com.vaadin.server.FileDownloader;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class OnDemandFileDownloader extends FileDownloader {
	private static final long serialVersionUID = 1L;

	public OnDemandFileDownloader(StreamResourceFactory factory) {
		super(factory.getStreamResource());
	}
}
