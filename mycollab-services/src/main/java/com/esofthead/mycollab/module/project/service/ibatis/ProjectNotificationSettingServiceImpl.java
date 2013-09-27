package com.esofthead.mycollab.module.project.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import com.esofthead.mycollab.module.project.dao.ProjectNotificationSettingMapper;
import com.esofthead.mycollab.module.project.domain.ProjectNotificationSetting;
import com.esofthead.mycollab.module.project.service.ProjectNotificationSettingService;

public class ProjectNotificationSettingServiceImpl extends
		DefaultCrudService<Integer, ProjectNotificationSetting> implements
		ProjectNotificationSettingService {

	@Autowired
	private ProjectNotificationSettingMapper projectNotificationSettingMapper;

	@Override
	public ICrudGenericDAO<Integer, ProjectNotificationSetting> getCrudMapper() {
		return projectNotificationSettingMapper;
	}

}
