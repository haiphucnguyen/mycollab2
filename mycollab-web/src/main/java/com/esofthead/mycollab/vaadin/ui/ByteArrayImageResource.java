package com.esofthead.mycollab.vaadin.ui;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.StreamResource;

public class ByteArrayImageResource extends StreamResource {
	private static final long serialVersionUID = 1L;

	public ByteArrayImageResource(final byte[] imageData, String mimeType) {
		super(new StreamResource.StreamSource() {
			private static final long serialVersionUID = 1L;

			public InputStream getStream() {
				return new ByteArrayInputStream(imageData);
			}
		}, "avatar", AppContext.getApplication());

		this.setMIMEType(mimeType);
	}

	public void setImageData(final byte[] imageData) {
		this.setStreamSource(new StreamResource.StreamSource() {
			private static final long serialVersionUID = 1L;

			public InputStream getStream() {
				return new ByteArrayInputStream(imageData);
			}
		});
	}
}
