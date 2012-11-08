package com.esofthead.mycollab.common.service;

import java.util.List;
import java.util.Map;

import com.esofthead.mycollab.common.domain.MonitorItem;
import com.esofthead.mycollab.core.persistence.ICrudService;

public interface MonitorItemService extends ICrudService<Integer, MonitorItem> {
	/**
	 * 
	 * @param itemid
	 * @return
	 */
	int countMonitorsOfItem(String itemid);

	/**
	 * 
	 * @param username
	 * @param itemid
	 * @return
	 */
	Boolean isWatchingItem(String username, String itemid);

	/**
	 * 
	 * @param itemid
	 * @return
	 */
	List<MonitorItem> getMonitorItems(String itemid);

	/**
	 * 
	 * @param itemid
	 */
	void deleteWatchingItems(String itemid);

	/**
	 * 
	 * @param username
	 * @param itemid
	 */
	void deleteWatchingItem(String username, String itemid);
}
