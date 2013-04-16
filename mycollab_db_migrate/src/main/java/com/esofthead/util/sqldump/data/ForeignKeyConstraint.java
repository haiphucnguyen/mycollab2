package com.esofthead.util.sqldump.data;

import java.sql.DatabaseMetaData;

public class ForeignKeyConstraint implements ISqlEntity {
	private String pkTableName;
	private String pkColumnName;
	private String fkTableName;
	private String fkColumnName;
	private short updateRule;
	private short deleteRule;
	private String fkName;
	private String pkName;

	/**
	 * @return the pkTableName
	 */
	public String getPkTableName() {
		return pkTableName;
	}

	/**
	 * @param pkTableName
	 *            the pkTableName to set
	 */
	public void setPkTableName(String pkTableName) {
		this.pkTableName = pkTableName;
	}

	/**
	 * @return the pkColumnName
	 */
	public String getPkColumnName() {
		return pkColumnName;
	}

	/**
	 * @param pkColumnName
	 *            the pkColumnName to set
	 */
	public void setPkColumnName(String pkColumnName) {
		this.pkColumnName = pkColumnName;
	}

	/**
	 * @return the fkTableName
	 */
	public String getFkTableName() {
		return fkTableName;
	}

	/**
	 * @param fkTableName
	 *            the fkTableName to set
	 */
	public void setFkTableName(String fkTableName) {
		this.fkTableName = fkTableName;
	}

	/**
	 * @return the fkColumnName
	 */
	public String getFkColumnName() {
		return fkColumnName;
	}

	/**
	 * @param fkColumnName
	 *            the fkColumnName to set
	 */
	public void setFkColumnName(String fkColumnName) {
		this.fkColumnName = fkColumnName;
	}

	/**
	 * @return the updateRule
	 */
	public short getUpdateRule() {
		return updateRule;
	}

	/**
	 * @param updateRule
	 *            the updateRule to set
	 */
	public void setUpdateRule(short updateRule) {
		this.updateRule = updateRule;
	}

	/**
	 * @return the deleteRule
	 */
	public short getDeleteRule() {
		return deleteRule;
	}

	/**
	 * @param deleteRule
	 *            the deleteRule to set
	 */
	public void setDeleteRule(short deleteRule) {
		this.deleteRule = deleteRule;
	}

	/**
	 * @return the fkName
	 */
	public String getFkName() {
		return fkName;
	}

	/**
	 * @param fkName
	 *            the fkName to set
	 */
	public void setFkName(String fkName) {
		this.fkName = fkName;
	}

	/**
	 * @return the pkName
	 */
	public String getPkName() {
		return pkName;
	}

	/**
	 * @param pkName
	 *            the pkName to set
	 */
	public void setPkName(String pkName) {
		this.pkName = pkName;
	}

	public String serialForeignConstraint() {
		final String alterForeignKeyConstraintTemplate = "ALTER TABLE %s ADD CONSTRAINT `%s` FOREIGN KEY (`%s`) REFERENCES %s(`%s`) %s;";
		StringBuilder script = new StringBuilder("");
		switch (updateRule) {
		case DatabaseMetaData.importedKeyNoAction:
			script.append("ON UPDATE NO ACTION ");
			break;
		case DatabaseMetaData.importedKeyRestrict:
			script.append("ON UPDATE RESTRICT ");
			break;
		case DatabaseMetaData.importedKeyCascade:
			script.append("ON UPDATE CASCADE ");
			break;
		case DatabaseMetaData.importedKeySetNull:
			script.append("ON UPDATE SET NULL ");
			break;
		case DatabaseMetaData.importedKeySetDefault:
			script.append("ON UPDATE SET DEFAULT ");
			break;
		}
		switch (deleteRule) {
		case DatabaseMetaData.importedKeyNoAction:
			script.append("ON DELETE NO ACTION");
			break;
		case DatabaseMetaData.importedKeyRestrict:
			script.append("ON DELETE RESTRICT");
			break;
		case DatabaseMetaData.importedKeyCascade:
			script.append("ON DELETE CASCADE");
			break;
		case DatabaseMetaData.importedKeySetNull:
			script.append("ON DELETE SET NULL");
			break;
		case DatabaseMetaData.importedKeySetDefault:
			script.append("ON DELETE SET DEFAULT");
			break;
		}
		return String.format(alterForeignKeyConstraintTemplate, fkTableName,
				fkName == null ? "" : fkName, fkColumnName, pkTableName,
				pkColumnName, script.toString());
	}
}
