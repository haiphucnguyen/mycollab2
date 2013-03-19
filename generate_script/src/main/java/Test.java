import com.esofthead.util.sqldump.H2DataAdapter;
import com.esofthead.util.sqldump.data.Schema;




public class Test {
	public static void main(String[] args) throws Exception {
		Schema schema = Schema.loadSchema();
		String script = schema.dumpSchema();
		H2DataAdapter.executeNonQuery(script);
		
//		File file = new File("D:/script.txt");
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
//		H2DataAdapter.executeNonQuery(new String(sout.toByteArray()));
		
//		Class.forName("com.mysql.jdbc.Driver");

//		Properties prop = new Properties();
//		prop.put("user", ApplicationConfigurations
//				.getProperty(ApplicationConfigurations.USER_NAME));
//		prop.put("password", ApplicationConfigurations
//				.getProperty(ApplicationConfigurations.PASSWORD));
//		Connection con = (Connection) DriverManager.getConnection(
//				ApplicationConfigurations
//						.getProperty(ApplicationConfigurations.URL), prop);
//		
//		DatabaseMetaData metaData = con.getMetaData();
//		ResultSet rs = metaData.getTables(null, null, "%", new String[] { "TABLE" });
//		while (rs.next()) {
//			System.out.println(rs.getString("TABLE_NAME"));
//		}
//		con.close();
//		System.out.println(new String[] { "TABLE" }.getClass());
		
//		List<Object> lsResult = DataAdapter.getData("getTables", new Class<?>[] { String.class, String.class, String.class, new String[] { }.getClass() }, new Object[] { null, null, "%", new String[] { "TABLE" } }, new TableParser());
//		for (Object obj : lsResult) {
//			Table table = (Table)obj;
//			System.out.println(table.getTableName());
//		}
		
//		for (int i = 0; i < 10; i++) {
//			Table table = (Table)lsResult.get(i);
//			table.loadColumn();
//			System.out.println("\r\n\r\n=====================================\r\n\r\n");
//		}
	}
}
