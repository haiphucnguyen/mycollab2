/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.file;

import com.vaadin.Application;
import com.vaadin.terminal.DownloadStream;
import com.vaadin.terminal.StreamResource;
import java.io.InputStream;

/**
 *
 * @author haiphucnguyen
 */
public class StreamDownloadResource extends StreamResource {

    public StreamDownloadResource(InputStream inputStream, String filename, Application application) {
        super(new DefaultStreamSource(inputStream), filename, application);
    }

    @Override
    public DownloadStream getStream() {
        final DownloadStream ds = new DownloadStream(getStreamSource().getStream(), getMIMEType(), getFilename());
//        ds.setParameter("Content-Disposition", "attachment; filename="
//                + getFilename());
        ds.setCacheTime(-1);
        return ds;
    }

    private static class DefaultStreamSource implements StreamSource {

        private InputStream inputStream;

        public DefaultStreamSource(InputStream inputStream) {
        }

        @Override
        public InputStream getStream() {
            return inputStream;
        }
    }
}
