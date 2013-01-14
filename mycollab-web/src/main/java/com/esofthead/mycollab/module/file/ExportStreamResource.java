/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.file;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.vaadin.terminal.StreamResource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author haiphucnguyen
 */
public abstract class ExportStreamResource implements StreamResource.StreamSource {

    private static Logger log = LoggerFactory.getLogger(ExportStreamResource.class);
    protected String[] visibleColumns;
    protected String[] headerNames;

    public ExportStreamResource(String[] visibleColumns, String[] headerNames) {
        this.visibleColumns = visibleColumns;
        this.headerNames = headerNames;
    }

    public static class AllItems<S extends SearchCriteria> extends ExportStreamResource {

        private ISearchableService<S> searchService;
        private S searchCriteria;

        public AllItems(String[] visibleColumns, String[] headerNames, ISearchableService searchService, S searchCriteria) {
            super(visibleColumns, headerNames);
            this.searchCriteria = searchCriteria;
            this.searchService = searchService;
        }

        @Override
        public InputStream getStream() {

            final int totalItems = searchService.getTotalCount(searchCriteria);

            final PipedInputStream inStream = new PipedInputStream();
            final PipedOutputStream outStream;

            try {
                outStream = new PipedOutputStream(inStream);
            } catch (IOException ex) {
                log.error("Can not create outstream file", ex);
                return null;
            }

            new Thread(new Runnable() {
                /**
                 * @see java.lang.Runnable#run()
                 */
                @Override
                public void run() {
                    OutputStreamWriter writer = null;
                    try {
                        writer = new OutputStreamWriter(outStream);

                        //print header
                        writer.write("###############\n\r");

                        for (int i = 0; i < headerNames.length; i++) {
                            String headerName = headerNames[i];
                            writer.write(headerName);
                            if (i < headerNames.length - 1) {
                                writer.write(",");
                            }
                        }
                        writer.write("\r\n");
                        writer.write("###############\n\r");

                        int numPage = totalItems / SearchRequest.DEFAULT_NUMBER_SEARCH_ITEMS + 1;

                        for (int i = 0; i < numPage; i++) {

                            List data = searchService.findPagableListByCriteria(new SearchRequest<S>(searchCriteria, i, SearchRequest.DEFAULT_NUMBER_SEARCH_ITEMS));

                            for (int j = 0; j < data.size(); j++) {
                                Object rowObj = data.get(j);

                                for (int k = 0; k < visibleColumns.length; k++) {
                                    String visColumn = visibleColumns[k];
                                    Object value = PropertyUtils.getProperty(rowObj, visColumn);
                                    if (value == null) {
                                        writer.write("");
                                    } else {
                                        writer.write(value.toString());
                                    }
                                    if (k < visibleColumns.length - 1) {
                                        writer.write(",");
                                    }

                                }
                                writer.write("\r\n");
                            }

                        }

                    } catch (Exception e) {
                        log.error("Failed to send GZIP XML data to the stream", e);
                    } finally {
                        if (writer != null) {
                            try {
                                writer.close();
                            } catch (IOException ex) {
                                log.error("Error while try to close resource", ex);
                            }
                        }
                    }
                }
            }).start();

            return inStream;
        }
    }

    public static class ListData extends ExportStreamResource {

        private List data;

        public ListData(String[] visibleColumns, String[] headerNames, List data) {
            super(visibleColumns, headerNames);
            this.data = data;
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

            new Thread(new Runnable() {
                /**
                 * @see java.lang.Runnable#run()
                 */
                @Override
                public void run() {
                    OutputStreamWriter writer = null;
                    try {
                        writer = new OutputStreamWriter(outStream);

                        //print header
                        writer.write("###############\n\r");

                        for (int i = 0; i < headerNames.length; i++) {
                            String headerName = headerNames[i];
                            writer.write(headerName);
                            if (i < headerNames.length - 1) {
                                writer.write(",");
                            }
                        }
                        writer.write("\r\n");
                        writer.write("###############\n\r");

                        for (int j = 0; j < data.size(); j++) {
                            Object rowObj = data.get(j);

                            for (int k = 0; k < visibleColumns.length; k++) {
                                String visColumn = visibleColumns[k];
                                Object value = PropertyUtils.getProperty(rowObj, visColumn);
                                if (value == null) {
                                    writer.write("");
                                } else {
                                    writer.write(value.toString());
                                }

                                if (k < visibleColumns.length - 1) {
                                    writer.write(",");
                                }

                            }
                            writer.write("\r\n");
                        }

                    } catch (Exception e) {
                        log.error("Failed to send GZIP XML data to the stream", e);
                    } finally {
                        if (writer != null) {
                            try {
                                writer.close();
                            } catch (IOException ex) {
                                log.error("Error while try to close resource", ex);
                            }
                        }
                    }
                }
            }).start();

            return inStream;
        }
    }
}
