package com.esofthead.mycollab.module.project;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.sbt;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.util.Date;

import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.module.file.resource.ExportItemsStreamResource;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.reporting.GroupIteratorDataSource;
import com.esofthead.mycollab.reporting.ReportExportType;

@SuppressWarnings("serial")
public class ExportTimeLoggingStreamResource extends
		ExportItemsStreamResource<SimpleItemTimeLogging> {

	private static Logger log = LoggerFactory
			.getLogger(ExportTimeLoggingStreamResource.class);

	private ItemTimeLoggingService searchService;
	private ItemTimeLoggingSearchCriteria searchCriteria;

	public ExportTimeLoggingStreamResource(String title,
			ReportExportType outputForm, ItemTimeLoggingService searchService,
			ItemTimeLoggingSearchCriteria searchCriteria) {
		super(title, outputForm);

		this.searchService = searchService;
		this.searchCriteria = searchCriteria;
	}

	@Override
	protected void initReport() throws Exception {
		TextColumnBuilder<String> summaryColumn = col.column("Summary",
				"summary", type.stringType()).setWidth(400);

		TextColumnBuilder<String> logUserColumn = col.column("Logged User",
				"logUserFullName", type.stringType());

		TextColumnBuilder<Double> logValueColumn = col.column("Hours",
				"logvalue", type.doubleType()).setWidth(150);

		TextColumnBuilder<Date> createdTimeColumn = col.column("Created Time",
				"createdtime", type.dateType()).setWidth(100);

		reportBuilder
				.columns(summaryColumn, logUserColumn, logValueColumn,
						createdTimeColumn).groupBy(logUserColumn)
				.subtotalsAtSummary(sbt.sum(logValueColumn));

	}

	@Override
	protected void fillReport() throws Exception {
		reportBuilder.setDataSource(new GroupIteratorDataSource(searchService,
				searchCriteria));

	}

}
