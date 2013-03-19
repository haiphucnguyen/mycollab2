package com.esofthead.util.sqldump.data;

import java.util.LinkedList;
import java.util.List;

import com.esofthead.util.sqldump.INFORMATION_SCHEMA;
import com.esofthead.util.sqldump.data.parser.ColumnParser;
import com.esofthead.util.sqldump.data.parser.PrimaryKeyColumnParser;

public class Table {
	
	public Table(Schema owner) {
		this.owner = owner;
	}
	
	Schema owner;
	private String tableName;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public final List<Column> Columns = new LinkedList<Column>();
	public final List<PrimaryKeyColumn> PrimaryKeys = new LinkedList<PrimaryKeyColumn>();

	public void loadColumn() throws Exception {
		List<Object> lsObjects = owner.adapter.getData(
				INFORMATION_SCHEMA.COLUMNS.getMethodName(),
				INFORMATION_SCHEMA.COLUMNS.getParameterTypes(),
				INFORMATION_SCHEMA.COLUMNS.getQueryParameters(tableName),
				new ColumnParser());

		for (Object obj : lsObjects) {
			Column column = (Column) obj;

			Columns.add(column);
		}

		lsObjects = owner.adapter.getData(
				INFORMATION_SCHEMA.PRIMARY_KEY.getMethodName(),
				INFORMATION_SCHEMA.PRIMARY_KEY.getParameterTypes(),
				INFORMATION_SCHEMA.PRIMARY_KEY.getQueryParameters(tableName),
				new PrimaryKeyColumnParser());
		for (Object obj : lsObjects) {
			PrimaryKeyColumn column = (PrimaryKeyColumn) obj;

			PrimaryKeys.add(column);
		}
	}

	public String serialTable() {
		final String createTableTemplate = "CREATE TABLE `%s` (\r\n";
		final String appendColStartWithCommaTemplate = ",\r\n\t%s";
		final String appendColNotStartWithCommaTemplate = "\t%s";
		final String primaryKeyTemplate = ",\r\n\tPRIMARY KEY (%s)";

		StringBuilder script = new StringBuilder();
		script.append(String.format(createTableTemplate, tableName));

		Column col = Columns.get(0);
		script.append(String.format(appendColNotStartWithCommaTemplate,
				col.serialColumn()));
		for (int i = 1; i < Columns.size(); i++) {
			col = Columns.get(i);
			script.append(String.format(appendColStartWithCommaTemplate,
					col.serialColumn()));
		}

		if (PrimaryKeys.size() > 0) {
			StringBuilder primaryKeyScript = new StringBuilder();
			final String appendKeyStartWithComma = ", `%s`";
			final String appendKeyNotStartWithComma = "`%s`";

			PrimaryKeyColumn key = PrimaryKeys.get(0);
			primaryKeyScript.append(String.format(appendKeyNotStartWithComma,
					key.getColumnName()));
			for (int j = 1; j < PrimaryKeys.size(); j++) {
				key = PrimaryKeys.get(j);
				primaryKeyScript.append(String.format(appendKeyStartWithComma,
						key.getColumnName()));
			}

			script.append(String.format(primaryKeyTemplate,
					primaryKeyScript.toString()));
		}

		script.append("\r\n);");
		return script.toString();
	}

}
