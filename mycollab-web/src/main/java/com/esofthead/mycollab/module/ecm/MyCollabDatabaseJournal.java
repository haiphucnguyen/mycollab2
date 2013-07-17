package com.esofthead.mycollab.module.ecm;

import org.apache.jackrabbit.core.journal.DatabaseJournal;

import com.esofthead.mycollab.common.ApplicationProperties;

public class MyCollabDatabaseJournal extends DatabaseJournal {

	public MyCollabDatabaseJournal() {
		this.setDriver(ApplicationProperties
				.getString(ApplicationProperties.DB_DRIVER_CLASS));
		this.setUrl(ApplicationProperties
				.getString(ApplicationProperties.DB_URL));
		this.setUser(ApplicationProperties
				.getString(ApplicationProperties.DB_USERNAME));
		this.setPassword(ApplicationProperties
				.getString(ApplicationProperties.DB_PASSWORD));
		this.setSchemaObjectPrefix("ecm_journal");
		this.setDatabaseType("mysql");
	}
}
