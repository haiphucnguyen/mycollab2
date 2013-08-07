package db.migration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.esofthead.mycollab.core.MyCollabException;
import com.googlecode.flyway.core.api.migration.spring.SpringJdbcMigration;

import db.migration.domain.Record;

public class V1_34__Correct_Table_M_Comment implements SpringJdbcMigration {

	public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
		try {

			List<Record> comments = jdbcTemplate
					.query("(select m_comment.*, m_prj_message.projectid from m_comment,  m_prj_message WHERE m_comment.type=\"Project-Message\" AND m_prj_message.id=m_comment.typeid) UNION (select m_comment.*, m_tracker_bug.projectid from m_comment,  m_tracker_bug WHERE m_comment.type=\"Project-Bug\" AND m_tracker_bug.id=m_comment.typeid) UNION (select m_comment.*, m_prj_milestone.projectid from m_comment,  m_prj_milestone WHERE m_comment.type=\"Project-Milestone\" AND m_prj_milestone.id=m_comment.typeid) UNION (select m_comment.*, m_prj_task_list.projectid from m_comment,  m_prj_task_list WHERE m_comment.type=\"Project-TaskList\" AND m_prj_task_list.id=m_comment.typeid) UNION (select m_comment.*, m_prj_task.projectid from m_comment,  m_prj_task WHERE m_comment.type=\"Project-Task\" AND m_prj_task.id=m_comment.typeid) UNION (select m_comment.*, m_prj_risk.projectid from m_comment,  m_prj_risk WHERE m_comment.type=\"Project-Risk\" AND m_prj_risk.id=m_comment.typeid) UNION (select m_comment.*, m_prj_problem.projectid from m_comment,  m_prj_problem WHERE m_comment.type=\"Project-Problem\" AND m_prj_problem.id=m_comment.typeid)",
							new RowMapper<Record>() {

								@Override
								public Record mapRow(ResultSet rs, int rowNum)
										throws SQLException {
									Record comment = new Record();
									comment.put("id", rs.getInt("id"));
									comment.put("projectId",
											rs.getInt("projectid"));
									return comment;
								}
							});
			for (Record comment : comments) {
				jdbcTemplate
						.execute("update m_comment set m_comment.extraTypeId="
								+ comment.getInt("projectId")
								+ " WHERE m_comment.id=" + comment.getInt("id"));
			}
		} catch (Exception e) {
			new MyCollabException(e);
		}
	}
}
