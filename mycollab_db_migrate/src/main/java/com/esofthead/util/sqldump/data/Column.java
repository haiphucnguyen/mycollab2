package com.esofthead.util.sqldump.data;

import java.sql.Types;

public class Column {
	private String columnName;
	private int dataType;
	private String typeName;
	private int columnSize;
	private int decimalDigits;
	private int numPreRadix;
	private String columnDef;
	private boolean isNullAble;
	private boolean isAutoIncrement;
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
	 * @return the dataType
	 */
	public int getDataType() {
		return dataType;
	}
	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(int dataType) {
		this.dataType = dataType;
	}
	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * @return the columnSize
	 */
	public int getColumnSize() {
		return columnSize;
	}
	/**
	 * @param columnSize the columnSize to set
	 */
	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}
	/**
	 * @return the decimalDigits
	 */
	public int getDecimalDigits() {
		return decimalDigits;
	}
	/**
	 * @param decimalDigits the decimalDigits to set
	 */
	public void setDecimalDigits(int decimalDigits) {
		this.decimalDigits = decimalDigits;
	}
	/**
	 * @return the numPreRadix
	 */
	public int getNumPreRadix() {
		return numPreRadix;
	}
	/**
	 * @param numPreRadix the numPreRadix to set
	 */
	public void setNumPreRadix(int numPreRadix) {
		this.numPreRadix = numPreRadix;
	}
	/**
	 * @return the columnDef
	 */
	public String getColumnDef() {
		return columnDef;
	}
	/**
	 * @param columnDef the columnDef to set
	 */
	public void setColumnDef(String columnDef) {
		this.columnDef = columnDef;
	}
	
	/**
	 * @return the isNullAble
	 */
	public boolean isNullAble() {
		return isNullAble;
	}
	/**
	 * @param isNullAble the isNullAble to set
	 */
	public void setNullAble(boolean isNullAble) {
		this.isNullAble = isNullAble;
	}
	/**
	 * @return the isAutoIncrement
	 */
	public boolean isAutoIncrement() {
		return isAutoIncrement;
	}
	/**
	 * @param isAutoIncrement the isAutoIncrement to set
	 */
	public void setAutoIncrement(boolean isAutoIncrement) {
		this.isAutoIncrement = isAutoIncrement;
	}
	
	public String serialColumn() {
		/*
		 * Order: ColumnName Type Unsigned Null(Not Null) AutoInCreament
		 */
		final String template = "`%s` %s %s %s %s";
		String AllowNull = isNullAble ? "NULL" : "NOT NULL";
		String AutoInCreament = isAutoIncrement ? "AUTO_INCREMENT" : "";
		
		String __typeName = typeName.toUpperCase();
		
		if (dataType == Types.DECIMAL)
			__typeName = String.format("%s(%d,%d)", __typeName, numPreRadix, decimalDigits);
		else if (dataType == Types.VARBINARY || dataType == Types.VARCHAR)
			__typeName = String.format("%s(%d)", __typeName, columnSize);
		
		boolean isQuote = true;
		switch (dataType) {
		case Types.ARRAY:
		case Types.BIGINT:
		case Types.BIT:
		case Types.BOOLEAN:
		case Types.DECIMAL:
		case Types.DOUBLE:
		case Types.FLOAT:
		case Types.INTEGER:
		case Types.NUMERIC:
		case Types.REAL:
		case Types.SMALLINT:
		case Types.TINYINT:
			isQuote = false;
			break;
		}
		
		
		String colDef;// = columnDef == null ? "" : "DEFAULT '" + columnDef + "'";
		if (columnDef != null) {
			if (columnDef.toUpperCase().equals("CURRENT_TIMESTAMP"))
				colDef = "DEFAULT " + columnDef;
			else if (isQuote)
				colDef = "DEFAULT '" + columnDef + "'";
			else 
				colDef = "DEFAULT " + columnDef;
		} else {
			colDef = "";
		}
		
		return String.format(template, columnName, __typeName, AllowNull, AutoInCreament, colDef).trim();
	}
	
	public final String representData(String data) {
		if (null == data)
			return "NULL";
		switch (dataType) {
		case Types.CHAR:
		case Types.LONGVARCHAR:
		case Types.VARCHAR:
		case Types.SQLXML:
			return String.format("'%s'", processingStringValue(data));
		case Types.NCHAR:
		case Types.LONGNVARCHAR:
		case Types.NVARCHAR: 
			return String.format("N'%s'", processingStringValue(data));
		case Types.BINARY:
		case Types.VARBINARY:
			return String.format("0x%s", data);
		case Types.DATE:
		case Types.TIME:
		case Types.TIMESTAMP:
			return String.format("'%s'", data);
		}
		return data;
	}
	
	private final String processingStringValue(String data) {
		String result = data.replace("\\", "\\\\");
		result = result.replace("\0", "\\0");
		result = result.replace("'", "\\'");
		result = result.replace("\"", "\\\"");
		result = result.replace("\b", "\\b");
		result = result.replace("\n", "\\n");
		result = result.replace("\r", "\\r");
		result = result.replace("\t", "\\t");
		return result;
	}
	
	public final String getSelectColumn() {
		if (dataType == Types.BINARY)
			return String.format("HEX(%s)", columnName);
		return columnName;
	}
	
	public final String disableAutoIncrement(String tableName) {
		if (isAutoIncrement) {
			final String template = "ALTER TABLE %s MODIFY COLUMN %s %s %s;\r\n";
			
			String __typeName = typeName.toUpperCase();
			
			if (dataType == Types.DECIMAL)
				__typeName = String.format("%s(%d,%d)", __typeName, numPreRadix, decimalDigits);
			else if (dataType == Types.VARBINARY || dataType == Types.VARCHAR)
				__typeName = String.format("%s(%d)", __typeName, columnSize);
			
			boolean isQuote = true;
			switch (dataType) {
			case Types.ARRAY:
			case Types.BIGINT:
			case Types.BIT:
			case Types.BOOLEAN:
			case Types.DECIMAL:
			case Types.DOUBLE:
			case Types.FLOAT:
			case Types.INTEGER:
			case Types.NUMERIC:
			case Types.REAL:
			case Types.SMALLINT:
			case Types.TINYINT:
				isQuote = false;
				break;
			}
			
			String colDef;// = columnDef == null ? "" : "DEFAULT '" + columnDef + "'";
			if (columnDef != null) {
				if (isQuote)
					colDef = "DEFAULT '" + columnDef + "'";
				else 
					colDef = "DEFAULT " + columnDef;
			} else {
				colDef = "";
			}
			
			return String.format(template, tableName, columnName, __typeName, colDef);
		}
		return null;
	}
	
	public final String enableAutoIncrement(String tableName) {
		if (isAutoIncrement) {
			final String template = "ALTER TABLE %s MODIFY COLUMN %s %s AUTO_INCREMENT %s;\r\n";
			
			String __typeName = typeName.toUpperCase();
			
			if (dataType == Types.DECIMAL)
				__typeName = String.format("%s(%d,%d)", __typeName, numPreRadix, decimalDigits);
			else if (dataType == Types.VARBINARY || dataType == Types.VARCHAR)
				__typeName = String.format("%s(%d)", __typeName, columnSize);
			
			boolean isQuote = true;
			switch (dataType) {
			case Types.ARRAY:
			case Types.BIGINT:
			case Types.BIT:
			case Types.BOOLEAN:
			case Types.DECIMAL:
			case Types.DOUBLE:
			case Types.FLOAT:
			case Types.INTEGER:
			case Types.NUMERIC:
			case Types.REAL:
			case Types.SMALLINT:
			case Types.TINYINT:
				isQuote = false;
				break;
			}
			
			String colDef;// = columnDef == null ? "" : "DEFAULT '" + columnDef + "'";
			if (columnDef != null) {
				if (isQuote)
					colDef = "DEFAULT '" + columnDef + "'";
				else 
					colDef = "DEFAULT " + columnDef;
			} else {
				colDef = "";
			}
			
			return String.format(template, tableName, columnName, __typeName, colDef);
		}
		return null;
	}
}
