package com.esofthead.db.sqldump.data.parser;

import java.sql.ResultSet;

import com.esofthead.db.sqldump.INFORMATION_SCHEMA;
import com.esofthead.db.sqldump.ISqlParser;
import com.esofthead.db.sqldump.data.Table;

public class TableParser implements ISqlParser {

	@Override
	public Object parse(ResultSet rs) throws Exception {
		String tableSchema = rs.getString(INFORMATION_SCHEMA.TABLES.TABLE_SCHEMA);
		String tableName = rs.getString(INFORMATION_SCHEMA.TABLES.TABLE_NAME);
		String tableType = rs.getString(INFORMATION_SCHEMA.TABLES.TABLE_TYPE);
		
		Table table = new Table();
		table.setTableName(tableName);
		table.setTableSchema(tableSchema);
		table.setTableType(tableType);
		return table;
	}

}
