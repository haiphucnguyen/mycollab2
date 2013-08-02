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

import com.esofthead.mycollab.core.MyCollabException;
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

	private boolean isSearchAction;

	public StreamFolderDownloadResource(String[] folderPath,
			boolean isSearchAction) {
		this.folderPath = folderPath;
		resourceService = ApplicationContextUtil.getBean(ResourceService.class);
		this.isSearchAction = isSearchAction;
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
					if (isSearchAction)
						zipResourceWithSearchAction(zipOutStream, folderPath);
					else
						zipResource(zipOutStream, folderPath);
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

	private void zipResourceWithSearchAction(ZipOutputStream zip,
			String... path) {
		try {
			for (String resPath : path) {
				Folder resParentFolder = resourceService
						.getParentFolder(resPath);
				Resource curRes = resourceService.getResource(resPath);
				String currentResourcePath = "";
				try {
					currentResourcePath = resParentFolder.getPath().substring(
							resParentFolder.getPath().indexOf("/", 2) + 1);
				} catch (Exception e) {
					currentResourcePath = "";
				}
				currentResourcePath = currentResourcePath.replace("/", "");
				byte[] buf = new byte[1024];
				int len = -1;
				InputStream contentStream = resourceService
						.getContentStream(curRes.getPath());
				zip.putNextEntry(new ZipEntry(currentResourcePath
						+ curRes.getName()));
				while ((len = contentStream.read(buf)) > 0) {
					zip.write(buf, 0, len);
				}
			}
		} catch (Exception e) {
			throw new MyCollabException(e);
		}
	}

	private void zipResource(ZipOutputStream zipOutputStream, String... path) {
		try {
			List<Resource> lstResource = new ArrayList<Resource>();
			for (String resPath : path) {
				Resource currentResource = resourceService.getResource(resPath);
				if (currentResource instanceof Folder) {
					lstResource = resourceService.getResources(resPath);
					if (lstResource.size() == 0) {
						zipOutputStream.putNextEntry(new ZipEntry(
								currentResource.getName() + "/"));
					} else {
						for (Resource res : lstResource) {
							if (res instanceof Content) {
								addFileToZip(currentResource.getName(),
										(Content) res, zipOutputStream);
							} else if (res instanceof Folder) {
								addFolderToZip(currentResource.getName(), res,
										zipOutputStream);
							}
						}
					}
				} else {
					addFileToZip("", (Content) currentResource, zipOutputStream);
				}
			}
		} catch (Exception e) {
			log.error("Error while save content", e);
		}
	}

	private void addFileToZip(String path, Content res, ZipOutputStream zip)
			throws Exception {
		byte[] buf = new byte[1024];
		int len = -1;
		InputStream contentStream = resourceService.getContentStream(res
				.getPath());
		if (path.length() == 0)
			path = res.getName();
		else
			path += "/" + res.getName();
		zip.putNextEntry(new ZipEntry(path));
		while ((len = contentStream.read(buf)) > 0) {
			zip.write(buf, 0, len);
		}
	}

	private void addFolderToZip(String path, Resource res, ZipOutputStream zip)
			throws Exception {
		List<Resource> lstResource = resourceService
				.getResources(res.getPath());
		if (res instanceof Folder && lstResource.size() == 0) { // emptyFolder
			zip.putNextEntry(new ZipEntry(path + "/" + res.getName() + "/"));
		} else {
			if (res instanceof Folder) {
				zip.putNextEntry(new ZipEntry(path + "/" + res.getName() + "/"));
			}
			for (Resource curRes : lstResource) {
				if (curRes instanceof Folder) {
					addFolderToZip(path + "/" + res.getName(), curRes, zip);
				} else {
					addFileToZip(path + "/" + res.getName(), (Content) curRes,
							zip);
				}
			}
		}
	}

}
