package com.esofthead.util.sqldump.data;

public class Constraint {
	private String constraintSchema;
	private String constraintName;
	private String tableName;
	private String columnName;
	private String referenceTableName;
	private String referenceColumnName;

	/**
	 * @return the constraintSchema
	 */
	public String getConstraintSchema() {
		return constraintSchema;
	}

	/**
	 * @param constraintSchema
	 *            the constraintSchema to set
	 */
	public void setConstraintSchema(String constraintSchema) {
		this.constraintSchema = constraintSchema;
	}

	/**
	 * @return the constraintName
	 */
	public String getConstraintName() {
		return constraintName;
	}

	/**
	 * @param constraintName
	 *            the constraintName to set
	 */
	public void setConstraintName(String constraintName) {
		this.constraintName = constraintName;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName
	 *            the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * @param columnName
	 *            the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * @return the referenceTableName
	 */
	public String getReferenceTableName() {
		return referenceTableName;
	}

	/**
	 * @param referenceTableName
	 *            the referenceTableName to set
	 */
	public void setReferenceTableName(String referenceTableName) {
		this.referenceTableName = referenceTableName;
	}

	/**
	 * @return the referenceColumnName
	 */
	public String getReferenceColumnName() {
		return referenceColumnName;
	}

	/**
	 * @param referenceColumnName
	 *            the referenceColumnName to set
	 */
	public void setReferenceColumnName(String referenceColumnName) {
		this.referenceColumnName = referenceColumnName;
	}

	public boolean isUniqueIndex() {
		return null == referenceTableName
				|| referenceTableName.trim().length() == 0;
	}

	private static final int __key1 = 1; // ON DELETE CASCADE
	private static final int __key2 = 2; // ON DELETE SET NULL
	private static final int __key4 = 4; // ON UPDATE CASCADE
	private static final int __key8 = 8; // ON UPDATE SET NULL
	private static final int __key16 = 16; // ON DELETE NO ACTION
	private static final int __key32 = 32; // ON UPDATE NO ACTION

	private String buildAction(int constraintType) {
		StringBuilder script = new StringBuilder("");
		if ((constraintType & __key1) == __key1) {
			if (script.length() > 0)
				script.append(" ON DELETE CASCADE");
			else
				script.append("ON DELETE CASCADE");
		}
		if ((constraintType & __key2) == __key2) {
			if (script.length() > 0)
				script.append(" ON DELETE SET NULL");
			else
				script.append("ON DELETE SET NULL");
		}
		if ((constraintType & __key4) == __key4) {
			if (script.length() > 0)
				script.append(" ON UPDATE CASCADE");
			else
				script.append("ON UPDATE CASCADE");
		}
		if ((constraintType & __key8) == __key8) {
			if (script.length() > 0)
				script.append(" ON UPDATE SET NULL");
			else
				script.append("ON UPDATE SET NULL");
		}
		if ((constraintType & __key16) == __key16) {
			if (script.length() > 0)
				script.append(" ON DELETE NO ACTION");
			else
				script.append("ON DELETE NO ACTION");
		}
		if ((constraintType & __key32) == __key32) {
			if (script.length() > 0)
				script.append(" ON UPDATE NO ACTION");
			else
				script.append("ON UPDATE NO ACTION");
		}
		return script.toString();
	}

	public String serialConstraint(int constraintType) {
		final String alterUniqueConstraintTemplate = "ALTER TABLE %s ADD UNIQUE INDEX `%s` (`%s` ASC);";
		final String alterForeignKeyConstraintTemplate = "ALTER TABLE %s ADD CONSTRAINT `%s` FOREIGN KEY (`%s`) REFERENCES %s(`%s`) %s;";

		if (isUniqueIndex()) {
			return String.format(alterUniqueConstraintTemplate, tableName,
					constraintName, columnName);
		} else {
			String option = buildAction(constraintType);
			return String.format(alterForeignKeyConstraintTemplate, tableName,
					constraintName, columnName, referenceTableName,
					referenceColumnName, option);
		}
	}

}
