package com.esofthead.mycollab.module.file.resource;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.MyCollabThread;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.esofthead.mycollab.core.utils.ClassUtils;
import com.esofthead.mycollab.reporting.Templates;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.vaadin.terminal.StreamResource;

public abstract class ExportItemsStreamResource implements
		StreamResource.StreamSource {
	private static final long serialVersionUID = 1L;

	public static int CSV_OUTPUT = 1;

	public static int PDF_OUTPUT = 2;

	public static int EXCEL_OUTPUT = 3;

	private static Logger log = LoggerFactory
			.getLogger(ExportItemsStreamResource.class);

	protected List<TableViewField> fields;
	protected int outputForm;

	public ExportItemsStreamResource(List<TableViewField> fields, int outputForm) {
		this.fields = fields;
		this.outputForm = outputForm;
	}

	public static class AllItems<S extends SearchCriteria, T> extends
			ExportItemsStreamResource {

		private ISearchableService<S> searchService;
		private S searchCriteria;
		private Class<T> classType;

		public AllItems(List<TableViewField> fields, int outputForm,
				ISearchableService searchService, S searchCriteria,
				Class<T> classType) {
			super(fields, outputForm);
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
						JasperReportBuilder reportBuilder = report()
								.setColumnTitleStyle(Templates.columnTitleStyle);

						// build columns of report
						for (TableViewField field : fields) {

							Field fieldCls = ClassUtils.getField(classType,
									field.getField());
							DRIDataType<?, ?> jrType = type.detectType(fieldCls
									.getType().getName());
							reportBuilder.addColumn(col.column(field.getDesc(),
									field.getField(), jrType));
						}

						reportBuilder
								.setDataSource(new LazyLoadingDataSource());

						if (outputForm == PDF_OUTPUT) {
							reportBuilder.toPdf(outStream);
						} else if (outputForm == CSV_OUTPUT) {
							reportBuilder.toCsv(outStream);
						} else if (outputForm == EXCEL_OUTPUT) {
							reportBuilder.toXlsx(outStream);
						} else {
							throw new IllegalArgumentException(
									"Do not support output type " + outputForm);
						}

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
			private Object currentItem;

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
				if (result) {
					if (currentIndex == (currentPage + 1) * ITEMS_PER_PAGE) {
						currentPage = currentPage + 1;
						SearchRequest searchRequest = new SearchRequest(
								searchCriteria, currentPage, ITEMS_PER_PAGE);
						currentData = searchService
								.findPagableListByCriteria(searchRequest);
						log.debug("Current data {}", currentData.size());
					}

					log.debug("Current index {} - {} - {} - {}", new Object[] {
							currentIndex, currentPage, currentData.size(),
							totalItems });
					if (currentIndex % ITEMS_PER_PAGE < currentData.size()) {
						currentItem = currentData.get(currentIndex
								% ITEMS_PER_PAGE);
					}

					currentIndex = currentIndex + 1;
				}

				return result;
			}

			@Override
			public Object getFieldValue(JRField jrField) throws JRException {
				try {
					String fieldName = jrField.getName();
					return PropertyUtils.getProperty(currentItem, fieldName);
				} catch (Exception e) {
					throw new JRException(e);
				}
			}

		}

	}

	public static class ListData<T> extends ExportItemsStreamResource {

		private Class<T> classType;
		private List<T> data;

		public ListData(List<TableViewField> fields, int outputForm,
				List<T> data, Class<T> classType) {
			super(fields, outputForm);
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

							List<String> visibleColumns = new ArrayList<String>();
							// build columns of report
							for (TableViewField field : fields) {

								Field fieldCls = ClassUtils.getField(classType,
										field.getField());
								DRIDataType<?, ?> jrType = type
										.detectType(fieldCls.getType()
												.getName());
								reportBuilder.addColumn(col.column(
										field.getDesc(), field.getField(),
										jrType));
								visibleColumns.add(field.getField());
							}

							DRDataSource ds = new DRDataSource(visibleColumns
									.toArray(new String[0]));
							ds.add(data.toArray());
							reportBuilder.setDataSource(ds);
							if (outputForm == PDF_OUTPUT) {
								reportBuilder.toPdf(outStream);
							} else if (outputForm == CSV_OUTPUT) {
								reportBuilder.toCsv(outStream);
							} else if (outputForm == EXCEL_OUTPUT) {
								reportBuilder.toXlsx(outStream);
							} else {
								throw new IllegalArgumentException(
										"Do not support output type "
												+ outputForm);
							}
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
