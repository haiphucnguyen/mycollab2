package db.migration;

import org.springframework.jdbc.core.JdbcTemplate;

import com.esofthead.mycollab.core.MyCollabException;
import com.googlecode.flyway.core.api.migration.spring.SpringJdbcMigration;

public class V1_36__Correct_Bug_Status implements SpringJdbcMigration {

	public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
		try {

			jdbcTemplate.execute("UPDATE m_tracker_bug SET status=\"Resolved\", resolution=\"Won't Fix\" WHERE status=\"Won't Fix\"");
			jdbcTemplate.execute("UPDATE m_tracker_bug SET status=\"Close\" WHERE status=\"Verified\"");
			jdbcTemplate.execute("UPDATE m_tracker_bug SET status=\"Resolved\" WHERE status=\"Test Pending\"");
		} catch (Exception e) {
			throw new MyCollabException(e);
		}
	}

}
