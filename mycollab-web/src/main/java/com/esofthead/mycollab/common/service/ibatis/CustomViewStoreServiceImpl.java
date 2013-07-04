package com.esofthead.mycollab.common.service.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.dao.CustomViewStoreMapper;
import com.esofthead.mycollab.common.domain.CustomViewStore;
import com.esofthead.mycollab.common.domain.CustomViewStoreExample;
import com.esofthead.mycollab.common.service.CustomViewStoreService;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;

@Service
public class CustomViewStoreServiceImpl extends
		DefaultCrudService<Integer, CustomViewStore> implements
		CustomViewStoreService {
	@Autowired
	private CustomViewStoreMapper customViewStoreMapper;

	@Override
	public ICrudGenericDAO<Integer, CustomViewStore> getCrudMapper() {
		return customViewStoreMapper;
	}

	@Override
	public CustomViewStore getViewLayoutDef(String username, String viewId) {
		CustomViewStoreExample ex = new CustomViewStoreExample();
		ex.createCriteria().andCreateduserEqualTo(username)
				.andViewidEqualTo(viewId);
		List<CustomViewStore> views = customViewStoreMapper.selectByExample(ex);
		if (views != null && views.size() > 0) {
			return views.get(0);
		}
		return null;
	}

	@Override
	public void saveOrUpdateViewLayoutDef(CustomViewStore viewStore) {
		CustomViewStore viewLayoutDef = getViewLayoutDef(
				viewStore.getCreateduser(), viewStore.getViewid());
		if (viewLayoutDef != null) {
			viewStore.setId(viewLayoutDef.getId());
			updateWithSession(viewStore, viewStore.getCreateduser());
		} else {
			saveWithSession(viewStore, viewStore.getCreateduser());
		}
	}

}
