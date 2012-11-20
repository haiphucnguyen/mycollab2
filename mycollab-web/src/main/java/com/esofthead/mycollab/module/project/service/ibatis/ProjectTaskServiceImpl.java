package com.esofthead.mycollab.module.project.service.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultService;
import com.esofthead.mycollab.module.project.dao.TaskMapper;
import com.esofthead.mycollab.module.project.dao.TaskMapperExt;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;

@Service
public class ProjectTaskServiceImpl extends DefaultService<Integer, Task, TaskSearchCriteria>
		implements ProjectTaskService {

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
	public int insertAndReturnKey(Task task) {
		taskMapperExt.insertAndReturnKey(task);
		return task.getId();
	}

	@Override
	public List<GroupItem> getTaskSummary(TaskSearchCriteria criteria) {
		return taskMapperExt.getTaskSummary(criteria);
	}
}
