package com.esofthead.util.sqldump.data.parser;

import java.sql.ResultSet;

import com.esofthead.util.sqldump.INFORMATION_SCHEMA;
import com.esofthead.util.sqldump.ISqlParser;
import com.esofthead.util.sqldump.data.Schema;
import com.esofthead.util.sqldump.data.Table;

public class TableParser implements ISqlParser<Table> {

	Schema schema;

	public TableParser(Schema schema) {
		this.schema = schema;
	}

	@Override
	public Table parse(ResultSet rs) throws Exception {
		String tableName = rs.getString(INFORMATION_SCHEMA.TABLES.TABLE_NAME);

		Table table = new Table(schema);
		table.setTableName(tableName);
		return table;
	}

}
