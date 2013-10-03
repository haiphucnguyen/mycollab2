package com.esofthead.mycollab.schedule.email.crm.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.common.service.AuditLogService;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.crm.domain.CrmNotificationSetting;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.service.CrmNotificationSettingService;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.schedule.email.crm.CampaignRelayEmailNotificationAction;

@Component
public class CampaignRelayEmailNotificationActionImpl extends
		CrmDefaultSendingRelayEmailAction implements
		CampaignRelayEmailNotificationAction {

	@Autowired
	private AuditLogService auditLogService;
	@Autowired
	private CampaignService campaignService;

	@Autowired
	private CrmNotificationSettingService notificationService;

	private final CampaignFieldNameMapper mapper;

	public CampaignRelayEmailNotificationActionImpl() {
		mapper = new CampaignFieldNameMapper();
	}

	@Override
	protected TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification) {
		SimpleCampaign simpleCampaign = campaignService.findById(
				emailNotification.getTypeid(),
				emailNotification.getSaccountid());
		if (simpleCampaign != null) {
			String subject = StringUtils.subString(
					simpleCampaign.getCampaignname(), 150);

			TemplateGenerator templateGenerator = new TemplateGenerator(
					"Campaign: \"" + subject + "\" has been created",
					"templates/email/crm/campaignCreatedNotifier.mt");

			templateGenerator.putVariable("simpleCampaign", simpleCampaign);
			templateGenerator.putVariable("hyperLinks",
					constructHyperLinks(simpleCampaign));
			return templateGenerator;
		} else {
			return null;
		}
	}

	@Override
	protected TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification) {
		SimpleCampaign simpleCampaign = campaignService.findById(
				emailNotification.getTypeid(),
				emailNotification.getSaccountid());

		String subject = StringUtils.subString(
				simpleCampaign.getCampaignname(), 150);

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"Campaign: \"" + subject + "...\" has been updated",
				"templates/email/crm/campaignUpdatedNotifier.mt");
		templateGenerator.putVariable("simpleCampaign", simpleCampaign);
		templateGenerator.putVariable("hyperLinks",
				constructHyperLinks(simpleCampaign));

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
		int accountRecordId = emailNotification.getTypeid();
		SimpleCampaign simpleCampaign = campaignService.findById(accountRecordId,
				emailNotification.getSaccountid());

		TemplateGenerator templateGenerator = new TemplateGenerator("[Campaign]"
				+ emailNotification.getChangeByUserFullName()
				+ " has commented on "
				+ StringUtils.subString(simpleCampaign.getCampaignname(), 100)
				+ "\"", "templates/email/crm/campaignAddNoteNotifier.mt");
		templateGenerator.putVariable("comment", emailNotification);
		// templateGenerator.putVariable("userComment", linkGenerator
		// .generateUserPreviewFullLink(emailNotification.getChangeby()));
		templateGenerator.putVariable("bug", simpleCampaign);
		templateGenerator.putVariable("hyperLinks",
				constructHyperLinks(simpleCampaign));

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

	private Map<String, String> constructHyperLinks(
			SimpleCampaign simpleCampaign) {
		Map<String, String> hyperLinks = new HashMap<String, String>();
		// hyperLinks.put("accountURL", CrmLinkGenerator.generateCrmItemLink(
		// CrmTypeConstants.ACCOUNT, simpleAccount.getId()));

		return hyperLinks;
	}

	public class CampaignFieldNameMapper {
		private final Map<String, String> fieldNameMap;

		CampaignFieldNameMapper() {
			fieldNameMap = new HashMap<String, String>();

			fieldNameMap.put("summary", "Bug Summary");
			fieldNameMap.put("description", "Description");
			fieldNameMap.put("status", "Status");
			fieldNameMap.put("assignuser", "Assigned to");
			fieldNameMap.put("resolution", "Resolution");
			fieldNameMap.put("severity", "Serverity");
			fieldNameMap.put("environment", "Environment");
			fieldNameMap.put("priority", "Priority");
			fieldNameMap.put("duedate", "Due Date");
		}

		public boolean hasField(String fieldName) {
			return fieldNameMap.containsKey(fieldName);
		}

		public String getFieldLabel(String fieldName) {
			return fieldNameMap.get(fieldName);
		}
	}

}
