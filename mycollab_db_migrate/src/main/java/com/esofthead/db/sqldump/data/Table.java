package com.esofthead.db.sqldump.data;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import com.esofthead.db.sqldump.DataAdapter;
import com.esofthead.db.sqldump.INFORMATION_SCHEMA;
import com.esofthead.db.sqldump.data.parser.ColumnParser;
import com.esofthead.db.sqldump.data.parser.TableParser;

public class Table {

	public static List<Table> loadTable(String schemaName) throws Exception {
		List<Object> lsObjects = DataAdapter.getData(
				INFORMATION_SCHEMA.TABLES.getTableListBySchema(schemaName),
				new TableParser());

		List<Table> lsResult = new LinkedList<Table>();
		for (Object obj : lsObjects) {
			lsResult.add((Table) obj);
		}
		lsObjects.clear();
		return lsResult;
	}

	private String tableName;
	private String tableSchema;
	private String tableType;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public final List<Column> Columns = new LinkedList<Column>();
	public final List<Column> PrimaryKeys = new LinkedList<Column>();
	private final Hashtable<String, Column> MapColumns = new Hashtable<String, Column>();

	public void loadColumn() throws Exception {
		List<Object> lsObjects = DataAdapter.getData(
				INFORMATION_SCHEMA.COLUMNS.getColumnListByTable(tableName),
				new ColumnParser());

		for (Object obj : lsObjects) {
			Column column = (Column) obj;

			Column match = MapColumns.get(column.getColumnName());
			if (null == match) {
				Columns.add(column);
				MapColumns.put(column.getColumnName(), column);

				if (null != column.getColumnKey()
						&& "PRI".equals(column.getColumnKey())) {
					PrimaryKeys.add(column);
				}
			}
		}
	}

	/**
	 * @return the tableSchema
	 */
	public String getTableSchema() {
		return tableSchema;
	}

	/**
	 * @param tableSchema
	 *            the tableSchema to set
	 */
	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}

	/**
	 * @return the tableType
	 */
	public String getTableType() {
		return tableType;
	}

	/**
	 * @param tableType
	 *            the tableType to set
	 */
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	
	public String serialTable() {
		final String createTableTemplate = "CREATE TABLE `%s` (\r\n";
		final String appendColStartWithCommaTemplate = ",\r\n\t%s";
		final String appendColNotStartWithCommaTemplate = "\t%s";
		final String primaryKeyTemplate = ",\r\n\tPRIMARY KEY (%s)";
		
		StringBuilder script = new StringBuilder();
		script.append(String.format(createTableTemplate, tableName));
		
		Column col = Columns.get(0);
		script.append(String.format(appendColNotStartWithCommaTemplate, col.serialColumn()));
		for (int i = 1; i < Columns.size(); i++) {
			col = Columns.get(i);
			script.append(String.format(appendColStartWithCommaTemplate, col.serialColumn()));
		}
		
		if (PrimaryKeys.size() > 0) {
			StringBuilder primaryKeyScript = new StringBuilder();
			final String appendKeyStartWithComma = ", `%s`";
			final String appendKeyNotStartWithComma = "`%s`";
			
			col = PrimaryKeys.get(0);
			primaryKeyScript.append(String.format(appendKeyNotStartWithComma, col.getColumnName()));
			for (int j = 1; j < PrimaryKeys.size(); j++) {
				col = PrimaryKeys.get(j);
				primaryKeyScript.append(String.format(appendKeyStartWithComma, col.getColumnName()));
			}
			
			script.append(String.format(primaryKeyTemplate, primaryKeyScript.toString()));
		}
		
		script.append("\r\n);");
		return script.toString();
	}
	
	

}
