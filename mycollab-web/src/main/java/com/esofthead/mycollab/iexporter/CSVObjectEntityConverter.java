package com.esofthead.mycollab.iexporter;

import com.esofthead.mycollab.iexporter.CSVObjectEntityConverter.CSVItemMapperDef;

public interface CSVObjectEntityConverter<E> extends
		ObjectEntityConverter<CSVItemMapperDef, E> {

	public static class FieldMapperDef {
		private String fieldname;

		private String description;

		public FieldMapperDef(String fieldname, String description) {
			this.fieldname = fieldname;
			this.description = description;
		}

		public String getFieldname() {
			return fieldname;
		}

		public void setFieldname(String fieldname) {
			this.fieldname = fieldname;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
	}

	public static class ImportFieldDef {
		private int columnIndex;
		private FieldMapperDef fieldMapperDef;

		public ImportFieldDef(int columnIndex, FieldMapperDef fieldMapperDef) {
			this.columnIndex = columnIndex;
			this.fieldMapperDef = fieldMapperDef;
		}

		public int getColumnIndex() {
			return columnIndex;
		}

		public void setColumnIndex(int columnIndex) {
			this.columnIndex = columnIndex;
		}

		public String getFieldname() {
			return fieldMapperDef.getFieldname();
		}

		public String getDescription() {
			return fieldMapperDef.getDescription();
		}
	}

	public static class CSVItemMapperDef {
		private String[] csvLine;
		private ImportFieldDef[] fieldsDef;

		public CSVItemMapperDef(String[] csvLine, ImportFieldDef[] fieldDefs) {
			this.csvLine = csvLine;
			this.fieldsDef = fieldDefs;
		}

		public String[] getCsvLine() {
			return csvLine;
		}

		public void setCsvLine(String[] csvLine) {
			this.csvLine = csvLine;
		}

		public ImportFieldDef[] getFieldsDef() {
			return fieldsDef;
		}

		public void setFieldsDef(ImportFieldDef[] fieldsDef) {
			this.fieldsDef = fieldsDef;
		}
	}
}
