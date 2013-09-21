package db.migration;

import org.springframework.jdbc.core.JdbcTemplate;

import com.esofthead.mycollab.module.ecm.dao.ContentJcrDao;
import com.esofthead.mycollab.module.file.service.RawContentService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.googlecode.flyway.core.api.migration.spring.SpringJdbcMigration;

public class V1_56__Adjust_Repo_Structure implements SpringJdbcMigration {

	public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
		RawContentService rawContentService = ApplicationContextUtil.getSpringBean(RawContentService.class);
		ContentJcrDao contentJcrDao = ApplicationContextUtil.getSpringBean(ContentJcrDao.class);
		
		
	}

}
