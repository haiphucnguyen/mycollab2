package com.esofthead.db.sqldump;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.esofthead.util.sqldump.data.Schema;

public class DbExport {
	public static void exportDb(DbConfiguration configuration, Writer writer)
			throws Exception {
		Schema schema = Schema.loadSchema(configuration);
		schema.dumpSchema(writer, true);
	}

	public static void main(String[] args) throws Exception {
//		StringWriter writer = new StringWriter();
//		DbExport.exportDb(DbConfiguration.loadDefault(), writer);
//		System.out.println(writer.toString());
//		String content = writer.toString();
//		
//		System.out.println(processingStringValue(content));
		
		File file = new File("D:/script.txt");
		FileInputStream fin = new FileInputStream(file);
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
	
	public static void executeNonQuery(String query) throws Exception {
		Class.forName("org.h2.Driver");
		Connection con = (Connection) DriverManager.getConnection("jdbc:h2:~/mycollab_extdb;MODE=MySQL");
		Statement stmt = (Statement) con.createStatement();
		stmt.execute(query);
		con.close();
	}
}
