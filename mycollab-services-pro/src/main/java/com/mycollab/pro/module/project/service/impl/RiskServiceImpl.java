package com.mycollab.pro.module.project.service.impl;

import com.google.common.eventbus.AsyncEventBus;
import com.mycollab.aspect.ClassInfo;
import com.mycollab.aspect.ClassInfoMap;
import com.mycollab.aspect.Traceable;
import com.mycollab.cache.CleanCacheEvent;
import com.mycollab.common.ModuleNameConstants;
import com.mycollab.common.event.TimelineTrackingUpdateEvent;
import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.db.persistence.service.DefaultService;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.Risk;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.mycollab.module.project.esb.DeleteProjectRiskEvent;
import com.mycollab.module.project.service.*;
import com.mycollab.pro.module.project.dao.RiskMapper;
import com.mycollab.pro.module.project.dao.RiskMapperExt;
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
@Traceable(nameField = "name", extraFieldName = "projectid")
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
                ProjectTicketService.class, ProjectActivityStreamService.class}));
        return recordId;
    }

    @Override
    public Integer updateSelectiveWithSession(Risk record, String username) {
        cleanAfterUpdate(record);
        return super.updateSelectiveWithSession(record, username);
    }

    @Override
    public Integer updateWithSession(Risk record, String username) {
        cleanAfterUpdate(record);
        return super.updateWithSession(record, username);
    }

    private void cleanAfterUpdate(Risk record) {
        asyncEventBus.post(new CleanCacheEvent(record.getSaccountid(), new Class[]{ProjectService.class,
                ProjectTicketService.class, ProjectActivityStreamService.class, ProjectTicketService.class}));
        asyncEventBus.post(new TimelineTrackingUpdateEvent(ProjectTypeConstants.TICKET, record.getId(), "status",
                record.getStatus(), record.getProjectid(), record.getSaccountid()));
    }

    @Override
    public void removeByCriteria(RiskSearchCriteria criteria, Integer accountId) {
        super.removeByCriteria(criteria, accountId);
        asyncEventBus.post(new CleanCacheEvent(accountId, new Class[]{ProjectService.class,
                ProjectTicketService.class, ProjectActivityStreamService.class, ItemTimeLoggingService.class}));
    }

    @Override
    public void massRemoveWithSession(List<Risk> items, String username, Integer accountId) {
        super.massRemoveWithSession(items, username, accountId);
        asyncEventBus.post(new CleanCacheEvent(accountId, new Class[]{ProjectService.class,
                ProjectTicketService.class, ProjectActivityStreamService.class, ItemTimeLoggingService.class}));
        DeleteProjectRiskEvent event = new DeleteProjectRiskEvent(items.toArray(new Risk[items.size()]),
                username, accountId);
        asyncEventBus.post(event);
    }
}
