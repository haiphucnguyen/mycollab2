package com.esofthead.mycollab.common.service.ibatis;

import java.util.List;

import com.esofthead.mycollab.common.dao.MonitorItemMapper;
import com.esofthead.mycollab.common.dao.MonitorItemMapperExt;
import com.esofthead.mycollab.common.domain.MonitorItem;
import com.esofthead.mycollab.common.domain.MonitorItemExample;
import com.esofthead.mycollab.common.service.MonitorItemService;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;

public class MonitorItemServiceImpl extends
		DefaultCrudService<Integer, MonitorItem> implements MonitorItemService {

	private MonitorItemMapperExt monitorItemExtDAO;

	public void setMonitorItemExtDAO(MonitorItemMapperExt monitorItemExtDAO) {
		this.monitorItemExtDAO = monitorItemExtDAO;
	}

	@Override
	public int countMonitorsOfItem(String itemid) {
		MonitorItemExample ex = new MonitorItemExample();
		ex.createCriteria().andItemidEqualTo(itemid);
		return ((MonitorItemMapper) daoObj).countByExample(ex);
	}

	@Override
	public Boolean isWatchingItem(String username, String itemid) {
		MonitorItemExample ex = new MonitorItemExample();
		ex.createCriteria().andItemidEqualTo(itemid).andUserEqualTo(username);
		return (((MonitorItemMapper) daoObj).countByExample(ex) > 0);
	}

	@Override
	public void deleteWatchingItems(String itemid) {
		MonitorItemExample ex = new MonitorItemExample();
		ex.createCriteria().andItemidEqualTo(itemid);
		((MonitorItemMapper) daoObj).deleteByExample(ex);
	}

	@Override
	public void deleteWatchingItem(String username, String itemid) {
		MonitorItemExample ex = new MonitorItemExample();
		ex.createCriteria().andUserEqualTo(username).andItemidEqualTo(itemid);
		((MonitorItemMapper) daoObj).deleteByExample(ex);
	}

	@Override
	public List<MonitorItem> getMonitorItems(String itemid) {
		MonitorItemExample ex = new MonitorItemExample();
		ex.createCriteria().andItemidEqualTo(itemid);
		return ((MonitorItemMapper) daoObj).selectByExample(ex);
	}
}
