package com.esofthead.mycollab.common.service.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.dao.CustomViewStoreMapper;
import com.esofthead.mycollab.common.domain.CustomViewStore;
import com.esofthead.mycollab.common.domain.CustomViewStoreExample;
import com.esofthead.mycollab.common.domain.NullCustomViewStore;
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
	public CustomViewStore getViewLayoutDef(int accountId, String username,
			String viewId) {
		CustomViewStoreExample ex = new CustomViewStoreExample();
		ex.createCriteria().andCreateduserEqualTo(username)
				.andViewidEqualTo(viewId).andSaccountidEqualTo(accountId);
		List<CustomViewStore> views = customViewStoreMapper
				.selectByExampleWithBLOBs(ex);
		if (views != null && views.size() > 0) {
			return views.get(0);
		}
		return new NullCustomViewStore();
	}

	@Override
	public void saveOrUpdateViewLayoutDef(CustomViewStore viewStore) {
		CustomViewStore viewLayoutDef = getViewLayoutDef(
				viewStore.getSaccountid(), viewStore.getCreateduser(),
				viewStore.getViewid());
		if (!(viewLayoutDef instanceof NullCustomViewStore)) {
			viewStore.setId(viewLayoutDef.getId());
			updateWithSession(viewStore, viewStore.getCreateduser());
		} else {
			saveWithSession(viewStore, viewStore.getCreateduser());
		}
	}

}
