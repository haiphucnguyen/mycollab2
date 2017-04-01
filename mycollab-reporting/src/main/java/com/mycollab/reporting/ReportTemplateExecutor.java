package com.mycollab.reporting;

import com.mycollab.core.utils.DateTimeUtils;
import net.sf.dynamicreports.report.builder.HyperLinkBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.exception.DRException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.hyperLink;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
public abstract class ReportTemplateExecutor {
    protected Map<String, Object> parameters;
    protected ReportStyles reportStyles;
    protected String reportTitle;
    protected ReportExportType outputForm;
    protected Locale locale;
    protected TimeZone timeZone;

    public ReportTemplateExecutor(TimeZone timezone, Locale languageSupport, String reportTitle,
                                  ReportExportType outputForm) {
        this.locale = languageSupport;
        this.timeZone = timezone;
        this.reportStyles = ReportStyles.instance();
        this.reportTitle = reportTitle;
        this.outputForm = outputForm;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    abstract public void initReport() throws Exception;

    abstract public void fillReport() throws DRException;

    abstract public void outputReport(OutputStream outputStream) throws IOException, DRException;

    protected ComponentBuilder<?, ?> defaultTitleComponent() {
        HyperLinkBuilder link = hyperLink("https://www.mycollab.com");
        ComponentBuilder<?, ?> dynamicReportsComponent = cmp.horizontalList(
                cmp.image(ReportTemplateExecutor.class.getClassLoader().getResourceAsStream("images/logo.png"))
                        .setFixedDimension(150, 28), cmp.horizontalGap(20),
                cmp.verticalList(
                        cmp.text("https://www.mycollab.com").setStyle(reportStyles.getItalicStyle()).setHyperLink(link)
                                .setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT), cmp.text(String.format("Generated at: %s",
                                DateTimeUtils.formatDate(new GregorianCalendar().getTime(), "yyyy-MM-dd'T'HH:mm:ss",
                                        Locale.US, timeZone)))
                                .setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT))
        );

        return cmp.horizontalList().add(dynamicReportsComponent).newRow().add(reportStyles.line()).newRow().add(cmp.verticalGap(10));
    }
}
