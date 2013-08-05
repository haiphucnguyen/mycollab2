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

public class V1_32__Migrate_Attachment_Of_Project_Task implements
		SpringJdbcMigration {

	private static Logger log = LoggerFactory
			.getLogger(V1_32__Migrate_Attachment_Of_Project_Task.class);

	public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
		try {

			List<Record> tasks = jdbcTemplate.query(
					"select id, projectid, sAccountId from m_prj_task",
					new RowMapper<Record>() {

						@Override
						public Record mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Record task = new Record();
							task.put("sAccountId", rs.getInt("sAccountId"));
							task.put("id", rs.getInt("id"));
							task.put("projectId", rs.getInt("projectid"));
							return task;
						}
					});

			ResourceService resourceService = ApplicationContextUtil
					.getBean(ResourceService.class);

			for (Record task : tasks) {
				String bugPath = AttachmentUtils.getTaskPath(
						task.getInt("sAccountId"), task.getInt("id"));

				List<Content> contents = resourceService.getContents(bugPath);
				if (contents != null && contents.size() > 0) {
					log.debug("Task {} has exist attachments, move them",
							task.getInt("id"));

					String taskAttachmentPath = AttachmentUtils
							.getProjectTaskAttachmentPath(
									task.getInt("sAccountId"),
									task.getInt("projectId"), task.getInt("id"));

					for (Content content : contents) {
						log.debug("Move content from " + content.getPath()
								+ " to " + taskAttachmentPath + "/"
								+ content.getName());
						resourceService.moveResource(content.getPath(),
								taskAttachmentPath, AppContext.getUsername());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyCollabException(e);
		}
	}

}
