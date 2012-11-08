package com.esofthead.mycollab.module.project.service.ibatis;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.project.dao.ProjectMapper;
import com.esofthead.mycollab.module.project.dao.ResourceMapper;
import com.esofthead.mycollab.module.project.dao.TaskMapperExt;
import com.esofthead.mycollab.module.project.dao.TaskResourceMapper;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.TaskService;

public class TaskServiceImpl extends DefaultCrudService<Integer, Task>
		implements TaskService {
	private static Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

	private ResourceMapper resourceDAO;

	private TaskResourceMapper taskResourceDAO;

	private TaskMapperExt taskExtDAO;

	private ProjectMapper projectDAO;

	public void setProjectDAO(ProjectMapper projectDAO) {
		this.projectDAO = projectDAO;
	}

	public void setTaskExtDAO(TaskMapperExt taskExtDAO) {
		this.taskExtDAO = taskExtDAO;
	}

	public void setResourceDAO(ResourceMapper resourceDAO) {
		this.resourceDAO = resourceDAO;
	}

	public void setTaskResourceDAO(TaskResourceMapper taskResourceDAO) {
		this.taskResourceDAO = taskResourceDAO;
	}

	@Override
	public List findPagableListByCriteria(TaskSearchCriteria criteria,
			int skipNum, int maxResult) {
		return taskExtDAO.findPagableList(criteria, new RowBounds(skipNum,
				maxResult));
	}

	@Override
	public int getTotalCount(TaskSearchCriteria criteria) {
		return taskExtDAO.getTotalCount(criteria);
	}

	@Override
	public int insertAndReturnKey(Task task) {
		taskExtDAO.insertAndReturnKey(task);
		return task.getId();
	}

	@Override
	public List<GroupItem> getTaskSummary(TaskSearchCriteria criteria) {
		return taskExtDAO.getTaskSummary(criteria);
	}
}
