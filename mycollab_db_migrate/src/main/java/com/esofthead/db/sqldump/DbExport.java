package com.esofthead.db.sqldump;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.zip.GZIPOutputStream;

import com.esofthead.util.sqldump.data.Schema;

public class DbExport {

	public static void exportDb(DbConfiguration configuration, Writer writer)
			throws Exception {
		Schema schema = Schema.loadSchema(configuration);
		schema.dumpSchema(writer, true);
	}

	public static void exportDb(DbConfiguration configuration,
			OutputStream out, boolean isZipped) throws Exception {
		final OutputStreamWriter writer;
		if (isZipped) {
			writer = new OutputStreamWriter(new GZIPOutputStream(out));
		} else {
			writer = new OutputStreamWriter(out);
		}
		
		 exportDb(configuration, writer);
		
		 writer.flush();
		 writer.close();
	}
	
	public static void main(String[] args) throws Exception {

		
		
		/*
		 * Code migrate h2 database
		 */
//		File file = new File("D:/out/data");
//		FileInputStream fin = new FileInputStream(file);
//		ByteArrayOutputStream sout = new ByteArrayOutputStream();
//		byte[] buffer = new byte[4096];
//		int byteRead;
//		while ((byteRead = fin.read(buffer)) != -1) {
//			sout.write(buffer, 0, byteRead);
//		}
//		fin.close();
//		sout.flush();
//		sout.close();
//
//		executeNonQuery(new String(sout.toByteArray()));
	}

//	public static final String processingStringValue(String data) {
//		String result = data.replace("\\", "\\\\");
//		result = result.replace("\0", "\\0");
//		result = result.replace("'", "\\'");
//		result = result.replace("\"", "\\\"");
//		result = result.replace("\b", "\\b");
//		result = result.replace("\n", "\\n");
//		result = result.replace("\r", "\\r");
//		result = result.replace("\t", "\\t");
//		return result;
//	}
//
//	public static void executeNonQuery(String query) throws Exception {
//		Class.forName("org.h2.Driver");
//		Connection con = (Connection) DriverManager
//				.getConnection("jdbc:h2:~/mycollab_extdb;MODE=MySQL");
//		Statement stmt = (Statement) con.createStatement();
//		stmt.execute(query);
//		con.close();
//	}
}
