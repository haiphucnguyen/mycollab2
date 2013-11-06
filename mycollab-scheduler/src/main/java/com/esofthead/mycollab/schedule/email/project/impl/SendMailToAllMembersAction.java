package com.esofthead.mycollab.schedule.email.project.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.common.domain.SimpleComment;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.common.domain.criteria.CommentSearchCriteria;
import com.esofthead.mycollab.common.service.CommentService;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.mail.service.ExtMailService;
import com.esofthead.mycollab.module.project.domain.ProjectNotificationSetting;
import com.esofthead.mycollab.module.project.domain.ProjectNotificationSettingType;
import com.esofthead.mycollab.module.project.domain.ProjectRelayEmailNotification;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.service.ProjectNotificationSettingService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.schedule.email.SendingRelayEmailNotificationAction;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

public abstract class SendMailToAllMembersAction implements
		SendingRelayEmailNotificationAction {

	@Autowired
	private ExtMailService extMailService;

	@Autowired
	private ProjectMemberService projectMemberService;

	@Autowired
	private ProjectNotificationSettingService projectNotificationService;

	protected List<SimpleUser> getNotifyUsers(
			ProjectRelayEmailNotification notification) {
		List<SimpleUser> usersInProject = projectMemberService
				.getActiveUsersInProject(
						((ProjectRelayEmailNotification) notification)
								.getProjectId(), notification.getSaccountid());

		List<ProjectNotificationSetting> notificationSettings = projectNotificationService
				.findNotifications(notification.getProjectId(),
						notification.getSaccountid());
		if (notificationSettings != null && notificationSettings.size() > 0) {
			for (ProjectNotificationSetting setting : notificationSettings) {
				if (ProjectNotificationSettingType.NONE.equals(setting
						.getLevel())
						|| ProjectNotificationSettingType.MINIMAL
								.equals(setting.getLevel())) {
					for (SimpleUser user : usersInProject) {
						if (user.getUsername().equals(setting.getUsername())) {
							usersInProject.remove(user);
							break;
						}
					}
				}
			}
		}
		return usersInProject;
	}

	@Override
	public void sendNotificationForCreateAction(
			SimpleRelayEmailNotification notification) {
		List<SimpleUser> notifiers = getNotifyUsers((ProjectRelayEmailNotification) notification);
		if ((notifiers != null) && !notifiers.isEmpty()) {
			TemplateGenerator templateGenerator = templateGeneratorForCreateAction(notification);
			if (templateGenerator != null) {
				for (SimpleUser user : notifiers) {

					templateGenerator.putVariable("userName",
							user.getDisplayName());

					MailRecipientField userMail = new MailRecipientField(
							user.getEmail(), user.getUsername());
					List<MailRecipientField> lst = new ArrayList<MailRecipientField>();
					lst.add(userMail);

					extMailService.sendHTMLMail("noreply@mycollab.com",
							"noreply@mycollab.com", lst, null, null,
							templateGenerator.generateSubjectContent(),
							templateGenerator.generateBodyContent(), null);
				}
			}
		}
	}

	@Override
	public void sendNotificationForUpdateAction(
			SimpleRelayEmailNotification notification) {
		List<SimpleUser> notifiers = getNotifyUsers((ProjectRelayEmailNotification) notification);
		if ((notifiers != null) && !notifiers.isEmpty()) {
			TemplateGenerator templateGenerator = templateGeneratorForUpdateAction(notification);
			if (templateGenerator != null) {
				for (SimpleUser user : notifiers) {

					templateGenerator.putVariable("userName",
							user.getDisplayName());

					MailRecipientField userMail = new MailRecipientField(
							user.getEmail(), user.getUsername());
					List<MailRecipientField> lst = new ArrayList<MailRecipientField>();
					lst.add(userMail);

					extMailService.sendHTMLMail("noreply@mycollab.com",
							"noreply@mycollab.com", lst, null, null,
							templateGenerator.generateSubjectContent(),
							templateGenerator.generateBodyContent(), null);
				}
			}
		}

	}

	@Override
	public void sendNotificationForCommentAction(
			SimpleRelayEmailNotification notification) {
		List<SimpleUser> notifiers = getNotifyUsers((ProjectRelayEmailNotification) notification);
		if ((notifiers != null) && !notifiers.isEmpty()) {
			TemplateGenerator templateGenerator = templateGeneratorForCommentAction(notification);
			if (templateGenerator != null) {
				for (SimpleUser user : notifiers) {

					templateGenerator.putVariable("userName",
							user.getDisplayName());

					MailRecipientField userMail = new MailRecipientField(
							user.getEmail(), user.getUsername());
					List<MailRecipientField> lst = new ArrayList<MailRecipientField>();
					lst.add(userMail);

					extMailService.sendHTMLMail("noreply@mycollab.com",
							"noreply@mycollab.com", lst, null, null,
							templateGenerator.generateSubjectContent(),
							templateGenerator.generateBodyContent(), null);
				}
			}
		}
	}

	public List<SimpleComment> getListComment(Integer sAccountId, String type,
			Integer typeId) {
		CommentService commentService = ApplicationContextUtil
				.getSpringBean(CommentService.class);
		CommentSearchCriteria criteria = new CommentSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(sAccountId));
		criteria.setType(new StringSearchField(type));
		criteria.setTypeid(new NumberSearchField(typeId));
		criteria.setOrderByField("createdtime");
		criteria.setSortDirection(SearchCriteria.DESC);

		List<SimpleComment> lstComment = commentService
				.findPagableListByCriteria(new SearchRequest<CommentSearchCriteria>(
						criteria, 0, 5));
		return lstComment;
	}

	protected abstract TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification);

	protected abstract TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification);

	protected abstract TemplateGenerator templateGeneratorForCommentAction(
			SimpleRelayEmailNotification emailNotification);
}
