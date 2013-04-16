package com.esofthead.util.sqldump.data.parser;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import com.esofthead.util.sqldump.ISqlParser;
import com.esofthead.util.sqldump.data.NullSqlStringData;
import com.esofthead.util.sqldump.data.RowData;
import com.esofthead.util.sqldump.data.SqlStringData;

public class RowDataParser implements ISqlParser<RowData> {
	private int fieldCount;

	public RowDataParser(int fieldCount) {
		this.fieldCount = fieldCount;
	}

	@Override
	public RowData parse(ResultSet rs) throws Exception {

		RowData result = new RowData();
		for (int i = 0; i < fieldCount; i++) {
			String data = rs.getString(i + 1);
			if (null != data)
				result.addData(new SqlStringData(data));
			else
				result.addData(new NullSqlStringData());
		}

		return result;
	}

}
