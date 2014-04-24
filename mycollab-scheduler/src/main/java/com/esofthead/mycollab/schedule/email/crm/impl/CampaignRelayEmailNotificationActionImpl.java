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
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.service.CrmNotificationSettingService;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.schedule.email.LinkUtils;
import com.esofthead.mycollab.schedule.email.MailContext;
import com.esofthead.mycollab.schedule.email.crm.CampaignRelayEmailNotificationAction;
import com.esofthead.mycollab.schedule.email.crm.CrmMailLinkGenerator;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CampaignRelayEmailNotificationActionImpl extends
		CrmDefaultSendingRelayEmailAction<SimpleCampaign> implements
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

	protected void setupMailHeaders(SimpleCampaign campaign,
			SimpleRelayEmailNotification emailNotification,
			TemplateGenerator templateGenerator) {

		CrmMailLinkGenerator crmLinkGenerator = new CrmMailLinkGenerator(
				LinkUtils.getSiteUrl(campaign.getSaccountid()));

		String summary = campaign.getCampaignname();
		String summaryLink = crmLinkGenerator
				.generateCasePreviewFullLink(campaign.getId());

		templateGenerator.putVariable("makeChangeUser",
				emailNotification.getChangeByUserFullName());
		templateGenerator.putVariable("itemType", "campaign");
		templateGenerator.putVariable("summary", summary);
		templateGenerator.putVariable("summaryLink", summaryLink);
	}

	@Override
	protected TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification, SimpleUser user) {
		SimpleCampaign simpleCampaign = campaignService.findById(
				emailNotification.getTypeid(),
				emailNotification.getSaccountid());
		if (simpleCampaign != null) {
			String subject = StringUtils.trim(simpleCampaign.getCampaignname(),
					100);

			TemplateGenerator templateGenerator = new TemplateGenerator(
					emailNotification.getChangeByUserFullName()
							+ " has created the campaign \"" + subject + "\"",
					"templates/email/crm/itemCreatedNotifier.mt");

			setupMailHeaders(simpleCampaign, emailNotification,
					templateGenerator);

			templateGenerator.putVariable("context",
					new MailContext<SimpleCampaign>(simpleCampaign, user));
			templateGenerator.putVariable("mapper", mapper);

			return templateGenerator;
		} else {
			return null;
		}
	}

	@Override
	protected TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification, SimpleUser user) {
		SimpleCampaign simpleCampaign = campaignService.findById(
				emailNotification.getTypeid(),
				emailNotification.getSaccountid());

		String subject = StringUtils
				.trim(simpleCampaign.getCampaignname(), 100);

		TemplateGenerator templateGenerator = new TemplateGenerator(
				emailNotification.getChangeByUserFullName()
						+ " has updated the campaign \"" + subject + "\"",
				"templates/email/crm/itemUpdatedNotifier.mt");

		setupMailHeaders(simpleCampaign, emailNotification, templateGenerator);

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
		int accountRecordId = emailNotification.getTypeid();
		SimpleCampaign simpleCampaign = campaignService.findById(
				accountRecordId, emailNotification.getSaccountid());

		TemplateGenerator templateGenerator = new TemplateGenerator(
				emailNotification.getChangeByUserFullName()
						+ " has commented on the campaign \""
						+ StringUtils.trim(simpleCampaign.getCampaignname(),
								100) + "\"",
				"templates/email/crm/itemAddNoteNotifier.mt");
		setupMailHeaders(simpleCampaign, emailNotification, templateGenerator);

		templateGenerator.putVariable("comment", emailNotification);

		return templateGenerator;
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
			fieldNameMap.put("currency", "Currency");
			fieldNameMap.put("budget", "Budget");
			fieldNameMap.put("expectedcost", "Expected Cost");
			fieldNameMap.put("expectedrevenue", "Expected Revenue");
			fieldNameMap.put("actualcost", "Actual Cost");
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
