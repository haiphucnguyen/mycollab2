package com.esofthead.mycollab.module.file.resource;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.export;
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
import net.sf.dynamicreports.jasper.builder.export.JasperCsvExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperXlsxExporterBuilder;
import net.sf.dynamicreports.jasper.constant.JasperProperty;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
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
import com.esofthead.mycollab.reporting.ReportExportType;
import com.esofthead.mycollab.reporting.Templates;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.vaadin.terminal.StreamResource;

public abstract class ExportItemsStreamResource<T> implements
		StreamResource.StreamSource {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(ExportItemsStreamResource.class);

	protected String reportTitle;
	protected List<TableViewField> fields;
	protected ReportExportType outputForm;
	protected Class<T> classType;

	public ExportItemsStreamResource(String reportTitle,
			List<TableViewField> fields, ReportExportType outputForm,
			Class<T> classType) {
		this.reportTitle = reportTitle;
		this.fields = fields;
		this.outputForm = outputForm;
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
					JasperReportBuilder reportBuilder = createReport(
							outputForm, reportTitle);

					// build columns of report
					for (TableViewField field : fields) {

						Field fieldCls = ClassUtils.getField(classType,
								field.getField());
						DRIDataType<Object, ? extends Object> jrType = type
								.detectType(fieldCls.getType().getName());
						reportBuilder.addColumn(col.column(field.getDesc(),
								field.getField(), jrType).setWidth(
								field.getDefaultWidth()));
					}

					reportBuilder.setDataSource(createDataSource());

					if (outputForm == ReportExportType.PDF) {
						reportBuilder.toPdf(outStream);
					} else if (outputForm == ReportExportType.CSV) {
						JasperCsvExporterBuilder csvExporter = export
								.csvExporter(outStream);
						reportBuilder.toCsv(csvExporter);
					} else if (outputForm == ReportExportType.EXCEL) {
						JasperXlsxExporterBuilder xlsExporter = export
								.xlsxExporter(outStream)
								.setDetectCellType(true)
								.setIgnorePageMargins(true)
								.setWhitePageBackground(false)
								.setRemoveEmptySpaceBetweenColumns(true);
						reportBuilder.toXlsx(xlsExporter);
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

	abstract protected JRDataSource createDataSource();

	private static JasperReportBuilder createReport(
			ReportExportType outputForm, String reportTitle) {
		JasperReportBuilder reportBuilder = report();
		if (outputForm == ReportExportType.PDF) {
			reportBuilder
					.title(Templates.createTitleComponent(reportTitle))
					.noData(Templates.createTitleComponent(reportTitle),
							cmp.text("There is no data"))
					.setPageFormat(PageType.A4, PageOrientation.LANDSCAPE)
					.setColumnTitleStyle(Templates.columnTitleStyle)
					.highlightDetailEvenRows()
					.pageFooter(
							cmp.pageXofY()
									.setStyle(Templates.boldCenteredStyle));
		} else if (outputForm == ReportExportType.CSV) {
			reportBuilder.setIgnorePagination(true);
		} else if (outputForm == ReportExportType.EXCEL) {
			reportBuilder.setColumnTitleStyle(Templates.columnTitleStyle)
					.addProperty(JasperProperty.EXPORT_XLS_FREEZE_ROW, "2")
					.ignorePageWidth().ignorePagination();

		} else {
			throw new IllegalArgumentException("Do not support output type "
					+ outputForm);
		}

		return reportBuilder;
	}

	public static class AllItems<S extends SearchCriteria, T> extends
			ExportItemsStreamResource {

		private ISearchableService<S> searchService;
		private S searchCriteria;

		public AllItems(String reportTitle, List<TableViewField> fields,
				ReportExportType outputForm, ISearchableService searchService,
				S searchCriteria, Class<T> classType) {
			super(reportTitle, fields, outputForm, classType);
			this.searchService = searchService;
			this.searchCriteria = searchCriteria;
		}

		@Override
		protected JRDataSource createDataSource() {
			return new LazyLoadingDataSource();
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

	public static class ListData<T> extends ExportItemsStreamResource<T> {
		private List<T> data;

		public ListData(String reportTitle, List<TableViewField> fields,
				ReportExportType outputForm, List<T> data, Class<T> classType) {
			super(reportTitle, fields, outputForm, classType);
			this.data = data;
		}

		@Override
		protected JRDataSource createDataSource() {
			List<String> visibleColumns = new ArrayList<String>();
			for (TableViewField field : fields) {
				visibleColumns.add(field.getField());
			}

			DRDataSource ds = new DRDataSource(
					visibleColumns.toArray(new String[0]));
			try {
				for (Object item : data) {
					Object[] tempVals = new Object[visibleColumns.size()];
					for (int i = 0; i < tempVals.length; i++) {
						tempVals[i] = PropertyUtils.getProperty(item,
								visibleColumns.get(i));
					}
					ds.add(tempVals);
				}
			} catch (Exception e) {
				log.error("Error while generating data source", e);
			}
			return ds;
		}
	}
}
