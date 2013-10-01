package com.esofthead.mycollab.jgroups.protocols;

import org.jgroups.conf.ClassConfigurator;
import org.jgroups.protocols.JDBC_PING;

import com.esofthead.mycollab.configuration.DatabaseConfiguration;
import com.esofthead.mycollab.configuration.SiteConfiguration;

public class JDBC_PING_EXT extends JDBC_PING {

	static {
		ClassConfigurator.add((short) 2048, JDBC_PING_EXT.class);
	}

	@Override
	public void init() throws Exception {
		DatabaseConfiguration dbConfiguration = SiteConfiguration
				.getDatabaseConfiguration();
		this.connection_driver = dbConfiguration.getDriverClass();
		this.connection_url = dbConfiguration.getDbUrl();
		this.connection_username = dbConfiguration.getUser();
		this.connection_password = dbConfiguration.getPassword();
		this.initialize_sql = " CREATE TABLE IF NOT EXISTS JGROUPSPING (own_addr varchar(200) NOT NULL, "
				+ "cluster_name varchar(200) NOT NULL,"
				+ "ping_data varbinary(5000) DEFAULT NULL, "
				+ "PRIMARY KEY (own_addr, cluster_name) ) "
				+ "ENGINE=InnoDB DEFAULT CHARSET=latin1;";
		this.id = 2048;
		super.init();
	}
}
