package com.esofthead.util.sqldump.data.parser;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import com.esofthead.util.sqldump.ISqlParser;
import com.esofthead.util.sqldump.data.NullSqlStringData;
import com.esofthead.util.sqldump.data.SqlStringData;

public class SqlStringDataParser implements ISqlParser {
	private int fieldCount;
	
	public SqlStringDataParser(int fieldCount) {
		this.fieldCount = fieldCount;
	}
	
	@Override
	public Object parse(ResultSet rs) throws Exception {
		
		List<SqlStringData> lsResult = new LinkedList<SqlStringData>();
		for (int i = 0; i < fieldCount; i++) {
			String data = rs.getString(i + 1);
			if (null != data)
				lsResult.add(new SqlStringData(data));
			else
				lsResult.add(new NullSqlStringData());
		}
		
		return lsResult;
	}
	
}
