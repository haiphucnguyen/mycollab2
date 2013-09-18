package com.esofthead.mycollab.reporting;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.hyperLink;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;

public class FieldReportComponentFactory {

	private static TableViewFieldDecorator field;

	public FieldReportComponentFactory(TableViewFieldDecorator field) {
		this.field = field;
	}

	public static ComponentBuilder getComponent() {
		ColumnInjectionRenderer render = field.getColumnRenderer();

		HorizontalListBuilder itemComponent = cmp.horizontalList();
		itemComponent.setFixedHeight(24);

		TextFieldBuilder textField = cmp.text(field.getField());

		if (render instanceof HyperLinkColumnInjectionRenderer) {
			textField
					.setHyperLink(hyperLink(((HyperLinkColumnInjectionRenderer) render)
							.getExpression()));

			itemComponent.add(textField);
		} else if (render instanceof EmailColumnInjectionRenderer) {
			textField
					.setHyperLink(hyperLink(((EmailColumnInjectionRenderer) render)
							.getExpression()));

			itemComponent.add(textField);
		} else if (render instanceof BugCustomLinkRenderer) {
		} else {
			itemComponent.addProperty(field.getDesc(), field.getField());
		}

		return itemComponent;
	}
}
