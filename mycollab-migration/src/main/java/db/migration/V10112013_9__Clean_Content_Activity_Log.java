package db.migration;

import org.springframework.jdbc.core.JdbcTemplate;

import com.googlecode.flyway.core.api.migration.spring.SpringJdbcMigration;

public class V10112013_9__Clean_Content_Activity_Log implements
		SpringJdbcMigration {

	public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
		jdbcTemplate.execute("DELETE FROM m_ecm_activity_log WHERE id>0");
	}

}
