package com.esofthead.mycollab.db.schema;

import schemacrawler.schema.Column;
import schemacrawler.schema.Database;
import schemacrawler.schema.Schema;
import schemacrawler.schema.Table;
import schemacrawler.schemacrawler.SchemaCrawlerException;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.schemacrawler.SchemaInfoLevel;
import schemacrawler.utility.SchemaCrawlerUtility;

public class DbTest {
	public static void main(String[] args) throws SchemaCrawlerException {
		final SchemaCrawlerOptions options = new SchemaCrawlerOptions();
		// Set what details are required in the schema - this affects the
		// time taken to crawl the schema
		options.setSchemaInfoLevel(SchemaInfoLevel.standard());

		final Database database = SchemaCrawlerUtility.getDatabase(null,
				options);
		for (final Schema schema : database.getSchemas()) {
			System.out.println(schema);
			for (final Table table : database.getTables(schema)) {
				System.out.print("o--> " + table);
				for (final Column column : table.getColumns()) {
					System.out.println("     o--> " + column);
				}
			}
		}
	}
}
