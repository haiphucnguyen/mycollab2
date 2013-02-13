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
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;

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
	public int countMonitorsOfItem(String type, int typeid) {
		MonitorItemExample ex = new MonitorItemExample();
		ex.createCriteria().andTypeEqualTo(type).andTypeidEqualTo(typeid);
		return monitorItemMapper.countByExample(ex);
	}

	@Override
	public Boolean isWatchingItem(String username, String type, int typeid) {
		MonitorItemExample ex = new MonitorItemExample();
		ex.createCriteria().andTypeEqualTo(type).andTypeidEqualTo(typeid).andUserEqualTo(username);
		return (monitorItemMapper.countByExample(ex) != 0);
	}

	@Override
	public List<MonitorItem> getMonitorItems(String type, int typeid) {
		MonitorItemExample ex = new MonitorItemExample();
		ex.createCriteria().andTypeEqualTo(type).andTypeidEqualTo(typeid);
		return monitorItemMapper.selectByExample(ex);
	}

	@Override
	public void deleteWatchingItems(String type, int typeid) {
		MonitorItemExample ex = new MonitorItemExample();
		ex.createCriteria().andTypeEqualTo(type).andTypeidEqualTo(typeid);
		monitorItemMapper.deleteByExample(ex);
	}

	@Override
	public void deleteWatchingItem(String username, String type, int typeid) {
		MonitorItemExample ex = new MonitorItemExample();
		ex.createCriteria().andTypeEqualTo(type).andTypeidEqualTo(typeid).andUserEqualTo(username);
		monitorItemMapper.deleteByExample(ex);
	}
}
