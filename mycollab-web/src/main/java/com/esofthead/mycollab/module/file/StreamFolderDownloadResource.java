package com.esofthead.mycollab.module.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.MyCollabThread;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.vaadin.terminal.StreamResource;

public class StreamFolderDownloadResource implements
		StreamResource.StreamSource {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(StreamFolderDownloadResource.class);

	private String[] folderPath;

	private ResourceService resourceService;

	public StreamFolderDownloadResource(String[] folderPath) {
		this.folderPath = folderPath;
		resourceService = ApplicationContextUtil.getApplicationContext()
				.getBean(ResourceService.class);
	}

	@Override
	public InputStream getStream() {
		final PipedInputStream inStream = new PipedInputStream();
		final PipedOutputStream outStream;

		try {
			outStream = new PipedOutputStream(inStream);
		} catch (IOException ex) {
			log.error("Can not create outstream file", ex);
			return null;
		}

		Thread threadExport = new MyCollabThread(new Runnable() {

			@Override
			public void run() {
				try {
					ZipOutputStream zipOutStream = new ZipOutputStream(
							outStream);
					saveContentToStream(zipOutStream, folderPath);
					zipOutStream.close();
					outStream.close();
				} catch (Exception e) {
					log.error("Error while saving content stream", e);
				}
			}

		});

		threadExport.start();

		return inStream;
	}

	private void saveContentToStream(ZipOutputStream zipOutputStream,
			String... path) {
		try {
			for (String resPath : path) {
				List<Resource> resources = resourceService
						.getResources(resPath);
				for (Resource resource : resources) {
					if (resource instanceof Content) {
						InputStream contentStream = resourceService
								.getContentStream(resource.getPath());
						log.debug("Add file entry " + resource.getName()
								+ " to zip file");
						String entryPath = resource.getPath().substring(
								resource.getPath().indexOf(resPath)
										+ resPath.length() + 1);
						ZipEntry entry = new ZipEntry(entryPath);
						zipOutputStream.putNextEntry(entry);
						byte[] bytes = new byte[1024];
						int length = -1;

						while ((length = contentStream.read(bytes)) != -1) {
							zipOutputStream.write(bytes, 0, length);
						}
						zipOutputStream.closeEntry();
					} else if (resource instanceof Folder) {
						saveContentToStream(zipOutputStream, resource.getPath());
					}
				}
			}
		} catch (Exception e) {
			log.error("Error while save content", e);
		}
	}

}
