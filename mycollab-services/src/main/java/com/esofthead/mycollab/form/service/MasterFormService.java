package com.esofthead.mycollab.form.service;

import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;

public interface MasterFormService {
	@Cacheable
	DynaForm findCustomForm(@CacheKey Integer sAccountId, String moduleName);
}
