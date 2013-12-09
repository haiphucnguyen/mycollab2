package com.esofthead.mycollab.vaadin.ui;

import java.io.IOException;

import com.esofthead.mycollab.core.MyCollabException;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinResponse;

public class OnDemandFileDownloader extends FileDownloader {
	/**
	 * Provide both the {@link StreamSource} and the filename in an on-demand
	 * way.
	 */
	public interface OnDemandStreamResource extends StreamSource {
		String getFilename();
	}

	private static final long serialVersionUID = 1L;
	private final OnDemandStreamResource onDemandStreamResource;

	public OnDemandFileDownloader(OnDemandStreamResource onDemandStreamResource) {
		super(new StreamResource(onDemandStreamResource, ""));
		if (onDemandStreamResource == null) {
			throw new MyCollabException(
					"The given on-demand stream resource may never be null!");
		}

		this.onDemandStreamResource = onDemandStreamResource;
	}

	@Override
	public boolean handleConnectorRequest(VaadinRequest request,
			VaadinResponse response, String path) throws IOException {
		getResource().setFilename(onDemandStreamResource.getFilename());
		return super.handleConnectorRequest(request, response, path);
	}

	private StreamResource getResource() {
		return (StreamResource) this.getResource("dl");
	}
}
