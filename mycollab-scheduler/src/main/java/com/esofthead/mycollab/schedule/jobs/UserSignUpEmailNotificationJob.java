package com.esofthead.mycollab.schedule.jobs;

import java.util.Arrays;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.mail.service.ExtMailService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.schedule.email.GenericLinkGenerator;

@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class UserSignUpEmailNotificationJob extends QuartzJobBean {
	private static Logger log = LoggerFactory
			.getLogger(UserSignUpEmailNotificationJob.class);

	@Autowired
	private UserService userService;

	@Autowired
	private ExtMailService extMailService;

	private static final String userSignUpEmailNotificationTemplate = "templates/email/billing/confirmUserSignUpNotification.mt";

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		UserSearchCriteria criteria = new UserSearchCriteria();
		criteria.setIsVerifiedEmail(new SetSearchField<Boolean>(new Boolean[] {
				null, false }));
		criteria.setSaccountid(null);
		List<SimpleUser> lstSimpleUsers = userService
				.findPagableListByCriteria(new SearchRequest<UserSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));
		if (lstSimpleUsers != null && lstSimpleUsers.size() > 0) {
			for (SimpleUser user : lstSimpleUsers) {
				TemplateGenerator templateGenerator = new TemplateGenerator(
						"Please confirm your email",
						userSignUpEmailNotificationTemplate);
				templateGenerator.putVariable("user", user);

				String linkComfirm = GenericLinkGenerator
						.generateSiteUrlByAccountId(user.getAccountId())
						+ "user/confirm_signup/";
				templateGenerator.putVariable("linkConfirm", linkComfirm);

				String linkCancelSignup = GenericLinkGenerator
						.generateSiteUrlByAccountId(user.getAccountId())
						+ "user/cancel_signup/"
						+ UrlEncodeDecoder.encode(user.getUsername() + "/"
								+ user.getAccountId());
				templateGenerator.putVariable("linkCancelSignUp",
						linkCancelSignup);

				extMailService.sendHTMLMail("noreply@mycollab.com",
						"noreply@mycollab.com", Arrays
								.asList(new MailRecipientField(user.getEmail(),
										user.getDisplayName())), null, null,
						templateGenerator.generateSubjectContent(),
						templateGenerator.generateBodyContent(), null);
			}
		}
	}
}
