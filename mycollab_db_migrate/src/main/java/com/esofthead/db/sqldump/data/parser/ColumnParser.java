package com.esofthead.db.sqldump.data.parser;

import java.sql.ResultSet;

import com.esofthead.db.sqldump.INFORMATION_SCHEMA;
import com.esofthead.db.sqldump.ISqlParser;
import com.esofthead.db.sqldump.data.Column;

public class ColumnParser implements ISqlParser {

	@Override
	public Object parse(ResultSet rs) throws Exception {
		String tableSchema = rs.getString(INFORMATION_SCHEMA.COLUMNS.TABLE_SCHEMA);
		String tableName = rs.getString(INFORMATION_SCHEMA.COLUMNS.TABLE_NAME);
		String columnName = rs.getString(INFORMATION_SCHEMA.COLUMNS.COLUMN_NAME);
		String columnDefault = rs.getString(INFORMATION_SCHEMA.COLUMNS.COLUMN_DEFAULT);
		boolean isNullable = rs.getBoolean(INFORMATION_SCHEMA.COLUMNS.IS_NULLABLE);
		String dataType = rs.getString(INFORMATION_SCHEMA.COLUMNS.DATA_TYPE);
		String characterMaxLength = rs.getString(INFORMATION_SCHEMA.COLUMNS.CHARACTER_MAXIMUM_LENGTH);
		String columnType = rs.getString(INFORMATION_SCHEMA.COLUMNS.COLUMN_TYPE);
		String extra = rs.getString(INFORMATION_SCHEMA.COLUMNS.EXTRA);
		String columnKey = rs.getString(INFORMATION_SCHEMA.COLUMNS.COLUMN_KEY);
		
		Column column = new Column();
		column.setTableName(tableName);
		column.setSchemaName(tableSchema);
		column.setColumnName(columnName);
		column.setColumnDefault(columnDefault);
		column.setAllowDBNull(isNullable);
		column.setDataType(dataType);
		try {
			column.setMaxCharacterLength(Integer.parseInt(characterMaxLength));
		} catch (Exception ex) {
			column.setMaxCharacterLength(-1);
		}
		column.setColumnType(columnType);
		column.setExtra(extra);
		column.setColumnKey(columnKey);
		return column;
	}

}
