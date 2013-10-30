package com.esofthead.mycollab.schedule.email.crm.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.common.service.AuditLogService;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.crm.CrmLinkGenerator;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.service.CrmNotificationSettingService;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.user.UserLinkUtils;
import com.esofthead.mycollab.schedule.email.crm.OpportunityRelayEmailNotificationAction;

@Component
public class OpportunityRelayEmailNotificationActionImpl extends
		CrmDefaultSendingRelayEmailAction implements
		OpportunityRelayEmailNotificationAction {

	@Autowired
	private AuditLogService auditLogService;
	@Autowired
	private OpportunityService opportunityService;

	@Autowired
	private CrmNotificationSettingService notificationService;

	private final OpportunityFieldNameMapper mapper;

	public OpportunityRelayEmailNotificationActionImpl() {
		super(CrmTypeConstants.OPPORTUNITY);
		mapper = new OpportunityFieldNameMapper();
	}

	@Override
	protected TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification) {
		SimpleOpportunity simpleOpportunity = opportunityService.findById(
				emailNotification.getTypeid(),
				emailNotification.getSaccountid());
		if (simpleOpportunity != null) {
			String subject = StringUtils.subString(
					simpleOpportunity.getOpportunityname(), 150);

			TemplateGenerator templateGenerator = new TemplateGenerator(
					"Opportunity: \"" + subject + "\" has been created",
					"templates/email/crm/opportunityCreatedNotifier.mt");

			templateGenerator.putVariable("simpleOpportunity",
					simpleOpportunity);
			templateGenerator.putVariable("hyperLinks",
					constructHyperLinks(simpleOpportunity));
			return templateGenerator;
		} else {
			return null;
		}
	}

	private Map<String, String> constructHyperLinks(
			SimpleOpportunity simpleOpportunity) {
		Map<String, String> hyperLinks = new HashMap<String, String>();
		hyperLinks.put(
				"opportunityURL",
				getSiteUrl(simpleOpportunity.getSaccountid())
						+ CrmLinkGenerator.generateCrmItemLink(
								CrmTypeConstants.OPPORTUNITY,
								simpleOpportunity.getId()));
		if (simpleOpportunity.getAssignuser() != null) {
			hyperLinks.put("assignUserURL", UserLinkUtils
					.generatePreviewFullUserLink(
							getSiteUrl(simpleOpportunity.getSaccountid()),
							simpleOpportunity.getAssignuser()));
		}
		return hyperLinks;
	}

	@Override
	protected TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification) {
		SimpleOpportunity simpleOpportunity = opportunityService.findById(
				emailNotification.getTypeid(),
				emailNotification.getSaccountid());

		String subject = StringUtils.subString(
				simpleOpportunity.getOpportunityname(), 150);

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"Opportunity: \"" + subject + "...\" has been updated",
				"templates/email/crm/opportunityUpdatedNotifier.mt");
		templateGenerator.putVariable("simpleOpportunity", simpleOpportunity);
		templateGenerator.putVariable("hyperLinks",
				constructHyperLinks(simpleOpportunity));

		if (emailNotification.getTypeid() != null) {
			SimpleAuditLog auditLog = auditLogService.findLatestLog(
					emailNotification.getTypeid(),
					emailNotification.getSaccountid());
			templateGenerator.putVariable("postedUserURL", UserLinkUtils
					.generatePreviewFullUserLink(
							getSiteUrl(simpleOpportunity.getSaccountid()),
							auditLog.getPosteduser()));
			templateGenerator.putVariable("historyLog", auditLog);

			templateGenerator.putVariable("mapper", mapper);
		}
		return templateGenerator;
	}

	@Override
	protected TemplateGenerator templateGeneratorForCommentAction(
			SimpleRelayEmailNotification emailNotification) {
		int accountRecordId = emailNotification.getTypeid();
		SimpleOpportunity simpleOpportunity = opportunityService.findById(
				accountRecordId, emailNotification.getSaccountid());

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[Opportunity]"
						+ emailNotification.getChangeByUserFullName()
						+ " has commented on "
						+ StringUtils.subString(
								simpleOpportunity.getOpportunityname(), 100)
						+ "\"",
				"templates/email/crm/opportunityAddNoteNotifier.mt");
		templateGenerator.putVariable("comment", emailNotification);
		templateGenerator.putVariable("userComment", UserLinkUtils
				.generatePreviewFullUserLink(
						getSiteUrl(simpleOpportunity.getSaccountid()),
						emailNotification.getChangeby()));

		templateGenerator.putVariable("simpleOpportunity", simpleOpportunity);
		templateGenerator.putVariable("hyperLinks",
				constructHyperLinks(simpleOpportunity));

		return templateGenerator;
	}

	public class OpportunityFieldNameMapper {
		private final Map<String, String> fieldNameMap;

		OpportunityFieldNameMapper() {
			fieldNameMap = new HashMap<String, String>();

			fieldNameMap.put("opportunityname", "Opportunity Name");
			fieldNameMap.put("accountName", "Account Name");
			fieldNameMap.put("status", "Status");
			// fieldNameMap.put("currency.symbol", "Currency");
			fieldNameMap.put("expectedcloseddate", "Expected Close Date");
			fieldNameMap.put("amount", "Amount");
			fieldNameMap.put("type", "Type");
			fieldNameMap.put("salesstage", "Sales Stage");
			fieldNameMap.put("leadsource", "Lead Source");
			fieldNameMap.put("probability", "Probability");
			fieldNameMap.put("campaignName", "Campaign");
			fieldNameMap.put("nextstep", "Next Step");
			fieldNameMap.put("assignuser", "Assignee");
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
