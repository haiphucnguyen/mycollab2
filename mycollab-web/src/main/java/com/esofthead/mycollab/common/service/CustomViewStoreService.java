package com.esofthead.mycollab.common.service;

import com.esofthead.mycollab.common.domain.CustomViewStore;
import com.esofthead.mycollab.core.persistence.service.ICrudService;

public interface CustomViewStoreService extends
		ICrudService<Integer, CustomViewStore> {
	CustomViewStore getViewLayoutDef(int accountId, String username,
			String viewId);

	void saveOrUpdateViewLayoutDef(CustomViewStore viewStore);
}
