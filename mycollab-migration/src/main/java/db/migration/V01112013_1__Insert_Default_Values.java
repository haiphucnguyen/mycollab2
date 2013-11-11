package db.migration;

import org.springframework.jdbc.core.JdbcTemplate;

import com.esofthead.mycollab.db.migration.InsertQuery;
import com.googlecode.flyway.core.api.migration.spring.SpringJdbcMigration;

public class V01112013_1__Insert_Default_Values implements SpringJdbcMigration {
	public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
		InsertQuery query = new InsertQuery("persons").columns("id", "name", "age")
                .values(1, "foo", 30)
                .values(2, "bar", 23)
                .values(3, "hello", 54)
                .values(4, "world", 19);
	}
}
