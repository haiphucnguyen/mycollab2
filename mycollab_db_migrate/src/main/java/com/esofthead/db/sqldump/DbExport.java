package com.esofthead.db.sqldump;

import java.io.StringWriter;
import java.io.Writer;

public class DbExport {
	public static void exportDb(DbConfiguration configuration, Writer writer) {

	}

	public static void main(String[] args) {
		StringWriter writer = new StringWriter();
		DbExport.exportDb(DbConfiguration.loadDefault(), writer);
		System.out.println(writer.toString());
	}
}
