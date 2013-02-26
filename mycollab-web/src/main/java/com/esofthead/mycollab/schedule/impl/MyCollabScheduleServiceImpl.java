/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.schedule.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.common.dao.ReportBugIssueMapper;
import com.esofthead.mycollab.common.domain.RelayEmailWithBLOBs;
import com.esofthead.mycollab.common.domain.ReportBugIssueExample;
import com.esofthead.mycollab.common.domain.ReportBugIssueWithBLOBs;
import com.esofthead.mycollab.common.logging.MyBatisFactory;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.mail.service.MailRelayService;
import com.esofthead.mycollab.module.mail.service.SystemMailService;
import com.esofthead.mycollab.schedule.MyCollabScheduleService;
import com.esofthead.mycollab.schedule.ScheduleConfig;
import com.thoughtworks.xstream.XStream;

/**
 * 
 * @author haiphucnguyen
 */
@Service
public class MyCollabScheduleServiceImpl implements MyCollabScheduleService {

	private static final Logger log = LoggerFactory
			.getLogger(MyCollabScheduleServiceImpl.class);

	@Autowired
	private SystemMailService mailService;

	@Autowired
	private MailRelayService mailRelayService;

	@Override
	@Scheduled(fixedDelay = ScheduleConfig.RUN_EMAIL_RELAY_INTERVAL)
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

				TemplateGenerator templateGenerator = new TemplateGenerator(
						"My Collab Error Report",
						"templates/email/errorReport.mt");
				templateGenerator.putVariable("issueCol", listIssues);
				mailService.sendHTMLMail("mail@esofthead.com", "Error Agent",
						new String[] { ApplicationProperties
								.getSendErrorEmail() },
						new String[] { ApplicationProperties
								.getSendErrorEmail() }, templateGenerator
								.generateSubjectContent(), templateGenerator
								.generateBodyContent());
			}
		} finally {
			session.close();
		}
	}

	@Override
	@Scheduled(fixedDelay = 60000)
	public void sendRelayEmails() {
		List<RelayEmailWithBLOBs> relayEmails = mailRelayService
				.getRelayEmails();
		mailRelayService.cleanEmails();
		for (RelayEmailWithBLOBs relayEmail : relayEmails) {
			String recipientVal = relayEmail.getRecipients();
			XStream xstream = new XStream();
			String[][] recipientArr = (String[][]) xstream
					.fromXML(recipientVal);
			try {
				mailService.sendHTMLMail(relayEmail.getFromemail(),
						relayEmail.getFromemail(), recipientArr[0],
						recipientArr[1], relayEmail.getSubject(),
						relayEmail.getBodycontent());
			} catch (Exception e) {
				log.error("Error when send relay email", e);
			}
		}
	}
}
