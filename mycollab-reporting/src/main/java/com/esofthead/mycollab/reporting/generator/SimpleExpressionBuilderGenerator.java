package com.esofthead.mycollab.reporting.generator;

import com.esofthead.mycollab.reporting.AbstractReportTemplate;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;

/**
 * @author MyCollab Ltd
 * @since 5.2.12
 */
public class SimpleExpressionBuilderGenerator implements ComponentBuilderGenerator {
    private AbstractSimpleExpression expression;

    public SimpleExpressionBuilderGenerator(AbstractSimpleExpression expression) {
        this.expression = expression;
    }

    @Override
    public ComponentBuilder getCompBuilder(AbstractReportTemplate reportTemplate) {
        return cmp.text(expression).setStyle(reportTemplate.getRootStyle());
    }
}
