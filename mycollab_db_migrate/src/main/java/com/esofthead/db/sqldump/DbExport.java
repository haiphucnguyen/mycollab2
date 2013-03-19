package com.esofthead.db.sqldump;

import java.io.StringWriter;
import java.io.Writer;

import com.esofthead.util.sqldump.data.Schema;

public class DbExport {
	public static void exportDb(DbConfiguration configuration, Writer writer)
			throws Exception {
		Schema schema = Schema.loadSchema(configuration);
		String script = schema.dumpSchema();
		writer.append(script.subSequence(0, script.length()));
	}

	public static void main(String[] args) throws Exception {
		StringWriter writer = new StringWriter();
		DbExport.exportDb(DbConfiguration.loadDefault(), writer);
		System.out.println(writer.toString());
	}
}
