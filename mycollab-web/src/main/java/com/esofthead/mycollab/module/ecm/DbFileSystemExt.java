package com.esofthead.mycollab.module.ecm;

import org.apache.jackrabbit.core.fs.db.DbFileSystem;
import org.apache.jackrabbit.core.fs.db.DerbyFileSystem;

import com.esofthead.mycollab.common.ApplicationProperties;

public class DbFileSystemExt extends DbFileSystem {
	DerbyFileSystem a;
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
