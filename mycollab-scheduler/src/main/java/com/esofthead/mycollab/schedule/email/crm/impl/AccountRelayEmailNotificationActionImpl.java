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
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.i18n.AccountI18nEnum;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.service.CrmNotificationSettingService;
import com.esofthead.mycollab.module.crm.service.NoteService;
import com.esofthead.mycollab.module.mail.MailUtils;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.user.AccountLinkUtils;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.schedule.email.ItemFieldMapper;
import com.esofthead.mycollab.schedule.email.MailContext;
import com.esofthead.mycollab.schedule.email.crm.AccountRelayEmailNotificationAction;
import com.esofthead.mycollab.schedule.email.format.FieldFormat;
import com.esofthead.mycollab.schedule.email.format.html.TagBuilder;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
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
public class AccountRelayEmailNotificationActionImpl extends
		CrmDefaultSendingRelayEmailAction<SimpleAccount> implements
		AccountRelayEmailNotificationAction {

	@Autowired
	private AuditLogService auditLogService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private UserService userService;
	@Autowired
	private NoteService noteService;

	@Autowired
	private CrmNotificationSettingService notificationService;

	private static AccountFieldNameMapper mapper = new AccountFieldNameMapper();

	private SimpleAccount simpleAccount;

	public AccountRelayEmailNotificationActionImpl() {
		super(CrmTypeConstants.ACCOUNT);
	}

	private void setupMailHeaders(SimpleAccount account,
			SimpleRelayEmailNotification emailNotification,
			TemplateGenerator templateGenerator) {
		String summary = account.getAccountname();
		String summaryLink = CrmLinkGenerator.generateAccountPreviewFullLink(
				siteUrl, account.getId());

		templateGenerator.putVariable("makeChangeUser",
				emailNotification.getChangeByUserFullName());
		templateGenerator.putVariable("itemType", "account");
		templateGenerator.putVariable("summary", summary);
		templateGenerator.putVariable("summaryLink", summaryLink);
	}

	@Override
	protected TemplateGenerator templateGeneratorForCreateAction(
			MailContext<SimpleAccount> context) {
		int recordAccountId = context.getEmailNotification().getTypeid();
		simpleAccount = accountService.findById(recordAccountId, context
				.getEmailNotification().getSaccountid());
		if (simpleAccount != null) {
			String subject = StringUtils.trim(simpleAccount.getAccountname(),
					100);

			TemplateGenerator templateGenerator = new TemplateGenerator(
					context.getMessage(
							AccountI18nEnum.MAIL_CREATE_ITEM_SUBJECT, context
									.getEmailNotification()
									.getChangeByUserFullName(), subject),
					MailUtils.templatePath(
							"templates/email/crm/itemCreatedNotifier.mt",
							context.getUser().getLanguage()));

			setupMailHeaders(simpleAccount, context.getEmailNotification(),
					templateGenerator);

			context.setWrappedBean(simpleAccount);
			templateGenerator.putVariable("context", context);
			templateGenerator.putVariable("mapper", mapper);

			return templateGenerator;
		} else {
			return null;
		}
	}

	@Override
	protected TemplateGenerator templateGeneratorForUpdateAction(
			MailContext<SimpleAccount> context) {
		simpleAccount = accountService.findById(context.getTypeid(),
				context.getSaccountid());
		if (simpleAccount != null) {
			String subject = StringUtils.trim(simpleAccount.getAccountname(),
					100);

			TemplateGenerator templateGenerator = new TemplateGenerator(
					context.getMessage(
							AccountI18nEnum.MAIL_UPDATE_ITEM_SUBJECT, subject),
					context.templatePath("templates/email/crm/itemUpdatedNotifier.mt"));

			setupMailHeaders(simpleAccount, context.getEmailNotification(),
					templateGenerator);

			if (context.getTypeid() != null) {
				SimpleAuditLog auditLog = auditLogService.findLatestLog(
						context.getTypeid(), context.getSaccountid());

				templateGenerator.putVariable("historyLog", auditLog);
				context.setWrappedBean(simpleAccount);
				templateGenerator.putVariable("context", context);
				templateGenerator.putVariable("mapper", mapper);
			}
			return templateGenerator;
		} else {
			return null;
		}

	}

	@Override
	protected TemplateGenerator templateGeneratorForCommentAction(
			MailContext<SimpleAccount> context) {
		int accountRecordId = context.getTypeid();
		simpleAccount = accountService.findById(accountRecordId,
				context.getSaccountid());

		if (simpleAccount != null) {
			TemplateGenerator templateGenerator = new TemplateGenerator(
					context.getMessage(
							AccountI18nEnum.MAIL_COMMENT_ITEM_SUBJECT,
							context.getChangeByUserFullName()),
					context.templatePath("templates/email/crm/itemAddNoteNotifier.mt"));

			setupMailHeaders(simpleAccount, context.getEmailNotification(),
					templateGenerator);

			templateGenerator.putVariable("comment",
					context.getEmailNotification());

			return templateGenerator;
		} else {
			return null;
		}

	}

	public static class AssigneeFieldFormat extends FieldFormat {

		public AssigneeFieldFormat(String fieldName, String displayName) {
			super(fieldName, displayName);
		}

		@Override
		public String formatField(MailContext<?> context) {
			SimpleAccount account = (SimpleAccount) context.getWrappedBean();
			if (account.getAssignuser() != null) {
				String userAvatarLink = MailUtils.getAvatarLink(
						account.getAssignUserAvatarId(), 16);
				Img img = TagBuilder.newImg("avatar", userAvatarLink);
				String userLink = AccountLinkUtils.generatePreviewFullUserLink(
						MailUtils.getSiteUrl(account.getSaccountid()),
						account.getAssignuser());

				A link = TagBuilder.newA(userLink,
						account.getAssignUserFullName());

				return TagBuilder.newLink(img, link).write();
			} else {
				return null;
			}
		}

		@Override
		public String formatField(MailContext<?> context, String value) {
			if (value == null || "".equals(value)) {
				return "";
			}

			UserService userService = ApplicationContextUtil
					.getSpringBean(UserService.class);
			SimpleUser user = userService.findUserByUserNameInAccount(value,
					context.getUser().getAccountId());
			if (user != null) {
				String userAvatarLink = MailUtils.getAvatarLink(
						user.getAvatarid(), 16);
				String userLink = AccountLinkUtils.generatePreviewFullUserLink(
						MailUtils.getSiteUrl(user.getAccountId()),
						user.getUsername());
				Img img = TagBuilder.newImg("avatar", userAvatarLink);
				A link = TagBuilder.newA(userLink, user.getDisplayName());
				return TagBuilder.newLink(img, link).write();
			}
			return value;
		}
	}

	public static class AccountFieldNameMapper extends ItemFieldMapper {

		public AccountFieldNameMapper() {
			put("accountname", "Name");
			put("phoneoffice", "Office Phone");

			put("website", "Website");
			put("numemployees", "Employees");

			put("fax", "Fax");
			put("alternatephone", "Other Phone");

			put("industry", "Industry");
			put("email", "Email");

			put("type", "Type");
			put("ownership", "Ownership");

			put("assignuser", new AssigneeFieldFormat("assignuser",
					"Assign User"));
			put("annualrevenue", "Annual Revenue");

			put("billingaddress", "Billing Address");
			put("shippingaddress", "Shipping Address");

			put("city", "Billing City");
			put("shippingcity", "Shipping City");

			put("state", "Billing State");
			put("shippingstate", "Shipping State");

			put("postalcode", "Billing Postal Code");
			put("shippingpostalcode", "Shipping Postal Code");

			put("billingcountry", "Billing Country");
			put("shippingcountry", "Shipping Country");

			put("description", "Description", true);
		}
	}
}
