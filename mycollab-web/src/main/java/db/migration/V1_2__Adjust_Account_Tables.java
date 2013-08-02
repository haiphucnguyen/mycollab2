package db.migration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.esofthead.mycollab.module.billing.RegisterSourceConstants;
import com.esofthead.mycollab.module.billing.RegisterStatusConstants;
import com.googlecode.flyway.core.api.migration.spring.SpringJdbcMigration;

import db.migration.domain.Record;

public class V1_2__Adjust_Account_Tables implements SpringJdbcMigration {
	public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
		try {
			List<Record> users = jdbcTemplate.query("select * from s_user",
					new RowMapper<Record>() {

						@Override
						public Record mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Record user = new Record();
							user.put("accountId", rs.getInt("accountId"));
							user.put("username", rs.getString("username"));
							user.put("roleid", rs.getInt("roleid"));
							user.put("isAdmin", rs.getBoolean("isAdmin"));
							return user;
						}

					});

			for (Record user : users) {
				boolean isAdmin = (user.getBoolean("isAdmin") == null) ? false
						: user.getBoolean("isAdmin");
				Object roleId = (user.getInt("roleid") == null || user
						.getInt("roleid") == 0) ? null : user.getInt("roleid");
				Date currentTime = new GregorianCalendar().getTime();

				jdbcTemplate
						.update("insert into s_user_account (username, accountId, isAdmin, isAccountOwner, roleId, registeredTime, registerStatus, lastAccessedTime, registrationSource ) values (?,?,?,?,?,?,?,?,?)",
								user.getString("username"),
								user.getInt("accountId"), isAdmin, isAdmin,
								roleId, currentTime,
								RegisterStatusConstants.ACTIVE, currentTime,
								RegisterSourceConstants.WEB);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
