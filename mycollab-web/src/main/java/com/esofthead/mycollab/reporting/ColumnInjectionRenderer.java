package com.esofthead.mycollab.reporting;

import net.sf.dynamicreports.report.definition.expression.DRIExpression;

public interface ColumnInjectionRenderer<T> {
	String getFieldName();

	DRIExpression<T> getExpression();
}
