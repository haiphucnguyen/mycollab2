/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.file;

import java.io.InputStream;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.service.FileTypeResolver;
import com.vaadin.terminal.DownloadStream;
import com.vaadin.terminal.StreamResource;

/**
 * 
 * @author haiphucnguyen
 */
public class S3StreamDownloadResource extends StreamResource {

	private static final long serialVersionUID = 1L;

	private String documentPath;

	public S3StreamDownloadResource(String documentPath) {
		super(new S3StreamSource(documentPath), getFilename(documentPath),
				AppContext.getApplication());
		this.documentPath = documentPath;
		this.setMIMEType(FileTypeResolver
				.getMIMEType(getFilename(documentPath)));
	}

	@Override
	public DownloadStream getStream() {
		final StreamSource ss = getStreamSource();
		if (ss == null) {
			return null;
		}
		final DownloadStream ds = new DownloadStream(ss.getStream(),
				getMIMEType(), getFilename(documentPath));
		ds.setBufferSize(getBufferSize());
		ds.setParameter("Content-Disposition", "attachment; filename="
				+ getFilename(documentPath));
		ds.setCacheTime(0);
		return ds;
	}

	private static String getFilename(String documentPath) {
		int index = documentPath.lastIndexOf("/");
		if (index > -1) {
			return documentPath.substring(index + 1);
		} else {
			return documentPath;
		}
	}

	private static class S3StreamSource implements StreamSource {
		private static final long serialVersionUID = 1L;
		private String documentPath;

		public S3StreamSource(String documentPath) {
			this.documentPath = documentPath;
		}

		@Override
		public InputStream getStream() {
			String fileName = getFilename(documentPath);
			fileName = fileName.replaceAll(" ", "_").replaceAll("-", "_");
			AmazonS3 s3Client = S3RepoConfig.getS3Client();
			try {
				S3Object obj = s3Client.getObject(new GetObjectRequest(
						S3RepoConfig.getBucket(), AppContext.getAccountId()
								+ "/" + documentPath));

				return obj.getObjectContent();
			} catch (Exception e) {
				throw new MyCollabException(e);
			}
		}

	}
}
