package com.esofthead.mycollab.module.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
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
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.StreamResource;

public class StreamFolderDownloadResource implements
		StreamResource.StreamSource {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(StreamFolderDownloadResource.class);

	private String[] folderPath;

	private ResourceService resourceService;

	private List<String> lstFileDownloadName;

	public StreamFolderDownloadResource(String[] folderPath) {
		this.folderPath = folderPath;
		resourceService = ApplicationContextUtil.getBean(ResourceService.class);
		lstFileDownloadName = new ArrayList<String>();
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
					lstFileDownloadName.clear();
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
				List<Resource> lstResource;
				Resource currentResource = resourceService.getResource(resPath);
				if (currentResource instanceof Folder) {
					lstResource = resourceService.getResources(resPath);
				} else {
					lstResource = new ArrayList<Resource>();
					lstResource.add(currentResource);
				}
				for (Resource res : lstResource) {
					if (res instanceof Content) {
						String contentName = res.getName();
						if (lstFileDownloadName.size() > 0) {
							if (lstFileDownloadName.indexOf(contentName) != -1) {
								contentName = resourceService.getParentFolder(
										res.getPath()).getName()
										+ "_" + contentName;
								if (lstFileDownloadName.indexOf(contentName) != -1) {
									contentName = res
											.getPath()
											.replace("/", "_")
											.substring(
													AppContext.getAccountId()
															.toString()
															.length() + 1);
								}
							}
							lstFileDownloadName.add(contentName);
						} else {
							lstFileDownloadName.add(contentName);
						}
						InputStream contentStream = resourceService
								.getContentStream(res.getPath());
						log.debug("Add file entry " + contentName
								+ " to zip file");

						ZipEntry entry = new ZipEntry(contentName);

						zipOutputStream.putNextEntry(entry);
						byte[] bytes = new byte[1024];
						int length = -1;

						while ((length = contentStream.read(bytes)) != -1) {
							zipOutputStream.write(bytes, 0, length);
						}
						zipOutputStream.closeEntry();
					} else if (res instanceof Folder) {
						String folderPath = res
								.getPath()
								.replace("/", "_")
								.substring(
										AppContext.getAccountId().toString()
												.length() + 1);
						ZipEntry entry = new ZipEntry(folderPath + "/");
						zipOutputStream.putNextEntry(entry);
						saveContentToStream(zipOutputStream, res.getPath());
					}
				}
			}
		} catch (Exception e) {
			log.error("Error while save content", e);
		}
	}

}
