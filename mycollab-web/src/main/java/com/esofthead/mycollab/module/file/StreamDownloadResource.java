/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.file;

import com.esofthead.mycollab.module.file.service.impl.RawContentServiceImpl;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.DownloadStream;
import com.vaadin.terminal.FileResource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 *
 * @author haiphucnguyen
 */
public class StreamDownloadResource extends FileResource {

    public StreamDownloadResource(String documentPath) {
        super(new File(RawContentServiceImpl.baseContentFolder, documentPath), AppContext.getApplication());
    }

    @Override
    public DownloadStream getStream() {
        try {
            final DownloadStream ds = new DownloadStream(new FileInputStream(
                    getSourceFile()), getMIMEType(), getFilename());
            ds.setParameter("Content-Disposition", "attachment; filename="
                    + getFilename());
            ds.setCacheTime(-1);
            return ds;
        } catch (final FileNotFoundException e) {
            return null;
        }
    }
    
    
}
