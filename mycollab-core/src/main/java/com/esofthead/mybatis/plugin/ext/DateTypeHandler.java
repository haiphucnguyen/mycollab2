package com.esofthead.mybatis.plugin.ext;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

@MappedJdbcTypes(JdbcType.TIMESTAMP)
public class DateTypeHandler extends BaseTypeHandler<Date> {

	private static DateTimeZone utcZone = DateTimeZone.UTC;

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			Date parameter, JdbcType jdbcType) throws SQLException {
		DateTime dt = new DateTime(parameter);
		dt.withZone(utcZone);
		ps.setTimestamp(i, new Timestamp(dt.getMillis()));
	}

	@Override
	public Date getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		Timestamp sqlTimestamp = rs.getTimestamp(columnName);
		if (sqlTimestamp != null) {
			return convertTimeToCorrectTimezone(sqlTimestamp.getTime());
		}
		return null;
	}

	private Date convertTimeToCorrectTimezone(long timeInMillis) {
		DateTime dt = new DateTime();
		dt = dt.withMillis(DateTimeZone.getDefault().getOffset(timeInMillis)
				+ timeInMillis);
		dt = dt.withZone(utcZone);
		Date date = dt.toDate();
		return date;
	}

	@Override
	public Date getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		Timestamp sqlTimestamp = rs.getTimestamp(columnIndex);
		if (sqlTimestamp != null) {
			return convertTimeToCorrectTimezone(sqlTimestamp.getTime());
		}
		return null;
	}

	@Override
	public Date getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		Timestamp sqlTimestamp = cs.getTimestamp(columnIndex);
		if (sqlTimestamp != null) {
			return new Date(sqlTimestamp.getTime());
		}
		return null;
	}

	public static void main(String[] args) throws ParseException {
		DateTime dt = new DateTime();
		System.out.println(DateTimeZone.getDefault().getOffset(1382573312000L)
				+ "---" + DateTimeZone.getDefault().getOffset(1382573312000L)
				+ 1382573312000L);
		dt = dt.withMillis(DateTimeZone.getDefault().getOffset(1382573312000L) + 1382573312000L);
		System.out.println(dt);
	}
}
