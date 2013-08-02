package db.migration;

import org.springframework.jdbc.core.JdbcTemplate;

import com.googlecode.flyway.core.api.migration.spring.SpringJdbcMigration;

public class V1_11__AdjustUserAvatar implements SpringJdbcMigration {
	public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
		try {
			jdbcTemplate
					.execute("ALTER TABLE `s_user` ADD COLUMN `avatarId` VARCHAR(90) NOT NULL");
			jdbcTemplate
					.execute("UPDATE s_user SET avatarId = username WHERE username IS NOT NULL");
		} catch (Exception e) {

		}
	}

}
