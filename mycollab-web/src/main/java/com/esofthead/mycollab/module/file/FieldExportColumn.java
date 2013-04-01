package com.esofthead.mycollab.module.file;

public class FieldExportColumn {

	private String name;
	private String displayName;
	private int columnWidth;

	public FieldExportColumn(String headerName, String displayName) {
		this(headerName, displayName, 0);
	}

	public FieldExportColumn(String headerName, String displayName,
			int columnWidth) {
		this.name = headerName;
		this.displayName = displayName;
		this.columnWidth = columnWidth;
	}

	public String getName() {
		return name;
	}

	public void setName(String headerName) {
		this.name = headerName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public int getColumnWidth() {
		return columnWidth;
	}

	public void setColumnWidth(int columnWidth) {
		this.columnWidth = columnWidth;
	}
}
