package com.mycollab.reporting;

import com.mycollab.vaadin.UserUIContext;

import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.exception.DRException;

import java.io.OutputStream;
import java.util.Map;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

/**
 * @author MyCollab Ltd
 * @since 5.4.7
 */
public class PdfReportBuilder extends AbstractReportBuilder {

    PdfReportBuilder(String reportTitle, RpFieldsBuilder fieldBuilder, Class classType, Map parameters) {
        super(reportTitle, fieldBuilder, classType, parameters);
    }

    @Override
    protected void initReport() {
        reportBuilder
                .setPageFormat(PageType.A3, PageOrientation.LANDSCAPE)
                .setHighlightDetailEvenRows(true)
                .setColumnStyle(stl.style(stl.style().setLeftPadding(4)))
                .setColumnTitleStyle(ReportStyles.instance().getColumnTitleStyle())
                .pageFooter(cmp.pageXofY())
                .setLocale(UserUIContext.getUserLocale());
    }

    @Override
    public void toStream(OutputStream outputStream) throws DRException {
        reportBuilder.toPdf(outputStream);
    }
}
