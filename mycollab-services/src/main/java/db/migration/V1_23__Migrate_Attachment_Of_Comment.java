package db.migration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.esofthead.mycollab.common.CommentTypeConstants;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.googlecode.flyway.core.api.migration.spring.SpringJdbcMigration;

import db.migration.domain.Record;

public class V1_23__Migrate_Attachment_Of_Comment implements
		SpringJdbcMigration {

	private static Logger log = LoggerFactory
			.getLogger(V1_23__Migrate_Attachment_Of_Comment.class);

	public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
		try {

			List<Record> comments = jdbcTemplate
					.query("select * from m_comment where m_comment.type=\"Crm-Note\"",
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
					if (CommentTypeConstants.CRM_NOTE.equals(comment
							.getString("type"))) {

						String commentNewPath = AttachmentUtils
								.getCrmNoteCommentAttachmentPath(
										comment.getInt("sAccountId"),
										comment.getInt("typeId"),
										comment.getInt("id"));
						for (Content content : contents) {
							log.debug("Move resource {} to {}",
									content.getPath(), commentNewPath);
							resourceService.moveResource(content.getPath(),
									commentNewPath, "");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyCollabException(e);
		}
	}
}
