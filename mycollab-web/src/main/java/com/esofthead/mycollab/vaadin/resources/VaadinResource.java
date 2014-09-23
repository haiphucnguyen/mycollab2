package com.esofthead.mycollab.vaadin.resources;

import com.vaadin.server.Resource;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.1
 *
 */
public interface VaadinResource {
	Resource getStreamResource(String documentPath);
}
