package com.esofthead.util.sqldump.data.parser;

import java.sql.ResultSet;

import com.esofthead.util.sqldump.INFORMATION_SCHEMA;
import com.esofthead.util.sqldump.ISqlParser;
import com.esofthead.util.sqldump.data.PrimaryKeyColumn;

public class PrimaryKeyColumnParser implements ISqlParser<PrimaryKeyColumn> {

	@Override
	public PrimaryKeyColumn parse(ResultSet rs) throws Exception {
		String columnName = rs
				.getString(INFORMATION_SCHEMA.PRIMARY_KEY.COLUMN_NAME);

		PrimaryKeyColumn column = new PrimaryKeyColumn();
		column.setColumnName(columnName);

		return column;
	}

}
