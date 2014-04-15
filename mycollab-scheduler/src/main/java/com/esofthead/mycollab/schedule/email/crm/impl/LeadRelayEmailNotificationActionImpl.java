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

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.common.service.AuditLogService;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.crm.CrmLinkGenerator;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.service.CrmNotificationSettingService;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.user.UserLinkUtils;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.schedule.email.MailItemLink;
import com.esofthead.mycollab.schedule.email.crm.CrmMailLinkGenerator;
import com.esofthead.mycollab.schedule.email.crm.LeadRelayEmailNotificationAction;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@Component
public class LeadRelayEmailNotificationActionImpl extends
		CrmDefaultSendingRelayEmailAction implements
		LeadRelayEmailNotificationAction {

	@Autowired
	private AuditLogService auditLogService;
	@Autowired
	private LeadService leadService;

	@Autowired
	private CrmNotificationSettingService notificationService;

	private final LeadFieldNameMapper mapper;

	public LeadRelayEmailNotificationActionImpl() {
		super(CrmTypeConstants.LEAD);
		mapper = new LeadFieldNameMapper();
	}

	protected void setupMailHeaders(SimpleLead lead,
			SimpleRelayEmailNotification emailNotification,
			TemplateGenerator templateGenerator) {

		CrmMailLinkGenerator crmLinkGenerator = new CrmMailLinkGenerator(
				getSiteUrl(lead.getSaccountid()));

		String summary = lead.getLeadName();
		String summaryLink = crmLinkGenerator
				.generateContactPreviewFullLink(lead.getId());

		templateGenerator.putVariable("makeChangeUser",
				emailNotification.getChangeByUserFullName());
		templateGenerator.putVariable("itemType", "lead");
		templateGenerator.putVariable("summary", summary);
		templateGenerator.putVariable("summaryLink", summaryLink);
	}

	protected Map<String, List<MailItemLink>> getListOfProperties(
			SimpleLead lead) {
		Map<String, List<MailItemLink>> listOfDisplayProperties = new LinkedHashMap<String, List<MailItemLink>>();

		CrmMailLinkGenerator crmLinkGenerator = new CrmMailLinkGenerator(
				getSiteUrl(lead.getSaccountid()));

		if (lead.getFirstname() != null) {
			listOfDisplayProperties.put(mapper.getFieldLabel("firstname"),
					Arrays.asList(new MailItemLink(null, lead.getFirstname())));
		} else {
			listOfDisplayProperties
					.put(mapper.getFieldLabel("firstname"), null);
		}

		if (lead.getEmail() != null) {
			listOfDisplayProperties.put(mapper.getFieldLabel("email"), Arrays
					.asList(new MailItemLink("mailto:" + lead.getEmail(), lead
							.getEmail())));
		} else {
			listOfDisplayProperties.put(mapper.getFieldLabel("email"), null);
		}

		if (lead.getLastname() != null) {
			listOfDisplayProperties.put(mapper.getFieldLabel("lastname"),
					Arrays.asList(new MailItemLink(null, lead.getLastname())));
		} else {
			listOfDisplayProperties.put(mapper.getFieldLabel("lastname"), null);
		}

		if (lead.getOfficephone() != null) {
			listOfDisplayProperties
					.put(mapper.getFieldLabel("officephone"), Arrays
							.asList(new MailItemLink(null, lead
									.getOfficephone())));
		} else {
			listOfDisplayProperties.put(mapper.getFieldLabel("officephone"),
					null);
		}

		if (lead.getTitle() != null) {
			listOfDisplayProperties.put(mapper.getFieldLabel("title"),
					Arrays.asList(new MailItemLink(null, lead.getTitle())));
		} else {
			listOfDisplayProperties.put(mapper.getFieldLabel("title"), null);
		}

		if (lead.getMobile() != null) {
			listOfDisplayProperties.put(mapper.getFieldLabel("mobile"),
					Arrays.asList(new MailItemLink(null, lead.getMobile())));
		} else {
			listOfDisplayProperties.put(mapper.getFieldLabel("mobile"), null);
		}

		if (lead.getDepartment() != null) {
			listOfDisplayProperties
					.put(mapper.getFieldLabel("department"),
							Arrays.asList(new MailItemLink(null, lead
									.getDepartment())));
		} else {
			listOfDisplayProperties.put(mapper.getFieldLabel("department"),
					null);
		}

		if (lead.getOtherphone() != null) {
			listOfDisplayProperties
					.put(mapper.getFieldLabel("otherphone"),
							Arrays.asList(new MailItemLink(null, lead
									.getOtherphone())));
		} else {
			listOfDisplayProperties.put(mapper.getFieldLabel("otherphone"),
					null);
		}

		if (lead.getAccountname() != null) {
			listOfDisplayProperties
					.put(mapper.getFieldLabel("accountname"), Arrays
							.asList(new MailItemLink(null, lead
									.getAccountname())));
		} else {
			listOfDisplayProperties.put(mapper.getFieldLabel("accountname"),
					null);
		}

		if (lead.getFax() != null) {
			listOfDisplayProperties.put(mapper.getFieldLabel("fax"),
					Arrays.asList(new MailItemLink(null, lead.getFax())));
		} else {
			listOfDisplayProperties.put(mapper.getFieldLabel("fax"), null);
		}

		if (lead.getLeadsourcedesc() != null) {
			listOfDisplayProperties.put(mapper.getFieldLabel("leadsourcedesc"),
					Arrays.asList(new MailItemLink(null, lead
							.getLeadsourcedesc())));
		} else {
			listOfDisplayProperties.put(mapper.getFieldLabel("leadsourcedesc"),
					null);
		}

		if (lead.getWebsite() != null) {
			listOfDisplayProperties.put(mapper.getFieldLabel("website"),
					Arrays.asList(new MailItemLink(null, lead.getWebsite())));
		} else {
			listOfDisplayProperties.put(mapper.getFieldLabel("website"), null);
		}

		return listOfDisplayProperties;
	}

	@Override
	protected TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification, SimpleUser user) {
		SimpleLead simpleLead = leadService.findById(
				emailNotification.getTypeid(),
				emailNotification.getSaccountid());
		if (simpleLead != null) {
			String subject = StringUtils.trim(simpleLead.getLeadName(), 150);

			TemplateGenerator templateGenerator = new TemplateGenerator(
					emailNotification.getChangeByUserFullName()
							+ " has created the lead \"" + subject + "\"",
					"templates/email/crm/leadCreatedNotifier.mt");

			templateGenerator.putVariable("simpleLead", simpleLead);
			templateGenerator.putVariable("hyperLinks",
					constructHyperLinks(simpleLead));
			return templateGenerator;
		} else {
			return null;
		}
	}

	@Override
	protected TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification, SimpleUser user) {
		SimpleLead lead = leadService.findById(emailNotification.getTypeid(),
				emailNotification.getSaccountid());
		if (lead == null) {
			return null;
		}

		String subject = StringUtils.trim(lead.getLeadName(), 150);

		TemplateGenerator templateGenerator = new TemplateGenerator(
				emailNotification.getChangeByUserFullName()
						+ " has updated the lead \"" + subject + "\"",
				"templates/email/crm/leadUpdatedNotifier.mt");
		templateGenerator.putVariable("simpleLead", lead);
		templateGenerator.putVariable("hyperLinks", constructHyperLinks(lead));

		if (emailNotification.getTypeid() != null) {
			SimpleAuditLog auditLog = auditLogService.findLatestLog(
					emailNotification.getTypeid(),
					emailNotification.getSaccountid());
			templateGenerator.putVariable(
					"postedUserURL",
					UserLinkUtils.generatePreviewFullUserLink(
							getSiteUrl(lead.getSaccountid()),
							auditLog.getPosteduser()));
			templateGenerator.putVariable("historyLog", auditLog);

			templateGenerator.putVariable("mapper", mapper);
		}
		return templateGenerator;
	}

	@Override
	protected TemplateGenerator templateGeneratorForCommentAction(
			SimpleRelayEmailNotification emailNotification, SimpleUser user) {
		SimpleLead simpleLead = leadService.findById(
				emailNotification.getTypeid(),
				emailNotification.getSaccountid());

		TemplateGenerator templateGenerator = new TemplateGenerator(
				emailNotification.getChangeByUserFullName()
						+ " has commented on the lead \""
						+ StringUtils.trim(simpleLead.getLeadName(), 100)
						+ "\"", "templates/email/crm/leadAddNoteNotifier.mt");
		templateGenerator.putVariable("comment", emailNotification);
		templateGenerator.putVariable("userComment", UserLinkUtils
				.generatePreviewFullUserLink(
						getSiteUrl(simpleLead.getSaccountid()),
						emailNotification.getChangeby()));
		templateGenerator.putVariable("simpleLead", simpleLead);
		templateGenerator.putVariable("hyperLinks",
				constructHyperLinks(simpleLead));

		return templateGenerator;
	}

	private Map<String, String> constructHyperLinks(SimpleLead simpleLead) {
		Map<String, String> hyperLinks = new HashMap<String, String>();
		hyperLinks.put(
				"leadURL",
				getSiteUrl(simpleLead.getSaccountid())
						+ CrmLinkGenerator.generateCrmItemLink(
								CrmTypeConstants.LEAD, simpleLead.getId()));

		if (simpleLead.getAssignuser() != null) {
			hyperLinks.put("assignUserURL", UserLinkUtils
					.generatePreviewFullUserLink(
							getSiteUrl(simpleLead.getSaccountid()),
							simpleLead.getAssignuser()));
		}

		return hyperLinks;
	}

	public class LeadFieldNameMapper {
		private final Map<String, String> fieldNameMap;

		LeadFieldNameMapper() {
			fieldNameMap = new HashMap<String, String>();

			fieldNameMap.put("firstname", "First Name");
			fieldNameMap.put("email", "Email");
			fieldNameMap.put("lastname", "Last Name");
			fieldNameMap.put("officephone", "Office Phone");
			fieldNameMap.put("title", "Title");
			fieldNameMap.put("mobile", "Mobile");
			fieldNameMap.put("department", "Department");
			fieldNameMap.put("otherphone", "Other Phone");
			fieldNameMap.put("accountName", "Account Name");
			fieldNameMap.put("fax", "Fax");
			fieldNameMap.put("leadsourcedesc", "Lead Source");
			fieldNameMap.put("website", "Web Site");
			fieldNameMap.put("industry", "Industry");
			fieldNameMap.put("status", "Status");
			fieldNameMap.put("noemployees", "No of Employees");
			fieldNameMap.put("primaddress", "Address");
			fieldNameMap.put("otheraddress", "Other Address");
			fieldNameMap.put("primcity", "City");
			fieldNameMap.put("othercity", "Other City");
			fieldNameMap.put("state", "State");
			fieldNameMap.put("otherstate", "Other State");
			fieldNameMap.put("primpostalcode", "Postal Code");
			fieldNameMap.put("otherpostalcode", "Other Postal Code");
			fieldNameMap.put("primcountry", "Country");
			fieldNameMap.put("othercountry", "Other Country");
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
