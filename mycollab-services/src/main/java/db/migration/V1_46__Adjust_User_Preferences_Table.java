package db.migration;

import org.springframework.jdbc.core.JdbcTemplate;

import com.esofthead.mycollab.core.MyCollabException;
import com.googlecode.flyway.core.api.migration.spring.SpringJdbcMigration;

public class V1_46__Adjust_User_Preferences_Table implements
		SpringJdbcMigration {

	public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
		try {
			jdbcTemplate.execute("DELETE FROM s_user_preference WHERE 1=1");
			jdbcTemplate
					.execute("ALTER TABLE `s_user_preference` ADD COLUMN `sAccountId` INT(11) NOT NULL, ADD CONSTRAINT `FK_s_user_preference_2` FOREIGN KEY (`sAccountId` ) REFERENCES `s_account` (`id` ) ON DELETE CASCADE ON UPDATE CASCADE, ADD INDEX `FK_s_user_preference_2_idx` (`sAccountId` ASC) ");
		} catch (Exception e) {
			throw new MyCollabException(e);
		}
	}

}
