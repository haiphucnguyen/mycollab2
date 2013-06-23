package com.esofthead.mycollab.module.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.DownloadStream;
import com.vaadin.terminal.FileResource;

public class StreamFolderDownloadResource extends FileResource {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(StreamFolderDownloadResource.class);

	public StreamFolderDownloadResource(String documentPath) {
		super(new File(FileStorageConfig.baseContentFolder, documentPath),
				AppContext.getApplication());
	}

	@Override
	public DownloadStream getStream() {
		try {
			String fileName = getFilename();
			fileName = fileName.replaceAll(" ", "_").replaceAll("-", "_");
			final DownloadStream ds = new DownloadStream(new FileInputStream(
					getSourceFile()), getMIMEType(), fileName);
			ds.setParameter("Content-Disposition", "attachment; filename="
					+ fileName);
			ds.setCacheTime(0);
			return ds;
		} catch (final FileNotFoundException e) {
			log.error("Error to create download stream", e);
			return null;
		}
	}
}
