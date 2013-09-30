package com.esofthead.mycollab.module.project.service;

import java.util.List;

import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.persistence.service.ICrudService;
import com.esofthead.mycollab.module.project.domain.ProjectNotificationSetting;

public interface ProjectNotificationSettingService extends
		ICrudService<Integer, ProjectNotificationSetting> {

	@Cacheable
	ProjectNotificationSetting findNotification(String username,
			Integer projectId, @CacheKey Integer sAccountId);

	@Cacheable
	List<ProjectNotificationSetting> findNotifications(Integer projectId,
			@CacheKey Integer sAccountId);
}
