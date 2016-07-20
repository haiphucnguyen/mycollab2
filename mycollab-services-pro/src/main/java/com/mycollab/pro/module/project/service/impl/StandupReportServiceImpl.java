package com.mycollab.pro.module.project.service.impl;

import com.mycollab.cache.CleanCacheEvent;
import com.mycollab.common.ModuleNameConstants;
import com.mycollab.common.interceptor.aspect.ClassInfo;
import com.mycollab.common.interceptor.aspect.ClassInfoMap;
import com.mycollab.common.interceptor.aspect.Traceable;
import com.mycollab.db.arguments.DateSearchField;
import com.mycollab.db.arguments.SearchRequest;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.core.cache.CacheKey;
import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.db.persistence.service.DefaultService;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.SimpleStandupReport;
import com.mycollab.module.project.domain.StandupReportStatistic;
import com.mycollab.module.project.domain.StandupReportWithBLOBs;
import com.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;
import com.mycollab.module.project.service.ProjectActivityStreamService;
import com.mycollab.module.project.service.StandupReportService;
import com.mycollab.module.user.domain.SimpleUser;
import com.mycollab.pro.module.project.dao.StandupReportMapper;
import com.mycollab.pro.module.project.dao.StandupReportMapperExt;
import com.google.common.eventbus.AsyncEventBus;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.RowBounds;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Service
@Transactional
@Traceable(nameField = "forday", extraFieldName = "projectid")
public class StandupReportServiceImpl extends DefaultService<Integer, StandupReportWithBLOBs, StandupReportSearchCriteria>
        implements StandupReportService {
    static {
        ClassInfoMap.put(StandupReportServiceImpl.class, new ClassInfo(ModuleNameConstants.PRJ, ProjectTypeConstants.STANDUP));
    }

    @Autowired
    private StandupReportMapper standupReportMapper;

    @Autowired
    private StandupReportMapperExt standupReportMapperExt;

    @Autowired
    private AsyncEventBus asyncEventBus;

    @Override
    public SimpleStandupReport findById(Integer standupId, Integer sAccountId) {
        return standupReportMapperExt.findReportById(standupId);
    }

    @Override
    public ICrudGenericDAO getCrudMapper() {
        return standupReportMapper;
    }

    @Override
    public ISearchableDAO<StandupReportSearchCriteria> getSearchMapper() {
        return standupReportMapperExt;
    }

    @Override
    public SimpleStandupReport findStandupReportByDateUser(Integer projectId, String username, Date onDate, Integer sAccountId) {
        StandupReportSearchCriteria criteria = new StandupReportSearchCriteria();
        criteria.setProjectIds(new SetSearchField<>(projectId));
        criteria.setLogBy(StringSearchField.and(username));
        criteria.setOnDate(new DateSearchField(onDate));
        List reports = standupReportMapperExt.findPageableListByCriteria(criteria, new RowBounds(0, Integer.MAX_VALUE));

        if (CollectionUtils.isNotEmpty(reports)) {
            return (SimpleStandupReport) reports.get(0);
        }

        return null;
    }

    @Override
    public Integer saveWithSession(StandupReportWithBLOBs record, String username) {
        int result = super.saveWithSession(record, username);
        asyncEventBus.post(new CleanCacheEvent(record.getSaccountid(), new Class[]{ProjectActivityStreamService.class}));
        return result;
    }

    @Override
    public Integer updateWithSession(StandupReportWithBLOBs record, String username) {
        asyncEventBus.post(new CleanCacheEvent(record.getSaccountid(), new Class[]{ProjectActivityStreamService.class}));
        return super.updateWithSession(record, username);
    }

    @Override
    public void massRemoveWithSession(List<StandupReportWithBLOBs> reports, String username, Integer accountId) {
        super.massRemoveWithSession(reports, username, accountId);
        asyncEventBus.post(new CleanCacheEvent(accountId, new Class[]{ProjectActivityStreamService.class}));
    }

    @Override
    public List<SimpleUser> findUsersNotDoReportYet(Integer projectId, Date onDate, @CacheKey Integer sAccountId) {
        LocalDate dateOnly = new LocalDate(onDate);
        return standupReportMapperExt.findUsersNotDoReportYet(projectId, dateOnly.toDate());
    }

    @Override
    public List<StandupReportStatistic> getProjectReportsStatistic(List<Integer> projectIds, Date onDate, SearchRequest searchRequest) {
        LocalDate dateOnly = new LocalDate(onDate);
        return standupReportMapperExt.getProjectReportsStatistic(projectIds, dateOnly.toDate(), new RowBounds((searchRequest.getCurrentPage() - 1) * searchRequest.getNumberOfItems(),
                searchRequest.getNumberOfItems()));
    }
}