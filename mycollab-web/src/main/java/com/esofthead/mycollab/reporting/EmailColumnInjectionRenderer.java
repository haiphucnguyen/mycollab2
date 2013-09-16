package com.esofthead.mycollab.reporting;

import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

public class EmailColumnInjectionRenderer implements
		ColumnInjectionRenderer<String> {
	private String fieldName;
	private FieldEmailExpression expression;

	public EmailColumnInjectionRenderer(String fieldName) {
		this.fieldName = fieldName;
		expression = new FieldEmailExpression(fieldName);
	}

	@Override
	public String getFieldName() {
		return fieldName;
	}

	@Override
	public DRIExpression<String> getExpression() {
		return expression;
	}

	private static class FieldEmailExpression extends
			AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;
		
		private String emailField;

		public FieldEmailExpression(String emailField) {
			this.emailField = emailField;
		}

		@Override
		public String evaluate(ReportParameters reportParameters) {
			String emailLinkVal = reportParameters.getValue(emailField);
			return "mailto:" + emailLinkVal;
		}

	}

}
