package com.esofthead.mycollab.reporting;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.common.domain.SimpleComment;
import com.esofthead.mycollab.common.domain.criteria.AuditLogSearchCriteria;
import com.esofthead.mycollab.common.domain.criteria.CommentSearchCriteria;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.common.service.AuditLogService;
import com.esofthead.mycollab.common.service.CommentService;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.SimpleLogging;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.form.view.builder.type.AbstractDynaField;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.utils.FieldGroupFormatter;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.registry.AuditLogRegistry;
import com.google.common.collect.Ordering;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.HyperLinkBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.exception.DRException;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

/**
 * @author MyCollab Ltd
 * @since 5.2.11
 */
public class FormReportTemplateExecutor<B> extends ReportTemplateExecutor {
    private static final int FORM_CAPTION = 100;
    private static final Logger LOG = LoggerFactory.getLogger(FormReportTemplateExecutor.class);
    private static Ordering dateComparator = new Ordering() {
        @Override
        public int compare(Object o1, Object o2) {
            try {
                Date createTime1 = (Date) PropertyUtils.getProperty(o1, "createdtime");
                Date createTime2 = (Date) PropertyUtils.getProperty(o2, "createdtime");
                return createTime1.compareTo(createTime2);
            } catch (Exception e) {
                return 0;
            }
        }
    };
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
        FormReportLayout formReportLayout = (FormReportLayout) parameters.get("layout");
        FieldGroupFormatter fieldGroupFormatter = AuditLogRegistry.getFieldGroupFormatter(formReportLayout.getModuleName());
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
        Integer typeId;
        try {
            typeId = (Integer) PropertyUtils.getProperty(bean, "id");
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOG.error("Error", e);
            return;
        }

        FormReportLayout formReportLayout = (FormReportLayout) parameters.get("layout");
        FieldGroupFormatter fieldGroupFormatter = AuditLogRegistry.getFieldGroupFormatter(formReportLayout.getModuleName());

        CommentService commentService = ApplicationContextUtil.getSpringBean(CommentService.class);
        final CommentSearchCriteria commentCriteria = new CommentSearchCriteria();
        commentCriteria.setType(StringSearchField.and(formReportLayout.getModuleName()));
        commentCriteria.setTypeId(StringSearchField.and(typeId + ""));
        final int commentCount = commentService.getTotalCount(commentCriteria);

        AuditLogService auditLogService = ApplicationContextUtil.getSpringBean(AuditLogService.class);
        final AuditLogSearchCriteria logCriteria = new AuditLogSearchCriteria();
        logCriteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
        logCriteria.setModule(StringSearchField.and(ModuleNameConstants.PRJ));
        logCriteria.setType(StringSearchField.and(formReportLayout.getModuleName()));
        logCriteria.setTypeId(StringSearchField.and(typeId + ""));
        final int logCount = auditLogService.getTotalCount(logCriteria);
        int totalNums = commentCount + logCount;
        HorizontalListBuilder historyHeader = cmp.horizontalList().add(cmp.text("History (" + totalNums + ")"));
        reportBuilder.title(historyHeader);

        List<SimpleComment> comments = commentService.findPagableListByCriteria(new SearchRequest<>(commentCriteria, 0, Integer.MAX_VALUE));
        List<SimpleAuditLog> auditLogs = auditLogService.findPagableListByCriteria(new SearchRequest<>(logCriteria, 0, Integer.MAX_VALUE));
        List activities = new ArrayList(commentCount + logCount);
        activities.addAll(comments);
        activities.addAll(auditLogs);
        Collections.sort(activities, dateComparator.reverse());
        for (Object activity : activities) {
            if (activity instanceof SimpleComment) {
                reportBuilder.title(buildCommentBlock((SimpleComment) activity));
                reportBuilder.title(cmp.verticalGap(10));
            } else if (activity instanceof SimpleAuditLog) {
                reportBuilder.title(buildAuditBlock((SimpleAuditLog) activity));
                reportBuilder.title(cmp.verticalGap(10));
            } else {
                SimpleLogging.error("Do not support activity " + activity);
            }
        }
    }

    private ComponentBuilder buildCommentBlock(SimpleComment comment) {
        TextFieldBuilder<String> authorField = cmp.text(StringUtils.trimHtmlTags(AppContext.getMessage(GenericI18Enum.EXT_ADDED_COMMENT, comment.getOwnerFullName(),
                AppContext.formatPrettyTime(comment.getCreatedtime())), Integer.MAX_VALUE));
        HorizontalListBuilder infoHeader = cmp.horizontalFlowList().add(authorField);
        return cmp.verticalList(infoHeader, cmp.text(StringUtils.formatRichText(comment.getComment())))
                .setStyle(reportTemplate.getBorderStyle());
    }

    private ComponentBuilder buildAuditBlock(SimpleAuditLog auditLog) {
        return cmp.text("Audit");
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
                        cmp.text(label).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT),
                        cmp.text("https://www.mycollab.com").setStyle(reportTemplate.getItalicStyle()).setHyperLink(link)),
                cmp.horizontalGap(20),
                cmp.text(String.format("Generated at: %s",
                        DateTimeUtils.formatDate(new GregorianCalendar().getTime(), "yyyy-MM-dd'T'HH:mm:ss", timeZone))));

        return cmp.horizontalList().add(dynamicReportsComponent).newRow().add(cmp.line()).newRow().add(cmp.verticalGap(10));
    }
}
