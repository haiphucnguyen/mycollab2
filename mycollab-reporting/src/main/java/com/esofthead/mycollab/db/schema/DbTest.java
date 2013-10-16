package com.esofthead.mycollab.db.schema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import schemacrawler.schema.Column;
import schemacrawler.schema.Database;
import schemacrawler.schema.Schema;
import schemacrawler.schema.Table;
import schemacrawler.schemacrawler.SchemaCrawlerException;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.schemacrawler.SchemaInfoLevel;
import schemacrawler.utility.SchemaCrawlerUtility;

public class DbTest {
	public static void main(String[] args) throws SchemaCrawlerException,
			ClassNotFoundException, SQLException {
		// STEP 2: Register JDBC driver
		Class.forName("com.mysql.jdbc.Driver");

		// STEP 3: Open a connection
		System.out.println("Connecting to database...");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/mycollab_live?useUnicode=true", "root",
				"esofthead321");

		final SchemaCrawlerOptions options = new SchemaCrawlerOptions();
		// Set what details are required in the schema - this affects the
		// time taken to crawl the schema
		options.setSchemaInfoLevel(SchemaInfoLevel.standard());

		final Database database = SchemaCrawlerUtility.getDatabase(conn,
				options);
		Schema schema = database.getSchema("mycollab_live");
		for (final Table table : database.getTables(schema)) {
			System.out.print("o--> " + table);
			for (final Column column : table.getColumns()) {
				System.out.println("     o--> " + column.getName() + "--"
						+ column.getColumnDataType());
			}
		}
	}
}
