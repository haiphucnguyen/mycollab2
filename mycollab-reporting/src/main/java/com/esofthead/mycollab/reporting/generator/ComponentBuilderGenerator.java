package com.esofthead.mycollab.reporting.generator;

import com.esofthead.mycollab.reporting.AbstractReportTemplate;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;

/**
 * @author MyCollab Ltd
 * @since 5.2.12
 */
public interface ComponentBuilderGenerator {
    ComponentBuilder getCompBuilder(AbstractReportTemplate reportTemplate);
}
