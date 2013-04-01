package com.esofthead.mybatis.plugin.ext;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

@MappedJdbcTypes(JdbcType.TIMESTAMP)
public class DateTypeHandler extends BaseTypeHandler<Date> {

	// static {
	// TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	// }

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			Date parameter, JdbcType jdbcType) throws SQLException {
		ps.setTimestamp(i, new Timestamp((parameter).getTime()));
	}

	@Override
	public Date getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		Timestamp sqlTimestamp = rs.getTimestamp(columnName);
		if (sqlTimestamp != null) {
			return new Date(sqlTimestamp.getTime());
		}
		return null;
	}

	@Override
	public Date getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		Timestamp sqlTimestamp = rs.getTimestamp(columnIndex);
		if (sqlTimestamp != null) {
			return new Date(sqlTimestamp.getTime());
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

	private static void convertDate(Date date1) throws ParseException {
		SimpleDateFormat sdfFormatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String dateStr = sdfFormatter.format(date1);
		System.out.println(dateStr);
		SimpleDateFormat sdfParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdfParser.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date utcDate = sdfParser.parse(dateStr);
		System.out.println(sdfFormatter.format(utcDate));
	}

	public static void main(String[] args) throws ParseException {
		Date d = new GregorianCalendar(2013, 3, 1, 17, 57, 34).getTime();
		convertDate(d);
	}
}
