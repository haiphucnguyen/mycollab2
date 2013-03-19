package com.esofthead.util.sqldump.data;

import java.util.LinkedList;
import java.util.List;

import com.esofthead.db.sqldump.DbConfiguration;
import com.esofthead.util.sqldump.DataAdapter;
import com.esofthead.util.sqldump.INFORMATION_SCHEMA;
import com.esofthead.util.sqldump.data.parser.ForeignKeyConstraintParser;
import com.esofthead.util.sqldump.data.parser.TableParser;
import com.esofthead.util.sqldump.data.parser.UniqueIndexParser;

public class Schema {
	private Schema(DataAdapter adapter) {
		this.adapter = adapter;
	}

	DataAdapter adapter;

	final public List<Table> Tables = new LinkedList<Table>();
	final public List<UniqueIndex> UniqueIndexs = new LinkedList<UniqueIndex>();
	final public List<ForeignKeyConstraint> ForeignKeyConstraints = new LinkedList<ForeignKeyConstraint>();

	private void loadTable() throws Exception {
		List<Object> lsObjects = adapter.getData(
				INFORMATION_SCHEMA.TABLES.getMethodName(),
				INFORMATION_SCHEMA.TABLES.getParameterTypes(),
				INFORMATION_SCHEMA.TABLES.getQueryParameters(),
				new TableParser(this));

		Tables.clear();
		for (Object obj : lsObjects) {
			Tables.add((Table) obj);
		}
		lsObjects.clear();
	}

	private void loadUniqueIndex(String tableName) throws Exception {
		List<Object> lsObjects = adapter
				.getData(INFORMATION_SCHEMA.UNIQUE_INDEX_INFO.getMethodName(),
						INFORMATION_SCHEMA.UNIQUE_INDEX_INFO
								.getParameterTypes(),
						INFORMATION_SCHEMA.UNIQUE_INDEX_INFO
								.getQueryParameters(tableName),
						new UniqueIndexParser());
		for (Object obj : lsObjects) {
			UniqueIndex index = (UniqueIndex) obj;
			if (!index.getIndexName().equals("PRIMARY"))
				UniqueIndexs.add((UniqueIndex) obj);
		}
	}

	private void loadForeignKeyConstraint(String tableName) throws Exception {
		List<Object> lsObjects = adapter.getData(
				INFORMATION_SCHEMA.FOREIGN_KEY_CONSTRAINT.getMethodName(),
				INFORMATION_SCHEMA.FOREIGN_KEY_CONSTRAINT.getParameterTypes(),
				INFORMATION_SCHEMA.FOREIGN_KEY_CONSTRAINT
						.getQueryParameters(tableName),
				new ForeignKeyConstraintParser());

		for (Object obj : lsObjects) {
			ForeignKeyConstraints.add((ForeignKeyConstraint) obj);
		}
	}

	public String dumpSchema() {
		StringBuilder script = new StringBuilder();
		for (Table table : Tables) {
			script.append(table.serialTable());
			script.append("\r\n\r\n");
		}

		script.append("\r\n-- Add unique constraint -------------------------------------------\r\n\r\n\r\n");

		for (UniqueIndex index : UniqueIndexs) {
			script.append(index.serialUniqueIndex());
			script.append("\r\n\r\n");
		}

		script.append("\r\n-- Add foreign key constraint -------------------------------------------\r\n\r\n\r\n");

		for (ForeignKeyConstraint constraint : ForeignKeyConstraints) {
			script.append(constraint.serialForeignConstraint());
			script.append("\r\n\r\n");
		}

		return script.toString();
	}

	public static final Schema loadSchema(DbConfiguration config)
			throws Exception {
		// DataAdapter.initContext(config.getUserName(), config.getPassword(),
		// config.getUrl());
		Schema schema = new Schema(new DataAdapter(config.getUserName(),
				config.getPassword(), config.getUrl()));
		schema.loadTable();

		schema.UniqueIndexs.clear();
		schema.ForeignKeyConstraints.clear();

		for (Table table : schema.Tables) {
			table.loadColumn();
			schema.loadUniqueIndex(table.getTableName());
			schema.loadForeignKeyConstraint(table.getTableName());
		}
		return schema;
	}
}
