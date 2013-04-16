package com.esofthead.util.sqldump.data.parser;

import java.sql.ResultSet;

import com.esofthead.util.sqldump.INFORMATION_SCHEMA;
import com.esofthead.util.sqldump.ISqlParser;
import com.esofthead.util.sqldump.data.Column;

public class ColumnParser implements ISqlParser<Column> {

	@Override
	public Column parse(ResultSet rs) throws Exception {
		String columnName = rs
				.getString(INFORMATION_SCHEMA.COLUMNS.COLUMN_NAME);
		int dataType = rs.getInt(INFORMATION_SCHEMA.COLUMNS.DATA_TYPE);
		String typeName = rs.getString(INFORMATION_SCHEMA.COLUMNS.TYPE_NAME);
		int columnSize = rs.getInt(INFORMATION_SCHEMA.COLUMNS.COLUMN_SIZE);
		int decimalDigits = rs
				.getInt(INFORMATION_SCHEMA.COLUMNS.DECIMAL_DIGITS);
		int numPreRadix = rs.getInt(INFORMATION_SCHEMA.COLUMNS.NUM_PREC_RADIX);
		String columnDef = rs.getString(INFORMATION_SCHEMA.COLUMNS.COLUMN_DEF);
		boolean isNullAble = rs.getString(
				INFORMATION_SCHEMA.COLUMNS.IS_NULLABLE).equals("YES");
		boolean isAutoIncrement = rs.getString(
				INFORMATION_SCHEMA.COLUMNS.IS_AUTOINCREMENT).equals("YES");

		Column column = new Column();
		column.setColumnName(columnName);
		column.setDataType(dataType);
		column.setTypeName(typeName);
		column.setColumnSize(columnSize);
		column.setDecimalDigits(decimalDigits);
		column.setNumPreRadix(numPreRadix);
		column.setColumnDef(columnDef);
		column.setNullAble(isNullAble);
		column.setAutoIncrement(isAutoIncrement);

		return column;
	}

}
