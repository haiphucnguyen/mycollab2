package com.esofthead.mycollab.reporting;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.form.view.builder.type.AbstractDynaField;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.utils.FieldGroupFormatter;
import com.esofthead.mycollab.vaadin.AppContext;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.HyperLinkBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.exception.DRException;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

/**
 * @author MyCollab Ltd
 * @since 5.2.11
 */
public class FormReportTemplateExecutor<B> extends ReportTemplateExecutor {
    private static final int FORM_CAPTION = 100;
    private static final Logger LOG = LoggerFactory.getLogger(FormReportTemplateExecutor.class);
    protected JasperReportBuilder reportBuilder;

    public FormReportTemplateExecutor(String reportTitle) {
        super(AppContext.getUserTimezone(), AppContext.getUserLocale(), reportTitle, ReportExportType.PDF);
    }

    @Override
    protected void initReport() throws Exception {
        reportBuilder = report();
        reportBuilder.setParameters(parameters);
        reportBuilder
                .title(createTitleComponent(reportTitle))
                .setPageFormat(PageType.A4, PageOrientation.PORTRAIT)
                .pageFooter(cmp.pageXofY().setStyle(reportTemplate.getBoldCenteredStyle()))
                .setLocale(locale);
    }

    @Override
    protected void fillReport() throws DRException {
        printForm();
        printActivities();
    }

    private void printForm() {
        Map<String, Object> parameters = this.getParameters();
        B bean = (B) parameters.get("bean");
        FieldGroupFormatter fieldGroupFormatter = (FieldGroupFormatter) parameters.get("formatter");
        FormReportLayout formReportLayout = (FormReportLayout) parameters.get("layout");
        DynaForm dynaForm = formReportLayout.getDynaForm();
        int sectionCount = dynaForm.getSectionCount();
        for (int i = 0; i < sectionCount; i++) {
            DynaSection section = dynaForm.getSection(i);
            if (section.isDeletedSection()) {
                continue;
            }

            if (StringUtils.isNotBlank(section.getHeader())) {

            }

            if (section.isDeletedSection() || section.getFieldCount() == 0) {
                continue;
            }

            if (section.getLayoutType() == DynaSection.LayoutType.ONE_COLUMN) {

            } else if (section.getLayoutType() == DynaSection.LayoutType.TWO_COLUMN) {
                int columnIndex = 0;
                HorizontalListBuilder tmpRow = null;
                for (int j = 0; j < section.getFieldCount(); j++) {
                    AbstractDynaField dynaField = section.getField(j);
                    if (!formReportLayout.getExcludeFields().contains(dynaField.getFieldName())) {
                        String value = "";
                        try {
                            Object tmpVal = PropertyUtils.getProperty(bean, dynaField.getFieldName());
                            if (tmpVal != null) {
                                if (tmpVal instanceof Date) {
                                    value = DateTimeUtils.formatDateToW3C((Date) tmpVal);
                                } else {
                                    value = String.valueOf(tmpVal);
                                }
                            }
                        } catch (Exception e) {
                            LOG.error("Error while getting property {}", dynaField.getFieldName(), e);
                        }

                        if (dynaField.isColSpan()) {
                            HorizontalListBuilder newRow = cmp.horizontalList().add(cmp.text(dynaField.getDisplayName
                                    ()).setFixedWidth(FORM_CAPTION), cmp.text(fieldGroupFormatter.getFieldDisplayHandler
                                    (dynaField.getFieldName()).getFormat().toString(value, false, "")));
                            reportBuilder.title(newRow);
                            columnIndex = 0;
                        } else {
                            if (columnIndex == 0) {
                                tmpRow = cmp.horizontalList().add(cmp.text(dynaField.getDisplayName()).setFixedWidth(FORM_CAPTION),
                                        cmp.text(fieldGroupFormatter.getFieldDisplayHandler(dynaField.getFieldName())
                                                .getFormat().toString(value, false, "")));
                                reportBuilder.title(tmpRow);
                            } else {
                                tmpRow.add(cmp.text(dynaField.getDisplayName()).setFixedWidth(FORM_CAPTION),
                                        cmp.text(fieldGroupFormatter.getFieldDisplayHandler(dynaField.getFieldName())
                                                .getFormat().toString(value, false, "")));
                            }

                            columnIndex++;
                            if (columnIndex == 2) {
                                columnIndex = 0;
                            }
                        }
                    }
                }
            } else {
                throw new MyCollabException("Does not support attachForm layout except 1 or 2 columns");
            }
        }
    }

    private void printActivities() {
        Map<String, Object> parameters = this.getParameters();
        B bean = (B) parameters.get("bean");
        FieldGroupFormatter fieldGroupFormatter = (FieldGroupFormatter) parameters.get("formatter");
        FormReportLayout formReportLayout = (FormReportLayout) parameters.get("layout");
        HorizontalListBuilder historyHeader = cmp.horizontalList().add(cmp.text("History"));
        reportBuilder.title(historyHeader);
    }

    @Override
    protected void outputReport(OutputStream outputStream) throws IOException, DRException {
        reportBuilder.toPdf(outputStream);
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
