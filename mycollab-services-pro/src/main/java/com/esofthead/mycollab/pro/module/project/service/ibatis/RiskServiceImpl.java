package com.esofthead.mycollab.pro.module.project.service.ibatis;

import com.esofthead.mycollab.cache.CleanCacheEvent;
import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.interceptor.aspect.ClassInfo;
import com.esofthead.mycollab.common.interceptor.aspect.ClassInfoMap;
import com.esofthead.mycollab.common.interceptor.aspect.Traceable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.module.project.esb.DeleteProjectRiskEvent;
import com.esofthead.mycollab.module.project.service.*;
import com.esofthead.mycollab.pro.module.project.dao.RiskMapper;
import com.esofthead.mycollab.pro.module.project.dao.RiskMapperExt;
import com.google.common.eventbus.AsyncEventBus;
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
@Traceable(nameField = "riskname", extraFieldName = "projectid")
public class RiskServiceImpl extends DefaultService<Integer, Risk, RiskSearchCriteria> implements RiskService {

    static {
        ClassInfoMap.put(RiskServiceImpl.class, new ClassInfo(ModuleNameConstants.PRJ, ProjectTypeConstants.RISK));
    }

    @Autowired
    private RiskMapper riskMapper;

    @Autowired
    private RiskMapperExt riskMapperExt;

    @Autowired
    private AsyncEventBus asyncEventBus;

    @Override
    public ICrudGenericDAO<Integer, Risk> getCrudMapper() {
        return riskMapper;
    }

    @Override
    public ISearchableDAO<RiskSearchCriteria> getSearchMapper() {
        return riskMapperExt;
    }

    @Override
    public SimpleRisk findById(Integer riskId, Integer sAccountId) {
        return riskMapperExt.findRiskById(riskId);
    }

    @Override
    public Integer saveWithSession(Risk record, String username) {
        Integer recordId = super.saveWithSession(record, username);
        asyncEventBus.post(new CleanCacheEvent(record.getSaccountid(), new Class[]{ProjectService.class,
                ProjectGenericTaskService.class, ProjectActivityStreamService.class}));
        return recordId;
    }

    @Override
    public Integer updateWithSession(Risk record, String username) {
        int result = super.updateWithSession(record, username);
        asyncEventBus.post(new CleanCacheEvent(record.getSaccountid(), new Class[]{ProjectService.class,
                ProjectGenericTaskService.class, ProjectActivityStreamService.class}));
        return result;
    }

    @Override
    public void removeByCriteria(RiskSearchCriteria criteria, Integer accountId) {
        super.removeByCriteria(criteria, accountId);
        asyncEventBus.post(new CleanCacheEvent(accountId, new Class[]{ProjectService.class,
                ProjectGenericTaskService.class, ProjectActivityStreamService.class, ItemTimeLoggingService.class}));
    }

    @Override
    public void massRemoveWithSession(List<Risk> items, String username, Integer accountId) {
        super.massRemoveWithSession(items, username, accountId);
        asyncEventBus.post(new CleanCacheEvent(accountId, new Class[]{ProjectService.class,
                ProjectGenericTaskService.class, ProjectActivityStreamService.class, ItemTimeLoggingService.class}));
        DeleteProjectRiskEvent event = new DeleteProjectRiskEvent(items.toArray(new Risk[items.size()]),
                username, accountId);
        asyncEventBus.post(event);
    }
}
