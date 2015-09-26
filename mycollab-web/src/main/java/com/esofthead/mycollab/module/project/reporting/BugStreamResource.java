package com.esofthead.mycollab.module.project.reporting;

import com.esofthead.mycollab.reporting.ExportItemsStreamResource;
import com.esofthead.mycollab.reporting.ReportExportType;
import com.esofthead.mycollab.vaadin.AppContext;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
public class BugStreamResource extends ExportItemsStreamResource {


    public BugStreamResource(String reportTitle, ReportExportType reportExportType) {
        super(AppContext.getTimezone(), AppContext.getUserLocale(), reportTitle, reportExportType, null);
    }

    @Override
    protected void initReport() throws Exception {

    }

    @Override
    protected void fillReport() throws Exception {

    }
}
