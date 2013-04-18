package com.esofthead.mycollab.common.service;

import java.util.List;

import com.esofthead.mycollab.common.domain.MonitorItem;
import com.esofthead.mycollab.common.domain.criteria.MonitorSearchCriteria;
import com.esofthead.mycollab.core.persistence.service.ICrudService;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;

public interface MonitorItemService extends ICrudService<Integer, MonitorItem>, ISearchableService<MonitorSearchCriteria>{
	/**
	 * 
	 * @param itemid
	 * @return
	 */
	int countMonitorsOfItem(String type, int typeid);

	/**
	 * 
	 * @param username
	 * @param itemid
	 * @return
	 */
	Boolean isWatchingItem(String username, String type, int typeid);

	/**
	 * 
	 * @param itemid
	 * @return
	 */
	List<MonitorItem> getMonitorItems(String type, int typeid);

	/**
	 * 
	 * @param itemid
	 */
	void deleteWatchingItems(String type, int typeid);

	/**
	 * 
	 * @param username
	 * @param itemid
	 */
	void deleteWatchingItem(String username, String type, int typeid);

	boolean isUserWatchingItem(String username, String type, int typeid);
}
