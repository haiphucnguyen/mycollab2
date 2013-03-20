package com.esofthead.util.sqldump.data.parser;

import java.sql.ResultSet;

import com.esofthead.util.sqldump.ISqlParser;

public class IntegerParser implements ISqlParser {
	@Override
	public Object parse(ResultSet rs) throws Exception {
		return rs.getInt(1);
	}
}
