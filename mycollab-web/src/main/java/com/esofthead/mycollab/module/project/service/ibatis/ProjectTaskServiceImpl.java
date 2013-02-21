package com.esofthead.mycollab.module.project.service.ibatis;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.common.interceptor.service.Auditable;
import com.esofthead.mycollab.common.interceptor.service.Traceable;
import com.esofthead.mycollab.common.interceptor.service.Watchable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.dao.TaskMapper;
import com.esofthead.mycollab.module.project.dao.TaskMapperExt;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
