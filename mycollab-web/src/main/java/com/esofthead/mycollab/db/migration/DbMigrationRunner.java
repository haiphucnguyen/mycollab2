package com.esofthead.mycollab.db.migration;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.flyway.core.Flyway;

public class DbMigrationRunner {
	private static Logger log = LoggerFactory
			.getLogger(DbMigrationRunner.class);

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void migrate() {
		try {
			Flyway flyway = new Flyway();
			flyway.setInitOnMigrate(true);
			flyway.setDataSource(dataSource);
			flyway.migrate();
		} catch (Exception e) {
			log.error("Error while migrate database", e);
		}
	}
}
