package com.esofthead.util.sqldump.data;

import java.io.Writer;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.db.sqldump.DbConfiguration;
import com.esofthead.util.sqldump.DataAdapter;
import com.esofthead.util.sqldump.INFORMATION_SCHEMA;
import com.esofthead.util.sqldump.data.parser.ForeignKeyConstraintParser;
import com.esofthead.util.sqldump.data.parser.TableParser;
import com.esofthead.util.sqldump.data.parser.UniqueIndexParser;

public class Schema {
	private static final String SKIP_SCHEMA_VERSION = "schema_version";

	private static Logger log = LoggerFactory.getLogger(Schema.class);

	DataAdapter adapter;
	boolean isMySQL = false;
	int exportOption = DbConfiguration.SCHEMA_DATA;

	final public List<Table> Tables = new LinkedList<Table>();
	final public List<UniqueIndex> UniqueIndexs = new LinkedList<UniqueIndex>();
	final public List<ForeignKeyConstraint> ForeignKeyConstraints = new LinkedList<ForeignKeyConstraint>();

	private Schema(DataAdapter adapter) {
		this.adapter = adapter;
	}

	private void loadTable() throws Exception {
		List<Object> lsObjects = adapter.getData(
				INFORMATION_SCHEMA.TABLES.getMethodName(),
				INFORMATION_SCHEMA.TABLES.getParameterTypes(),
				INFORMATION_SCHEMA.TABLES.getQueryParameters(),
				new TableParser(this));

		Tables.clear();
		for (Object obj : lsObjects) {
			Table table = (Table) obj;
			if (table.getTableName().toLowerCase().equals(SKIP_SCHEMA_VERSION))
				continue;
			Tables.add((Table) obj);

			log.debug("Add table definition: " + table.getTableName());
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
			if (!index.getIndexName().equals("PRIMARY")) {
				UniqueIndexs.add((UniqueIndex) obj);
				log.debug("Add unique index: " + index.getIndexName());
			}
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
			ForeignKeyConstraint fkConstraint = (ForeignKeyConstraint) obj;
			if (fkConstraint.getPkTableName().toLowerCase()
					.equals(SKIP_SCHEMA_VERSION)
					|| fkConstraint.getFkTableName().toLowerCase()
							.equals(SKIP_SCHEMA_VERSION))
				continue;
			ForeignKeyConstraints.add((ForeignKeyConstraint) obj);
			log.debug("Add foreign key: " + fkConstraint.getFkName() + "--"
					+ fkConstraint.getFkTableName() + "--"
					+ fkConstraint.getPkTableName());
		}
	}

	public void dumpSchema(Writer writer) throws Exception {
		log.debug("Dump Schema");

		if (exportOption != DbConfiguration.DATA_ONLY) {
			for (Table table : Tables) {
				writer.append(table.serialTable());
				writer.append("\r\n\r\n");
			}
		}

		if (exportOption == DbConfiguration.DATA_ONLY
				|| exportOption == DbConfiguration.SCHEMA_DATA
				|| exportOption == DbConfiguration.SCHEMA_DATA_NO_CONSTAINT) {
			writer.append("\r\n-- dump data -------------------------------------------\r\n\r\n\r\n");
			List<Table> lsTables = resolveDependencies();
			for (Table table : lsTables) {
				table.dumpTableData(writer, isMySQL);
			}
		}

		if (exportOption != DbConfiguration.DATA_ONLY) {
			writer.append("\r\n-- Add unique constraint -------------------------------------------\r\n\r\n\r\n");

			for (UniqueIndex index : UniqueIndexs) {
				writer.append(index.serialUniqueIndex());
				writer.append("\r\n\r\n");
			}
		}

		if (exportOption != DbConfiguration.DATA_ONLY
				&& exportOption != DbConfiguration.SCHEMA_DATA_NO_CONSTAINT
				&& exportOption != DbConfiguration.SCHEMA_NO_CONSTRAINT) {
			writer.append("\r\n-- Add foreign key constraint -------------------------------------------\r\n\r\n\r\n");

			for (ForeignKeyConstraint constraint : ForeignKeyConstraints) {
				writer.append(constraint.serialForeignConstraint());
				writer.append("\r\n");
			}
		}
	}

	public static final Schema loadSchema(DbConfiguration config)
			throws Exception {
		Schema schema = new Schema(new DataAdapter(config));
		schema.isMySQL = config.isMySqlModel();
		schema.exportOption = config.getExportOption();

		schema.loadTable();

		schema.UniqueIndexs.clear();
		schema.ForeignKeyConstraints.clear();

		for (Table table : schema.Tables) {
			table.loadColumns();
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
