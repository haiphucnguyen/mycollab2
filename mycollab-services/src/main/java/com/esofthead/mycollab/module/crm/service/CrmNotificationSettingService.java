package com.esofthead.mycollab.module.crm.service;

import java.util.List;

import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.persistence.service.ICrudService;
import com.esofthead.mycollab.module.crm.domain.CrmNotificationSetting;

public interface CrmNotificationSettingService extends
		ICrudService<Integer, CrmNotificationSetting> {
	@Cacheable
	CrmNotificationSetting findNotification(String username,
			@CacheKey Integer sAccountId);

	@Cacheable
	List<CrmNotificationSetting> findNotifications(@CacheKey Integer sAccountId);
}
