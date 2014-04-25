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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.common.service.AuditLogService;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.crm.CrmLinkGenerator;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleCall;
import com.esofthead.mycollab.module.crm.service.CallService;
import com.esofthead.mycollab.module.crm.service.CrmNotificationSettingService;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.user.UserLinkUtils;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.schedule.email.ItemFieldMapper;
import com.esofthead.mycollab.schedule.email.LinkUtils;
import com.esofthead.mycollab.schedule.email.MailContext;
import com.esofthead.mycollab.schedule.email.crm.CallRelayEmailNotificationAction;
import com.esofthead.mycollab.schedule.email.format.DateTimeFieldFormat;
import com.esofthead.mycollab.schedule.email.format.FieldFormat;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Img;
import com.hp.gagawa.java.elements.Span;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CallRelayEmailNotificationActionImpl extends
		CrmDefaultSendingRelayEmailAction<SimpleCall> implements
		CallRelayEmailNotificationAction {

	@Autowired
	private AuditLogService auditLogService;
	@Autowired
	private CallService callService;

	@Autowired
	private CrmNotificationSettingService notificationService;

	private static final CallFieldNameMapper mapper = new CallFieldNameMapper();

	public CallRelayEmailNotificationActionImpl() {
		super(CrmTypeConstants.CALL);
	}

	protected void setupMailHeaders(SimpleCall call,
			SimpleRelayEmailNotification emailNotification,
			TemplateGenerator templateGenerator) {

		String summary = call.getSubject();
		String summaryLink = CrmLinkGenerator.generateCallPreviewFullLink(
				siteUrl, call.getId());

		templateGenerator.putVariable("makeChangeUser",
				emailNotification.getChangeByUserFullName());
		templateGenerator.putVariable("itemType", "call");
		templateGenerator.putVariable("summary", summary);
		templateGenerator.putVariable("summaryLink", summaryLink);
	}

	@Override
	protected TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification, SimpleUser user) {
		SimpleCall simpleCall = callService.findById(
				emailNotification.getTypeid(),
				emailNotification.getSaccountid());
		if (simpleCall != null) {
			String subject = StringUtils.trim(simpleCall.getSubject(), 100);

			TemplateGenerator templateGenerator = new TemplateGenerator(
					emailNotification.getChangeByUserFullName()
							+ "has created the call \"" + subject + "\"",
					"templates/email/crm/itemCreatedNotifier.mt");
			setupMailHeaders(simpleCall, emailNotification, templateGenerator);

			templateGenerator.putVariable("context",
					new MailContext<SimpleCall>(simpleCall, user, siteUrl));
			templateGenerator.putVariable("mapper", mapper);

			return templateGenerator;
		} else {
			return null;
		}
	}

	@Override
	protected TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification, SimpleUser user) {
		SimpleCall simpleCall = callService.findById(
				emailNotification.getTypeid(),
				emailNotification.getSaccountid());

		String subject = StringUtils.trim(simpleCall.getSubject(), 150);

		TemplateGenerator templateGenerator = new TemplateGenerator(
				emailNotification.getChangeByUserFullName()
						+ " has updated the call \"" + subject + "\"",
				"templates/email/crm/itemUpdatedNotifier.mt");

		setupMailHeaders(simpleCall, emailNotification, templateGenerator);

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
		SimpleCall simpleCall = callService.findById(
				emailNotification.getTypeid(),
				emailNotification.getSaccountid());

		TemplateGenerator templateGenerator = new TemplateGenerator(
				emailNotification.getChangeByUserFullName()
						+ " has commented on the call \""
						+ StringUtils.trim(simpleCall.getSubject(), 100) + "\"",
				"templates/email/crm/itemAddNoteNotifier.mt");
		setupMailHeaders(simpleCall, emailNotification, templateGenerator);

		templateGenerator.putVariable("comment", emailNotification);

		return templateGenerator;
	}

	public static class CallFieldNameMapper extends ItemFieldMapper {

		public CallFieldNameMapper() {
			put("subject", "Subject");
			put("status", "Status");
			put("startdate", new DateTimeFieldFormat("startdate",
					"Start Date & Time"));
			put("typeid", "Related to");
			put("durationinseconds", "Duration");
			put("purpose", "Purpose");
			put("assignuser", new AssigneeFieldFormat("assignuser", "Assignee"));
			put("description", "Description");
			put("result", "Result");
		}
	}

	public static class AssigneeFieldFormat extends FieldFormat {

		public AssigneeFieldFormat(String fieldName, String displayName) {
			super(fieldName, displayName);
		}

		@Override
		public String formatField(MailContext<?> context) {
			SimpleCall call = (SimpleCall) context.getWrappedBean();
			String userLink = UserLinkUtils.generatePreviewFullUserLink(
					LinkUtils.getSiteUrl(call.getSaccountid()),
					call.getAssignuser());
			String userAvatarLink = LinkUtils.getAvatarLink(
					call.getAssignUserAvatarId(), 16);

			Span span = new Span();
			Img img = new Img("avatar", userAvatarLink);
			span.appendChild(img);

			A link = new A();
			link.setHref(userLink);
			link.appendText(call.getAssignUserFullName());
			span.appendChild(link);
			return span.write();
		}
	}

}
