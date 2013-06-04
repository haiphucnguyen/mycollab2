package com.esofthead.mycollab.module.ecm;

import org.apache.jackrabbit.core.persistence.PMContext;
import org.apache.jackrabbit.core.persistence.pool.BundleDbPersistenceManager;

import com.esofthead.mycollab.common.ApplicationProperties;

public class BundleDbPersistenceManagerExt extends BundleDbPersistenceManager {

	/**
	 * {@inheritDoc}
	 */
	public void init(PMContext context) throws Exception {
		// init default values
		if (getDriver() == null) {
			setDriver("com.mysql.jdbc.Driver");
		}

		setUrl(ApplicationProperties.getString(ApplicationProperties.DB_URL));
		setUser(ApplicationProperties
				.getString(ApplicationProperties.DB_USERNAME));
		setPassword(ApplicationProperties
				.getString(ApplicationProperties.DB_PASSWORD));
		setSchemaObjectPrefix("ecm_p_");

		if (getDatabaseType() == null) {
			setDatabaseType("mysql");
		}
		super.init(context);
	}
}
