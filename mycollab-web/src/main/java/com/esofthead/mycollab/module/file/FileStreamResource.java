package com.esofthead.mycollab.module.file;

import java.io.InputStream;

import com.vaadin.Application;
import com.vaadin.terminal.StreamResource;

@SuppressWarnings("serial")
public class FileStreamResource extends StreamResource {
	public FileStreamResource(final InputStream inputStream, String filename,
			Application app) {
		super(new StreamSource() {

			@Override
			public InputStream getStream() {
				return inputStream;
			}
		}, filename, app);
	}

}
