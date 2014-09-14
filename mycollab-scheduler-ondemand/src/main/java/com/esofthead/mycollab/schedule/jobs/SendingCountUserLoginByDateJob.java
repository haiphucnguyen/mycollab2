/**
 * This file is part of mycollab-scheduler.
 *
 * mycollab-scheduler is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-scheduler is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-scheduler.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.schedule.jobs;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.mail.IContentGenerator;
import com.esofthead.mycollab.module.mail.service.ExtMailService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.module.user.service.UserService;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SendingCountUserLoginByDateJob extends GenericQuartzJobBean {
	private static Logger log = LoggerFactory
			.getLogger(SendingCountUserLoginByDateJob.class);

	@Autowired
	private UserService userService;

	@Autowired
	private ExtMailService extMailService;

	@Autowired
	private IContentGenerator contentGenerator;

	static final String COUNT_USER_LOGIN_TEMPLATE = "templates/email/user/countUserLoginByDate.mt";

	@SuppressWarnings("unchecked")
	@Override
	protected void executeJob(JobExecutionContext context)
			throws JobExecutionException {
		UserSearchCriteria criteria = new UserSearchCriteria();
		GregorianCalendar calendar = new GregorianCalendar();
		Date to = calendar.getTime();
		calendar.add(Calendar.DATE, -1);
		Date from = calendar.getTime();
		criteria.setSaccountid(null);
		criteria.setLastAccessTimeRange(from, to);

		List<SimpleUser> lstSimpleUsers = userService
				.findPagableListByCriteria(new SearchRequest<UserSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));
		if (lstSimpleUsers != null && lstSimpleUsers.size() > 0) {
			contentGenerator.putVariable("lstUser", lstSimpleUsers);
			contentGenerator.putVariable("count", lstSimpleUsers.size());

			try {
				extMailService
						.sendHTMLMail(
								SiteConfiguration.getNoReplyEmail(),
								SiteConfiguration.getNoReplyEmail(),
								Arrays.asList(new MailRecipientField(
										"hainguyen@esofthead.com", "Hai Nguyen")),
								null,
								null,
								contentGenerator
										.generateSubjectContent("Today system-logins count"),
								contentGenerator
										.generateBodyContent(COUNT_USER_LOGIN_TEMPLATE),
								null);
			} catch (Exception e) {
				log.error("Error whle generate template", e);
			}
		}
	}
}
