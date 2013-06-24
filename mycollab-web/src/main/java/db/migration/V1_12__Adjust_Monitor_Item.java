package db.migration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.esofthead.mycollab.core.MyCollabException;
import com.googlecode.flyway.core.api.migration.spring.SpringJdbcMigration;

import db.migration.domain.Record;

public class V1_12__Adjust_Monitor_Item implements SpringJdbcMigration {
	public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
		try {
			jdbcTemplate
					.execute("ALTER TABLE `m_monitor_item` ADD COLUMN `extraTypeId` INT UNSIGNED NULL");

			List<Record> records = jdbcTemplate
					.query("SELECT m_monitor_item.id, m_prj_project.id AS projectId FROM m_monitor_item, m_prj_project, m_prj_task WHERE m_monitor_item.type='Project-Task' AND m_monitor_item.typeid=m_prj_task.id AND m_prj_project.id=m_prj_task.projectid",
							new RowMapper<Record>() {

								@Override
								public Record mapRow(ResultSet rs, int rowNum)
										throws SQLException {
									Record monitorItem = new Record();
									monitorItem.put("id", rs.getInt("id"));
									monitorItem.put("projectid",
											rs.getInt("projectId"));
									return monitorItem;
								}

							});

			updateMonitorItemTable(jdbcTemplate, records);

			records = jdbcTemplate
					.query("SELECT m_monitor_item.id, m_prj_project.id AS projectId FROM m_monitor_item, m_prj_project, m_tracker_bug WHERE m_monitor_item.type='Project-Bug' AND m_monitor_item.typeid=m_tracker_bug.id AND m_prj_project.id=m_tracker_bug.projectid",
							new RowMapper<Record>() {

								@Override
								public Record mapRow(ResultSet rs, int rowNum)
										throws SQLException {
									Record monitorItem = new Record();
									monitorItem.put("id", rs.getInt("id"));
									monitorItem.put("projectid",
											rs.getInt("projectId"));
									return monitorItem;
								}

							});

			updateMonitorItemTable(jdbcTemplate, records);

		} catch (Exception e) {
			throw new MyCollabException(e);
		}
	}

	private void updateMonitorItemTable(JdbcTemplate jdbcTemplate,
			List<Record> records) {
		for (Record record : records) {
			jdbcTemplate.update(
					"UPDATE m_monitor_item SET extraTypeId=? WHERE id=?",
					record.getInt("projectid"), record.getInt("id"));
		}
	}
}
