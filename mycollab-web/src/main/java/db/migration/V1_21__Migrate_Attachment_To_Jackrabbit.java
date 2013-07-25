package db.migration;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.module.ecm.dao.ContentJcrDao;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.file.service.RawContentService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.googlecode.flyway.core.api.migration.spring.SpringJdbcMigration;

import db.migration.domain.Record;

public class V1_21__Migrate_Attachment_To_Jackrabbit implements
		SpringJdbcMigration {

	private static Logger log = LoggerFactory
			.getLogger(V1_21__Migrate_Attachment_To_Jackrabbit.class);

	public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
		try {
			List<Record> records = jdbcTemplate
					.query("(SELECT m_attachment.id, m_attachment.type, m_attachment.documentpath, m_attachment.typeid, m_comment.sAccountId FROM m_attachment, m_comment WHERE m_attachment.type=\"common-comment\" AND m_attachment.typeid=m_comment.id) UNION (SELECT m_attachment.id, m_attachment.type, m_attachment.documentpath, m_attachment.typeid, m_prj_task.sAccountId FROM m_attachment, m_prj_task WHERE m_attachment.type=\"project-task\" AND m_attachment.typeid=m_prj_task.id) UNION (SELECT m_attachment.id, m_attachment.type, m_attachment.documentpath, m_attachment.typeid, m_tracker_bug.sAccountId FROM m_attachment, m_tracker_bug WHERE m_attachment.type=\"project-bug\" AND m_attachment.typeid=m_tracker_bug.id) UNION (SELECT m_attachment.id, m_attachment.type, m_attachment.documentpath, m_attachment.typeid, m_prj_message.sAccountId FROM m_attachment, m_prj_message WHERE m_attachment.type=\"project-message\" AND m_attachment.typeid=m_prj_message.id) UNION (SELECT m_attachment.id, m_attachment.type, m_attachment.documentpath, m_attachment.typeid, m_crm_note.sAccountId FROM m_attachment, m_crm_note WHERE m_attachment.type=\"crm-note\" AND m_attachment.typeid=m_crm_note.id)",
							new RowMapper<Record>() {

								@Override
								public Record mapRow(ResultSet rs, int rowNum)
										throws SQLException {
									Record monitorItem = new Record();
									monitorItem.put("id", rs.getInt("id"));
									monitorItem.put("documentpath",
											rs.getString("documentpath"));
									monitorItem.put("type",
											rs.getString("type"));
									monitorItem.put("typeid",
											rs.getInt("typeid"));
									monitorItem.put("sAccountId",
											rs.getInt("sAccountId"));
									return monitorItem;
								}

							});

			ContentJcrDao contentJrcDao = ApplicationContextUtil
					.getBean(ContentJcrDao.class);
			RawContentService rawContentService = ApplicationContextUtil
					.getBean(RawContentService.class);

			log.debug("Service {}{}", contentJrcDao, rawContentService);

			for (Record record : records) {
				log.debug("Start migrate attachment {}",
						BeanUtility.printBeanObj(record));
				String newContentPath = record.getInt("sAccountId")
						+ "/.attachments/" + record.getString("documentpath");
				String oldContentPath = record.getInt("sAccountId") + "/"
						+ record.getString("documentpath");
				int contentSize = 0;
				try {
					InputStream contentStream = rawContentService
							.getContent(oldContentPath);
					contentSize = contentStream.available();
					rawContentService.moveContent(oldContentPath,
							newContentPath);

				} catch (Exception e) {
					e.printStackTrace();
				}

				// save content
				Content content = new Content();
				content.setPath(newContentPath);
				content.setSize(Double.parseDouble(contentSize + ""));
				content.setTitle("");
				contentJrcDao.saveContent(content, "");
			}
			// jdbcTemplate.execute("drop table `m_attachment`");
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyCollabException(e);
		}
	}
}
