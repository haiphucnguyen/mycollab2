package com.esofthead.mycollab.module.file.resource;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.lang.reflect.Field;
import java.util.List;

import net.sf.dynamicreports.report.builder.column.ComponentColumnBuilder;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.exception.DRException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.esofthead.mycollab.core.utils.ClassUtils;
import com.esofthead.mycollab.reporting.BeanDataSource;
import com.esofthead.mycollab.reporting.ColumnFieldComponentBuilder;
import com.esofthead.mycollab.reporting.GroupIteratorDataSource;
import com.esofthead.mycollab.reporting.ReportExportType;
import com.esofthead.mycollab.reporting.RpParameterBuilder;
import com.esofthead.mycollab.reporting.SimpleColumnComponentBuilderMap;
import com.esofthead.mycollab.reporting.TableViewFieldDecorator;

public abstract class SimpleGridExportItemsStreamResource<T> extends
		ExportItemsStreamResource<T> {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(SimpleGridExportItemsStreamResource.class);

	protected Class<T> classType;
	protected RpParameterBuilder parameters;

	public SimpleGridExportItemsStreamResource(String reportTitle,
			RpParameterBuilder paramters, ReportExportType outputForm,
			Class<T> classType) {
		super(reportTitle, outputForm);
		this.parameters = paramters;
		this.classType = classType;
	}

	@Override
	protected void initReport() throws DRException {
		log.debug("Init report");

		// Add field of report
		Field[] clsFields = ClassUtils.getAllFields(classType);
		for (Field objField : clsFields) {
			DRIDataType<Object, ? extends Object> jrType = type
					.detectType(objField.getType().getName());
			reportBuilder.addField(objField.getName(), jrType);
		}
		List<TableViewFieldDecorator> fields = parameters.getFields();

		List<? extends ColumnFieldComponentBuilder> lstFieldBuilder = SimpleColumnComponentBuilderMap
				.getListFieldBuilder(classType);
		// build columns of report
		for (TableViewFieldDecorator field : fields) {

			log.debug("Inject renderer if any");
			if (lstFieldBuilder != null) {
				for (int i = lstFieldBuilder.size() - 1; i >= 0; i--) {
					ColumnFieldComponentBuilder fieldBuilder = lstFieldBuilder
							.get(i);
					if (field.getField().equals(fieldBuilder.getFieldName())) {
						field.setFieldComponentExpression(fieldBuilder
								.getDriExpression());
						field.setComponentBuilder(fieldBuilder
								.getComponentBuilder());
					}
				}
			}
			ComponentColumnBuilder columnBuilder = col.componentColumn(
					field.getDesc(), field.getComponentBuilder()).setWidth(
					field.getDefaultWidth());

			reportBuilder.addColumn(columnBuilder);
		}

		log.debug("Accomplish init report");
	}

	public static class AllItems<S extends SearchCriteria, T> extends
			SimpleGridExportItemsStreamResource<T> {
		private static final long serialVersionUID = 1L;
		private ISearchableService<S> searchService;
		private S searchCriteria;

		public AllItems(String reportTitle, RpParameterBuilder parameters,
				ReportExportType outputForm, ISearchableService searchService,
				S searchCriteria, Class<T> classType) {
			super(reportTitle, parameters, outputForm, classType);
			this.searchService = searchService;
			this.searchCriteria = searchCriteria;
		}

		@Override
		protected void fillReport() {
			reportBuilder.setDataSource(new GroupIteratorDataSource(
					searchService, searchCriteria));
		}

	}

	public static class ListData<T> extends
			SimpleGridExportItemsStreamResource<T> {
		private static final long serialVersionUID = 1L;
		private List<T> data;

		public ListData(String reportTitle, RpParameterBuilder parameters,
				ReportExportType outputForm, List<T> data, Class<T> classType) {
			super(reportTitle, parameters, outputForm, classType);
			this.data = data;
		}

		@Override
		protected void fillReport() {
			BeanDataSource ds = new BeanDataSource(data);
			reportBuilder.setDataSource(ds);
		}
	}
}
