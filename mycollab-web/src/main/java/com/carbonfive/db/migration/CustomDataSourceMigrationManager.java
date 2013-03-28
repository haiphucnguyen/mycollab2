package com.carbonfive.db.migration;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import com.carbonfive.db.jdbc.DatabaseType;
import com.carbonfive.db.jdbc.DatabaseUtils;

public class CustomDataSourceMigrationManager extends
		DataSourceMigrationManager {
	private static Logger log = LoggerFactory
			.getLogger(CustomDataSourceMigrationManager.class);

	private static final String H2_MIGRATION_LOCATION = "classpath:/db/h2/migrations/";
	private static final String DEFAULT_MIGRATIONS_LOCATION = "classpath:/db/migrations/";

	private final JdbcTemplate jdbcTemplate;

	public CustomDataSourceMigrationManager(DataSource dataSource) {
		super(dataSource);

		jdbcTemplate = new JdbcTemplate(dataSource);

		DatabaseType type = getDatabaseType();
		log.debug("Database type: " + type);
		switch (type) {
		case H2:
			setMigrationResolver(new ResourceMigrationResolver(
					H2_MIGRATION_LOCATION));
			break;
		default:
			setMigrationResolver(new ResourceMigrationResolver(
					DEFAULT_MIGRATIONS_LOCATION));
			break;
		}
	}

	private DatabaseType getDatabaseType() {
		return jdbcTemplate.execute(new ConnectionCallback<DatabaseType>() {
			public DatabaseType doInConnection(Connection connection)
					throws SQLException, DataAccessException {
				return DatabaseUtils.databaseType(connection.getMetaData()
						.getURL());
			}
		});
	}

}
