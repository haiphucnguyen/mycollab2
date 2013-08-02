package com.esofthead.util.sqldump.data.parser;

import java.sql.ResultSet;

import com.esofthead.util.sqldump.INFORMATION_SCHEMA;
import com.esofthead.util.sqldump.ISqlParser;
import com.esofthead.util.sqldump.data.ForeignKeyConstraint;

public class ForeignKeyConstraintParser implements
		ISqlParser<ForeignKeyConstraint> {

	@Override
	public ForeignKeyConstraint parse(ResultSet rs) throws Exception {
		String pkTableName = rs
				.getString(INFORMATION_SCHEMA.FOREIGN_KEY_CONSTRAINT.PKTABLE_NAME);
		String pkColumnName = rs
				.getString(INFORMATION_SCHEMA.FOREIGN_KEY_CONSTRAINT.PKCOLUMN_NAME);
		String fkTableName = rs
				.getString(INFORMATION_SCHEMA.FOREIGN_KEY_CONSTRAINT.FKTABLE_NAME);
		String fkColumnName = rs
				.getString(INFORMATION_SCHEMA.FOREIGN_KEY_CONSTRAINT.FKCOLUMN_NAME);
		short updateRule = rs
				.getShort(INFORMATION_SCHEMA.FOREIGN_KEY_CONSTRAINT.UPDATE_RULE);
		short deleteRule = rs
				.getShort(INFORMATION_SCHEMA.FOREIGN_KEY_CONSTRAINT.DELETE_RULE);
		String fkName = rs
				.getString(INFORMATION_SCHEMA.FOREIGN_KEY_CONSTRAINT.FK_NAME);
		String pkName = rs
				.getString(INFORMATION_SCHEMA.FOREIGN_KEY_CONSTRAINT.PK_NAME);

		ForeignKeyConstraint constraint = new ForeignKeyConstraint();
		constraint.setPkTableName(pkTableName);
		constraint.setPkColumnName(pkColumnName);
		constraint.setFkTableName(fkTableName);
		constraint.setFkColumnName(fkColumnName);
		constraint.setUpdateRule(updateRule);
		constraint.setDeleteRule(deleteRule);
		constraint.setFkName(fkName);
		constraint.setPkName(pkName);
		return constraint;
	}

}
