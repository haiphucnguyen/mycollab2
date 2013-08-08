package com.esofthead.mycollab.module.ecm;

import org.apache.jackrabbit.core.journal.DatabaseJournal;

import com.esofthead.mycollab.configuration.DatabaseConfiguration;
import com.esofthead.mycollab.configuration.SiteConfiguration;

public class MyCollabDatabaseJournal extends DatabaseJournal {

	public MyCollabDatabaseJournal() {
		DatabaseConfiguration dbConfiguration = SiteConfiguration
				.getDatabaseConfiguration();
		this.setDriver(dbConfiguration.getDriverClass());
		this.setUrl(dbConfiguration.getDbUrl());
		this.setUser(dbConfiguration.getUser());
		this.setPassword(dbConfiguration.getPassword());
		this.setSchemaObjectPrefix("ecm_journal");
		this.setDatabaseType("mysql");
	}
}
