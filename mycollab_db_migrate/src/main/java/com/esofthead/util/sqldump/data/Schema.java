package com.esofthead.util.sqldump.data;

import java.io.Writer;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
	boolean isMySQL = false;

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

	public void dumpSchema(Writer writer, boolean isDumpData) throws Exception {
		for (Table table : Tables) {
			writer.append(table.serialTable());
			writer.append("\r\n\r\n");
		}

		if (isDumpData) {
			writer.append("\r\n-- dump data -------------------------------------------\r\n\r\n\r\n");
			List<Table> lsTables = resolveDependencies();
			for (Table table : lsTables) {
				table.dumpTableData(writer, isMySQL);
			}
		}

		writer.append("\r\n-- Add unique constraint -------------------------------------------\r\n\r\n\r\n");

		for (UniqueIndex index : UniqueIndexs) {
			writer.append(index.serialUniqueIndex());
			writer.append("\r\n\r\n");
		}

		writer.append("\r\n-- Add foreign key constraint -------------------------------------------\r\n\r\n\r\n");

		for (ForeignKeyConstraint constraint : ForeignKeyConstraints) {
			writer.append(constraint.serialForeignConstraint());
			writer.append("\r\n");
		}
	}

	public static final Schema loadSchema(DbConfiguration config)
			throws Exception {
		Schema schema = new Schema(new DataAdapter(config.getUserName(),
				config.getPassword(), config.getUrl()));
		schema.isMySQL = config.isMySqlModel();
		
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

	private List<Table> resolveDependencies() {
		List<Table> lsResult = new LinkedList<Table>();

		Hashtable<String, Integer> childTables = new Hashtable<String, Integer>();
		Hashtable<String, List<String>> parentTables = new Hashtable<String, List<String>>();

		for (ForeignKeyConstraint constraint : ForeignKeyConstraints) {
			List<String> match = parentTables.get(constraint.getPkTableName());
			if (null == match) {
				match = new LinkedList<String>();
				match.add(constraint.getFkTableName());
				parentTables.put(constraint.getPkTableName(), match);
			} else {
				match.add(constraint.getFkTableName());
			}

			Integer value = childTables.get(constraint.getFkTableName());
			if (null == value) {
				childTables.put(constraint.getFkTableName(), new Integer(1));
			} else {
				value += 1;
			}
		}

		Queue<Table> unstackTables = new LinkedList<Table>();

		for (Table table : Tables) {
			unstackTables.add(table);
		}

		while (unstackTables.size() > 0) {
			Table table = unstackTables.poll();

			Integer match = childTables.get(table.getTableName());
			if (null == match) { /* This table do not have any parent */
				// add this table to processing list
				lsResult.add(table);

				List<String> lsChild = parentTables.get(table.getTableName());
				if (null != lsChild) {
					for (String tableName : lsChild) {
						Integer value = childTables.get(tableName);
						if (null != value) {
							value -= 1;
							if (0 == value) {
								childTables.remove(tableName);
							}
						}
					}
					lsChild.clear();
					lsChild = null;
					parentTables.remove(table.getTableName());
				}
				/* if this table have childs */

			} else {
				// add this table to the tail
				unstackTables.add(table);
			}
		}

		if (parentTables.size() > 0)
			parentTables.clear();
		if (childTables.size() > 0)
			childTables.clear();

		return lsResult;
	}
}
