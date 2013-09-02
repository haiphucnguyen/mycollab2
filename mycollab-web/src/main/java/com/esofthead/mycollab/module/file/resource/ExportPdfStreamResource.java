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
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.MyCollabThread;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
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

	public static class AllItems<S extends SearchCriteria, T> extends
			ExportPdfStreamResource {

		private ISearchableService<S> searchService;
		private S searchCriteria;
		private Class<T> classType;

		public AllItems(String[] visibleColumns, String[] headerNames,
				ISearchableService searchService, S searchCriteria,
				Class<T> classType) {
			super(visibleColumns, headerNames);
			this.searchService = searchService;
			this.searchCriteria = searchCriteria;
			this.classType = classType;
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

							Field field = classType
									.getDeclaredField(visibleColumn);
							reportBuilder.addColumn(col.column(headerNames[i],
									visibleColumn, type.stringType()));
						}

						// DRDataSource ds = new DRDataSource("");
						reportBuilder
								.setDataSource(new LazyLoadingDataSource());
						reportBuilder.toPdf(outStream);
					} catch (Exception e) {
						log.error("Exception while generating report ", e);
						throw new MyCollabException(e);
					}
				}
			});
			threadExport.start();
			return inStream;
		}

		private class LazyLoadingDataSource implements JRDataSource {
			private static final int ITEMS_PER_PAGE = 20;
			private int currentIndex = 0;
			private int currentPage = 0;
			private List currentData;

			private int totalItems;

			public LazyLoadingDataSource() {
				totalItems = searchService.getTotalCount(searchCriteria);
				SearchRequest searchRequest = new SearchRequest(searchCriteria,
						currentPage, ITEMS_PER_PAGE);
				currentData = searchService
						.findPagableListByCriteria(searchRequest);
			}

			@Override
			public boolean next() throws JRException {
				boolean result = (currentIndex < totalItems);
				currentIndex = currentIndex + 1;

				if (currentIndex == (currentPage + 1) * ITEMS_PER_PAGE) {
					currentPage = currentPage + 1;
					SearchRequest searchRequest = new SearchRequest(
							searchCriteria, currentPage, ITEMS_PER_PAGE);
					currentData = searchService
							.findPagableListByCriteria(searchRequest);
				}
				return result;
			}

			@Override
			public Object getFieldValue(JRField jrField) throws JRException {
				// TODO Auto-generated method stub
				return "A";
			}

		}

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
