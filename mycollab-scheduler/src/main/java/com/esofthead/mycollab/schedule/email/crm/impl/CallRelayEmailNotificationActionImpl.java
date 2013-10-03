package com.esofthead.mycollab.schedule.email.crm.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.common.service.AuditLogService;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.CrmNotificationSetting;
import com.esofthead.mycollab.module.crm.domain.SimpleCall;
import com.esofthead.mycollab.module.crm.service.CallService;
import com.esofthead.mycollab.module.crm.service.CrmNotificationSettingService;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.project.ProjectLinkUtils;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.schedule.email.crm.CallRelayEmailNotificationAction;
import com.esofthead.mycollab.schedule.email.crm.CrmLinkGenerator;

@Component
public class CallRelayEmailNotificationActionImpl extends
		CrmDefaultSendingRelayEmailAction implements
		CallRelayEmailNotificationAction {

	@Autowired
	private AuditLogService auditLogService;
	@Autowired
	private CallService callService;

	@Autowired
	private CrmNotificationSettingService notificationService;

	private final CallFieldNameMapper mapper;

	public CallRelayEmailNotificationActionImpl() {
		mapper = new CallFieldNameMapper();
	}

	@Override
	protected TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification) {
		SimpleCall simpleCall = callService.findById(
				emailNotification.getTypeid(),
				emailNotification.getSaccountid());
		if (simpleCall != null) {
			String subject = StringUtils
					.subString(simpleCall.getSubject(), 150);

			TemplateGenerator templateGenerator = new TemplateGenerator(
					"Call: \"" + subject + "\" has been created",
					"templates/email/crm/callCreatedNotifier.mt");

			templateGenerator.putVariable("simpleCall", simpleCall);
			templateGenerator.putVariable("hyperLinks",
					constructHyperLinks(simpleCall));
			return templateGenerator;
		} else {
			return null;
		}
	}

	private Map<String, String> constructHyperLinks(SimpleCall simpleCall) {
		Map<String, String> hyperLinks = new HashMap<String, String>();
		hyperLinks.put(
				"callURL",
				getSiteUrl(simpleCall.getSaccountid())
						+ CrmLinkGenerator.generateCrmItemLink(
								CrmTypeConstants.CALL, simpleCall.getId()));

		return hyperLinks;
	}

	@Override
	protected TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification) {
		SimpleCall simpleCall = callService.findById(
				emailNotification.getTypeid(),
				emailNotification.getSaccountid());

		String subject = StringUtils.subString(simpleCall.getSubject(), 150);

		TemplateGenerator templateGenerator = new TemplateGenerator("Call: \""
				+ subject + "...\" has been updated",
				"templates/email/crm/callUpdatedNotifier.mt");
		templateGenerator.putVariable("simpleCall", simpleCall);
		templateGenerator.putVariable("hyperLinks",
				constructHyperLinks(simpleCall));

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
			SimpleRelayEmailNotification emailNotification) {
		SimpleCall simpleCall = callService.findById(
				emailNotification.getTypeid(),
				emailNotification.getSaccountid());

		TemplateGenerator templateGenerator = new TemplateGenerator("[Call]"
				+ emailNotification.getChangeByUserFullName()
				+ " has commented on "
				+ StringUtils.subString(simpleCall.getSubject(), 100) + "\"",
				"templates/email/crm/callAddNoteNotifier.mt");
		templateGenerator.putVariable("comment", emailNotification);
		templateGenerator.putVariable(
				"userComment",
				getSiteUrl(emailNotification.getSaccountid())
						+ ProjectLinkUtils.URL_PREFIX_PARAM
						+ "account/user/preview/"
						+ UrlEncodeDecoder.encode(emailNotification
								.getChangeby()));
		templateGenerator.putVariable("simpleCall", simpleCall);
		templateGenerator.putVariable("hyperLinks",
				constructHyperLinks(simpleCall));

		return templateGenerator;
	}

	@Override
	protected List<SimpleUser> getListNotififyUserWithFilter(
			SimpleRelayEmailNotification notification) {
		List<CrmNotificationSetting> notificationSettings = notificationService
				.findNotifications(notification.getChangeby(),
						notification.getSaccountid());

		List<SimpleUser> inListUsers = notification.getNotifyUsers();

		// TODO: MORE CODE FILTER
		return inListUsers;
	}

	public class CallFieldNameMapper {
		private final Map<String, String> fieldNameMap;

		CallFieldNameMapper() {
			fieldNameMap = new HashMap<String, String>();

			fieldNameMap.put("subject", "Subject");
			fieldNameMap.put("status", "Status");
			fieldNameMap.put("startdate", "Start Date & Time");
			fieldNameMap.put("relatedTo", "Related to");
			fieldNameMap.put("durationinseconds", "Duration");
			fieldNameMap.put("purpose", "Purpose");
			fieldNameMap.put("assignuser", "Assignee");
			fieldNameMap.put("description", "Description");
			fieldNameMap.put("result", "Result");
		}

		public boolean hasField(String fieldName) {
			return fieldNameMap.containsKey(fieldName);
		}

		public String getFieldLabel(String fieldName) {
			return fieldNameMap.get(fieldName);
		}
	}

}
