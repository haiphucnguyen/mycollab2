package com.esofthead.util.sqldump;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class DataAdapter {

	private String userName;
	private String password;
	private String url;

	public DataAdapter(String userName, String password, String url) {
		this.userName = userName;
		this.password = password;
		this.url = url;
	}

	public final List<Object> getData(String methodName,
			Class<?>[] parameterTypes, Object[] arguments, ISqlParser parser)
			throws Exception {

		if (null == userName || null == password || null == url)
			throw new Exception("Context is not initialized");

		Properties prop = new Properties();
		prop.put("user", userName);
		prop.put("password", password);
		Connection con = (Connection) DriverManager.getConnection(url, prop);

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

	public final List<Object> getData(String query, ISqlParser parser)
			throws Exception {
		if (null == userName || null == password || null == url)
			throw new Exception("Context is not initialized");

		Properties prop = new Properties();
		prop.put("user", userName);
		prop.put("password", password);
		Connection con = (Connection) DriverManager.getConnection(url, prop);
		
		List<Object> lsResult = new LinkedList<Object>();
		
		Statement stmt = (Statement) con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		while (rs.next()) {
			Object obj = parser.parse(rs);
			if (null != obj)
				lsResult.add(obj);
		}
		
		con.close();
		return lsResult;
	}

	public final Object getSingleResult(String query, ISqlParser parser)
			throws Exception {
		if (null == userName || null == password || null == url)
			throw new Exception("Context is not initialized");

		Properties prop = new Properties();
		prop.put("user", userName);
		prop.put("password", password);
		Connection con = (Connection) DriverManager.getConnection(url, prop);

		Statement stmt = (Statement) con.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		Object result = null;

		if (rs.next()) {
			result = parser.parse(rs);
		}

		con.close();
		return result;
	}
}
