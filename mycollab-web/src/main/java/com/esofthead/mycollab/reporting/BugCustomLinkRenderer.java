package com.esofthead.mycollab.reporting;

import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

public class BugCustomLinkRenderer implements
		ColumnFieldComponentBuilder<HorizontalListBuilder> {

	private String fieldName;
	private DRIExpression expression;

	public BugCustomLinkRenderer(String fieldName, DRIExpression expression) {
		this.fieldName = fieldName;
		this.expression = expression;
	}

	@Override
	public String getFieldName() {
		return fieldName;
	}

	@Override
	public DRIExpression<HorizontalListBuilder> getDriExpression() {
		return expression;
	}

	@Override
	public ComponentBuilder getComponentBuilder() {
		// TODO Auto-generated method stub
		return null;
	}
}
