package com.esofthead.mycollab.module.file.resource;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.esofthead.mycollab.core.utils.ClassUtils;
import com.esofthead.mycollab.reporting.ReportExportType;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;

public abstract class SimpleGridExportItemsStreamResource<T> extends
		ExportItemsStreamResource<T> {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(SimpleGridExportItemsStreamResource.class);

	protected Class<T> classType;
	protected List<TableViewField> fields;

	public SimpleGridExportItemsStreamResource(String reportTitle,
			List<TableViewField> fields, ReportExportType outputForm,
			Class<T> classType) {
		super(reportTitle, outputForm);
		this.fields = fields;
		this.classType = classType;
	}

	@Override
	protected void initReport() throws DRException {
		// build columns of report
		for (TableViewField field : fields) {

			Field fieldCls = ClassUtils.getField(classType, field.getField());
			DRIDataType<Object, ? extends Object> jrType = type
					.detectType(fieldCls.getType().getName());
			reportBuilder
					.addColumn(col.column(field.getDesc(), field.getField(),
							jrType).setWidth(field.getDefaultWidth()));
		}
	}

	public static class AllItems<S extends SearchCriteria, T> extends
			SimpleGridExportItemsStreamResource<T> {
		private static final long serialVersionUID = 1L;
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
		protected void fillReport() {
			reportBuilder.setDataSource(new LazyLoadingDataSource());
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
				SearchRequest<S> searchRequest = new SearchRequest<S>(searchCriteria,
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
						SearchRequest<S> searchRequest = new SearchRequest<S>(
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

	public static class ListData<T> extends
			SimpleGridExportItemsStreamResource<T> {
		private static final long serialVersionUID = 1L;
		private List<T> data;

		public ListData(String reportTitle, List<TableViewField> fields,
				ReportExportType outputForm, List<T> data, Class<T> classType) {
			super(reportTitle, fields, outputForm, classType);
			this.data = data;
		}

		@Override
		protected void fillReport() {
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
			reportBuilder.setDataSource(ds);
		}
	}
}
