package com.esofthead.mycollab.reporting;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.exception.DRException;

public class ReportingLoader {
	public static void main(String[] args) throws DRException {
		JasperReportBuilder report = DynamicReports.report();
		report.columns(
				Columns.column("Customer Id", "id", DataTypes.integerType()),
				Columns.column("First Name", "first_name",
						DataTypes.stringType()),
				Columns.column("Last Name", "last_name", DataTypes.stringType()),
				Columns.column("Date", "date", DataTypes.dateType())).title(
				Components.text("Hello world"));
		report.show();
	}
}
