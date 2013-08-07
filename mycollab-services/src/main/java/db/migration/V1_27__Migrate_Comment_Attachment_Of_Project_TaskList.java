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
import com.googlecode.flyway.core.api.migration.spring.SpringJdbcMigration;

import db.migration.domain.Record;

public class V1_27__Migrate_Comment_Attachment_Of_Project_TaskList implements
		SpringJdbcMigration {

	private static Logger log = LoggerFactory
			.getLogger(V1_27__Migrate_Comment_Attachment_Of_Project_TaskList.class);

	public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
		try {

			List<Record> comments = jdbcTemplate
					.query("select m_comment.*, m_prj_task_list.projectid from m_comment,  m_prj_task_list WHERE m_comment.type=\"Project-TaskList\" AND m_prj_task_list.id=m_comment.typeid",
							new RowMapper<Record>() {

								@Override
								public Record mapRow(ResultSet rs, int rowNum)
										throws SQLException {
									Record comment = new Record();
									comment.put("sAccountId",
											rs.getInt("sAccountId"));
									comment.put("id", rs.getInt("id"));
									comment.put("typeId", rs.getInt("typeId"));
									comment.put("type", rs.getString("type"));
									comment.put("projectId",
											rs.getInt("projectid"));
									return comment;
								}
							});

			ResourceService resourceService = ApplicationContextUtil
					.getBean(ResourceService.class);

			for (Record comment : comments) {
				String oldCommentPath = AttachmentUtils.getCommentPath(
						comment.getInt("sAccountId"), comment.getInt("typeId"));
				List<Content> contents = resourceService
						.getContents(oldCommentPath);
				if (contents != null && contents.size() > 0) {
					log.debug("Note {} has exist attachments, move them",
							comment.getInt("id"));

					String messageNewPath = AttachmentUtils
							.getProjectTaskListCommentAttachmentPath(
									comment.getInt("sAccountId"),
									comment.getInt("projectId"),
									comment.getInt("typeId"),
									comment.getInt("id"));
					for (Content content : contents) {
						log.debug("Move resource {} to {}", content.getPath(),
								messageNewPath);
						resourceService.moveResource(content.getPath(),
								messageNewPath, "");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyCollabException(e);
		}
	}

}
