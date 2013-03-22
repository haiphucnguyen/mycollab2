package com.esofthead.db.sqldump;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.zip.GZIPOutputStream;

import com.esofthead.util.sqldump.data.Schema;

public class DbExport {

	public static void exportDb(DbConfiguration configuration, Writer writer)
			throws Exception {
		Schema schema = Schema.loadSchema(configuration);
		schema.dumpSchema(writer, true);
	}

	public static void backupDB(DbConfiguration configuration,
			OutputStream out, boolean isZipped) throws Exception {
		
		DbConfiguration config = new DbConfiguration();
		config.setPassword(configuration.getPassword());
		config.setUrl(configuration.getUrl());
		config.setUserName(configuration.getUserName());
		config.setMySqlModel(null != config.getUrl() && config.getUrl().toLowerCase().startsWith("jdbc:mysql"));
		
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

		File outFile = File.createTempFile(String.valueOf(System.currentTimeMillis()), ".sql");
		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(outFile));
		exportDb(DbConfiguration.loadDefault(), writer);
		writer.flush();
		writer.close();
		/*
		 * Code migrate h2 database
		 */
		FileInputStream fin = new FileInputStream(outFile);
		ByteArrayOutputStream sout = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int byteRead;
		while ((byteRead = fin.read(buffer)) != -1) {
			sout.write(buffer, 0, byteRead);
		}
		fin.close();
		sout.flush();
		sout.close();

		executeNonQuery(new String(sout.toByteArray()));
	}
	
	public static void executeNonQuery(String query) throws Exception {
		Class.forName("org.h2.Driver");
		Connection con = (Connection) DriverManager
				.getConnection("jdbc:h2:~/mycollab_extdb;MODE=MySQL");
		Statement stmt = (Statement) con.createStatement();
		stmt.execute(query);
		con.close();
	}
}
