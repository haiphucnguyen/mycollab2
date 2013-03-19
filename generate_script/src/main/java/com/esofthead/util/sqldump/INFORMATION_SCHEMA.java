package com.esofthead.util.sqldump;

public class INFORMATION_SCHEMA {
	public static class TABLES {
		public static final String TABLE_NAME = "TABLE_NAME";

		public static final String getMethodName() {
			return "getTables";
		}

		public static final Class<?>[] getParameterTypes() {
			return new Class<?>[] { String.class, String.class, String.class,
					new String[] {}.getClass() };
		}

		public static final Object[] getQueryParameters() {
			return new Object[] { null, null, "%", new String[] { "TABLE" } };
		}

	}

	public static class COLUMNS {
		public static final String COLUMN_NAME = "COLUMN_NAME";
		public static final String DATA_TYPE = "DATA_TYPE";
		public static final String TYPE_NAME = "TYPE_NAME";
		public static final String COLUMN_SIZE = "COLUMN_SIZE";
		public static final String DECIMAL_DIGITS = "DECIMAL_DIGITS";
		public static final String NUM_PREC_RADIX = "NUM_PREC_RADIX";
		public static final String COLUMN_DEF = "COLUMN_DEF";
		public static final String IS_NULLABLE = "IS_NULLABLE";
		public static final String IS_AUTOINCREMENT = "IS_AUTOINCREMENT";

		public static final String getMethodName() {
			return "getColumns";
		}

		public static final Class<?>[] getParameterTypes() {
			return new Class<?>[] { String.class, String.class, String.class,
					String.class };
		}

		public static final Object[] getQueryParameters(String tableName) {
			return new Object[] { null, null, tableName, "%" };
		}
	}

	public static class UNIQUE_INDEX_INFO {
		public static final String TABLE_NAME = "TABLE_NAME";
		public static final String INDEX_NAME = "INDEX_NAME";
		public static final String COLUMN_NAME = "COLUMN_NAME";
		public static final String ASC_OR_DESC = "ASC_OR_DESC";

		public static final String getMethodName() {
			return "getIndexInfo";
		}

		public static final Class<?>[] getParameterTypes() {
			return new Class<?>[] { String.class, String.class, String.class,
					boolean.class, boolean.class };
		}

		public static final Object[] getQueryParameters(String tableName) {
			return new Object[] { null, null, tableName, true, false };
		}
	}

	public static class PRIMARY_KEY {
		public static final String COLUMN_NAME = "COLUMN_NAME";

		public static final String getMethodName() {
			return "getPrimaryKeys";
		}

		public static final Class<?>[] getParameterTypes() {
			return new Class<?>[] { String.class, String.class, String.class };
		}
		
		public static final Object[] getQueryParameters(String tableName) {
			return new Object[] { null, null, tableName };
		}
	}
	
	public static class FOREIGN_KEY_CONSTRAINT {
		public static final String PKTABLE_NAME = "PKTABLE_NAME";
		public static final String PKCOLUMN_NAME = "PKCOLUMN_NAME";
		public static final String FKTABLE_NAME = "FKTABLE_NAME";
		public static final String FKCOLUMN_NAME = "FKCOLUMN_NAME";
		public static final String UPDATE_RULE = "UPDATE_RULE";
		public static final String DELETE_RULE = "DELETE_RULE";
		public static final String FK_NAME = "FK_NAME";
		public static final String PK_NAME = "PK_NAME";
		
		public static final String getMethodName() {
			return "getExportedKeys";
		}
		public static final Class<?>[] getParameterTypes() {
			return new Class<?>[] { String.class, String.class, String.class };
		}
		public static final Object[] getQueryParameters(String tableName) {
			return new Object[] { null, null, tableName };
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
							schemaName)
					+ "%'";
		}
	}

}
