/**
 * This file is part of mycollab-migration.
 *
 * mycollab-migration is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-migration is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-migration.  If not, see <http://www.gnu.org/licenses/>.
 */
package db.migration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.esofthead.mycollab.core.utils.JsonDeSerializer;
import com.esofthead.mycollab.security.PermissionMap;
import com.googlecode.flyway.core.api.migration.spring.SpringJdbcMigration;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import db.migration.domain.Record;

public class V10112013_8__Remove_XStream_Serialize implements
		SpringJdbcMigration {

	private static Logger log = LoggerFactory
			.getLogger(V10112013_8__Remove_XStream_Serialize.class);

	public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
		log.debug("Update value of s_role_permission table");
		XStream xstream = new XStream(new StaxDriver());

		List<Record> records = jdbcTemplate.query(
				"select id, roleVal from s_role_permission",
				new RowMapper<Record>() {
					public Record mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Record role = new Record();
						role.put("id", rs.getInt("id"));
						role.put("roleVal", rs.getString("roleVal"));
						return role;
					}
				});
		for (Record record : records) {
			String roleVal = record.getString("roleVal");
			Integer roleId = record.getInt("id");

			PermissionMap permissionMap = (PermissionMap) xstream
					.fromXML(roleVal);
			String jsonValue = JsonDeSerializer.toJson(permissionMap);
			jdbcTemplate.update(
					"update s_role_permission set roleVal = ? where id = ?",
					jsonValue, roleId);
		}

		log.debug("Update value of m_prj_role_permission table");

		records = jdbcTemplate.query(
				"select id, roleVal from m_prj_role_permission",
				new RowMapper<Record>() {
					public Record mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Record role = new Record();
						role.put("id", rs.getInt("id"));
						role.put("roleVal", rs.getString("roleVal"));
						return role;
					}
				});
		for (Record record : records) {
			String roleVal = record.getString("roleVal");
			Integer roleId = record.getInt("id");

			PermissionMap permissionMap = (PermissionMap) xstream
					.fromXML(roleVal);
			String jsonValue = JsonDeSerializer.toJson(permissionMap);

			jdbcTemplate
					.update("update m_prj_role_permission set roleVal = ? where id = ?",
							jsonValue, roleId);
		}
	}
}
