package com.esofthead.util.sqldump.data.parser;

import java.sql.ResultSet;

import com.esofthead.util.sqldump.INFORMATION_SCHEMA;
import com.esofthead.util.sqldump.ISqlParser;
import com.esofthead.util.sqldump.data.InnoDBSysForeign;

public class InnoDBSysForeignParser implements ISqlParser {
	@Override
	public Object parse(ResultSet rs) throws Exception {
		String id = rs.getString(INFORMATION_SCHEMA.INNODB_SYS_FOREIGN.ID);
		String forName = rs.getString(INFORMATION_SCHEMA.INNODB_SYS_FOREIGN.FOR_NAME);
		String refName = rs.getString(INFORMATION_SCHEMA.INNODB_SYS_FOREIGN.REF_NAME);
		String nCols = rs.getString(INFORMATION_SCHEMA.INNODB_SYS_FOREIGN.N_COLS);
		int type = rs.getInt(INFORMATION_SCHEMA.INNODB_SYS_FOREIGN.TYPE);
		
		InnoDBSysForeign option = new InnoDBSysForeign();
		option.setId(id);
		option.setForName(forName);
		option.setRefName(refName);
		option.setnCols(nCols);
		option.setType(type);
		
		return option;
	}
}
