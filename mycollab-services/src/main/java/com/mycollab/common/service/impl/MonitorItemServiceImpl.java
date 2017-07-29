package com.mycollab.common.service.impl;

import com.mycollab.common.dao.MonitorItemMapper;
import com.mycollab.common.dao.MonitorItemMapperExt;
import com.mycollab.common.domain.MonitorItem;
import com.mycollab.common.domain.MonitorItemExample;
import com.mycollab.common.domain.criteria.MonitorSearchCriteria;
import com.mycollab.common.service.MonitorItemService;
import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.db.persistence.service.DefaultService;
import com.mycollab.module.user.domain.SimpleUser;
import com.google.common.eventbus.AsyncEventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Service
public class MonitorItemServiceImpl extends DefaultService<Integer, MonitorItem, MonitorSearchCriteria> implements MonitorItemService {

    @Autowired
    private MonitorItemMapper monitorItemMapper;

    @Autowired
    private MonitorItemMapperExt monitorItemMapperExt;

    @Autowired
    private AsyncEventBus asyncEventBus;

    @Override
    public ICrudGenericDAO<Integer, MonitorItem> getCrudMapper() {
        return monitorItemMapper;
    }

    @Override
    public ISearchableDAO<MonitorSearchCriteria> getSearchMapper() {
        return monitorItemMapperExt;
    }

    @Override
    public boolean isUserWatchingItem(String username, String type, Integer typeId) {
        MonitorItemExample ex = new MonitorItemExample();
        ex.createCriteria().andUserEqualTo(username).andTypeEqualTo(type).andTypeidEqualTo(typeId);
        return monitorItemMapper.countByExample(ex) > 0;
    }

    @Override
    public Integer saveWithSession(MonitorItem record, String username) {
        MonitorItemExample ex = new MonitorItemExample();
        ex.createCriteria().andTypeEqualTo(record.getType()).andTypeidEqualTo(record.getTypeid()).andUserEqualTo(record.getUser());
        Long count = monitorItemMapper.countByExample(ex);
        if (count > 0) {
            return 1;
        } else {
            return super.saveWithSession(record, username);
        }
    }

    @Override
    public void saveMonitorItems(Collection<MonitorItem> monitorItems) {
        if (monitorItems.size() > 0) {
            monitorItemMapperExt.saveMonitorItems(monitorItems);
        }
    }

    @Override
    public List<SimpleUser> getWatchers(String type, Integer typeId) {
        return monitorItemMapperExt.getWatchers(type, typeId);
    }

    @Override
    public Integer getNextItemKey(MonitorSearchCriteria arg0) {
        return null;
    }

    @Override
    public Integer getPreviousItemKey(MonitorSearchCriteria arg0) {
        return null;
    }
}
