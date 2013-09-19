package com.esofthead.mycollab.module.file.resource;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.lang.reflect.Field;
import java.util.List;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.esofthead.mycollab.core.utils.ClassUtils;
import com.esofthead.mycollab.reporting.ColumnFieldComponentBuilder;
import com.esofthead.mycollab.reporting.GroupIteratorDataSource;
import com.esofthead.mycollab.reporting.ReportExportType;
import com.esofthead.mycollab.reporting.RpParameterBuilder;
import com.esofthead.mycollab.reporting.SimpleColumnComponentBuilderMap;
import com.esofthead.mycollab.reporting.TableViewFieldDecorator;
import com.esofthead.mycollab.reporting.Templates;

public class ExportTaskListStreamResource<SimpleTaskList, S extends SearchCriteria>
		extends ExportItemsStreamResource<SimpleTaskList> {
	private static final long serialVersionUID = 1L;

	private ISearchableService searchService;
	private S searchCriteria;
	private static Logger log = LoggerFactory
			.getLogger(ExportTaskListStreamResource.class);
	protected RpParameterBuilder parameters;

	public ExportTaskListStreamResource(String reportTitle,
			ReportExportType outputForm, ISearchableService searchService,
			S searchCriteria, RpParameterBuilder parameters) {
		super(reportTitle, outputForm);
		this.searchCriteria = searchCriteria;
		this.searchService = searchService;
		this.parameters = parameters;
	}

	@Override
	protected void initReport() throws Exception {
		SubreportBuilder subreport = cmp.subreport(new TaskListSubReportInit())
				.setDataSource(new TaskListSubReportFillDataExpression());
	}

	@Override
	protected void fillReport() throws Exception {
		reportBuilder.setDataSource(new GroupIteratorDataSource(searchService,
				searchCriteria));
	}

	private class TaskListSubReportInit extends
			AbstractSimpleExpression<JasperReportBuilder> {
		private static final long serialVersionUID = 1L;

		@Override
		public JasperReportBuilder evaluate(ReportParameters reportParameters) {
			int masterRowNumber = reportParameters.getReportRowNumber();
			String taskListName = reportParameters.getFieldValue("name");

			JasperReportBuilder report = report();
			report.setTemplate(Templates.reportTemplate).title(
					cmp.text(taskListName).setStyle(
							Templates.bold12CenteredStyle));

			Class<?> classType = com.esofthead.mycollab.module.project.domain.SimpleTaskList.class;
			log.debug("Init report for task list--------");
			// Add field of report
			Field[] clsFields = ClassUtils.getAllFields(classType);
			for (Field objField : clsFields) {
				DRIDataType<Object, ? extends Object> jrType = null;
				try {
					jrType = type.detectType(objField.getType().getName());
				} catch (DRException e) {
					throw new MyCollabException(e);
				}
				report.addField(objField.getName(), jrType);
			}
			List<TableViewFieldDecorator> fields = parameters.getFields();
			List<? extends ColumnFieldComponentBuilder> renderers = SimpleColumnComponentBuilderMap
					.getListFieldBuilder(classType);
			// build columns of report
			for (TableViewFieldDecorator field : fields) {

				log.debug("Inject renderer if any");
				if (renderers != null) {
					for (int i = renderers.size() - 1; i >= 0; i--) {
						ColumnFieldComponentBuilder columnInjectionRenderer = renderers
								.get(i);
						if (field.getField().equals(
								columnInjectionRenderer.getFieldName())) {
							// field.setFieldComponentBuilder(columnInjectionRenderer);
						}
					}
				}

				// Field fieldCls = ClassUtils.getField(classType,
				// field.getField());
				// DRIDataType<Object, ? extends Object> jrType = null;
				// try {
				// jrType = type.detectType(fieldCls.getType().getName());
				// } catch (DRException e) {
				// throw new MyCollabException(e);
				// }
				// TextColumnBuilder<? extends Object> columnBuilder =
				// col.column(
				// field.getDesc(), field.getField(), jrType).setWidth(
				// field.getDefaultWidth());
				//
				// ColumnFieldComponentBuilder columnRenderer = field
				// .getColumnRenderer();
				// if (columnRenderer != null) {
				// if (columnRenderer instanceof
				// HyperLinkColumnInjectionRenderer) {
				// columnBuilder
				// .setHyperLink(hyperLink(((HyperLinkColumnInjectionRenderer)
				// columnRenderer)
				// .getExpression()));
				// columnBuilder
				// .setStyle(((HyperLinkColumnInjectionRenderer) columnRenderer)
				// .getStyle());
				// } else if (columnRenderer instanceof
				// EmailColumnInjectionRenderer) {
				// columnBuilder
				// .setHyperLink(hyperLink(((EmailColumnInjectionRenderer)
				// columnRenderer)
				// .getExpression()));
				// columnBuilder
				// .setStyle(((EmailColumnInjectionRenderer) columnRenderer)
				// .getStyle());
				// } else {
				// throw new MyCollabException(
				// "Does not support column renderer "
				// + columnRenderer);
				// }
				// }
				// report.addColumn(columnBuilder);
			}

			log.debug("Accomplish init report for task list ---------");
			return report;
		}
	}

	private class TaskListSubReportFillDataExpression extends
			AbstractSimpleExpression<JRDataSource> {
		private static final long serialVersionUID = 1L;

		@Override
		public JRDataSource evaluate(ReportParameters reportParameters) {
			int masterRowNumber = reportParameters.getReportRowNumber();
			String[] columns = new String[masterRowNumber];
			for (int i = 1; i <= masterRowNumber; i++) {
				columns[i - 1] = "column" + i;
			}
			DRDataSource dataSource = new DRDataSource(columns);

			for (int i = 1; i <= masterRowNumber; i++) {
				Object[] values = new Object[masterRowNumber];
				for (int j = 1; j <= masterRowNumber; j++) {
					values[j - 1] = "row" + i + "_column" + j;
				}
				dataSource.add(values);
			}

			return dataSource;
		}
	}
}
