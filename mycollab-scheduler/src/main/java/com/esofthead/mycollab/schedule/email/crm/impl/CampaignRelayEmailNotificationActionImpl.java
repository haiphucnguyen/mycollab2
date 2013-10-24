package com.esofthead.mycollab.schedule.email.crm.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.common.service.AuditLogService;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.service.CrmNotificationSettingService;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.project.ProjectLinkUtils;
import com.esofthead.mycollab.schedule.email.crm.CampaignRelayEmailNotificationAction;
import com.esofthead.mycollab.schedule.email.crm.CrmLinkGenerator;

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
		super(CrmTypeConstants.CAMPAIGN);
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
			CrmLinkGenerator linkGenerator = new CrmLinkGenerator(
					simpleCampaign.getSaccountid());
			templateGenerator.putVariable("postedUserURL", linkGenerator
					.generateUserPreviewFullLink(auditLog.getPosteduser()));
			templateGenerator.putVariable("historyLog", auditLog);
			templateGenerator.putVariable("mapper", mapper);
		}
		return templateGenerator;
	}

	@Override
	protected TemplateGenerator templateGeneratorForCommentAction(
			SimpleRelayEmailNotification emailNotification) {
		int accountRecordId = emailNotification.getTypeid();
		SimpleCampaign simpleCampaign = campaignService.findById(
				accountRecordId, emailNotification.getSaccountid());

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[Campaign]"
						+ emailNotification.getChangeByUserFullName()
						+ " has commented on "
						+ StringUtils.subString(
								simpleCampaign.getCampaignname(), 100) + "\"",
				"templates/email/crm/campaignAddNoteNotifier.mt");
		templateGenerator.putVariable("comment", emailNotification);
		CrmLinkGenerator linkGenerator = new CrmLinkGenerator(
				simpleCampaign.getSaccountid());
		templateGenerator.putVariable("userComment", linkGenerator.getSiteUrl()
				+ ProjectLinkUtils.URL_PREFIX_PARAM + "account/user/preview/"
				+ UrlEncodeDecoder.encode(emailNotification.getChangeby()));
		templateGenerator.putVariable("simpleCampaign", simpleCampaign);
		templateGenerator.putVariable("hyperLinks",
				constructHyperLinks(simpleCampaign));

		return templateGenerator;
	}

	private Map<String, String> constructHyperLinks(
			SimpleCampaign simpleCampaign) {
		Map<String, String> hyperLinks = new HashMap<String, String>();
		CrmLinkGenerator linkGenerator = new CrmLinkGenerator(
				simpleCampaign.getSaccountid());
		hyperLinks.put(
				"campaignURL",
				linkGenerator.getSiteUrl()
						+ CrmLinkGenerator.generateCrmItemLink(
								CrmTypeConstants.CAMPAIGN,
								simpleCampaign.getId()));
		if (simpleCampaign.getAssignuser() != null) {
			hyperLinks.put("assignUserURL",
					linkGenerator.generateUserPreviewFullLink(simpleCampaign
							.getAssignuser()));
		}
		return hyperLinks;
	}

	public class CampaignFieldNameMapper {
		private final Map<String, String> fieldNameMap;

		CampaignFieldNameMapper() {
			fieldNameMap = new HashMap<String, String>();

			fieldNameMap.put("campaignname", "Name");
			fieldNameMap.put("status", "Status");
			fieldNameMap.put("startdate", "StartDate");
			fieldNameMap.put("type", "Type");
			fieldNameMap.put("enddate", "EndDate");
			fieldNameMap.put("assignuser", "Assignee");
			// fieldNameMap.put("currency.symbol", "Currency");
			fieldNameMap.put("budget", "Budget");
			fieldNameMap.put("expectedcost", "Expected Cost");
			fieldNameMap.put("budget", "Budget");
			fieldNameMap.put("actualcost", "Actual Cost");
			fieldNameMap.put("expectedcost", "Expected Revenue");
			fieldNameMap.put("actualcost", "Actual Cost");
			fieldNameMap.put("expectedrevenue", "Expected Revenue");
			fieldNameMap.put("description", "Description");
		}

		public boolean hasField(String fieldName) {
			return fieldNameMap.containsKey(fieldName);
		}

		public String getFieldLabel(String fieldName) {
			return fieldNameMap.get(fieldName);
		}
	}

}
