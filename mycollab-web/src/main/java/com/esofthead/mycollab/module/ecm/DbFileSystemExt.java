package com.esofthead.mycollab.module.ecm;

import org.apache.jackrabbit.core.fs.db.DbFileSystem;

import com.esofthead.mycollab.common.ApplicationProperties;

public class DbFileSystemExt extends DbFileSystem {
	
	public DbFileSystemExt() {
		this.schema = "mysql";
		this.url = ApplicationProperties
				.getString(ApplicationProperties.DB_URL);
		this.user = ApplicationProperties
				.getString(ApplicationProperties.DB_USERNAME);
		this.password = ApplicationProperties
				.getString(ApplicationProperties.DB_PASSWORD);
		this.schemaObjectPrefix = "ecm_s_";
	}
}
