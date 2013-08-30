package com.esofthead.mycollab.reporting;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;

public class ReportingLoader {
	public static void main(String[] args) throws DRException {
		DRDataSource ds = new DRDataSource("id", "name");
		ds.add("1", "Nguyen Hai");
		ds.add("2", "Duong Linh");

		JasperReportBuilder report = DynamicReports.report();
		report.columns(
				Columns.column("Customer Id", "id", DataTypes.stringType()),
				Columns.column("Name", "name", DataTypes.stringType()))
				.title(Components.text("Bug Report")).setDataSource(ds);

		report.show();
	}
}
