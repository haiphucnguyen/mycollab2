package com.esofthead.mycollab.common.service.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.dao.MonitorItemMapper;
import com.esofthead.mycollab.common.dao.MonitorItemMapperExt;
import com.esofthead.mycollab.common.domain.MonitorItem;
import com.esofthead.mycollab.common.domain.MonitorItemExample;
import com.esofthead.mycollab.common.service.MonitorItemService;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;

@Service
public class MonitorItemServiceImpl extends
		DefaultCrudService<Integer, MonitorItem> implements MonitorItemService {

	@Autowired
	private MonitorItemMapper monitorItemMapper;
	
	@Autowired
	private MonitorItemMapperExt monitorItemMapperExt;
	
	@Override
	public ICrudGenericDAO<Integer, MonitorItem> getCrudMapper() {
		return monitorItemMapper;
	}

	@Override
	public int countMonitorsOfItem(String itemid) {
		MonitorItemExample ex = new MonitorItemExample();
		ex.createCriteria().andItemidEqualTo(itemid);
		return monitorItemMapper.countByExample(ex);
	}

	@Override
	public Boolean isWatchingItem(String username, String itemid) {
		MonitorItemExample ex = new MonitorItemExample();
		ex.createCriteria().andItemidEqualTo(itemid).andUserEqualTo(username);
		return (monitorItemMapper.countByExample(ex) > 0);
	}

	@Override
	public void deleteWatchingItems(String itemid) {
		MonitorItemExample ex = new MonitorItemExample();
		ex.createCriteria().andItemidEqualTo(itemid);
		monitorItemMapper.deleteByExample(ex);
	}

	@Override
	public void deleteWatchingItem(String username, String itemid) {
		MonitorItemExample ex = new MonitorItemExample();
		ex.createCriteria().andUserEqualTo(username).andItemidEqualTo(itemid);
		monitorItemMapper.deleteByExample(ex);
	}

	@Override
	public List<MonitorItem> getMonitorItems(String itemid) {
		MonitorItemExample ex = new MonitorItemExample();
		ex.createCriteria().andItemidEqualTo(itemid);
		return monitorItemMapper.selectByExample(ex);
	}
}
