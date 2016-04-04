package com.esofthead.mycollab.reporting;

import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.vaadin.AppContext;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.HyperLinkBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.exception.DRException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.GregorianCalendar;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

/**
 * @author MyCollab Ltd
 * @since 5.2.11
 */
public class FormReportTemplateExecutor<B> extends ReportTemplateExecutor {
    protected JasperReportBuilder reportBuilder;

    public FormReportTemplateExecutor(String reportTitle) {
        super(AppContext.getUserTimezone(), AppContext.getUserLocale(), reportTitle, ReportExportType.PDF);
    }

    @Override
    protected void initReport() throws Exception {
        reportBuilder = createReport();
    }

    @Override
    protected void fillReport() {

    }

    @Override
    protected void outputReport(OutputStream outputStream) throws IOException, DRException {
        reportBuilder.toPdf(outputStream);
    }

    private JasperReportBuilder createReport() {
        JasperReportBuilder reportBuilder = report();
        reportBuilder.setParameters(parameters);
        reportBuilder.title(createTitleComponent(reportTitle))
                .setPageFormat(PageType.A4, PageOrientation.PORTRAIT)
                .setColumnTitleStyle(reportTemplate.getColumnTitleStyle())
                .highlightDetailEvenRows().pageFooter(cmp.pageXofY().setStyle(reportTemplate.getBoldCenteredStyle()))
                .setLocale(locale);
        return reportBuilder;
    }

    private ComponentBuilder<?, ?> createTitleComponent(String label) {
        HyperLinkBuilder link = hyperLink("https://www.mycollab.com");
        ComponentBuilder<?, ?> dynamicReportsComponent = cmp.horizontalList(
                cmp.image(
                        ReportTemplateFactory.class.getClassLoader().getResourceAsStream("images/logo.png"))
                        .setFixedDimension(150, 28), cmp.horizontalGap(10), cmp.verticalList(
                        cmp.text(label).setStyle(reportTemplate.bold22CenteredStyle)
                                .setHorizontalTextAlignment(HorizontalTextAlignment.LEFT),
                        cmp.text("https://www.mycollab.com").setStyle(reportTemplate.italicStyle).setHyperLink(link)),
                cmp.horizontalGap(20),
                cmp.text(String.format("Generated at: %s",
                        DateTimeUtils.formatDate(new GregorianCalendar().getTime(), "yyyy-MM-dd'T'HH:mm:ss", timeZone))));

        return cmp.horizontalList().add(dynamicReportsComponent).newRow().add(cmp.line()).newRow().add(cmp.verticalGap(10));
    }
}
