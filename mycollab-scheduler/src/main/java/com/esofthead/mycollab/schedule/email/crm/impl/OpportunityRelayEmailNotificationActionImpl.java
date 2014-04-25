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
import com.esofthead.mycollab.module.crm.CrmResources;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.service.CrmNotificationSettingService;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.user.UserLinkUtils;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.schedule.email.ItemFieldMapper;
import com.esofthead.mycollab.schedule.email.LinkUtils;
import com.esofthead.mycollab.schedule.email.MailContext;
import com.esofthead.mycollab.schedule.email.crm.OpportunityRelayEmailNotificationAction;
import com.esofthead.mycollab.schedule.email.format.CurrencyFieldFormat;
import com.esofthead.mycollab.schedule.email.format.DateFieldFormat;
import com.esofthead.mycollab.schedule.email.format.LinkFieldFormat;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Img;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class OpportunityRelayEmailNotificationActionImpl extends
		CrmDefaultSendingRelayEmailAction<SimpleOpportunity> implements
		OpportunityRelayEmailNotificationAction {

	@Autowired
	private AuditLogService auditLogService;
	@Autowired
	private OpportunityService opportunityService;

	@Autowired
	private CrmNotificationSettingService notificationService;

	private static final OpportunityFieldNameMapper mapper = new OpportunityFieldNameMapper();

	public OpportunityRelayEmailNotificationActionImpl() {
		super(CrmTypeConstants.OPPORTUNITY);
	}

	protected void setupMailHeaders(SimpleOpportunity simpleOpportunity,
			SimpleRelayEmailNotification emailNotification,
			TemplateGenerator templateGenerator) {

		String summary = simpleOpportunity.getOpportunityname();
		String summaryLink = CrmLinkGenerator
				.generateOpportunityPreviewFullLink(siteUrl,
						simpleOpportunity.getId());

		templateGenerator.putVariable("makeChangeUser",
				emailNotification.getChangeByUserFullName());
		templateGenerator.putVariable("itemType", "opportunity");
		templateGenerator.putVariable("summary", summary);
		templateGenerator.putVariable("summaryLink", summaryLink);
	}

	@Override
	protected TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification, SimpleUser user) {
		SimpleOpportunity simpleOpportunity = opportunityService.findById(
				emailNotification.getTypeid(),
				emailNotification.getSaccountid());
		if (simpleOpportunity != null) {
			String subject = StringUtils.trim(
					simpleOpportunity.getOpportunityname(), 100);

			TemplateGenerator templateGenerator = new TemplateGenerator(
					emailNotification.getChangeByUserFullName()
							+ " has created the opportunity \"" + subject
							+ "\"",
					"templates/email/crm/itemCreatedNotifier.mt");
			setupMailHeaders(simpleOpportunity, emailNotification,
					templateGenerator);

			templateGenerator.putVariable("context",
					new MailContext<SimpleOpportunity>(simpleOpportunity, user,
							siteUrl));
			templateGenerator.putVariable("mapper", mapper);

			return templateGenerator;
		} else {
			return null;
		}
	}

	@Override
	protected TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification, SimpleUser user) {
		SimpleOpportunity simpleOpportunity = opportunityService.findById(
				emailNotification.getTypeid(),
				emailNotification.getSaccountid());

		String subject = StringUtils.trim(
				simpleOpportunity.getOpportunityname(), 100);

		TemplateGenerator templateGenerator = new TemplateGenerator(
				emailNotification.getChangeByUserFullName()
						+ " has updated the opportunity \"" + subject + "\"",
				"templates/email/crm/itemUpdatedNotifier.mt");
		setupMailHeaders(simpleOpportunity, emailNotification,
				templateGenerator);

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
		SimpleOpportunity simpleOpportunity = opportunityService.findById(
				accountRecordId, emailNotification.getSaccountid());

		TemplateGenerator templateGenerator = new TemplateGenerator(
				emailNotification.getChangeByUserFullName()
						+ " has commented on the opportunity \""
						+ StringUtils.trim(
								simpleOpportunity.getOpportunityname(), 100)
						+ "\"", "templates/email/crm/itemAddNoteNotifier.mt");
		setupMailHeaders(simpleOpportunity, emailNotification,
				templateGenerator);
		templateGenerator.putVariable("comment", emailNotification);

		return templateGenerator;
	}

	public static class OpportunityFieldNameMapper extends ItemFieldMapper {

		public OpportunityFieldNameMapper() {
			put("opportunityname", "Opportunity Name");
			put("accountid", new AccountFieldFormat("accountid", "Account"));
			put("currencyid", new CurrencyFieldFormat("currency", "Currency"));
			put("expectedcloseddate", new DateFieldFormat("expectedcloseddate",
					"Expected Close Date"));
			put("amount", "Amount");
			put("opportunitytype", "Type");
			put("salesstage", "Sales Stage");
			put("source", "Lead Source");
			put("probability", "Probability (%)");
			put("campaignid", new CampaignFieldFormat("campaignid", "Campaign"));
			put("nextstep", "Next Step");
			put("assignuser", new AssigneeFieldFormat("assignuser", "Assignee"));
			put("description", "Description");
		}
	}

	public static class AccountFieldFormat extends LinkFieldFormat {

		public AccountFieldFormat(String fieldName, String displayName) {
			super(fieldName, displayName);
		}

		@Override
		protected Img buildImage(MailContext<?> context) {
			String accountIconLink = CrmResources
					.getResourceLink(CrmTypeConstants.ACCOUNT);
			Img img = new Img("avatar", accountIconLink);
			return img;
		}

		@Override
		protected A buildLink(MailContext<?> context) {
			SimpleOpportunity opportunity = (SimpleOpportunity) context
					.getWrappedBean();
			A link = new A();
			String accountLink = CrmLinkGenerator
					.generateAccountPreviewFullLink(context.getSiteUrl(),
							opportunity.getAccountid());
			link.setHref(accountLink);
			link.appendText(opportunity.getAccountName());
			return link;
		}

	}

	public static class CampaignFieldFormat extends LinkFieldFormat {

		public CampaignFieldFormat(String fieldName, String displayName) {
			super(fieldName, displayName);
		}

		@Override
		protected Img buildImage(MailContext<?> context) {

			String campaignIconLink = CrmResources
					.getResourceLink(CrmTypeConstants.CAMPAIGN);
			Img img = new Img("avatar", campaignIconLink);
			return img;
		}

		@Override
		protected A buildLink(MailContext<?> context) {
			SimpleOpportunity opportunity = (SimpleOpportunity) context
					.getWrappedBean();
			A link = new A();
			String campaignLink = CrmLinkGenerator
					.generateCampaignPreviewFullLink(context.getSiteUrl(),
							opportunity.getCampaignid());
			link.setHref(campaignLink);
			link.appendText(opportunity.getCampaignName());
			return link;
		}

	}

	public static class AssigneeFieldFormat extends LinkFieldFormat {

		public AssigneeFieldFormat(String fieldName, String displayName) {
			super(fieldName, displayName);
		}

		@Override
		protected Img buildImage(MailContext<?> context) {
			SimpleOpportunity opportunity = (SimpleOpportunity) context
					.getWrappedBean();

			String userAvatarLink = LinkUtils.getAvatarLink(
					opportunity.getAssignUserAvatarId(), 16);

			Img img = new Img("avatar", userAvatarLink);

			return img;
		}

		@Override
		protected A buildLink(MailContext<?> context) {
			SimpleOpportunity opportunity = (SimpleOpportunity) context
					.getWrappedBean();
			String userLink = UserLinkUtils.generatePreviewFullUserLink(
					LinkUtils.getSiteUrl(opportunity.getSaccountid()),
					opportunity.getAssignuser());

			A link = new A();
			link.setHref(userLink);
			link.appendText(opportunity.getAssignUserFullName());

			return link;
		}
	}

}
