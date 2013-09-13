package com.esofthead.mycollab.common.service;

import com.esofthead.mycollab.common.domain.CustomViewStore;
import com.esofthead.mycollab.core.cache.CacheEvict;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.persistence.service.ICrudService;

public interface CustomViewStoreService extends
		ICrudService<Integer, CustomViewStore> {
	@Cacheable
	CustomViewStore getViewLayoutDef(@CacheKey int accountId, String username,
			String viewId);

	@CacheEvict
	void saveOrUpdateViewLayoutDef(@CacheKey CustomViewStore viewStore);
}
