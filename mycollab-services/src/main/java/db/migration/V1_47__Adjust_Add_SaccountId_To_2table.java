package db.migration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.esofthead.mycollab.core.MyCollabException;
import com.googlecode.flyway.core.api.migration.spring.SpringJdbcMigration;

import db.migration.domain.Record;

public class V1_47__Adjust_Add_SaccountId_To_2table implements
		SpringJdbcMigration {

	public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
		try {
			adjustAddSaccountIdtoPRJMember(jdbcTemplate);
			adjustAddSaccountIdtoMonitorItem(jdbcTemplate);
		} catch (Exception e) {
			throw new MyCollabException(e);
		}
	}

	private void adjustAddSaccountIdtoPRJMember(JdbcTemplate jdbcTemplate) {
		jdbcTemplate
				.execute("ALTER TABLE `mycollab_live`.`m_prj_member` ADD COLUMN `sAccountId` INT(11) NULL DEFAULT 0  AFTER `status`");
		List<Record> messages = jdbcTemplate
				.query("select m_prj_member.id as id ,Project.sAccountId as sAccountId from m_prj_member JOIN m_prj_project as Project ON Project.id = m_prj_member.projectId",
						new RowMapper<Record>() {

							@Override
							public Record mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								Record message = new Record();
								message.put("id", rs.getInt("id"));
								message.put("sAccountId",
										rs.getInt("sAccountId"));
								return message;
							}
						});
		for (Record message : messages) {
			String sql = "update m_prj_member set m_prj_member.sAccountId = "
					+ message.getInt("sAccountId")
					+ " where m_prj_member.id = " + message.getInt("id");
			jdbcTemplate.execute(sql);
		}
		jdbcTemplate
				.execute("ALTER TABLE `mycollab_live`.`m_prj_member` ADD CONSTRAINT `sAccountId` FOREIGN KEY (`sAccountId` ) REFERENCES `mycollab_live`.`s_account` (`id` ) ON DELETE NO ACTION ON UPDATE NO ACTION, ADD INDEX `sAccountId_idx` (`sAccountId` ASC)");
	}

	private void adjustAddSaccountIdtoMonitorItem(JdbcTemplate jdbcTemplate) {
		jdbcTemplate
				.execute("ALTER TABLE `mycollab_live`.`m_monitor_item` ADD COLUMN `sAccountId` INT(11) NULL DEFAULT 0  AFTER `extraTypeId`");
		List<Record> messages = jdbcTemplate
				.query("select m_monitor_item.id as id , Bug.sAccountId as sAccountId"
						+ " from m_monitor_item JOIN m_tracker_bug as Bug ON "
						+ " m_monitor_item.type=\"Project-Bug\" and m_monitor_item.typeid = Bug.id"
						+ " UNION "
						+ " select m_monitor_item.id as id , Task.sAccountId as sAccountId"
						+ " from m_monitor_item JOIN m_prj_task as Task ON"
						+ " m_monitor_item.type=\"Project-Task\" and m_monitor_item.typeid = Task.id",
						new RowMapper<Record>() {
							@Override
							public Record mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								Record message = new Record();
								message.put("id", rs.getInt("id"));
								message.put("sAccountId",
										rs.getInt("sAccountId"));
								return message;
							}
						});
		for (Record message : messages) {
			String sql = "update m_monitor_item set m_monitor_item.sAccountId = "
					+ message.getInt("sAccountId")
					+ " where m_monitor_item.id = " + message.getInt("id");
			jdbcTemplate.execute(sql);
		}
		jdbcTemplate.execute("ALTER TABLE `mycollab_live`.`m_monitor_item` "
				+ "ADD CONSTRAINT `sAccounId` "
				+ "FOREIGN KEY (`sAccountId` ) "
				+ "REFERENCES `mycollab_live`.`s_account` (`id` ) "
				+ "ON DELETE NO ACTION " + "ON UPDATE NO ACTION "
				+ ", ADD INDEX `sAccounId_idx` (`sAccountId` ASC)");

	}
}
