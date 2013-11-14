package db.migration;

import java.io.StringReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.esofthead.mycollab.core.utils.JsonDeSerializer;
import com.googlecode.flyway.core.api.migration.spring.SpringJdbcMigration;

import db.migration.domain.Record;

public class V10112013_11__Convert_AuditLog_To_Json_Serialize implements
		SpringJdbcMigration {

	private static Logger log = LoggerFactory
			.getLogger(V10112013_11__Convert_AuditLog_To_Json_Serialize.class);

	public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
		List<Record> records = jdbcTemplate.query(
				"select id, changeset from m_audit_log",
				new RowMapper<Record>() {
					public Record mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Record changeset = new Record();
						changeset.put("id", rs.getInt("id"));
						changeset.put("changeset", rs.getString("changeset"));
						return changeset;
					}
				});

		for (Record record : records) {
			String xmlVal = record.getString("changeset");
			List<AuditChangeItem> changeItems = parseChangeItems(xmlVal);

			String jsonVal = JsonDeSerializer.toJson(changeItems);
			jdbcTemplate.update(
					"update m_audit_log set changeset = ? where id = ?",
					jsonVal, record.getInt("id"));
		}
	}

	private List<AuditChangeItem> parseChangeItems(String xmlVal) {
		List<AuditChangeItem> items = new ArrayList<AuditChangeItem>();
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		builderFactory.setIgnoringComments(true);
		builderFactory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
			Document document = docBuilder.parse(new InputSource(
					new StringReader(xmlVal)));
			NodeList changeElements = document
					.getElementsByTagName("changelog");

			for (int i = 0; i < changeElements.getLength(); i++) {
				Element element = (Element) changeElements.item(i);
				AuditChangeItem changeItem = new AuditChangeItem();
				changeItem.setField(element.getAttribute("field"));
				changeItem.setNewvalue(element.getAttribute("newvalue"));
				changeItem.setOldvalue(element.getAttribute("oldvalue"));
				items.add(changeItem);
			}
		} catch (Exception e) {
			log.error("Error while parse change log item", e);
		}

		return items;
	}

	private static class AuditChangeItem {
		private String field;

		private String oldvalue;

		private String newvalue;

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public String getOldvalue() {
			return oldvalue;
		}

		public void setOldvalue(String oldvalue) {
			this.oldvalue = oldvalue;
		}

		public String getNewvalue() {
			return newvalue;
		}

		public void setNewvalue(String newvalue) {
			this.newvalue = newvalue;
		}
	}

}
