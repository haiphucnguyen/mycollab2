package com.carbonfive.db.migration;


public class CustomDataSourceMigrationManager /*extends*/
		/*DataSourceMigrationManager*/ {
//	private static Logger log = LoggerFactory
//			.getLogger(CustomDataSourceMigrationManager.class);
//
//	private static final String H2_MIGRATION_LOCATION = "classpath:/db/h2/migrations/";
//	private static final String DEFAULT_MIGRATIONS_LOCATION = "classpath:/db/migrations/";
//
//	public CustomDataSourceMigrationManager(DataSource dataSource) {
//		super(dataSource);
//
//		DatabaseType type = DatabaseType.UNKNOWN;
//
//		try {
//			DatabaseMetaData metaData = dataSource.getConnection()
//					.getMetaData();
//			String databaseName = metaData.getDatabaseProductName();
//			if ("MySQL".equals(databaseName)) {
//				type = DatabaseType.MYSQL;
//			} else if ("H2".equals(databaseName)) {
//				type = DatabaseType.H2;
//			}
//		} catch (SQLException e) {
//			log.error("Error while detecting database type", e);
//		}
//
//		switch (type) {
//		case H2:
//			setMigrationResolver(new ResourceMigrationResolver(
//					H2_MIGRATION_LOCATION));
//			break;
//		default:
//			setMigrationResolver(new ResourceMigrationResolver(
//					DEFAULT_MIGRATIONS_LOCATION));
//			break;
//		}
//	}

}
