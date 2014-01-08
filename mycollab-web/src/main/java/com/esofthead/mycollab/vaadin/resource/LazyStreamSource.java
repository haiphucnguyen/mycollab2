package com.esofthead.mycollab.vaadin.resource;

import java.io.InputStream;

import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public abstract class LazyStreamSource implements StreamResource.StreamSource {
	private static final long serialVersionUID = 1L;

	@Override
	public InputStream getStream() {
		StreamSource streamSource = buildStreamSource();
		return streamSource.getStream();
	}

	abstract protected StreamSource buildStreamSource();

}
