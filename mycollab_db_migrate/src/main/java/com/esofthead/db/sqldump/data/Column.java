package com.esofthead.db.sqldump.data;

public class Column {
	private String schemaName;
	private String tableName;
	private String columnName;
	private Object columnDefault;
	private boolean allowDBNull;
	private String dataType;
	private int maxCharacterLength;
	private String columnType;
	private String columnKey;
	private String extra;
	/**
	 * @return the schemaName
	 */
	public String getSchemaName() {
		return schemaName;
	}
	/**
	 * @param schemaName the schemaName to set
	 */
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * @param tableName the tableName to set
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
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	/**
	 * @return the columnDefault
	 */
	public Object getColumnDefault() {
		return columnDefault;
	}
	/**
	 * @param columnDefault the columnDefault to set
	 */
	public void setColumnDefault(Object columnDefault) {
		this.columnDefault = columnDefault;
	}
	/**
	 * @return the allowDBNull
	 */
	public boolean isAllowDBNull() {
		return allowDBNull;
	}
	/**
	 * @param allowDBNull the allowDBNull to set
	 */
	public void setAllowDBNull(boolean allowDBNull) {
		this.allowDBNull = allowDBNull;
	}
	/**
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}
	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	/**
	 * @return the maxCharacterLength
	 */
	public int getMaxCharacterLength() {
		return maxCharacterLength;
	}
	/**
	 * @param maxCharacterLength the maxCharacterLength to set
	 */
	public void setMaxCharacterLength(int maxCharacterLength) {
		this.maxCharacterLength = maxCharacterLength;
	}
	/**
	 * @return the columnKey
	 */
	public String getColumnKey() {
		return columnKey;
	}
	/**
	 * @param columnKey the columnKey to set
	 */
	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}
	/**
	 * @return the columnType
	 */
	public String getColumnType() {
		return columnType;
	}
	/**
	 * @param columnType the columnType to set
	 */
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	/**
	 * @return the extra
	 */
	public String getExtra() {
		return extra;
	}
	/**
	 * @param extra the extra to set
	 */
	public void setExtra(String extra) {
		this.extra = extra;
	}
	
	public boolean isPrimaryKeyColumn() {
		return null != columnKey && "PRI".equals(columnKey);				
	}
	
	public String serialColumn() {
		/*
		 * Order: ColumnName Type Unsigned Null(Not Null) AutoInCreament
		 */
		final String template = "`%s` %s %s %s";
		String AllowNull = isAllowDBNull() ? "NULL" : "NOT NULL";
		String AutoInCreament = null == extra ? "" : extra.toUpperCase();
		if (AutoInCreament.contains("ON UPDATE CURRENT_TIMESTAMP")) {
			AutoInCreament = "";
		}
		return String.format(template, columnName, columnType.toUpperCase(), AllowNull, AutoInCreament).trim();
	}
	
}
