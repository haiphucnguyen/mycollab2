package com.esofthead.util.sqldump;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.db.sqldump.conf.DbConfiguration;
import com.esofthead.util.sqldump.data.ISqlEntity;

public class DataAdapter {

	private static final Logger log = LoggerFactory
			.getLogger(DataAdapter.class);

	private BasicDataSource datasource;

	public DataAdapter(DbConfiguration configuration) {
		datasource = new BasicDataSource();
		datasource.setUsername(configuration.getUserName());
		datasource.setPassword(configuration.getPassword());
		datasource.setUrl(configuration.getUrl());
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		datasource.setLogAbandoned(true);
	}

	public final List<Object> getData(String methodName,
			Class<?>[] parameterTypes, Object[] arguments, ISqlParser parser)
			throws Exception {
		Connection con = datasource.getConnection();

		DatabaseMetaData metaData = con.getMetaData();
		List<Object> lsResult = new LinkedList<Object>();

		Method method = metaData.getClass().getMethod(methodName,
				parameterTypes);
		if (null != method) {
			ResultSet rs = (ResultSet) method.invoke(metaData, arguments);
			if (null != rs) {
				while (rs.next()) {
					Object obj = parser.parse(rs);
					if (null != obj)
						lsResult.add(obj);
				}
			}
		}

		con.close();
		return lsResult;
	}

	public final List<ISqlEntity> getData(String query, ISqlParser parser)
			throws Exception {
		log.debug("Get data from query: " + query);
		Connection con = datasource.getConnection();
		List<ISqlEntity> lsResult = new LinkedList<ISqlEntity>();

		Statement stmt = (Statement) con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		log.debug("Result set: " + rs);
		while (rs.next()) {
			ISqlEntity obj = parser.parse(rs);
			log.debug("Add row data " + obj);
			if (null != obj)
				lsResult.add(obj);
		}

		stmt.close();
		con.close();
		log.debug("Connection is closed: " + con.isClosed());
		return lsResult;
	}

	public final Object getSingleResult(String query, ISqlParser parser)
			throws Exception {
		Connection con = datasource.getConnection();

		Statement stmt = (Statement) con.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		Object result = null;

		if (rs.next()) {
			if (parser != null) {
				result = parser.parse(rs);
			} else {
				result = rs.getObject(1);
			}
		}

		stmt.close();
		con.close();
		return result;
	}

	public final Object getSingleResult(String query) throws Exception {

		return getSingleResult(query, null);
	}
}
