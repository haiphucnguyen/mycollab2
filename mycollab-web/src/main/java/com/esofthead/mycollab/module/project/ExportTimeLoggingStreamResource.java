package com.esofthead.mycollab.module.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.module.file.resource.ExportItemsStreamResource;
import com.esofthead.mycollab.module.file.resource.FieldExportColumn;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.reporting.ReportExportType;

@SuppressWarnings("serial")
public class ExportTimeLoggingStreamResource extends
		ExportItemsStreamResource<SimpleItemTimeLogging> {

	private static Logger log = LoggerFactory
			.getLogger(ExportTimeLoggingStreamResource.class);

	public ExportTimeLoggingStreamResource(String title,
			ReportExportType outputForm, FieldExportColumn[] exportColumns,
			ItemTimeLoggingService searchService,
			ItemTimeLoggingSearchCriteria searchCriteria) {
		super(title, outputForm);
	}

	@Override
	protected void initReport() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void fillReport() throws Exception {
		// TODO Auto-generated method stub

	}

}
