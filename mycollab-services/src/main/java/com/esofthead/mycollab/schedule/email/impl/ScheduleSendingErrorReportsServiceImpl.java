package com.esofthead.mycollab.schedule.email.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.dao.ReportBugIssueMapper;
import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.common.domain.ReportBugIssueExample;
import com.esofthead.mycollab.common.domain.ReportBugIssueWithBLOBs;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.module.mail.Mailer;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.schedule.email.ScheduleConfig;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

@Service
public class ScheduleSendingErrorReportsServiceImpl {
	@Scheduled(fixedDelay = ScheduleConfig.RUN_EMAIL_RELAY_INTERVAL)
	public void sendErrorReports() {
		ReportBugIssueMapper mapper = ApplicationContextUtil
				.getBean(ReportBugIssueMapper.class);
		List<ReportBugIssueWithBLOBs> listIssues = mapper
				.selectByExampleWithBLOBs(new ReportBugIssueExample());

		if (!listIssues.isEmpty()) {
			TemplateGenerator templateGenerator = new TemplateGenerator(
					"My Collab Error Report", "templates/email/errorReport.mt");
			templateGenerator.putVariable("issueCol", listIssues);
			Mailer mailer = new Mailer(
					SiteConfiguration.getEmailConfiguration());
			mailer.sendHTMLMail("mail@esofthead.com", "Error Agent", Arrays
					.asList(new MailRecipientField(SiteConfiguration
							.getSendErrorEmail(), SiteConfiguration
							.getSendErrorEmail())), null, null,
					templateGenerator.generateSubjectContent(),
					templateGenerator.generateBodyContent(), null);

			// Remove all issues in table
			ReportBugIssueExample ex = new ReportBugIssueExample();
			ex.createCriteria().andIdGreaterThan(0);
			mapper.deleteByExample(ex);
		}
	}

}
