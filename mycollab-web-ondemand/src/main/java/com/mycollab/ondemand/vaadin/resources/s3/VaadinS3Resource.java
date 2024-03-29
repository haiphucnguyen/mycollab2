package com.mycollab.ondemand.vaadin.resources.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.mycollab.core.MyCollabException;
import com.mycollab.ondemand.module.file.service.AmazonServiceConfiguration;
import com.mycollab.vaadin.resources.VaadinResource;
import com.vaadin.server.DownloadStream;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.util.FileTypeResolver;

import java.io.InputStream;

/**
 * @author MyCollab Ltd.
 * @since 4.5.1
 */
public class VaadinS3Resource implements VaadinResource {

    @Override
    public Resource getStreamResource(String documentPath) {
        return new S3StreamDownloadResource(documentPath);
    }

    private static class S3StreamDownloadResource extends StreamResource {
        private static final long serialVersionUID = 1L;

        private String documentPath;

        S3StreamDownloadResource(String documentPath) {
            super(new S3StreamSource(documentPath), extractFileName(documentPath));
            this.documentPath = documentPath;
            this.setMIMEType(FileTypeResolver.getMIMEType(extractFileName(documentPath)));
        }

        @Override
        public DownloadStream getStream() {
            StreamSource ss = getStreamSource();
            if (ss == null) {
                return null;
            }
            DownloadStream ds = new DownloadStream(ss.getStream(), getMIMEType(), extractFileName(documentPath));
            ds.setBufferSize(getBufferSize());
            ds.setParameter("Content-Disposition", "attachment; filename=" + extractFileName(documentPath));
            ds.setCacheTime(0);
            return ds;
        }
    }

    private static class S3StreamSource implements StreamResource.StreamSource {
        private static final long serialVersionUID = 1L;
        private String documentPath;

        S3StreamSource(String documentPath) {
            this.documentPath = documentPath;
        }

        @Override
        public InputStream getStream() {
            AmazonS3 s3Client = AmazonServiceConfiguration.getInstance().newS3Client();
            try {
                S3Object obj = s3Client.getObject(new GetObjectRequest(AmazonServiceConfiguration.getInstance().getBucket(), documentPath));
                return obj.getObjectContent();
            } catch (Exception e) {
                throw new MyCollabException("Error when get input stream from s3 with path " + documentPath, e);
            }
        }
    }

    private static String extractFileName(String documentPath) {
        int index = documentPath.lastIndexOf("/");
        if (index > -1) {
            return documentPath.substring(index + 1);
        } else {
            return documentPath;
        }
    }
}
