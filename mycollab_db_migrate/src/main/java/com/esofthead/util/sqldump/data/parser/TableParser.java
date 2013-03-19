package com.esofthead.util.sqldump.data.parser;

import java.sql.ResultSet;

import com.esofthead.util.sqldump.INFORMATION_SCHEMA;
import com.esofthead.util.sqldump.ISqlParser;
import com.esofthead.util.sqldump.data.Table;

public class TableParser implements ISqlParser {

	@Override
	public Object parse(ResultSet rs) throws Exception {
		String tableName = rs.getString(INFORMATION_SCHEMA.TABLES.TABLE_NAME);
		
		Table table = new Table();
		table.setTableName(tableName);
		return table;
	}

}
