package com.esofthead.util.sqldump;

import java.sql.ResultSet;

import com.esofthead.util.sqldump.data.ISqlEntity;

public interface ISqlParser<E extends ISqlEntity> {
	E parse(ResultSet rs) throws Exception;

}
