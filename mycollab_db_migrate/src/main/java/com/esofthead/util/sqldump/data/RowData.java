package com.esofthead.util.sqldump.data;

import java.util.ArrayList;
import java.util.List;

public class RowData implements ISqlEntity {

	private List<SqlStringData> data = new ArrayList<SqlStringData>();

	public void addData(SqlStringData value) {
		data.add(value);
	}

	public SqlStringData get(int index) {
		return data.get(index);
	}
}
