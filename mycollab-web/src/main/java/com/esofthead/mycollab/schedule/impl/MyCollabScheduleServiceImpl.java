/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.schedule.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.dao.ReportBugIssueMapper;
import com.esofthead.mycollab.common.domain.ReportBugIssueExample;
import com.esofthead.mycollab.common.domain.ReportBugIssueWithBLOBs;
import com.esofthead.mycollab.common.logging.MyBatisFactory;
import com.esofthead.mycollab.module.mail.VelocityTemplate;
import com.esofthead.mycollab.module.mail.service.MailService;
import com.esofthead.mycollab.schedule.MyCollabScheduleService;

/**
 * 
 * @author haiphucnguyen
 */
@Service
public class MyCollabScheduleServiceImpl implements MyCollabScheduleService {

	@Autowired
	private MailService mailService;

	@Override
	@Scheduled(fixedDelay = 600000)
	public void sendErrorReports() {
		SqlSession session = MyBatisFactory.build().openSession();
		try {

			ReportBugIssueMapper mapper = session
					.getMapper(ReportBugIssueMapper.class);
			List<ReportBugIssueWithBLOBs> listIssues = mapper
					.selectByExampleWithBLOBs(new ReportBugIssueExample());

			if (!listIssues.isEmpty()) {
				// Remove all issues in table
				ReportBugIssueExample ex = new ReportBugIssueExample();
				ex.createCriteria().andIdGreaterThan(0);
				mapper.deleteByExample(ex);
				session.commit(true);

				VelocityContext context = new VelocityContext();
				context.put("issueCol", listIssues);
				String msg = VelocityTemplate.format(context,
						"templates/email/errorReport.mt");
				mailService.sendHTMLMail("mail@esofthead.com", "Error Agent",
						new String[] { "hainguyen@esofthead.com" },
						new String[] { "Hai Nguyen" },
						"My Collab Error Report", msg);
			}
		} finally {
			session.close();
		}
	}

	public static void main(String[] args) {
		new MyCollabScheduleServiceImpl().sendErrorReports();
	}
}
