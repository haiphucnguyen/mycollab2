package com.esofthead.db.sqldump;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.zip.GZIPOutputStream;

import com.esofthead.util.sqldump.data.Schema;

public class DbExport {

	private static boolean isExportInprocessing = false;
	private static Object synKey = new Object();

	public static final int ANOTHER_PROCESS_INPROGRESS = 0;
	public static final int DUMP_SQL_SUCCESS = 1;
	public static final int DUMP_SQL_FAIL = 2;

	public static interface IBackupHandler {
		void onBackupTerminate(int resultCode, Exception e);
	}

	public static final void asynDumpDB(final DbConfiguration configuration,
			final OutputStream out, final boolean isZipped, final IBackupHandler handler) {
		synchronized (synKey) {
			if (isExportInprocessing) {
				if (null != handler) {
					handler.onBackupTerminate(ANOTHER_PROCESS_INPROGRESS, null);
				}
				return;
			} else {
				isExportInprocessing = true;
			}
		}
		
		try {
			final OutputStreamWriter writer;// = new OutputStreamWriter(new FileOutputStream(tmpFile));
			
			if (isZipped) {
				writer = new OutputStreamWriter(new GZIPOutputStream(out));
			} else {
				writer = new OutputStreamWriter(out);
			}
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						exportDb(configuration, writer);
						
						writer.flush();
						writer.close();
					} catch (Exception e) {
						/*
						 * this catch because the processing is running in difference thread
						 * so, the catch before not effect
						 */
						synchronized (synKey) {
							isExportInprocessing = false;
						}
						
						if (null != handler) {
							handler.onBackupTerminate(DUMP_SQL_FAIL, e);
						}
						
						return;
					}
					
					synchronized (synKey) {
						isExportInprocessing = false;
					}
					
					handler.onBackupTerminate(DUMP_SQL_SUCCESS, null);
				}
			}).start();
		} catch (Exception e) {
			synchronized (synKey) {
				isExportInprocessing = false;
			}

			if (null != handler) {
				handler.onBackupTerminate(DUMP_SQL_FAIL, e);
			}
			return;
		}
	}

	public static void exportDb(DbConfiguration configuration, Writer writer)
			throws Exception {
		Schema schema = Schema.loadSchema(configuration);
		schema.dumpSchema(writer, true);
	}

	public static final void zipFile(String sourceFile, String destFile)
			throws Exception {
		zipFile(new FileInputStream(new File(sourceFile)),
				new FileOutputStream(new File(destFile)));
	}

	public static final void zipFile(InputStream in, OutputStream out)
			throws Exception {
		final int buffSize = 4096;

		byte[] buffer = new byte[buffSize];
		GZIPOutputStream gzos = new GZIPOutputStream(out);

		int byteRead;
		while ((byteRead = in.read(buffer)) != -1) {
			gzos.write(buffer, 0, byteRead);
		}
		gzos.finish();
		gzos.flush();
		gzos.close();

		in.close();
	}

	public static void main(String[] args) throws Exception {
//		StringWriter writer = new StringWriter();
//		DbExport.exportDb(DbConfiguration.loadDefault(), writer);
//		System.out.println(writer.toString());
		
		asynDumpDB(DbConfiguration.loadDefault(), new FileOutputStream(
				new File("D:\\out\\data.zip")), true, new IBackupHandler() {

			@Override
			public void onBackupTerminate(int resultCode, Exception e) {
				if (null != e) {
					System.out.println(e.getMessage());
				} else {
					System.out.println("Export terminate success");
				}
			}
		});
		
//		asynDumpDB(DbConfiguration.loadDefault(), System.out, true, new IBackupHandler() {
//			
//			@Override
//			public void onBackupTerminate(int resultCode, Exception e) {
//				if (null != e) {
//					System.out.println(e.getMessage());
//				} else {
//					System.out.println("Export terminate success");
//				}
//			}
//		});
		
		Thread.sleep(5000);
		
		while (true) {
			Thread.sleep(5000);
			synchronized (synKey) {
				if (!isExportInprocessing)
					break;
			}
		}
		
		
		// String content = writer.toString();
		//
		// System.out.println(processingStringValue(content));

		// File file = new File("D:/script.txt");
		// FileInputStream fin = new FileInputStream(file);
		// ByteArrayOutputStream sout = new ByteArrayOutputStream();
		// byte[] buffer = new byte[4096];
		// int byteRead;
		// while ((byteRead = fin.read(buffer)) != -1) {
		// sout.write(buffer, 0, byteRead);
		// }
		// fin.close();
		// sout.flush();
		// sout.close();
		//
		// executeNonQuery(new String(sout.toByteArray()));
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
		Connection con = (Connection) DriverManager
				.getConnection("jdbc:h2:~/mycollab_extdb;MODE=MySQL");
		Statement stmt = (Statement) con.createStatement();
		stmt.execute(query);
		con.close();
	}
}
