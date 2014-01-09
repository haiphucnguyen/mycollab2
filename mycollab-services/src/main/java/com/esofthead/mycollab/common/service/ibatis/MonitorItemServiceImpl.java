/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.common.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.dao.MonitorItemMapper;
import com.esofthead.mycollab.common.dao.MonitorItemMapperExt;
import com.esofthead.mycollab.common.domain.MonitorItem;
import com.esofthead.mycollab.common.domain.MonitorItemExample;
import com.esofthead.mycollab.common.domain.criteria.MonitorSearchCriteria;
import com.esofthead.mycollab.common.service.MonitorItemService;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@Service
public class MonitorItemServiceImpl extends
		DefaultService<Integer, MonitorItem, MonitorSearchCriteria> implements
		MonitorItemService {

	@Autowired
	private MonitorItemMapper monitorItemMapper;

	@Autowired
	private MonitorItemMapperExt monitorItemMapperExt;

	@Override
	public ICrudGenericDAO<Integer, MonitorItem> getCrudMapper() {
		return monitorItemMapper;
	}

	@Override
	public ISearchableDAO<MonitorSearchCriteria> getSearchMapper() {
		return monitorItemMapperExt;
	}

	@Override
	public boolean isUserWatchingItem(String username, String type, int typeid) {
		MonitorItemExample ex = new MonitorItemExample();
		ex.createCriteria().andUserEqualTo(username).andTypeEqualTo(type)
				.andTypeidEqualTo(typeid);
		return monitorItemMapper.countByExample(ex) > 0;
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
