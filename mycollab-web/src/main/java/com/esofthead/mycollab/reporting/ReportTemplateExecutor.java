package com.esofthead.mycollab.reporting;

import java.io.OutputStream;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
public abstract class ReportTemplateExecutor {
    protected Map<String, Object> parameters;
    protected AbstractReportTemplate reportTemplate;
    protected String reportTitle;
    protected ReportExportType outputForm;
    protected Locale locale;
    protected TimeZone timeZone;

    public ReportTemplateExecutor(TimeZone timezone, Locale languageSupport, String reportTitle, ReportExportType outputForm) {
        this.locale = languageSupport;
        this.timeZone = timezone;
        this.reportTemplate = ReportTemplateFactory.getTemplate(languageSupport);
        this.reportTitle = reportTitle;
        this.outputForm = outputForm;
    }

    void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    abstract protected void initReport() throws Exception;

    abstract protected void fillReport();

    abstract protected void outputReport(OutputStream outputStream);
}
