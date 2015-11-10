package db.migration;

import org.flywaydb.core.api.migration.spring.SpringJdbcMigration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
public class V20151109_2__Simplify_Theme_Management implements SpringJdbcMigration {
    @Override
    public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
        jdbcTemplate.execute("ALTER TABLE `s_account_theme` \n" +
                "DROP COLUMN `tabsheetTextSelected`,\n" +
                "DROP COLUMN `tabsheetText`,\n" +
                "DROP COLUMN `tabsheetBgSelected`,\n" +
                "DROP COLUMN `tabsheetBg`;");

        jdbcTemplate.update("UPDATE s_account_theme SET vTabsheetBg=?,vTabsheetBgSelected=?,vTabsheetText=?," +
                "vTabsheetTextSelected=? WHERE isDefault=? ", new Object[]{"1A90CB", "167AAD", "FFFFFF", "FFFFFF", 1});
    }
}
