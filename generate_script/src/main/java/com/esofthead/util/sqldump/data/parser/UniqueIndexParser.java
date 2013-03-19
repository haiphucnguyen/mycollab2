package com.esofthead.util.sqldump.data.parser;

import java.sql.ResultSet;

import com.esofthead.util.sqldump.INFORMATION_SCHEMA;
import com.esofthead.util.sqldump.ISqlParser;
import com.esofthead.util.sqldump.data.UniqueIndex;

public class UniqueIndexParser implements ISqlParser {

	@Override
	public Object parse(ResultSet rs) throws Exception {
		String tableName = rs.getString(INFORMATION_SCHEMA.UNIQUE_INDEX_INFO.TABLE_NAME);
		String indexName = rs.getString(INFORMATION_SCHEMA.UNIQUE_INDEX_INFO.INDEX_NAME);
		String columnName = rs.getString(INFORMATION_SCHEMA.UNIQUE_INDEX_INFO.COLUMN_NAME);
		String ascOrDesc = rs.getString(INFORMATION_SCHEMA.UNIQUE_INDEX_INFO.ASC_OR_DESC);
		
		UniqueIndex index = new UniqueIndex();
		index.setTableName(tableName);
		index.setIndexName(indexName);
		index.setColumnName(columnName);
		index.setAscOrDesc(ascOrDesc);
		return index;
	}

}
