package db.migration;

import org.springframework.jdbc.core.JdbcTemplate;

import com.esofthead.mycollab.core.MyCollabException;
import com.googlecode.flyway.core.api.migration.spring.SpringJdbcMigration;

public class V1_37__Correct_Bug_Status2  implements SpringJdbcMigration {

	public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
		try {
			jdbcTemplate.execute("UPDATE m_tracker_bug SET status=\"Verified\" WHERE status=\"Close\"");
		} catch (Exception e) {
			throw new MyCollabException(e);
		}
	}

}
