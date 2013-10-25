package com.esofthead.mycollab.schedule.jobs;

import java.util.Arrays;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.dao.ReportBugIssueMapper;
import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.common.domain.ReportBugIssueExample;
import com.esofthead.mycollab.common.domain.ReportBugIssueWithBLOBs;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.module.mail.Mailer;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class SendingErrorReportEmailJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) {
		ReportBugIssueMapper mapper = ApplicationContextUtil
				.getSpringBean(ReportBugIssueMapper.class);
		if (mapper != null) {
			List<ReportBugIssueWithBLOBs> listIssues = mapper
					.selectByExampleWithBLOBs(new ReportBugIssueExample());

			if (!listIssues.isEmpty()) {
				TemplateGenerator templateGenerator = new TemplateGenerator(
						"My Collab Error Report",
						"templates/email/errorReport.mt");
				templateGenerator.putVariable("issueCol", listIssues);
				Mailer mailer = new Mailer(
						SiteConfiguration.getEmailConfiguration());
				mailer.sendHTMLMail("mail@mycollab.com", "Error Agent", Arrays
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

}
