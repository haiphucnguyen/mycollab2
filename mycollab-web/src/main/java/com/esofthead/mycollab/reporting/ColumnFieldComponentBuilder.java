package com.esofthead.mycollab.reporting;

import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

public interface ColumnFieldComponentBuilder<T> {
	String getFieldName();

	DRIExpression<T> getDriExpression();

	ComponentBuilder getComponentBuilder();
}
