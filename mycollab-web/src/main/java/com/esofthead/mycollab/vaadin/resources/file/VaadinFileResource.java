package com.esofthead.mycollab.vaadin.resources.file;

import com.esofthead.mycollab.vaadin.resources.VaadinResource;
import com.vaadin.server.Resource;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.1
 *
 */
public class VaadinFileResource implements VaadinResource {

	@Override
	public Resource getStreamResource(String documentPath) {
		return new FileStreamDownloadResource(documentPath);
	}

}
