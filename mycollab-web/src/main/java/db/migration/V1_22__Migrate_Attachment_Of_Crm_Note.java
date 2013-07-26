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

public class V1_22__Migrate_Attachment_Of_Crm_Note implements
		SpringJdbcMigration {

	private static Logger log = LoggerFactory
			.getLogger(V1_22__Migrate_Attachment_Of_Crm_Note.class);

	public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
		try {

			List<Record> notes = jdbcTemplate.query("select * from m_crm_note",
					new RowMapper<Record>() {

						@Override
						public Record mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Record note = new Record();
							note.put("sAccountId", rs.getInt("sAccountId"));
							note.put("id", rs.getInt("id"));
							return note;
						}
					});

			ResourceService resourceService = ApplicationContextUtil
					.getBean(ResourceService.class);

			for (Record note : notes) {
				String crmNotePath = AttachmentUtils.getCrmNotePath(
						note.getInt("sAccountId"), note.getInt("id"));
				List<Content> contents = resourceService
						.getContents(crmNotePath);
				if (contents != null && contents.size() > 0) {
					log.debug("Note {} has exist attachments, move them",
							note.getInt("id"));

					String crmNoteAttachmentPath = AttachmentUtils
							.getCrmNoteAttachmentPath(
									note.getInt("sAccountId"),
									note.getInt("id"));
					for (Content content : contents) {
						log.debug("Move content from " + content.getPath()
								+ " to " + crmNoteAttachmentPath + "/"
								+ content.getName());
						resourceService.moveResource(content.getPath(),
								crmNoteAttachmentPath);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyCollabException(e);
		}
	}
}
