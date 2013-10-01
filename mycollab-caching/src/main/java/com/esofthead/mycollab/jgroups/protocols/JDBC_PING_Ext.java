package com.esofthead.mycollab.jgroups.protocols;

import org.jgroups.protocols.JDBC_PING;

import com.esofthead.mycollab.configuration.DatabaseConfiguration;
import com.esofthead.mycollab.configuration.SiteConfiguration;

public class JDBC_PING_Ext extends JDBC_PING {

	@Override
	public void init() throws Exception {
		DatabaseConfiguration dbConfiguration = SiteConfiguration
				.getDatabaseConfiguration();
		this.connection_driver = dbConfiguration.getDriverClass();
		this.connection_url = dbConfiguration.getDbUrl();
		this.connection_username = dbConfiguration.getUser();
		this.connection_password = dbConfiguration.getPassword();
		super.init();
	}
}
