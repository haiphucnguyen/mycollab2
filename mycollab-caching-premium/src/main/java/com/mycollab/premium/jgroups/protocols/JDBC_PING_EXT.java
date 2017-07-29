package com.mycollab.premium.jgroups.protocols;

import org.jgroups.conf.ClassConfigurator;
import org.jgroups.protocols.JDBC_PING;

public class JDBC_PING_EXT extends JDBC_PING {

	static {
		ClassConfigurator.add((short) 2048, JDBC_PING_EXT.class);
	}

	@Override
	public void init() throws Exception {
		this.datasource_jndi_name = "java:comp/env/jdbc/mycollabdatasource";
		this.initialize_sql = " CREATE TABLE IF NOT EXISTS JGROUPSPING (own_addr varchar(200) NOT NULL, "
				+ "cluster_name varchar(200) NOT NULL,"
				+ "ping_data varbinary(5000) DEFAULT NULL, "
				+ "PRIMARY KEY (own_addr, cluster_name) ) "
				+ "ENGINE=InnoDB DEFAULT CHARSET=latin1;";
		this.id = 2048;
		super.init();
	}
}
