package db.migration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.googlecode.flyway.core.api.migration.spring.SpringJdbcMigration;

import db.migration.domain.Record;

public class V1_49__Adjust_Project_Item_TimeLogging_Table implements
		SpringJdbcMigration {

	public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
		jdbcTemplate
				.execute("ALTER TABLE `m_prj_time_logging` ADD COLUMN `sAccountId` INT(11) NULL");

		List<Record> timeItems = jdbcTemplate
				.query("select m_prj_time_logging.id, m_prj_project.sAccountId from m_prj_time_logging JOIN m_prj_project ON m_prj_project.id = m_prj_time_logging.projectId",
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

		for (Record timeLoggingItem : timeItems) {
			String sql = "update m_prj_time_logging set m_prj_time_logging.sAccountId = "
					+ timeLoggingItem.getInt("sAccountId")
					+ " where m_prj_time_logging.id = "
					+ timeLoggingItem.getInt("id");
			jdbcTemplate.execute(sql);
		}
		
		jdbcTemplate
				.execute("ALTER TABLE `m_prj_time_logging` CHANGE COLUMN `sAccountId` `sAccountId` INT(11) NOT NULL  , ADD CONSTRAINT `FK_m_prj_time_logging_2` FOREIGN KEY (`sAccountId` ) REFERENCES `s_account` (`id` ) ON DELETE CASCADE ON UPDATE CASCADE");
	}
}
