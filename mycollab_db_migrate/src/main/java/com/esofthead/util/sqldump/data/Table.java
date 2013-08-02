package com.esofthead.util.sqldump.data;

import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.util.sqldump.INFORMATION_SCHEMA;
import com.esofthead.util.sqldump.data.parser.ColumnParser;
import com.esofthead.util.sqldump.data.parser.PrimaryKeyColumnParser;
import com.esofthead.util.sqldump.data.parser.RowDataParser;

public class Table implements ISqlEntity {

	private static final int MAX_ITEM_PER_QUERY = 1000;

	private static Logger log = LoggerFactory.getLogger(Table.class);

	Schema owner;
	private String tableName;
	private final List<Column> columns = new LinkedList<Column>();
	private final List<PrimaryKeyColumn> primaryKeys = new LinkedList<PrimaryKeyColumn>();

	public Table(Schema owner) {
		this.owner = owner;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void loadColumns() throws Exception {
		List<Object> lsObjects = owner.adapter.getData(
				INFORMATION_SCHEMA.COLUMNS.getMethodName(),
				INFORMATION_SCHEMA.COLUMNS.getParameterTypes(),
				INFORMATION_SCHEMA.COLUMNS.getQueryParameters(tableName),
				new ColumnParser());

		for (Object obj : lsObjects) {
			Column column = (Column) obj;

			columns.add(column);
		}

		lsObjects = owner.adapter.getData(
				INFORMATION_SCHEMA.PRIMARY_KEY.getMethodName(),
				INFORMATION_SCHEMA.PRIMARY_KEY.getParameterTypes(),
				INFORMATION_SCHEMA.PRIMARY_KEY.getQueryParameters(tableName),
				new PrimaryKeyColumnParser());
		for (Object obj : lsObjects) {
			PrimaryKeyColumn column = (PrimaryKeyColumn) obj;

			primaryKeys.add(column);
		}
	}

	public String serialTable() {
		final String createTableTemplate = "CREATE TABLE `%s` (\r\n";
		final String appendColStartWithCommaTemplate = ",\r\n\t%s";
		final String appendColNotStartWithCommaTemplate = "\t%s";
		final String primaryKeyTemplate = ",\r\n\tPRIMARY KEY (%s)";

		StringBuilder script = new StringBuilder();
		script.append(String.format(createTableTemplate, tableName));

		Column col = columns.get(0);
		script.append(String.format(appendColNotStartWithCommaTemplate,
				col.serialColumn()));
		for (int i = 1; i < columns.size(); i++) {
			col = columns.get(i);
			script.append(String.format(appendColStartWithCommaTemplate,
					col.serialColumn()));
		}

		if (primaryKeys.size() > 0) {
			StringBuilder primaryKeyScript = new StringBuilder();
			final String appendKeyStartWithComma = ", `%s`";
			final String appendKeyNotStartWithComma = "`%s`";

			PrimaryKeyColumn key = primaryKeys.get(0);
			primaryKeyScript.append(String.format(appendKeyNotStartWithComma,
					key.getColumnName()));
			for (int j = 1; j < primaryKeys.size(); j++) {
				key = primaryKeys.get(j);
				primaryKeyScript.append(String.format(appendKeyStartWithComma,
						key.getColumnName()));
			}

			script.append(String.format(primaryKeyTemplate,
					primaryKeyScript.toString()));
		}

		script.append("\r\n);");

		return script.toString();
	}

	public void dumpTableData(Writer writer, boolean isMySQL) throws Exception {
		log.debug("Dump table data: " + tableName);
		int count = getCountOfRecords();
		if (count == 0) {
			/* Do nothing if no record found */
			return;
		}

		final String selectCommand = getSelectCommand();
		final String insertCommand = getInsertCommand();

		int queryTimes = count / MAX_ITEM_PER_QUERY;
		if ((count % MAX_ITEM_PER_QUERY) > 0) {
			queryTimes += 1;
		}

		writer.append("\r\n\r\n-- Generate script insert data for " + tableName
				+ " -----------------------------------\r\n");
		writer.append(disableAutoIncrementColumn(isMySQL));

		RowDataParser parser = new RowDataParser(columns.size());

		for (int i = 0; i < queryTimes; i++) {
			int recordIndex = i * MAX_ITEM_PER_QUERY;
			final String query = String.format(selectCommand, recordIndex,
					MAX_ITEM_PER_QUERY);

			log.debug("Start retrieve dump data for table: " + tableName);
			List<ISqlEntity> lsObject = (List<ISqlEntity>) owner.adapter
					.getData(query, parser);

			log.debug("Retrieve row data : " + tableName + ". Size: "
					+ lsObject.size());

			for (int j = 0; j < lsObject.size(); j++) {
				@SuppressWarnings("unchecked")
				RowData values = (RowData) lsObject.get(j);
				writer.append(dumpRow(insertCommand, values));
			}
		}

		writer.append(enableAutoIncrementColumn(isMySQL));

	}

	private final String getSelectCommand() {

		final String selectTemplate = "SELECT %s FROM %s";

		StringBuilder script = new StringBuilder();
		Column col = columns.get(0);
		script.append(col.getSelectColumn());
		for (int i = 1; i < columns.size(); i++) {
			script.append(", " + columns.get(i).getSelectColumn());
		}

		return String.format(selectTemplate, script.toString(), tableName)
				+ " LIMIT %d, %d";
	}

	private final String getInsertCommand() {
		final String insertTemplate = "INSERT INTO %s(%s) VALUES"; // %s;\r\n";

		StringBuilder scriptColumns = new StringBuilder();
		StringBuilder scriptValues = new StringBuilder();

		Column col = columns.get(0);
		scriptColumns.append(col.getColumnName());
		scriptValues.append(" (%s");

		for (int i = 1; i < columns.size(); i++) {
			scriptColumns.append(", " + columns.get(i).getColumnName());
			scriptValues.append(", %s");
		}
		scriptValues.append(");\r\n");

		return String.format(insertTemplate, tableName,
				scriptColumns.toString())
				+ scriptValues.toString();
	}

	private final int getCountOfRecords() throws Exception {
		final String selectCount = "SELECT COUNT(*) FROM %s";
		return ((Long) owner.adapter.getSingleResult(String.format(selectCount,
				tableName))).intValue();
	}

	private final String dumpRow(String insertTemplate, RowData values) {
		Object[] arrs = new String[columns.size()];
		for (int i = 0; i < arrs.length; i++) {
			SqlStringData data = values.get(i);
			Column col = columns.get(i);
			if (data instanceof NullSqlStringData) {
				arrs[i] = col.representData(null);
			} else {
				arrs[i] = col.representData(data.getData());
			}
		}
		return String.format(insertTemplate, arrs);
	}

	private final String disableAutoIncrementColumn(boolean isMySQL) {
		StringBuilder script = new StringBuilder();
		for (Column col : columns) {
			String query = col.disableAutoIncrement(tableName, isMySQL);
			if (null != query) {
				script.append(query);
			}
		}
		return script.toString();
	}

	private final String enableAutoIncrementColumn(boolean isMySQL) {
		StringBuilder script = new StringBuilder();
		for (Column col : columns) {
			String query = col.enableAutoIncrement(tableName, isMySQL);
			if (null != query) {
				script.append(query);
			}
		}
		return script.toString();
	}

}
