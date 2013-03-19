package com.esofthead.util.sqldump;

import java.sql.ResultSet;

public interface ISqlParser {
	Object parse(ResultSet rs) throws Exception;
	
}
