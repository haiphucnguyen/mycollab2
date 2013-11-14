package db.migration;

import org.springframework.jdbc.core.JdbcTemplate;

import com.googlecode.flyway.core.api.migration.spring.SpringJdbcMigration;

public class V10112013_10__Clean_Custom_View_Table implements
		SpringJdbcMigration {

	public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
		jdbcTemplate.execute("DELETE FROM s_table_customize_view WHERE id>0");
	}

}
