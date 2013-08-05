package db.migration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.web.AppContext;
import com.googlecode.flyway.core.api.migration.spring.SpringJdbcMigration;

import db.migration.domain.Record;

public class V1_31__Migrate_Attachment_Of_Project_Bug implements
		SpringJdbcMigration {

	private static Logger log = LoggerFactory
			.getLogger(V1_31__Migrate_Attachment_Of_Project_Bug.class);

	public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
		try {

			List<Record> bugs = jdbcTemplate.query(
					"select id, projectid, sAccountId from m_tracker_bug",
					new RowMapper<Record>() {

						@Override
						public Record mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Record bug = new Record();
							bug.put("sAccountId", rs.getInt("sAccountId"));
							bug.put("id", rs.getInt("id"));
							bug.put("projectId", rs.getInt("projectid"));
							return bug;
						}
					});

			ResourceService resourceService = ApplicationContextUtil
					.getBean(ResourceService.class);

			for (Record bug : bugs) {
				String bugPath = AttachmentUtils.getBugPath(
						bug.getInt("sAccountId"), bug.getInt("id"));

				List<Content> contents = resourceService.getContents(bugPath);
				if (contents != null && contents.size() > 0) {
					log.debug("Bug {} has exist attachments, move them",
							bug.getInt("id"));

					String bugAttachmentPath = AttachmentUtils
							.getProjectBugAttachmentPath(
									bug.getInt("sAccountId"),
									bug.getInt("projectId"), bug.getInt("id"));

					for (Content content : contents) {
						log.debug("Move content from " + content.getPath()
								+ " to " + bugAttachmentPath + "/"
								+ content.getName());
						resourceService.moveResource(content.getPath(),
								bugAttachmentPath, AppContext.getUsername());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyCollabException(e);
		}
	}

}
