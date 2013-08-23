package com.esofthead.mycollab.module.ecm;

import org.apache.jackrabbit.core.persistence.PMContext;
import org.apache.jackrabbit.core.persistence.pool.BundleDbPersistenceManager;

import com.esofthead.mycollab.configuration.DatabaseConfiguration;
import com.esofthead.mycollab.configuration.SiteConfiguration;

public class BundleDbPersistenceManagerExt extends BundleDbPersistenceManager {

	/**
	 * {@inheritDoc}
	 */
	public void init(PMContext context) throws Exception {
		DatabaseConfiguration dbConfiguration = SiteConfiguration
				.getDatabaseConfiguration();
		this.setDriver(dbConfiguration.getDriverClass());

		setUrl(dbConfiguration.getDbUrl());
		setUser(dbConfiguration.getUser());
		setPassword(dbConfiguration.getPassword());

		if (getSchemaObjectPrefix() == null) {
			setSchemaObjectPrefix("ecm_p_workspace");
		}

		if (getDatabaseType() == null) {
			setDatabaseType("mysql");
		}
		super.init(context);
	}
}
