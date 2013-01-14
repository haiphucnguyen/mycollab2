/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.file;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.vaadin.terminal.StreamResource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author haiphucnguyen
 */
public class ExportStreamResource<S extends SearchCriteria> implements StreamResource.StreamSource {
    private static Logger log = LoggerFactory.getLogger(ExportStreamResource.class);

    private ISearchableService<S> searchService;
    private S searchCriteria;

    public ExportStreamResource(ISearchableService searchService, S searchCriteria) {
        this.searchCriteria = searchCriteria;
        this.searchService = searchService;
    }

    @Override
    public InputStream getStream() {
        
        int totalItems = searchService.getTotalCount(searchCriteria);
        if (totalItems == 0) {
            return null;
        }
        
        int numPage = totalItems / SearchRequest.DEFAULT_NUMBER_SEARCH_ITEMS + 1;
        BufferedOutputStream outStream;
        
        try {
            outStream = new BufferedOutputStream(new FileOutputStream(File.createTempFile("export", ".tmp")));
        } catch (IOException ex) {
            log.error("Can not create temp file", ex);
            return null;
        }
        
        for (int i = 0; i < numPage; i++) {
            
        }
        return null;
    }
}
