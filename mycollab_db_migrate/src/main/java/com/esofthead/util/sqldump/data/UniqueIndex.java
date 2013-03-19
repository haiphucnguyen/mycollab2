package com.esofthead.util.sqldump.data;

public class UniqueIndex {
	private String tableName;
	private String indexName;
	private String columnName;
	private String ascOrDesc;
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
	 * @return the indexName
	 */
	public String getIndexName() {
		return indexName;
	}
	/**
	 * @param indexName the indexName to set
	 */
	public void setIndexName(String indexName) {
		this.indexName = indexName;
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
	
	public final String serialIndex() {
		return "";
	}
	/**
	 * @return the ascOrDesc
	 */
	public String getAscOrDesc() {
		return ascOrDesc;
	}
	/**
	 * @param ascOrDesc the ascOrDesc to set
	 */
	public void setAscOrDesc(String ascOrDesc) {
		this.ascOrDesc = ascOrDesc;
	}
	
	public String serialUniqueIndex() {
		String order = "ASC";
		if (!ascOrDesc.equals("A"))
			order = "DESC";
		final String alterUniqueConstraintTemplate = "ALTER TABLE %s ADD UNIQUE INDEX `%s` (`%s` %s);";
		
		return String.format(alterUniqueConstraintTemplate, tableName,
				indexName, columnName, order);
	}
}
