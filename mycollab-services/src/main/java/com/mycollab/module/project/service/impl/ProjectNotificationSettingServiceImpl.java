package com.mycollab.module.project.service.impl;

import com.mycollab.common.NotificationType;
import com.mycollab.core.cache.CacheKey;
import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.db.persistence.service.DefaultCrudService;
import com.mycollab.module.project.dao.ProjectNotificationSettingMapper;
import com.mycollab.module.project.domain.ProjectNotificationSetting;
import com.mycollab.module.project.domain.ProjectNotificationSettingExample;
import com.mycollab.module.project.service.ProjectNotificationSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Service
public class ProjectNotificationSettingServiceImpl extends DefaultCrudService<Integer, ProjectNotificationSetting> implements
        ProjectNotificationSettingService {

    @Autowired
    private ProjectNotificationSettingMapper projectNotificationSettingMapper;

    @Override
    public ICrudGenericDAO<Integer, ProjectNotificationSetting> getCrudMapper() {
        return projectNotificationSettingMapper;
    }

    @Override
    public ProjectNotificationSetting findNotification(String username, Integer projectId, @CacheKey Integer sAccountId) {
        ProjectNotificationSettingExample ex = new ProjectNotificationSettingExample();
        ex.createCriteria().andUsernameEqualTo(username).andProjectidEqualTo(projectId).andSaccountidEqualTo(sAccountId);
        List<ProjectNotificationSetting> settings = projectNotificationSettingMapper.selectByExample(ex);
        if (settings.size() > 0) {
            return settings.get(0);
        } else {
            ProjectNotificationSetting setting = new ProjectNotificationSetting();
            setting.setLevel(NotificationType.Default.name());
            setting.setProjectid(projectId);
            setting.setSaccountid(sAccountId);
            setting.setUsername(username);
            return setting;
        }
    }

    @Override
    public List<ProjectNotificationSetting> findNotifications(Integer projectId, @CacheKey Integer sAccountId) {
        ProjectNotificationSettingExample ex = new ProjectNotificationSettingExample();
        ex.createCriteria().andProjectidEqualTo(projectId).andSaccountidEqualTo(sAccountId);
        return projectNotificationSettingMapper.selectByExample(ex);
    }

}
