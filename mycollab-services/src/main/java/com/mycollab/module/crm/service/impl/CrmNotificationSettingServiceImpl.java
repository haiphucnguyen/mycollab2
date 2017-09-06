package com.mycollab.module.crm.service.impl;

import com.mycollab.common.NotificationType;
import com.mycollab.core.cache.CacheKey;
import com.mycollab.core.cache.Cacheable;
import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.db.persistence.service.DefaultCrudService;
import com.mycollab.module.crm.dao.CrmNotificationSettingMapper;
import com.mycollab.module.crm.domain.CrmNotificationSetting;
import com.mycollab.module.crm.domain.CrmNotificationSettingExample;
import com.mycollab.module.crm.service.CrmNotificationSettingService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Service
public class CrmNotificationSettingServiceImpl extends DefaultCrudService<Integer, CrmNotificationSetting> implements
        CrmNotificationSettingService {

    @Autowired
    private CrmNotificationSettingMapper crmNotificationSettingMapper;

    @Override
    public ICrudGenericDAO<Integer, CrmNotificationSetting> getCrudMapper() {
        return crmNotificationSettingMapper;
    }

    @Override
    @Cacheable
    public CrmNotificationSetting findNotification(String username, @CacheKey Integer sAccountId) {
        CrmNotificationSettingExample ex = new CrmNotificationSettingExample();
        ex.createCriteria().andUsernameEqualTo(username).andSaccountidEqualTo(sAccountId);
        List<CrmNotificationSetting> notifications = crmNotificationSettingMapper.selectByExample(ex);
        if (CollectionUtils.isNotEmpty(notifications)) {
            return notifications.get(0);
        } else {
            CrmNotificationSetting notification = new CrmNotificationSetting();
            notification.setSaccountid(sAccountId);
            notification.setUsername(username);
            notification.setLevel(NotificationType.Default.name());
            return notification;
        }
    }

    @Override
    @Cacheable
    public List<CrmNotificationSetting> findNotifications(@CacheKey Integer sAccountId) {
        CrmNotificationSettingExample ex = new CrmNotificationSettingExample();
        ex.createCriteria().andSaccountidEqualTo(sAccountId);
        return crmNotificationSettingMapper.selectByExample(ex);
    }

}
