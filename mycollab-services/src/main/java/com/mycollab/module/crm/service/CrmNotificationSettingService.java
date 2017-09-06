package com.mycollab.module.crm.service;

import com.mycollab.core.cache.CacheKey;
import com.mycollab.core.cache.Cacheable;
import com.mycollab.db.persistence.service.ICrudService;
import com.mycollab.module.crm.domain.CrmNotificationSetting;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface CrmNotificationSettingService extends ICrudService<Integer, CrmNotificationSetting> {
    @Cacheable
    CrmNotificationSetting findNotification(String username, @CacheKey Integer sAccountId);

    @Cacheable
    List<CrmNotificationSetting> findNotifications(@CacheKey Integer sAccountId);
}
