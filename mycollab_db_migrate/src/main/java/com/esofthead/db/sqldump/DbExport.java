package com.esofthead.db.sqldump;

import java.io.StringWriter;
import java.io.Writer;

import com.esofthead.util.sqldump.data.Schema;

public class DbExport {
	public static void exportDb(DbConfiguration configuration, Writer writer)
			throws Exception {
		Schema schema = Schema.loadSchema(configuration);
		schema.dumpSchema(writer, true);
	}

	public static void main(String[] args) throws Exception {
		StringWriter writer = new StringWriter();
		DbExport.exportDb(DbConfiguration.loadDefault(), writer);
		System.out.println(writer.toString());
//		String content = writer.toString();
//		
//		System.out.println(processingStringValue(content));
	}
	
	public static final String processingStringValue(String data) {
		String result = data.replace("\\", "\\\\");
		result = result.replace("\0", "\\0");
		result = result.replace("'", "\\'");
		result = result.replace("\"", "\\\"");
		result = result.replace("\b", "\\b");
		result = result.replace("\n", "\\n");
		result = result.replace("\r", "\\r");
		result = result.replace("\t", "\\t");
		return result;
	}
}
