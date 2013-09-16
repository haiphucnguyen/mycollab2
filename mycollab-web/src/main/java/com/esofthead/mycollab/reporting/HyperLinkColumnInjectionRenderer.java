package com.esofthead.mycollab.reporting;

import net.sf.dynamicreports.report.definition.expression.DRIExpression;

public class HyperLinkColumnInjectionRenderer implements
		ColumnInjectionRenderer {

	private String fieldName;

	private DRIExpression<String> expression;

	public HyperLinkColumnInjectionRenderer(String fieldName,
			DRIExpression<String> expression) {
		this.fieldName = fieldName;
		this.expression = expression;
	}

	public DRIExpression<String> getExpression() {
		return expression;
	}

	@Override
	public String getFieldName() {
		return fieldName;
	}

}
