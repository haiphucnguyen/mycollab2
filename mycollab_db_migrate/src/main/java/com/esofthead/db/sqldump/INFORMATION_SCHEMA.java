package com.esofthead.db.sqldump;

public class INFORMATION_SCHEMA {
	public static class TABLES {
		public static final String TABLE_SCHEMA = "TABLE_SCHEMA";
		public static final String TABLE_NAME = "TABLE_NAME";
		public static final String TABLE_TYPE = "TABLE_TYPE";

		public static final String getTableListBySchema(String schemaName) {
			return String
					.format("SELECT %s, %s, %s FROM INFORMATION_SCHEMA.TABLES WHERE %s='%s';",
							TABLE_SCHEMA, TABLE_NAME, TABLE_TYPE, TABLE_SCHEMA,
							schemaName);
		}
	}

	public static class COLUMNS {
		public static final String TABLE_SCHEMA = "TABLE_SCHEMA";
		public static final String TABLE_NAME = "TABLE_NAME";
		public static final String COLUMN_NAME = "COLUMN_NAME";
		public static final String COLUMN_DEFAULT = "COLUMN_DEFAULT";
		public static final String IS_NULLABLE = "IS_NULLABLE";
		public static final String DATA_TYPE = "DATA_TYPE";
		public static final String CHARACTER_MAXIMUM_LENGTH = "CHARACTER_MAXIMUM_LENGTH";
		public static final String COLUMN_TYPE = "COLUMN_TYPE";
		public static final String COLUMN_KEY = "COLUMN_KEY";
		public static final String EXTRA = "EXTRA";
		public static final String ORDINAL_POSITION = "ORDINAL_POSITION";

		public static final String getColumnListByTable(String tableName) {
			return String
					.format("SELECT %s,%s,%s,%s,%s,%s,%s,%s,%s,%s FROM INFORMATION_SCHEMA.COLUMNS WHERE %s='%s'",
							TABLE_SCHEMA, TABLE_NAME, COLUMN_NAME,
							COLUMN_DEFAULT, IS_NULLABLE, DATA_TYPE,
							CHARACTER_MAXIMUM_LENGTH, COLUMN_TYPE, COLUMN_KEY,
							EXTRA, TABLE_NAME, tableName);
		}
	}

	public static class CONSTRAINTS {
		public static final String CONSTRAINT_SCHEMA = "CONSTRAINT_SCHEMA";
		public static final String CONSTRAINT_NAME = "CONSTRAINT_NAME";
		public static final String TABLE_NAME = "TABLE_NAME";
		public static final String COLUMN_NAME = "COLUMN_NAME";
		public static final String REFERENCED_TABLE_NAME = "REFERENCED_TABLE_NAME";
		public static final String REFERENCED_COLUMN_NAME = "REFERENCED_COLUMN_NAME";

		public static final String getConstraintListBySchema(String schemaName) {
			return String
					.format("SELECT %s,%s,%s,%s,%s,%s FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE %s='%s' AND %s <> 'PRIMARY'",
							CONSTRAINT_SCHEMA, CONSTRAINT_NAME, TABLE_NAME,
							COLUMN_NAME, REFERENCED_TABLE_NAME,
							REFERENCED_COLUMN_NAME, CONSTRAINT_SCHEMA,
							schemaName, CONSTRAINT_NAME);
		}
	}

	public static class INNODB_SYS_FOREIGN {
		public static final String ID = "ID";
		public static final String FOR_NAME = "FOR_NAME";
		public static final String REF_NAME = "REF_NAME";
		public static final String N_COLS = "N_COLS";
		public static final String TYPE = "TYPE";

		public static final String getSysForeignBySchema(String schemaName) {
			return String
					.format("SELECT %s,%s,%s,%s,%s FROM INFORMATION_SCHEMA.INNODB_SYS_FOREIGN WHERE %s LIKE '%s/",
							ID, FOR_NAME, REF_NAME, N_COLS, TYPE, ID,
							schemaName) + "%'";
		}
	}

}
