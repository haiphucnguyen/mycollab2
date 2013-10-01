package com.esofthead.mycollab.schedule.email.project.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.common.domain.MonitorItem;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.common.domain.criteria.MonitorSearchCriteria;
import com.esofthead.mycollab.common.service.MonitorItemService;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.mail.service.ExtMailService;
import com.esofthead.mycollab.module.project.domain.ProjectNotificationSetting;
import com.esofthead.mycollab.module.project.domain.ProjectRelayEmailNotification;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.service.ProjectNotificationSettingService;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.schedule.email.SendingRelayEmailNotificationAction;

public abstract class DefaultSendingRelayEmailNotificationForProjectAction
		implements SendingRelayEmailNotificationAction {
	@Autowired
	protected ExtMailService extMailService;

	@Autowired
	protected ProjectMemberService projectMemberService;

	@Autowired
	protected ProjectNotificationSettingService projectNotificationService;

	@Autowired
	protected MonitorItemService monitorItemService;

	@Autowired
	protected BugService bugService;

	@Autowired
	protected ProjectTaskService projectTaskService;

	protected List<SimpleUser> getNotifyUsers(
			SimpleRelayEmailNotification notification) {

		if (notification instanceof ProjectRelayEmailNotification) {
			List<ProjectNotificationSetting> lstNotificationSetting = projectNotificationService
					.findNotifications(
							((ProjectRelayEmailNotification) notification)
									.getProjectId(), notification
									.getSaccountid());

			List<SimpleUser> usersInProject = projectMemberService
					.getActiveUsersInProject(
							((ProjectRelayEmailNotification) notification)
									.getProjectId(), notification
									.getSaccountid());

			for (SimpleUser user : usersInProject) {
				for (ProjectNotificationSetting projectNotificationSetting : lstNotificationSetting) {
					if (user.getUsername().equals(
							projectNotificationSetting.getUsername())) {
						if (projectNotificationSetting.getLevel()
								.equals("Full")) {
							// not remove...sending.
						} else if (projectNotificationSetting.getLevel()
								.equals("None")) {
							usersInProject.remove(user);
						} else if (projectNotificationSetting.getLevel()
								.equals("Minimal")) {
							if (notification.getType().equals("Project-Bug")) {
								SimpleBug bug = bugService.findById(
										notification.getTypeid(),
										notification.getSaccountid());
								if (bug != null
										&& !bug.getAssignuser().equals(
												user.getUsername())) {
									usersInProject.remove(user);
								}
							} else if (notification.getType().equals(
									"Project-Task")) {
								SimpleTask task = projectTaskService.findById(
										notification.getTypeid(),
										notification.getSaccountid());
								if (task != null
										&& !task.getAssignuser().equals(
												user.getUsername())) {
									usersInProject.remove(user);
								}
							}
						} else if (projectNotificationSetting.getLevel()
								.equals("Default")) {
							// For Assingee and Follower Iteam ...

							MonitorSearchCriteria searchCriteria = new MonitorSearchCriteria();
							searchCriteria.setTypeId(new NumberSearchField(
									notification.getTypeid()));
							searchCriteria.setType(new StringSearchField(
									notification.getType()));
							searchCriteria.setSaccountid(new NumberSearchField(
									notification.getSaccountid()));
							searchCriteria.setUser(new StringSearchField(user
									.getUsername()));
							SearchRequest<MonitorSearchCriteria> searchRequest = new SearchRequest<MonitorSearchCriteria>(
									new MonitorSearchCriteria(), 0,
									Integer.MAX_VALUE);

							@SuppressWarnings("unchecked")
							List<MonitorItem> lstMonitor = monitorItemService
									.findPagableListByCriteria(searchRequest);
							boolean checkExist = false;
							for (MonitorItem item : lstMonitor) {
								if (item.getUser().equals(user.getUsername())) {
									checkExist = true;
									break;
								}
							}
							if (!checkExist) {
								if (notification.getType()
										.equals("Project-Bug")) {
									SimpleBug bug = bugService.findById(
											notification.getTypeid(),
											notification.getSaccountid());
									if (bug != null
											&& !bug.getAssignuser().equals(
													user.getUsername())) {
										usersInProject.remove(user);
									}
								} else if (notification.getType().equals(
										"Project-Task")) {
									SimpleTask task = projectTaskService
											.findById(notification.getTypeid(),
													notification
															.getSaccountid());
									if (task != null
											&& !task.getAssignuser().equals(
													user.getUsername())) {
										usersInProject.remove(user);
									}
								}
							}
						}
					}
				}
			}
			return usersInProject;
		} else {
			return new ArrayList<SimpleUser>();
		}
	}

	@Override
	public void sendNotificationForCreateAction(
			SimpleRelayEmailNotification notification) {
		List<SimpleUser> notifiers = getListNotififyUserWithFilter((ProjectRelayEmailNotification) notification);
		if ((notifiers != null) && !notifiers.isEmpty()) {
			TemplateGenerator templateGenerator = templateGeneratorForCreateAction(notification);
			if (templateGenerator != null) {
				for (SimpleUser user : notifiers) {
					String userName = user.getDisplayName();
					templateGenerator.putVariable("userName", userName);

					MailRecipientField userMail = new MailRecipientField(
							user.getEmail(), user.getUsername());
					List<MailRecipientField> lst = new ArrayList<MailRecipientField>();
					lst.add(userMail);

					extMailService.sendHTMLMail("noreply@esofthead.com",
							"noreply@esofthead.com", lst, null, null,
							templateGenerator.generateSubjectContent(),
							templateGenerator.generateBodyContent(), null);
				}
			}
		}
	}

	@Override
	public void sendNotificationForUpdateAction(
			SimpleRelayEmailNotification notification) {
		List<SimpleUser> notifiers = getListNotififyUserWithFilter((ProjectRelayEmailNotification) notification);
		if ((notifiers != null) && !notifiers.isEmpty()) {
			TemplateGenerator templateGenerator = templateGeneratorForUpdateAction(notification);
			if (templateGenerator != null) {
				for (SimpleUser user : notifiers) {
					String userName = user.getDisplayName();
					templateGenerator.putVariable("userName", userName);

					MailRecipientField userMail = new MailRecipientField(
							user.getEmail(), user.getUsername());
					List<MailRecipientField> lst = new ArrayList<MailRecipientField>();
					lst.add(userMail);

					extMailService.sendHTMLMail("noreply@esofthead.com",
							"noreply@esofthead.com", lst, null, null,
							templateGenerator.generateSubjectContent(),
							templateGenerator.generateBodyContent(), null);
				}
			}
		}

	}

	@Override
	public void sendNotificationForCommentAction(
			SimpleRelayEmailNotification notification) {
		List<SimpleUser> notifiers = getListNotififyUserWithFilter((ProjectRelayEmailNotification) notification);
		if ((notifiers != null) && !notifiers.isEmpty()) {
			TemplateGenerator templateGenerator = templateGeneratorForCommentAction(notification);
			if (templateGenerator != null) {
				for (SimpleUser user : notifiers) {
					String userName = user.getDisplayName();
					templateGenerator.putVariable("userName", userName);

					MailRecipientField userMail = new MailRecipientField(
							user.getEmail(), user.getUsername());
					List<MailRecipientField> lst = new ArrayList<MailRecipientField>();
					lst.add(userMail);

					extMailService.sendHTMLMail("noreply@esofthead.com",
							"noreply@esofthead.com", lst, null, null,
							templateGenerator.generateSubjectContent(),
							templateGenerator.generateBodyContent(), null);
				}
			}
		}
	}

	protected abstract TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification);

	protected abstract TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification);

	protected abstract TemplateGenerator templateGeneratorForCommentAction(
			SimpleRelayEmailNotification emailNotification);

	protected abstract List<SimpleUser> getListNotififyUserWithFilter(
			ProjectRelayEmailNotification notification);
}
