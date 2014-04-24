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
package com.esofthead.mycollab.schedule.email.crm.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.common.service.AuditLogService;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.service.CaseService;
import com.esofthead.mycollab.module.crm.service.CrmNotificationSettingService;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.schedule.email.LinkUtils;
import com.esofthead.mycollab.schedule.email.MailContext;
import com.esofthead.mycollab.schedule.email.crm.CaseRelayEmailNotificationAction;
import com.esofthead.mycollab.schedule.email.crm.CrmMailLinkGenerator;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CaseRelayEmailNotificationActionImpl extends
		CrmDefaultSendingRelayEmailAction<SimpleCase> implements
		CaseRelayEmailNotificationAction {

	@Autowired
	private AuditLogService auditLogService;
	@Autowired
	private CaseService caseService;

	@Autowired
	private CrmNotificationSettingService notificationService;

	private final CaseFieldNameMapper mapper;

	public CaseRelayEmailNotificationActionImpl() {
		super(CrmTypeConstants.CASE);
		mapper = new CaseFieldNameMapper();
	}

	protected void setupMailHeaders(SimpleCase simpleCase,
			SimpleRelayEmailNotification emailNotification,
			TemplateGenerator templateGenerator) {

		CrmMailLinkGenerator crmLinkGenerator = new CrmMailLinkGenerator(
				LinkUtils.getSiteUrl(simpleCase.getSaccountid()));

		String summary = simpleCase.getSubject();
		String summaryLink = crmLinkGenerator
				.generateCasePreviewFullLink(simpleCase.getId());

		templateGenerator.putVariable("makeChangeUser",
				emailNotification.getChangeByUserFullName());
		templateGenerator.putVariable("itemType", "case");
		templateGenerator.putVariable("summary", summary);
		templateGenerator.putVariable("summaryLink", summaryLink);
	}

	@Override
	protected TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification, SimpleUser user) {
		SimpleCase simpleCase = caseService.findById(
				emailNotification.getTypeid(),
				emailNotification.getSaccountid());
		if (simpleCase != null) {
			String subject = StringUtils.trim(simpleCase.getSubject(), 100);

			TemplateGenerator templateGenerator = new TemplateGenerator(
					emailNotification.getChangeByUserFullName()
							+ "has created the case \"" + subject + "\"",
					"templates/email/crm/itemCreatedNotifier.mt");
			setupMailHeaders(simpleCase, emailNotification, templateGenerator);

			templateGenerator.putVariable("context",
					new MailContext<SimpleCase>(simpleCase, user));
			templateGenerator.putVariable("mapper", mapper);

			return templateGenerator;
		} else {
			return null;
		}
	}

	@Override
	protected TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification, SimpleUser user) {
		SimpleCase simpleCase = caseService.findById(
				emailNotification.getTypeid(),
				emailNotification.getSaccountid());

		String subject = StringUtils.trim(simpleCase.getSubject(), 100);

		TemplateGenerator templateGenerator = new TemplateGenerator(
				emailNotification.getChangeByUserFullName()
						+ " has updated the case \"" + subject + "\"",
				"templates/email/crm/itemUpdatedNotifier.mt");

		setupMailHeaders(simpleCase, emailNotification, templateGenerator);

		if (emailNotification.getTypeid() != null) {
			SimpleAuditLog auditLog = auditLogService.findLatestLog(
					emailNotification.getTypeid(),
					emailNotification.getSaccountid());

			templateGenerator.putVariable("historyLog", auditLog);

			templateGenerator.putVariable("mapper", mapper);
		}
		return templateGenerator;
	}

	@Override
	protected TemplateGenerator templateGeneratorForCommentAction(
			SimpleRelayEmailNotification emailNotification, SimpleUser user) {
		SimpleCase simpleCase = caseService.findById(
				emailNotification.getTypeid(),
				emailNotification.getSaccountid());

		TemplateGenerator templateGenerator = new TemplateGenerator(
				emailNotification.getChangeByUserFullName()
						+ " has commented on case \""
						+ StringUtils.trim(simpleCase.getSubject(), 100) + "\"",
				"templates/email/crm/itemAddNoteNotifier.mt");

		setupMailHeaders(simpleCase, emailNotification, templateGenerator);

		templateGenerator.putVariable("comment", emailNotification);

		return templateGenerator;
	}

	public class CaseFieldNameMapper {
		private final Map<String, String> fieldNameMap;

		CaseFieldNameMapper() {
			fieldNameMap = new HashMap<String, String>();

			fieldNameMap.put("priority", "Priority");
			fieldNameMap.put("type", "Type");
			fieldNameMap.put("status", "Status");
			fieldNameMap.put("reason", "Reason");
			fieldNameMap.put("accountName", "Account Name");
			fieldNameMap.put("subject", "Subject");
			fieldNameMap.put("phonenumber", "Phone Number");
			fieldNameMap.put("email", "Email");
			fieldNameMap.put("origin", "Origin");
			fieldNameMap.put("assignuser", "Assignee");
			fieldNameMap.put("description", "Description");
			fieldNameMap.put("resolution", "Resolution");
		}

		public boolean hasField(String fieldName) {
			return fieldNameMap.containsKey(fieldName);
		}

		public String getFieldLabel(String fieldName) {
			return fieldNameMap.get(fieldName);
		}
	}

}
