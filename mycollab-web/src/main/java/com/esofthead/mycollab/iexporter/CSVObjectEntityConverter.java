package com.esofthead.mycollab.iexporter;

import com.esofthead.mycollab.iexporter.CSVObjectEntityConverter.CSVItemMapperDef;
import com.esofthead.mycollab.iexporter.csv.CSVFormatter;

public interface CSVObjectEntityConverter<E> extends
		ObjectEntityConverter<CSVItemMapperDef, E> {

	public static class FieldMapperDef {
		private String fieldname;

		private String description;

		private CSVFormatter fieldFormatter;

		public FieldMapperDef(String fieldname, String description) {
			this(fieldname, description, null);
		}

		public FieldMapperDef(String fieldname, String description,
				CSVFormatter formatter) {
			this.fieldname = fieldname;
			this.description = description;
			this.fieldFormatter = formatter;
		}

		public String getFieldname() {
			return fieldname;
		}

		public String getDescription() {
			return description;
		}

		public CSVFormatter getFieldFormatter() {
			return fieldFormatter;
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

		public CSVFormatter getFieldFormatter() {
			return fieldMapperDef.getFieldFormatter();
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
