package com.esofthead.db.sqldump;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.zip.GZIPOutputStream;

import com.esofthead.db.sqldump.conf.DbConfiguration;
import com.esofthead.util.sqldump.data.Schema;

public class DbExport {

	public static void exportDb(DbConfiguration configuration, Writer writer)
			throws Exception {
		Schema schema = Schema.loadSchema(configuration);
		schema.dumpSchema(writer);
	}

	public static void backupDB(DbConfiguration config, OutputStream out,
			boolean isZipped) throws Exception {

		final OutputStreamWriter writer;
		if (isZipped) {
			writer = new OutputStreamWriter(new GZIPOutputStream(out));
		} else {
			writer = new OutputStreamWriter(out);
		}

		exportDb(config, writer);

		writer.flush();
		writer.close();
	}

	public static void main(String[] args) throws Exception {
		OutputStreamWriter writer = new OutputStreamWriter(
				new FileOutputStream("backup.sql"), "UTF-8");
		exportDb(DbConfiguration.loadDefault(), writer);
		writer.flush();
		writer.close();

		System.out.println(writer.toString());
	}
}