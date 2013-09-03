package com.esofthead.mycollab.module.file.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.MyCollabThread;
import com.esofthead.mycollab.module.ecm.domain.ExternalContent;
import com.esofthead.mycollab.module.ecm.domain.ExternalDrive;
import com.esofthead.mycollab.module.ecm.domain.ExternalFolder;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.ExternalResourceService;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.StreamResource;

public class StreamFolderDropboxDownloadResource implements
		StreamResource.StreamSource {
	private static final long serialVersionUID = 1L;
	private Resource res;
	private ExternalResourceService externalResourceService;
	private ExternalDrive drive;
	private static Logger log = LoggerFactory
			.getLogger(StreamFolderDropboxDownloadResource.class);

	public StreamFolderDropboxDownloadResource(Resource res) {
		this.res = res;
		externalResourceService = AppContext
				.getSpringBean(ExternalResourceService.class);
		if (!(res instanceof ExternalFolder || res instanceof ExternalContent)) {
			throw new MyCollabException("Only Support Dropbox drive");
		} else {
			if (res instanceof ExternalFolder)
				drive = ((ExternalFolder) res).getExternalDrive();
			else
				drive = ((ExternalContent) res).getExternalDrive();
		}
	}

	@Override
	public InputStream getStream() {
		if (res instanceof ExternalFolder) {
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
						zipResource(zipOutStream, res);
						zipOutStream.close();
						outStream.close();
					} catch (Exception e) {
						log.error(
								"Error while zip content stream from Dropbox",
								e);
					}
				}
			});
			threadExport.start();
			return inStream;
		} else {
			return externalResourceService.download(
					StreamFolderDropboxDownloadResource.this.drive,
					StreamFolderDropboxDownloadResource.this.res.getPath());
		}
	}

	private void zipResource(ZipOutputStream zipOutStream, Resource res) {
		try {
			addFolderToZip(getFolderName((ExternalFolder) res), res,
					zipOutStream);
		} catch (Exception e) {
			log.error("Error while zip folder Dropbox", e);
		}
	}

	private void addFolderToZip(String path, Resource res, ZipOutputStream zip)
			throws Exception {
		ExternalDrive drive = getCurrentDrive(res);
		List<Resource> lstResource = externalResourceService.getResources(
				drive, res.getPath());
		if (res instanceof ExternalFolder && lstResource.size() == 0) { // emptyFolder
			zip.putNextEntry(new ZipEntry(path + "/"
					+ getFolderName((ExternalFolder) res) + "/"));
		} else {
			if (res instanceof ExternalFolder) {
				zip.putNextEntry(new ZipEntry(path + "/"
						+ getFolderName((ExternalFolder) res) + "/"));
			}
			for (Resource curRes : lstResource) {
				if (curRes instanceof ExternalContent) {
					addFileToZip(path, (ExternalContent) curRes, zip);
				} else {
					addFolderToZip(path + "/"
							+ getFolderName((ExternalFolder) res), curRes, zip);
				}
			}
		}
	}

	private void addFileToZip(String path, ExternalContent res,
			ZipOutputStream zip) throws Exception {
		byte[] buf = new byte[1024];
		int len = -1;
		InputStream contentStream = externalResourceService.download(
				res.getExternalDrive(), res.getPath());
		if (path.length() == 0)
			path = res.getName();
		else
			path += "/" + res.getName();
		zip.putNextEntry(new ZipEntry(path));
		while ((len = contentStream.read(buf)) > 0) {
			zip.write(buf, 0, len);
		}
	}

	private String getFolderName(ExternalFolder res) {
		if (res.getPath().equals("/"))
			return res.getExternalDrive().getFoldername();
		else {
			return res.getPath().substring(res.getPath().lastIndexOf("/") + 1);
		}
	}

	private ExternalDrive getCurrentDrive(Resource res) {
		if (res instanceof ExternalFolder)
			return ((ExternalFolder) res).getExternalDrive();
		return ((ExternalContent) res).getExternalDrive();
	}
}
