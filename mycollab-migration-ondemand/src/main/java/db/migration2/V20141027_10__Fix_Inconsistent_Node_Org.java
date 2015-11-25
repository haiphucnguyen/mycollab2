package db.migration2;

import org.flywaydb.core.api.migration.spring.SpringJdbcMigration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.ContentJcrDao;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

public class V20141027_10__Fix_Inconsistent_Node_Org implements
		SpringJdbcMigration {

	private static Logger LOG = LoggerFactory
			.getLogger(V20141027_10__Fix_Inconsistent_Node_Org.class);

	@Override
	public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
		ContentJcrDao contentJcrDao = ApplicationContextUtil
				.getSpringBean(ContentJcrDao.class);
		Resource resource = contentJcrDao.getResource("avatar");
		LOG.info("Get resource: " + resource.getClass());
		if (resource instanceof Content) {
			contentJcrDao.removeResource("avatar");
		}
	}
}
