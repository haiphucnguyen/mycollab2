package com.esofthead.mycollab.module.ecm;

import org.apache.jackrabbit.core.fs.db.DbFileSystem;

import com.esofthead.mycollab.configuration.DatabaseConfiguration;
import com.esofthead.mycollab.configuration.SiteConfiguration;

public class DbFileSystemExt extends DbFileSystem {

	public DbFileSystemExt() {
		this.schema = "mysql";
		DatabaseConfiguration dbConfiguration = SiteConfiguration
				.getDatabaseConfiguration();
		this.driver = dbConfiguration.getDriverClass();
		this.url = dbConfiguration.getDbUrl();
		this.user = dbConfiguration.getUser();
		this.password = dbConfiguration.getPassword();
		this.schemaObjectPrefix = "ecm_s_";
	}
}
