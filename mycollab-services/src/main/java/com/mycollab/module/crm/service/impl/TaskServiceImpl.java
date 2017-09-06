package com.mycollab.module.crm.service.impl;

import com.google.common.eventbus.AsyncEventBus;
import com.mycollab.aspect.ClassInfo;
import com.mycollab.aspect.ClassInfoMap;
import com.mycollab.aspect.Traceable;
import com.mycollab.aspect.Watchable;
import com.mycollab.cache.CleanCacheEvent;
import com.mycollab.common.ModuleNameConstants;
import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.db.persistence.service.DefaultService;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.dao.CrmTaskMapper;
import com.mycollab.module.crm.dao.CrmTaskMapperExt;
import com.mycollab.module.crm.domain.CrmTask;
import com.mycollab.module.crm.domain.SimpleCrmTask;
import com.mycollab.module.crm.domain.criteria.CrmTaskSearchCriteria;
import com.mycollab.module.crm.service.EventService;
import com.mycollab.module.crm.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Service
@Transactional
@Traceable(nameField = "subject")
@Watchable(userFieldName = "assignuser")
public class TaskServiceImpl extends DefaultService<Integer, CrmTask, CrmTaskSearchCriteria> implements TaskService {

    static {
        ClassInfoMap.put(TaskServiceImpl.class, new ClassInfo(ModuleNameConstants.CRM, CrmTypeConstants.TASK));
    }

    @Autowired
    private CrmTaskMapper taskMapper;

    @Autowired
    private CrmTaskMapperExt taskMapperExt;

    @Autowired
    private AsyncEventBus asyncEventBus;

    @Override
    public ICrudGenericDAO<Integer, CrmTask> getCrudMapper() {
        return taskMapper;
    }

    @Override
    public ISearchableDAO<CrmTaskSearchCriteria> getSearchMapper() {
        return taskMapperExt;
    }

    @Override
    public SimpleCrmTask findById(Integer taskId, Integer sAccountId) {
        return taskMapperExt.findById(taskId);
    }

    @Override
    public Integer saveWithSession(CrmTask record, String username) {
        Integer result = super.saveWithSession(record, username);
        asyncEventBus.post(new CleanCacheEvent(record.getSaccountid(), new Class[]{EventService.class}));
        return result;
    }

    @Override
    public Integer updateWithSession(CrmTask record, String username) {
        Integer result = super.updateWithSession(record, username);
        asyncEventBus.post(new CleanCacheEvent(record.getSaccountid(), new Class[]{EventService.class}));
        return result;
    }

    @Override
    public void removeByCriteria(CrmTaskSearchCriteria criteria, Integer accountId) {
        super.removeByCriteria(criteria, accountId);
        asyncEventBus.post(new CleanCacheEvent(accountId, new Class[]{EventService.class}));
    }

    @Override
    public void massRemoveWithSession(List<CrmTask> tasks, String username, Integer accountId) {
        super.massRemoveWithSession(tasks, username, accountId);
        asyncEventBus.post(new CleanCacheEvent(accountId, new Class[]{EventService.class}));
    }

    @Override
    public void massUpdateWithSession(CrmTask record, List<Integer> primaryKeys, Integer accountId) {
        super.massUpdateWithSession(record, primaryKeys, accountId);
        asyncEventBus.post(new CleanCacheEvent(accountId, new Class[]{EventService.class}));
    }

    @Override
    public void updateBySearchCriteria(CrmTask record, CrmTaskSearchCriteria searchCriteria) {
        super.updateBySearchCriteria(record, searchCriteria);
        asyncEventBus.post(new CleanCacheEvent((Integer) searchCriteria.getAccountId().getValue(),
                new Class[]{EventService.class}));
    }

}
