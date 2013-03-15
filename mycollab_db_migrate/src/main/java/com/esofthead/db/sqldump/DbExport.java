package com.esofthead.db.sqldump;

import java.io.StringWriter;
import java.io.Writer;

import com.esofthead.db.sqldump.data.Schema;

public class DbExport {
	public static void exportDb(DbConfiguration configuration, Writer writer)
			throws Exception {
		Schema.loadSchema(configuration).dumpSchema(writer);
	}

	public static void main(String[] args) throws Exception {
		StringWriter writer = new StringWriter();
		DbExport.exportDb(DbConfiguration.loadDefault(), writer);
		System.out.println(writer.toString());
	}
}
