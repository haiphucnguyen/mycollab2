package com.esofthead.mycollab.reporting;

import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

public class BugCustomLinkRenderer implements
		ColumnInjectionRenderer<HorizontalListBuilder> {

	private String fieldName;
	private DRIExpression<HorizontalListBuilder> expression;

	public BugCustomLinkRenderer(String fieldName,
			DRIExpression<HorizontalListBuilder> expression) {
		this.fieldName = fieldName;
		this.expression = expression;
	}

	@Override
	public String getFieldName() {
		return fieldName;
	}

	@Override
	public DRIExpression<HorizontalListBuilder> getExpression() {
		return expression;
	}
}
