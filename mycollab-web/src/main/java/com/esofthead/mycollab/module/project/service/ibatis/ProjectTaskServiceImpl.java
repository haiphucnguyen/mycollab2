package com.esofthead.mycollab.module.project.service.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.bull.javamelody.MonitoredWithSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.common.domain.criteria.RelayEmailNotificationSearchCriteria;
import com.esofthead.mycollab.common.interceptor.service.Auditable;
import com.esofthead.mycollab.common.interceptor.service.Traceable;
import com.esofthead.mycollab.common.interceptor.service.Watchable;
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.mail.service.SystemMailService;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.dao.TaskMapper;
import com.esofthead.mycollab.module.project.dao.TaskMapperExt;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.schedule.ScheduleConfig;

@Service
@Transactional
@Traceable(module = ModuleNameConstants.PRJ, type = ProjectContants.TASK, nameField = "taskname", extraFieldName = "projectid")
@Auditable(module = ModuleNameConstants.PRJ, type = ProjectContants.TASK)
@Watchable(type = MonitorTypeConstants.PRJ_TASK, userFieldName = "assignuser")
public class ProjectTaskServiceImpl extends
		DefaultService<Integer, Task, TaskSearchCriteria> implements
		ProjectTaskService {

	@Autowired
	private TaskMapper taskMapper;
	@Autowired
	private TaskMapperExt taskMapperExt;
	@Autowired
	private RelayEmailNotificationService relayEmailNotificationService;
	
	@Autowired
	private SystemMailService mailService;

	@Override
	public ICrudGenericDAO<Integer, Task> getCrudMapper() {
		return taskMapper;
	}

	@Override
	public ISearchableDAO<TaskSearchCriteria> getSearchMapper() {
		return taskMapperExt;
	}

	@Override
	public SimpleTask findTaskById(int taskId) {
		return taskMapperExt.findTaskById(taskId);
	}

	@Override
	public int saveWithSession(Task record, String username) {
		if ((record.getPercentagecomplete() != null)
				&& (record.getPercentagecomplete() == 100)) {
			record.setStatus("Closed");
		} else {
			record.setStatus("Open");
		}
		record.setLogby(username);
		return super.saveWithSession(record, username);
	}

	@Override
	public int updateWithSession(Task record, String username) {
		if ((record.getPercentagecomplete() != null)
				&& (record.getPercentagecomplete() == 100)) {
			record.setStatus("Closed");
		} else {
			record.setStatus("Open");
		}
		return super.updateWithSession(record, username);
	}

	@Scheduled(fixedDelay = ScheduleConfig.RUN_EMAIL_NOTIFICATION_INTERVAL)
	@MonitoredWithSpring
	@Override
	public void runNotification() {
		System.out.print("Run task email");
		RelayEmailNotificationSearchCriteria criteria = new RelayEmailNotificationSearchCriteria();
		criteria.setType(new StringSearchField(MonitorTypeConstants.PRJ_TASK));
		List<SimpleRelayEmailNotification> relayEmaiNotifications = relayEmailNotificationService
				.findPagableListByCriteria(new SearchRequest<RelayEmailNotificationSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));
		relayEmailNotificationService.removeByCriteria(criteria);
		for (SimpleRelayEmailNotification emailNotification : relayEmaiNotifications) {
			if (MonitorTypeConstants.CREATE_ACTION.equals(emailNotification
					.getAction())) {
				List<SimpleUser> notifiers = emailNotification.getNotifyUsers();
				if (notifiers != null && !notifiers.isEmpty()) {
					int taskId = emailNotification.getTypeid();
					SimpleTask task = this.findTaskById(taskId);

					Map<String, String> hyperLinks = new HashMap<String, String>();
					hyperLinks.put("taskUrl", "#");
					hyperLinks.put("projectUrl", "#");
					hyperLinks.put("assignUserUrl", "#");
					hyperLinks.put("taskListUrl", "#");

					TemplateGenerator templateGenerator = new TemplateGenerator(
							"[$task.projectName]: Task $task.taskname created",
							"templates/email/project/taskCreatedNotifier.mt");
					templateGenerator.putVariable("task", task);
					templateGenerator.putVariable("hyperLinks", hyperLinks);
					mailService.sendHTMLMail("mail@esofthead.com",
							emailNotification.getChangeByUserFullName(),
							notifiers,
							templateGenerator.generateSubjectContent(),
							templateGenerator.generateBodyContent());
				}
			}
		}
	}
}
