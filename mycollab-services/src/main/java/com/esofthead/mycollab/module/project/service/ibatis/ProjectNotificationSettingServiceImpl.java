package com.esofthead.mycollab.module.project.service.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import com.esofthead.mycollab.module.project.dao.ProjectNotificationSettingMapper;
import com.esofthead.mycollab.module.project.domain.ProjectNotificationSetting;
import com.esofthead.mycollab.module.project.domain.ProjectNotificationSettingExample;
import com.esofthead.mycollab.module.project.service.ProjectNotificationSettingService;

@Service
public class ProjectNotificationSettingServiceImpl extends
		DefaultCrudService<Integer, ProjectNotificationSetting> implements
		ProjectNotificationSettingService {

	@Autowired
	private ProjectNotificationSettingMapper projectNotificationSettingMapper;

	@Override
	public ICrudGenericDAO<Integer, ProjectNotificationSetting> getCrudMapper() {
		return projectNotificationSettingMapper;
	}

	@Override
	public ProjectNotificationSetting findNotification(String username,
			Integer projectId, @CacheKey Integer sAccountId) {
		ProjectNotificationSettingExample ex = new ProjectNotificationSettingExample();
		ex.createCriteria().andUsernameEqualTo(username)
				.andProjectidEqualTo(projectId)
				.andSaccountidEqualTo(sAccountId);
		List<ProjectNotificationSetting> settings = projectNotificationSettingMapper
				.selectByExample(ex);
		if (settings.size() > 0) {
			return settings.get(0);
		}
		return null;
	}

	@Override
	public List<ProjectNotificationSetting> findNotifications(
			Integer projectId, @CacheKey Integer sAccountId) {
		ProjectNotificationSettingExample ex = new ProjectNotificationSettingExample();
		ex.createCriteria().andProjectidEqualTo(projectId)
				.andSaccountidEqualTo(sAccountId);

		return projectNotificationSettingMapper.selectByExample(ex);
	}

}
