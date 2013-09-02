package com.esofthead.mycollab.module.file.resource;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.lang.reflect.Field;
import java.util.List;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.MyCollabException;
import com.vaadin.terminal.StreamResource;

public abstract class ExportPdfStreamResource implements
		StreamResource.StreamSource {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(ExportPdfStreamResource.class);

	protected String[] visibleColumns;
	protected String[] headerNames;

	public ExportPdfStreamResource(String[] visibleColumns, String[] headerNames) {
		this.visibleColumns = visibleColumns;
		this.headerNames = headerNames;
	}

	public static class ListData<T> extends ExportPdfStreamResource {

		private Class<T> classType;
		private List<T> data;

		public ListData(String[] visibleColumns, String[] headerNames,
				List<T> data, Class<T> classType) {
			super(visibleColumns, headerNames);
			this.data = data;
		}

		@Override
		public InputStream getStream() {
			try {
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
						try {
							JasperReportBuilder reportBuilder = report();

							// build columns of report
							for (int i = 0; i < visibleColumns.length; i++) {
								String visibleColumn = visibleColumns[i];

								Field field = classType.getField(visibleColumn);
								reportBuilder.addColumn(col.column(
										visibleColumn, headerNames[i],
										type.stringType()));
							}
							reportBuilder.toPdf(outStream);
						} catch (Exception e) {
							log.error("Error while exporting pdf", e);
						}
					}
				}).start();

				return inStream;
			} catch (Exception e) {
				throw new MyCollabException(e);
			}
		}
	}
}
