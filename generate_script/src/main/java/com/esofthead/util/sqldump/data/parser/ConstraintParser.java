package com.esofthead.util.sqldump.data.parser;

import java.sql.ResultSet;

import com.esofthead.util.sqldump.INFORMATION_SCHEMA;
import com.esofthead.util.sqldump.ISqlParser;
import com.esofthead.util.sqldump.data.Constraint;

public class ConstraintParser implements ISqlParser {

	@Override
	public Object parse(ResultSet rs) throws Exception {
		String constraintSchema = rs.getString(INFORMATION_SCHEMA.CONSTRAINTS.CONSTRAINT_SCHEMA);
		String constraintName = rs.getString(INFORMATION_SCHEMA.CONSTRAINTS.CONSTRAINT_NAME);
		String tableName = rs.getString(INFORMATION_SCHEMA.CONSTRAINTS.TABLE_NAME);
		String columnName = rs.getString(INFORMATION_SCHEMA.CONSTRAINTS.COLUMN_NAME);
		String referenceTableName = rs.getString(INFORMATION_SCHEMA.CONSTRAINTS.REFERENCED_TABLE_NAME);
		String referenceColumnName = rs.getString(INFORMATION_SCHEMA.CONSTRAINTS.REFERENCED_COLUMN_NAME);
		
		Constraint constraint = new Constraint();
		constraint.setConstraintSchema(constraintSchema);
		constraint.setConstraintName(constraintName);
		constraint.setTableName(tableName);
		constraint.setColumnName(columnName);
		constraint.setReferenceTableName(referenceTableName);
		constraint.setReferenceColumnName(referenceColumnName);
		
		return constraint;
	}

}
