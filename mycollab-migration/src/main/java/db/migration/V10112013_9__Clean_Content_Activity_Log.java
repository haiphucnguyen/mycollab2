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

import org.springframework.jdbc.core.JdbcTemplate;

import com.googlecode.flyway.core.api.migration.spring.SpringJdbcMigration;

public class V10112013_9__Clean_Content_Activity_Log implements
		SpringJdbcMigration {

	public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
		jdbcTemplate.execute("DELETE FROM m_ecm_activity_log WHERE id>0");
	}

}
