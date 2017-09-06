package com.mycollab.common.service;

import com.mycollab.cache.IgnoreCacheClass;
import com.mycollab.common.domain.MonitorItem;
import com.mycollab.common.domain.criteria.MonitorSearchCriteria;
import com.mycollab.db.persistence.service.ICrudService;
import com.mycollab.db.persistence.service.ISearchableService;
import com.mycollab.module.user.domain.SimpleUser;

import java.util.Collection;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@IgnoreCacheClass
public interface MonitorItemService extends ICrudService<Integer, MonitorItem>, ISearchableService<MonitorSearchCriteria> {

    boolean isUserWatchingItem(String username, String type, Integer typeId);

    List<SimpleUser> getWatchers(String type, Integer typeId);

    void saveMonitorItems(Collection<MonitorItem> monitorItems);
}
