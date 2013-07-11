package com.esofthead.mycollab.iexporter;

import com.esofthead.mycollab.iexporter.CSVObjectEntityConverter.ImportFieldDef;

public interface CSVObjectEntityConverter<E> extends
		ObjectEntityConverter<ImportFieldDef, E> {

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
		private String[] label;
		private String[] values;

		public ImportFieldDef(String[] label, String[] values) {
			this.label = label;
			this.values = values;
		}

		public String[] getLabel() {
			return label;
		}

		public void setLabel(String[] label) {
			this.label = label;
		}

		public String[] getValues() {
			return values;
		}

		public void setValues(String[] values) {
			this.values = values;
		}

	}
}
